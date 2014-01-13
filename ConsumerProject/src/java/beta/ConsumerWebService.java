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
import java.util.concurrent.atomic.AtomicInteger;
import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.namespace.QName;
import javax.xml.ws.WebServiceRef;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import pojos.Phase;
import multithreading.PhaseLauncher;
import pojos.LogResultList;
import pojos.LogResultListSingletonFactory;
import providerpckg.ProviderWSService;
/**
 *
 * @author marc
 */
@WebService()
public class ConsumerWebService {

    public static final int CONSUMER_ID = 1;
    public static AtomicInteger messageNumber;

    private ArrayList<Thread> threadsList;

    @WebServiceRef(wsdlLocation = "http://localhost:8080/ProviderProject/ProviderWSService?wsdl")
    private ProviderWSService service;


     //logging in different levels
    //log.trace("This is a Trace");
     //log.debug("This is a Debug");
     //log.info("This is an Info");
     //log.warn("This is a Warn");
     //log.error("This is an Error");
     //log.fatal("This is a Fatal");


    private List<Phase> phasesList;
    private Phase currentPhase;
    public static Logger log = Logger.getLogger(ConsumerWebService.class.getName());

    public ConsumerWebService() {
        log.setLevel(Level.DEBUG);
        this.phasesList = new ArrayList<Phase>();

        //Phase de test
//        Calendar startDate = new GregorianCalendar();
   //     startDate.add(Calendar.SECOND, 35);
     //   Phase testPhase = new Phase("http://192.168.1.95:8080/ProviderProject/ProviderWSService?wsdl", 5, 3, 10, 3, startDate);
       // phasesList.add(testPhase);

        log.debug("The consumer is deployed");
        threadsList = new ArrayList<Thread>();
        messageNumber = new AtomicInteger(0);

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
        threadsList = new ArrayList<Thread>();
        messageNumber= new AtomicInteger(0);
        LogResultListSingletonFactory.resetInstance();
        log.debug("Previous configurations deleted");
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "configurePhase")
    public void configurePhase(@WebParam(name = "providerID")
    int providerID, @WebParam(name = "recepientAddress")
    String recepientAddress,@WebParam(name = "phaseNumber")
    int phaseNumber, @WebParam(name = "NumberOfrequests")
    int targetedProviderConfiguration, @WebParam(name = "targetedProviderConfiguration")
    int NumberOfrequests, @WebParam(name = "sendingPeriod")
    int sendingPeriod, @WebParam(name = "payloadSize")
    int payloadSize, @WebParam(name = "repetitions")
    int repetitions, @WebParam(name = "startDate")
    long startDate) {
        //TODO verify if two phases do not overlap

        Calendar startDateInCal = new GregorianCalendar();
        log.debug("timestamp long : "+startDate);
        startDateInCal.setTimeInMillis(startDate);

        Phase newPhase = new Phase(providerID, recepientAddress, NumberOfrequests,
                sendingPeriod, payloadSize, repetitions, startDateInCal, targetedProviderConfiguration);

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
            
            for (Phase p : phasesList){
                Thread t = new Thread(new PhaseLauncher(p));
                threadsList.add(t);
                t.start();
            }

            for (Thread t1 : threadsList){
                    try {
                        log.debug("A thread has joined");
                        t1.join();
                    } catch (InterruptedException ex) {
                        log.fatal(ConsumerWebService.class.getName());
                    }
                }
                log.debug("All phases have ended");
                // send message to launcher to say we're done.
               onFinish();


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

    private void onFinish(){
        
        String nextLog= LogResultListSingletonFactory.getInstance().getFirstLog();
        log.debug(nextLog);
        while (!nextLog.equalsIgnoreCase("")){
            nextLog=LogResultListSingletonFactory.getInstance().getNextLog();
            log.debug(nextLog);
        }
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getResults")
    public String getResults(@WebParam(name = "isFirst")
    boolean isFirst) {

        if (isFirst)
            return LogResultListSingletonFactory.getInstance().getFirstLog();
        else
            return LogResultListSingletonFactory.getInstance().getNextLog();
    }

    


    


}
