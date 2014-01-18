/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package multithreading;

import java.util.logging.Level;
import java.util.logging.Logger;
import providerpckg.InterruptedException_Exception;
import providerpckg.ProviderWS;
import beta.ConsumerWebService;
import java.util.GregorianCalendar;
import pojos.LogResultList;
import pojos.LogResultListSingletonFactory;

/**
 *
 * @author marc
 */
public class MessageSender implements Runnable{

    public providerpckg.ProviderWS port;
    int consumerId;
    int payloadSize;
    int targetConf;
    String messageId;
    int consumerPhaseId;

    public MessageSender(ProviderWS port, int consumerPhase, int consumerId, int payloadSize, int targetConf, String messageId) {
        this.port = port;
        this.consumerPhaseId = consumerPhase;
        this.consumerId = consumerId;
        this.payloadSize = payloadSize;
        this.targetConf = targetConf;
        this.messageId= messageId;
    }
    
    public void run() {

        String payload="";
        for (int i=0; i< payloadSize; i++){
            payload+="B";
        }

        // send operation in a different thread
        String result = null;
        try {
            //log message information before sending it
            LogResultListSingletonFactory.getInstance().addLog(messageId, targetConf,
                    payload, new GregorianCalendar().getTimeInMillis(),
                    LogResultList.OUT_CONSUMER, consumerPhaseId);

            // send the request
            result = port.operation(messageId, targetConf, payload);

            // log the response informations after receiving it
            LogResultListSingletonFactory.getInstance().addLog(messageId, targetConf,
                    result, new GregorianCalendar().getTimeInMillis(),
                    LogResultList.IN_CONSUMER, consumerPhaseId);
            
        } catch (InterruptedException_Exception ex) {
            Logger.getLogger(MessageSender.class.getName()).log(Level.SEVERE, null, ex);
        } 

        if (result==null){
                ConsumerWebService.log.fatal("The remote service experienced a problem, aborting");
        }else{
                ConsumerWebService.log.debug("Provider answered with "+result);
        }

    }

        

}
