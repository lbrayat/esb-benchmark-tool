/*
 * To change this template, choose Tools | Templates 
 * and open the template in the editor.
 */

package launcherproject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import launcherproject.xml.PhaseConsumer;

/**
 *
 * @author lbrayat
 */
public class ConsumerClient extends ESBWSClient {

    private static final Logger logger = Logger.getLogger("Launcher");

        protected URL getURL() {

        URL url = null;
        URL baseUrl; 
        baseUrl = beta.ConsumerWebServiceService.class.getResource(".");
        try {
            url = new URL(baseUrl, mWSDL);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }
        

    private int mIdConsumerWS;
    private List<PhaseConsumer> mPhaseList;

    private static QName mQName = new QName("http://beta/", "ConsumerWebServiceService");

    private QName getQName() {
        return mQName;
    };

    public ConsumerClient(int id, String wsdl) {
        mIdConsumerWS = id;
        mWSDL = wsdl;
        mPhaseList = new ArrayList<PhaseConsumer>();
    }

    public void addPhase(
        String mIdProviderWS,
        int mId,
        int mTrafficProfile,
        int mTargetedProviderConf,
        int mNbRequests,
        int mPeriod,
        int mSize,
        long mStartTimeStamp,
        int mNbRepeat) {

        mPhaseList.add(
                new PhaseConsumer(
                    mIdProviderWS,
                    mTrafficProfile,
                    mNbRequests,
                    mPeriod,
                    mSize,
                    mStartTimeStamp,
                    mNbRepeat,
                    mTargetedProviderConf,
                    mId));
    }

    private void callAddPhase(PhaseConsumer aPhase) {

        logger.log(Level.INFO, "callAddPhase : begin");

        try { // Call Web Service Operation
            beta.ConsumerWebServiceService service = new beta.ConsumerWebServiceService(getURL(), getQName());
            beta.ConsumerWebService port = service.getConsumerWebServicePort();
            System.err.println(aPhase.getProviderId());

            Date date = new Date();
            long time = date.getTime();
            time = time + 1000 * aPhase.getStartDate();

            port.configurePhase(
                    aPhase.getProviderId(),
                    aPhase.getId(),
                    aPhase.getTargetedProviderConf(),
                    aPhase.getNumberOfRequests(),
                    aPhase.getSendPeriod(),
                    aPhase.getPacketSize(),
                    aPhase.getNumberOfBursts(),
                    time
                    );
        } catch (Exception ex) {
            logger.log(Level.INFO, "callAddPhase : " + ex.getMessage());
        }

        logger.log(Level.INFO, "callAddPhase : complete");
    }

    protected int callEndConf() {

        logger.log(Level.INFO, "callEndConf : begin");

        try { // Call Web Service Operation
            beta.ConsumerWebServiceService service = new beta.ConsumerWebServiceService(getURL(), getQName());
            beta.ConsumerWebService port = service.getConsumerWebServicePort();
            port.endConfiguration();
        } catch (Exception ex) {
            logger.log(Level.INFO, "callEndConf : " + ex.getMessage());
        }

        logger.log(Level.INFO, "callEndConf : complete");

        return 0;
    }

    public int callStart() {

        logger.log(Level.INFO, "callStart : begin");

        try { // Call Web Service Operation
            beta.ConsumerWebServiceService service = new beta.ConsumerWebServiceService(getURL(), getQName());
            beta.ConsumerWebService port = service.getConsumerWebServicePort();
            port.startScenario();
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "callStart : " + ex.getMessage());
        }

        logger.log(Level.INFO, "callStart : complete");

        return 0;
    }

    protected int callStartConf() {

        logger.log(Level.INFO, "callStartConf : begin");

        try { // Call Web Service Operation
            beta.ConsumerWebServiceService service = new beta.ConsumerWebServiceService(getURL(), getQName());
            beta.ConsumerWebService port = service.getConsumerWebServicePort();
            port.startConfiguration();
        } catch (Exception ex) {
            logger.log(Level.INFO, "callStartConf : " + ex.getMessage());
        }

        logger.log(Level.INFO, "callStartConf : complete");

        return 0;
    }

    public int start() {
        return callStart();
    }

    public boolean configure() {

        callStartConf();

        for(PhaseConsumer iPhase : mPhaseList) {
            callAddPhase(iPhase);
        }

        callEndConf();

        return true;
    }
}
