/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pojos;

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

    public MessageSender(ProviderWS port, int consumerId, int payloadSize) {
        this.port = port;
        this.consumerId = consumerId;
        this.payloadSize = payloadSize;
    }
    
    public void run() {

        String payload="";
        for (int i=0; i< payloadSize; i++){
            payload+="B";
        }

        // send operation in a different thread
        String result = port.operation(consumerId, consumerId, payload);

        if (result==null){
                ConsumerWebService.log.fatal("The remote service experienced a problem, aborting");
        }else{
                ConsumerWebService.log.debug("Provider answered with "+result);
        }

    }

        

}
