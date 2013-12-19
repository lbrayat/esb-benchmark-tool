/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package launcherproject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.namespace.QName;

/**
 *
 * @author lbrayat
 */
public class ProviderClient extends ESBWSClient {

    private static final Logger logger = Logger.getLogger("Launcher");
    
    private class ProviderPhase {

        public int mIdPhase;
        public int mTreatmentTime;
        public long mResponseSize;
        public int mLostPercent;
        public long mEndTimeStamp;

        public ProviderPhase(int mIdPhase, int mTreatmentTime, long mResponseSize, int mLostPercent, long mEndTimeStamp) {
            this.mIdPhase = mIdPhase;
            this.mTreatmentTime = mTreatmentTime;
            this.mResponseSize = mResponseSize;
            this.mLostPercent = mLostPercent;
            this.mEndTimeStamp = mEndTimeStamp;
        }

    }

    private static QName mQName = new QName("http://providerPckg/", "ProviderWSService");

    private QName getQName() {
        return mQName;
    };

    protected URL getURL() {

        URL url = null;
        URL baseUrl;
        baseUrl = providerpckg.ProviderWSService.class.getResource(".");
        try {
            url = new URL(baseUrl, mWSDL);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    private int mIdConsumerWS;
    private List<ProviderPhase> mPhaseList;

    public ProviderClient(int id, String wsdl) {
        mIdConsumerWS = id;
        mWSDL = wsdl;
        mPhaseList = new ArrayList<ProviderPhase>();
    }

    protected int callEndConf() {

        logger.log(Level.INFO, "callEndConf : begin");

        try { // Call Web Service Operation
            providerpckg.ProviderWSService service = new providerpckg.ProviderWSService(getURL(), getQName());
            providerpckg.ProviderWS port = service.getProviderWSPort();
            // TODO process result here
            int result = port.endConf();
            System.out.println("Result = "+result);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        logger.log(Level.INFO, "callEndConf : complete");

        return 0;
    }

    /**
     * Useless : autostart after endConf
     * @return
     */
    protected int callStart() {
        return 0;
    }

    protected int callStartConf() {

        logger.log(Level.INFO, "callStartConf : begin");

        try { // Call Web Service Operation
            providerpckg.ProviderWSService service = new providerpckg.ProviderWSService(getURL(), getQName());
            providerpckg.ProviderWS port = service.getProviderWSPort();
            port.startConf();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        logger.log(Level.INFO, "callStartConf : complete");

        return 0;
    }

    private void callAddPhase(ProviderPhase aPhase) {

        logger.log(Level.INFO, "callAddPhase : begin");


        try { // Call Web Service Operation
            providerpckg.ProviderWSService service = new providerpckg.ProviderWSService();
            providerpckg.ProviderWS port = service.getProviderWSPort();
            // TODO initialize WS operation arguments here
            int phaseNumber = aPhase.mIdPhase;
            long dateFin = aPhase.mEndTimeStamp;
            long tempsTraitement = aPhase.mTreatmentTime;
            long tailleReponse = aPhase.mResponseSize;
            int packetLoss = aPhase.mLostPercent;
            // TODO process result here
            int result = port.addPhase(phaseNumber, dateFin, tempsTraitement, tailleReponse, packetLoss);
            System.out.println("Result = "+result);
        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }


        logger.log(Level.INFO, "callAddPhase : complete");
    }

    public int start() {
        return callStart();
    }

        public void addPhase(
            int mIdPhase,
            int mTreatmentTime,
            long mResponseSize,
            int mLostPercent,
            long mEndTimeStamp) {

        mPhaseList.add(
                new ProviderPhase(
                    mIdPhase,
                    mTreatmentTime,
                    mResponseSize,
                    mLostPercent,
                    mEndTimeStamp));
    }
        
    public boolean configure() {

        callStartConf();

        for(ProviderPhase iPhase : mPhaseList) {
            callAddPhase(iPhase);
        }

        callEndConf();

        return true;
    }
}
