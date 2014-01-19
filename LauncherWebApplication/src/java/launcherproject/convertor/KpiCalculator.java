/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package launcherproject.convertor;

import java.util.List;

/**
 *
 * @author 19012831
 */
public class KpiCalculator {

    List<MessageData> messagesList;


    public KpiCalculator(List<MessageData> messagesList) {
        this.messagesList = messagesList;
    }


    /*
     * ------------------------------------------------
     * TOTAL KPIs
     *    . average propagation time
     *    . min and max propagation time
     *    . average size of packets in every way
     *    . total packet size 
     *    . standard deviation of the propagation time
     *    . average throughput
     * ------------------------------------------------
     */
    public double propagationTimeAverageTotal(){

        double average=0;
        for (MessageData m : messagesList){
            average = average + (m.ts2-m.ts1) + (m.ts4-m.ts3);
        }

        average = average/messagesList.size();
        return average;
    }

     public double propagationTimeStandardDeviationTotal() {
        double deviation=0;
        double average = propagationTimeAverageTotal();

        for (MessageData m : messagesList){
            deviation += Math.pow(((m.ts2-m.ts1) + (m.ts4-m.ts3)) - average, 2);
        }

        deviation = deviation/messagesList.size();
        deviation = Math.sqrt(deviation);
        return deviation;
    }

        /**
     *
     * @param way 1 for provider-consumer, 2 for consumer-provider, 3 for both
     * @return
     */
    public double packetSizeAverageTotal(int way){
        double average=0;
        for (MessageData m : messagesList){
            if (way==1)
                average += m.payloadGo.length();
            if (way==2)
                average += m.payloadBack.length();
            else
                average += (m.payloadGo.length()+m.payloadBack.length());
        }

        average = average/messagesList.size();
        return average;
    }

    public double packetSizeTotal(){
        double total=0;
        for (MessageData m : messagesList){
            total += (m.payloadGo.length()+m.payloadBack.length());
        }
        return total;
    }

    public double maxPropagationTime(){
        double max = 0;
        double propagTime=0;
        for (MessageData m : messagesList){
            propagTime = ((m.ts2-m.ts1) + (m.ts4-m.ts3));
            if (max < propagTime){
                max = propagTime;
            }
        }
        return max;
    }

   public double minPropagationTime(){
        double min = Double.MAX_VALUE;
        double propagTime=0;
        for (MessageData m : messagesList){
            propagTime = ((m.ts2-m.ts1) + (m.ts4-m.ts3));
            if (min > propagTime){
                min = propagTime;
            }
        }
        return min;
    }

   public double timeOfSimulationInMillis(){
       double startTS=Double.MAX_VALUE;
       double endTS=0;

       for (MessageData m : messagesList){
           if (m.ts1 < startTS){
               startTS=m.ts1;
           }
           if (m.ts4 > endTS){
               endTS = m.ts4;
           }
       }

       return endTS-startTS;
   }

   public double averageThroughputTotal(){
       double totalPacketSize = packetSizeTotal();
       double timeSimu= timeOfSimulationInMillis();

       return totalPacketSize/timeSimu;
   }

     /*
     * ------------------------------------------------
     * PER PHASE KPIs
     *    . mean propagation time
      *   . propagation time standard deviation
      *   . max and min propagation time
      *   . packet size total
      *   . mean thruput
     * ------------------------------------------------
     */
    public double propagationTimeAveragePhase(String consumerID, int phase){

        double average=0;
        int size=0;

        for (MessageData m : messagesList){
            if((m.consumerId.equals(consumerID)) && (m.consumerPhaseId == phase)){
                average += ((m.ts2-m.ts1) + (m.ts4-m.ts3));
                size++;
            }
        }

        average = average/size;
        return average;
    }

    public double propagationTimeStandardDeviationPhase(String consumerID, int phase) {

        double deviation=0;
        int size=0;
        double average = propagationTimeAverageTotal();

        for (MessageData m : messagesList){
            if((m.consumerId.equals(consumerID)) && (m.consumerPhaseId == phase)){
                deviation += Math.pow(((m.ts2-m.ts1) + (m.ts4-m.ts3)) - average, 2);
                size++;
            }
        }
        
        deviation = deviation/size;
        deviation = Math.sqrt(deviation);

        return deviation;
    }

    public double maxPropagationTimePerPhase(String consumerID, int phase){
        double max = 0;
        double propagTime=0;
        for (MessageData m : messagesList){
            if ((m.consumerId.equals(consumerID)) && (m.consumerPhaseId==phase)){
                propagTime = ((m.ts2-m.ts1) + (m.ts4-m.ts3));
                if (max < propagTime){
                    max = propagTime;
                }
            }
        }
        return max;
    }

   public double minPropagationTimePerPhase(String consumerID, int phase){
        double min = Double.MAX_VALUE;
        double propagTime=0;
        for (MessageData m : messagesList){
            if ((m.consumerId.equals(consumerID)) && (m.consumerPhaseId==phase)){
                propagTime = ((m.ts2-m.ts1) + (m.ts4-m.ts3));
                if (min > propagTime){
                    min = propagTime;
                }
            }
        }
        return min;
    }

   public double totalPacketSize(String consumerID, int phase){
       double total=0;
        for (MessageData m : messagesList){
            if ((m.consumerId.equals(consumerID)) && (m.consumerPhaseId==phase))
                total += (m.payloadGo.length()+m.payloadBack.length());
        }
        return total;
   }

