/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package providerPckg;
import java.util.*;

/**
 *
 * @author Théo Mérou
 */
public class Phase {

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
