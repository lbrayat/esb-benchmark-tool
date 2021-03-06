package multithreading;

import pojos.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.xml.namespace.QName;
import providerpckg.ProviderWSService;
import beta.ConsumerWebService;

/**
 *
 * @author marc
 */
public class PhaseLauncher implements Runnable{

    Phase thePhase;
    ProviderWSService service;

    public PhaseLauncher(Phase thePhase) {
        this.thePhase = thePhase;
        service = new ProviderWSService(getURL(), getQName());
    }

    public void run() {
        launchPhase();
    }


     private void launchPhase(){

         // wait for the start date
            ConsumerWebService.log.debug("Waiting for the beginning of the phase at time "+
                    thePhase.getStartDate().get(Calendar.HOUR_OF_DAY)+"h "
                    +thePhase.getStartDate().get(Calendar.MINUTE)+"m "
                    +thePhase.getStartDate().get(Calendar.SECOND)+"s ");
            while(new GregorianCalendar().before(thePhase.getStartDate())){
                try {
                    Thread.sleep(500);
                    ConsumerWebService.log.debug("just waited .5sec");
                } catch (InterruptedException ex) {
                    ConsumerWebService.log.fatal(ex);
                }
            }

        ConsumerWebService.log.debug(thePhase + " is launched");
            // launch the phase
            for (int i =0; i<thePhase.getRepetitions(); i++){
                for (int j =0; j<thePhase.getNumberOfRequests(); j++){
                    // send messages
                    sendRequest();
                }
            try {

                if (i!=thePhase.getRepetitions()-1){
                    // wait for next burst
                    ConsumerWebService.log.debug("Waiting for the next burst");
                }
                else {
                    // wait for next phase
                    ConsumerWebService.log.debug("Waiting for next phase");

                }
                // TODO improve this sleep
                Thread.sleep(thePhase.getSendingPeriod() * 1000);

            } catch (InterruptedException ex) {
                java.util.logging.Logger.getLogger(ConsumerWebService.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }

            }

    }


     private void sendRequest(){

        try { // Call Web Service Operation
            
            providerpckg.ProviderWS port = service.getProviderWSPort();
            // TODO initialize WS operation arguments here
            String messageId = "ConsumerID:"+ConsumerWebService.CONSUMER_ID + "-MessageID:" +ConsumerWebService.messageNumber.get();
            ConsumerWebService.messageNumber.incrementAndGet();
            // TODO process result here
            ConsumerWebService.log.debug("Request message id "+messageId+" about to be sent");

            // send operation in a different thread
            Thread messageThread = new Thread(new MessageSender(port, ConsumerWebService.CONSUMER_ID, thePhase.getPayloadSize(), thePhase.getTargetConfig(), messageId));
            messageThread.start();
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    /**
     * Notify the launcher that we are done
     */
    private void onFinish(){

    }

     private URL getURL() {

        URL url = null;
        try {
            URL baseUrl;
            baseUrl = providerpckg.ProviderWSService.class.getResource(".");
            url = new URL(baseUrl, this.thePhase.getRecepientAddress());
        } catch (MalformedURLException e) {
            ConsumerWebService.log.fatal("Failed to create URL for the wsdl Location: 'http://192.168.1.95:8080/ProviderProject/ProviderWSService?WSDL', retrying as a local file");
            ConsumerWebService.log.fatal(e.getMessage());
        }

        return url;
    }

    private QName getQName() {
        return new QName("http://providerPckg/", "ProviderWSService");
    }

}
