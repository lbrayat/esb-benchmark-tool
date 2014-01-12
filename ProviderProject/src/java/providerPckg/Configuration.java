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
public class Configuration {

        private long tempsTraitement;
        private long tailleReponse;
        private int packetLoss;


        public Configuration(long tempsTraitement, long tailleReponse, int packetLoss){
            setTempsTraitement(tempsTraitement);
            setTailleReponse(tailleReponse);
            setPacketLoss(packetLoss);
        }

        /**
         * SETTERS
         */

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
