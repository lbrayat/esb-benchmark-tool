/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package providerPckg;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Théo Mérou
 */
@WebService()
public class ProviderWS {

    private HashMap<Integer, Configuration> confList;
    private int readyToStart; //le WS est configuré et prết à démarrer

     private static final Logger logger = Logger.getLogger("ProviderWS");

    public ProviderWS(){
        logger.setLevel(Level.ALL);
        readyToStart = 0;
        confList = new HashMap<Integer, Configuration>();
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "ping")
    public int ping() {
        System.out.println("ping entrant");
        return 1;
    }
    

    /**
     * Web service operation
     */
    @WebMethod(operationName = "startConf")
    public void startConf(){
        confList.clear();
    }
    

    /**
     * Web service operation
     */
    @WebMethod(operationName = "addConf")
    public int addConf(@WebParam(name = "confNumber")
    int confNumber, @WebParam(name = "tempsTraitement")
    long tempsTraitement, @WebParam(name = "tailleReponse")
    long tailleReponse, @WebParam(name = "packetLoss")
    int packetLoss) {
        //log
        logger.info("Provider WS received a new conf :");
        logger.info("phaseNumber : "+confNumber);
        logger.info("tempsTraitement : "+tempsTraitement);
        logger.info("tailleReponse : "+tailleReponse);
        logger.info("packetLoss : "+packetLoss);


        
        Configuration conf = new Configuration(tempsTraitement, tailleReponse, packetLoss);
        confList.put(confNumber, conf);

        return 1;

    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "endConf")
    public int endConf() {
        readyToStart = 1;
        return 0;
    }

    /**
     * Web service operation
     * TODO : tempsTraitement
     */
    @WebMethod(operationName = "operation")
    public String operation(String idMessage, int idConf, String payload) throws InterruptedException{

        System.out.println("[ProviderWS] conf id="+idConf);
        Configuration currentConf = this.confList.get(idConf);
        System.out.println("[ProviderWS] current conf="+currentConf);
        System.out.println("[ProviderWS] Réception d'un message, id:"+idMessage);

        String payloadProvider = new String(); 
        if(readyToStart == 1){

            int i; 

            Thread.sleep(currentConf.getTempsTraitement());
            
            // construit la chaîne de caractères en fonction de la taille de la réponse
            for(i=0; i<currentConf.getTailleReponse(); i++){
                    payloadProvider = payloadProvider.concat("A");
            }

            System.out.println("Message écrit : "+payloadProvider);
          
        }
        
        System.out.println("Envoi d'un message");
        return payloadProvider;
    }

    


}
