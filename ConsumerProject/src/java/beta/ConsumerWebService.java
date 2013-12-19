/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package beta;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.namespace.QName;
import javax.xml.ws.WebServiceRef;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import pojos.Phase;
import providerpckg.ProviderWSService;

/**
 *
 * @author marc
 */
@WebService()
public class ConsumerWebService {

    public static final int CONSUMER_ID = 1;
    private static final String PAYLOAD = "ta soeur";
    private int messageNumber;

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/192.168.1.95_8080/ProviderProject/ProviderWSService.wsdl")
    private ProviderWSService service;// = new ProviderWSService(getURL(), getQName());


     //logging in different levels
    //log.trace("This is a Trace");
     //log.debug("This is a Debug");
     //log.info("This is an Info");
     //log.warn("This is a Warn");
     //log.error("This is an Error");
     //log.fatal("This is a Fatal");


    private List<Phase> phasesList;
    private Phase currentPhase;
    static Logger log = Logger.getLogger(ConsumerWebService.class.getName());

    public ConsumerWebService() {
        log.setLevel(Level.DEBUG);
        this.phasesList = new ArrayList<Phase>();

        //Phase de test
//        Calendar startDate = new GregorianCalendar();
   //     startDate.add(Calendar.SECOND, 35);
     //   Phase testPhase = new Phase("http://192.168.1.95:8080/ProviderProject/ProviderWSService?wsdl", 5, 3, 10, 3, startDate);
       // phasesList.add(testPhase);
        
        log.trace("The consumer is deployed");
    } 


    /**
     * Web service operation
     */
    @WebMethod(operationName = "ping")
    public int ping() {
        log.debug("Someone pinged this WS");
        return 1;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "startConfiguration")
    public void startConfiguration() {
        log.debug("Asked for a new configuration, erasing previous configurations");
        //we start a new configuration by voiding previous list
        phasesList=new ArrayList<Phase>();
        log.debug("Previous configurations deleted");
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "configurePhase")
    public void configurePhase(@WebParam(name = "recepientAddress")
    String recepientAddress,@WebParam(name = "phaseNumber")
    int phaseNumber, @WebParam(name = "NumberOfrequests")
    int NumberOfrequests, @WebParam(name = "sendingPeriod")
    int sendingPeriod, @WebParam(name = "payloadSize")
    int payloadSize, @WebParam(name = "repetitions")
    int repetitions, @WebParam(name = "startDate")
    long startDate) {
        //TODO verify if two phases do not overlap

        Calendar startDateInCal = new GregorianCalendar();
        log.debug("timestamp long : "+startDate);
        startDateInCal.setTimeInMillis(startDate);
 
        Phase newPhase = new Phase(recepientAddress, NumberOfrequests,
                sendingPeriod, payloadSize, repetitions, startDateInCal);

        phasesList.add(newPhase);
        log.debug(newPhase.toString());
    }


    @WebMethod(operationName = "endConfiguration")
    public void endConfiguration() {

    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "startScenario")
    @Oneway
    public void startScenario() {
        // look for the first phase in time
        if (phasesList.isEmpty()){
            log.error("The phases list is empty !! You must configure a scenario before starting it");
        }
        else {
            Phase earlierPhase = phasesList.get(0);
            for (Phase p : phasesList){
                if(p.getStartDate().before(earlierPhase)){
                    earlierPhase=p;
                }
            }

            currentPhase = earlierPhase;
            log.error("The current Phase is "+currentPhase);

            // wait for the start date
            log.debug("Waiting for the beginning of the scenario at time "+currentPhase.getStartDate().toString());
            while(new GregorianCalendar().before(currentPhase.getStartDate())){
                try {
                    Thread.sleep(500); 
                    log.debug("woke up");
                } catch (InterruptedException ex) {
                    log.fatal(ex);
                }
            }

            for (int i=0; i<phasesList.size(); i++){

                launchPhase(currentPhase);

                if (i!=phasesList.size()-1){
                    currentPhase = getNextPhase();
                }
            }
        }
       
    }

    private void launchPhase(Phase thaPhase){
        log.debug("Phase " + thaPhase + " is launched");
        // launch the phase
            for (int i =0; i<thaPhase.getRepetitions(); i++){
                for (int j =0; j<thaPhase.getNumberOfRequests(); j++){
                    // send messages
                    sendRequest();
                }
            try {
                // wait for next burst
                log.debug("Waiting for the next burst");
                Thread.sleep(thaPhase.getSendingPeriod() * 1000);
            } catch (InterruptedException ex) {
                java.util.logging.Logger.getLogger(ConsumerWebService.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }

            }

    }

    /**
     * This function is used to get the next phase after the one set as the current one
     * @return Phase 
     */
    private Phase getNextPhase(){
        if (phasesList.isEmpty()){
            log.error("The phases list is empty !! You must configure a scenario before starting it");
            return null;
        }
        else {
            Phase nextPhase = phasesList.get(0);
            for (Phase p : phasesList){
                if(p.getStartDate().before(nextPhase) && 
                        p.getStartDate().after(new GregorianCalendar()))
                {
                    nextPhase=p;
                    log.debug("Current phase is "+ this.currentPhase);
                    log.debug("Next phase is " + nextPhase);
                }
            }

            return nextPhase;
        }
    }

    private URL getURL() {

        URL url = null;
        try {
            URL baseUrl;
            baseUrl = providerpckg.ProviderWSService.class.getResource(".");
            url = new URL(baseUrl, this.currentPhase.getRecepientAddress());
        } catch (MalformedURLException e) {
            log.fatal("Failed to create URL for the wsdl Location: 'http://192.168.1.95:8080/ProviderProject/ProviderWSService?WSDL', retrying as a local file");
            log.fatal(e.getMessage());
        }

        return url;
    }

    private QName getQName() {
        return new QName("http://providerPckg/", "ProviderWSService");
    }

    private void sendRequest(){
        //TODO implement sendRequest()

        try { // Call Web Service Operation
            service = new ProviderWSService(getURL(), getQName());
            providerpckg.ProviderWS port = service.getProviderWSPort();
            // TODO initialize WS operation arguments here
            int consumerId = ConsumerWebService.CONSUMER_ID;
            int messageId = this.messageNumber;
            this.messageNumber++;
            String payload = ConsumerWebService.PAYLOAD;
            // TODO process result here
            log.debug("Request message id "+messageId+" about to be sent");
            String result = port.operation(consumerId, messageId, payload);
            if (result==null){
                log.fatal("The remote service experienced a problem, aborting");
            }else{
                log.debug("Provider answered with "+result);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
