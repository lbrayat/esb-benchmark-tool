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
import launcherproject.xml.PhaseProvider;
import providerpckg.ProviderWSService;

/**
 *
 * @author lbrayat
 */
public class ProviderClient extends ESBWSClient {

    private static final Logger logger = Logger.getLogger("Launcher");
    
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
    private List<PhaseProvider> mPhaseList;

    public ProviderClient(int id, String wsdl) {
        mIdConsumerWS = id;
        mWSDL = wsdl;
        mPhaseList = new ArrayList<PhaseProvider>();
    }

    protected int callEndConf() {

        logger.log(Level.INFO, "callEndConf : begin");

        try { // Call Web Service Operation
            ProviderWSService service = new ProviderWSService(getURL(), getQName());
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

    private void callAddPhase(PhaseProvider aPhase) {

        logger.log(Level.INFO, "callAddPhase : begin");


        try { // Call Web Service Operation
            providerpckg.ProviderWSService service = new providerpckg.ProviderWSService();
            providerpckg.ProviderWS port = service.getProviderWSPort();
            // TODO initialize WS operation arguments here
            int phaseNumber = aPhase.getConfId();
            long tempsTraitement = aPhase.getProcessingTime();
            long tailleReponse = aPhase.getPacketSize();
            int packetLoss = aPhase.getPercentageLoss();
            logger.log(Level.INFO, "callAddPhase parameters :" +
                    "\n   phaseNumber:"+phaseNumber+
                    "\n   tempsTraitement:"+tempsTraitement+
                    "\n   tailleReponse:"+tailleReponse+
                    "\n   packetLoss:"+packetLoss);
            // TODO process result here
            int result = port.addConf(phaseNumber, tempsTraitement, tailleReponse, packetLoss);
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
            int mResponseSize,
            int mLostPercent) {

        mPhaseList.add(
                new PhaseProvider(
                    mIdPhase,
                    mTreatmentTime,
                    mResponseSize,
                    mLostPercent
                    ));
    }
        
    public boolean configure() {

        callStartConf();

        for(PhaseProvider iPhase : mPhaseList) {
            callAddPhase(iPhase);
        }

        callEndConf();

        return true;
    }
}