   public double averageThroughputTotalPerPhase(String consumerID, int phase){
       double totalPacketSize = totalPacketSize(consumerID, phase);
       double timeSimu= timeOfSimulationInMillis();

       return totalPacketSize/timeSimu;
   }

    /*
     * ------------------------------------------------
     * PER CONF KPIs
     *    . mean propagation time
      *   . propagation time standard deviation
      *   . max and min propagation time
      *   . packet size total
      *   . mean thruput
     * ------------------------------------------------
     */
    public double propagationTimeAverageConf(String providerId, int conf){

        double average=0;
        int size=0;

        for (MessageData m : messagesList){
            if((m.providerId.equals(providerId)) && (m.providerConfId == conf)){
                average += ((m.ts2-m.ts1) + (m.ts4-m.ts3));
                size++;
            }
        }

        average = average/size;
        return average;
    }

    public double propagationTimeStandardDeviationConf(String providerId, int conf) {

        double deviation=0;
        int size=0;
        double average = propagationTimeAverageTotal();

        for (MessageData m : messagesList){
            if((m.providerId.equals(providerId)) && (m.providerConfId == conf)){
                deviation += Math.pow(((m.ts2-m.ts1) + (m.ts4-m.ts3)) - average, 2);
                size++;
            }
        }

        deviation = deviation/size;
        deviation = Math.sqrt(deviation);

        return deviation;
    }

    public double maxPropagationTimePerConf(String providerId, int conf){
        double max = 0;
        double propagTime=0;
        for (MessageData m : messagesList){
            if((m.providerId.equals(providerId)) && (m.providerConfId == conf)){
                propagTime = ((m.ts2-m.ts1) + (m.ts4-m.ts3));
                if (max < propagTime){
                    max = propagTime;
                }
            }
        }
        return max;
    }

   public double minPropagationTimePerConf(String providerId, int conf){
        double min = Double.MAX_VALUE;
        double propagTime=0;
        for (MessageData m : messagesList){
            if((m.providerId.equals(providerId)) && (m.providerConfId == conf)){
                propagTime = ((m.ts2-m.ts1) + (m.ts4-m.ts3));
                if (min > propagTime){
                    min = propagTime;
                }
            }
        }
        return min;
    }

   public double totalPacketSizeConf(String providerId, int conf){
       double total=0;
        for (MessageData m : messagesList){
            if((m.providerId.equals(providerId)) && (m.providerConfId == conf)){
                total += (m.payloadGo.length()+m.payloadBack.length());
            }
        }
        return total;
   }

   public double averageThroughputTotalPerConf(String providerId, int conf){
       double totalPacketSize = totalPacketSizeConf(providerId, conf);
       double timeSimu= timeOfSimulationInMillis();

       return totalPacketSize/timeSimu;
   }


     /*
     * ------------------------------------------------
     * PER COUPLE KPIs
      *   . mean propagation time
      *   . propagation time standard deviation
      *   . max and min propagation time
      *   . packet size total
      *   . mean thruput
     * ------------------------------------------------
     */

     public boolean hasStatsForCouple(String consumerId, String ProviderId){

        for (MessageData m : messagesList){
            if(m.consumerId.equals(consumerId) && m.providerId.equals(ProviderId)){
                return true;
            }
        }

        return false;
    }

     public double propagationTimeAveragePerCouple(String consumerId, String ProviderId){

        double average=0;
        int size=0;

        for (MessageData m : messagesList){
            if(m.consumerId.equals(consumerId) && m.providerId.equals(ProviderId)){
                average += ((m.ts2-m.ts1) + (m.ts4-m.ts3));
                size++;
            }
        }

        average = average/size;
        return average;
    }

    public double propagationTimeStandardDeviationPerCouple(String consumerId, String ProviderId) {

        double deviation=0;
        int size=0;
        double average = propagationTimeAverageTotal();

        for (MessageData m : messagesList){
            if(m.consumerId.equals(consumerId) && m.providerId.equals(ProviderId)){
                deviation += Math.pow(((m.ts2-m.ts1) + (m.ts4-m.ts3)) - average, 2);
                size++;
            }
        }

        deviation = deviation/size;
        deviation = Math.sqrt(deviation);

        return deviation;
    }

    public double maxPropagationTimePerCouple(String consumerId, String ProviderId){
        double max = 0;
        double propagTime=0;
        for (MessageData m : messagesList){
            if(m.consumerId.equals(consumerId) && m.providerId.equals(ProviderId)){
                propagTime = ((m.ts2-m.ts1) + (m.ts4-m.ts3));
                if (max < propagTime){
                    max = propagTime;
                }
            }
        }
        return max;
    }

   public double minPropagationTimePerCouple(String consumerId, String ProviderId){
        double min = Double.MAX_VALUE;
        double propagTime=0;
        for (MessageData m : messagesList){
            if(m.consumerId.equals(consumerId) && m.providerId.equals(ProviderId)){
                propagTime = ((m.ts2-m.ts1) + (m.ts4-m.ts3));
                if (min > propagTime){
                    min = propagTime;
                }
            }
        }
        return min;
    }

   public double totalPacketSizePerCouple(String consumerId, String ProviderId){
       double total=0;
        for (MessageData m : messagesList){
            if(m.consumerId.equals(consumerId) && m.providerId.equals(ProviderId)){
                total += (m.payloadGo.length()+m.payloadBack.length());
            }
        }
        return total;
   }

   public double averageThroughputTotalPerCouple(String consumerId, String ProviderId){
       double totalPacketSize = totalPacketSizePerCouple(consumerId, ProviderId);
       double timeSimu= timeOfSimulationInMillis();

       return totalPacketSize/timeSimu;
   }

}
