/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package providerPckg;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.*;

/**
 *
 * @author root
 */
@WebService()
public class ProviderWS {


    private class Phase{

        private int phaseNumber;
        private GregorianCalendar dateFin;
        private long tempsTraitement;
        private long tailleReponse;
        private int packetLoss;


        public Phase(int phaseNumber, GregorianCalendar dateFin, long tempsTraitement, long tailleReponse, int packetLoss){
            setDateFin(dateFin);
            setTempsTraitement(tempsTraitement);
            setTailleReponse(tailleReponse);
            setPacketLoss(packetLoss);
        }

        /**
         * SETTERS
         */

        public void setPhaseNumber(int id){
            phaseNumber = id;
        }

        public void setDateFin(GregorianCalendar date){
            dateFin = date;
        }

        public void setTempsTraitement(long temps){
            tempsTraitement = temps;
        }

        public void setTailleReponse(long taille){
            tailleReponse = taille;
        }

        public void setPacketLoss(int loss){
            packetLoss = loss;
        }



        /**
         * GETTERS
         */
        public int getPhaseNumber(){
            return phaseNumber;
        }

        public Calendar getDateFin(){
            return dateFin;
        }

        public long getTempsTraitement(){
            return tempsTraitement;
        }

        public long getTailleReponse(){
            return tailleReponse;
        }

        public int setPacketLoss(){
            return packetLoss;
        }

    }

    private List<Phase> phaseList;
    private int readyToStart; //le WS est configuré et prết à démarrer
    private GregorianCalendar dateStart;


    public ProviderWS(){
        readyToStart = 0;
        phaseList = new ArrayList<Phase>();
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
        phaseList.clear();
    }
    

    /**
     * Web service operation
     */
    @WebMethod(operationName = "addPhase")
    public int addPhase(@WebParam(name = "phaseNumber")
    int phaseNumber, @WebParam(name = "dateFin")
    long dateFin, @WebParam(name = "tempsTraitement")
    long tempsTraitement, @WebParam(name = "tailleReponse")
    long tailleReponse, @WebParam(name = "packetLoss")
    int packetLoss) {

        GregorianCalendar dateF = new GregorianCalendar();
        dateF.setTimeInMillis(dateFin);
        Phase phase = new Phase(phaseNumber, dateF, tempsTraitement, tailleReponse, packetLoss);
        phaseList.add(phase);

        //log
        System.out.println("phaseNumber : "+phaseNumber);
        System.out.println("dateF : "+dateF);
        System.out.println("tempsTraitement : "+tempsTraitement);
        System.out.println("tailleReponse : "+tailleReponse);
        System.out.println("packetLoss : "+packetLoss);

        return 0;

    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "endConf")
    public int endConf() {
        readyToStart = 1;
        dateStart = new GregorianCalendar();
        return 0;
    }

    /**
     * Web service operation
     * TODO : tempsTraitement
     */
    @WebMethod(operationName = "operation")
    public String operation(int idConsumer, int idMEssage, String payloadConsumer){

        System.out.println("Réception d'un message");
        int index = 0;

        String payloadProvider = new String();
        if(readyToStart == 1){
            GregorianCalendar date = new GregorianCalendar();

            //tant que la date de fin n'est pas atteinte
            System.out.println("date de fin : "+this.phaseList.get(index).getDateFin().getTime());

            int i;
            // construit la chaîne de caractères en fonction de la taille de la réponse
            for(i=0; i<this.phaseList.get(index).getTailleReponse(); i++){
                    payloadProvider = payloadProvider.concat("A");
            }

            System.out.println("Message écrit : "+payloadProvider);
           
            if (date.getTime().after(this.phaseList.get(index).getDateFin().getTime())){
                index ++;
            }
        }
        
        System.out.println("Envoi d'un message");
        return payloadProvider;
    }


}
