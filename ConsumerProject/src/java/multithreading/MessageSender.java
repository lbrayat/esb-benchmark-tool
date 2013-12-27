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

/**
 *
 * @author marc
 */
public class MessageSender implements Runnable{

    public providerpckg.ProviderWS port;
    int consumerId;
    int payloadSize;
    int targetConf;
    long messageId;

    public MessageSender(ProviderWS port, int consumerId, int payloadSize, int targetConf, long messageId) {
        this.port = port;
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
            result = port.operation(messageId, targetConf, payload);
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
