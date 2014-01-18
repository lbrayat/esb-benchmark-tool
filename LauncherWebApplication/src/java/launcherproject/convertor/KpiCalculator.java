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
            average += ((m.ts2-m.ts1) + (m.ts4-m.ts3));
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
        double min = 0;
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
    public double propagationTimeAveragePhase(int phase){

        double average=0;
        int size=0;

        for (MessageData m : messagesList){
            if(m.consumerPhaseId == phase){
                average += ((m.ts2-m.ts1) + (m.ts4-m.ts3));
                size++;
            }
        }

        average = average/size;
        return average;
    }

    public double propagationTimeStandardDeviationPhase(int phase) {

        double deviation=0;
        int size=0;
        double average = propagationTimeAverageTotal();

        for (MessageData m : messagesList){
            if(m.consumerPhaseId == phase){
                deviation += Math.pow(((m.ts2-m.ts1) + (m.ts4-m.ts3)) - average, 2);
                size++;
            }
        }
        
        deviation = deviation/size;
        deviation = Math.sqrt(deviation);

        return deviation;
    }

    public double maxPropagationTimePerPhase(int phase){
        double max = 0;
        double propagTime=0;
        for (MessageData m : messagesList){
            if (m.consumerPhaseId==phase){
                propagTime = ((m.ts2-m.ts1) + (m.ts4-m.ts3));
                if (max < propagTime){
                    max = propagTime;
                }
            }
        }
        return max;
    }

   public double minPropagationTimePerPhase(int phase){
        double min = 0;
        double propagTime=0;
        for (MessageData m : messagesList){
            if (m.consumerPhaseId==phase){
                propagTime = ((m.ts2-m.ts1) + (m.ts4-m.ts3));
                if (min > propagTime){
                    min = propagTime;
                }
            }
        }
        return min;
    }

   public double totalPacketSize(int phase){
       double total=0;
        for (MessageData m : messagesList){
            if (m.consumerPhaseId==phase)
                total += (m.payloadGo.length()+m.payloadBack.length());
        }
        return total;
   }

   public double averageThroughputTotalPerPhase(int phase){
       double totalPacketSize = totalPacketSize(phase);
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
    public double propagationTimeAverageConf(int conf){

        double average=0;
        int size=0;

        for (MessageData m : messagesList){
            if(m.providerConfId == conf){
                average += ((m.ts2-m.ts1) + (m.ts4-m.ts3));
                size++;
            }
        }

        average = average/size;
        return average;
    }

    public double propagationTimeStandardDeviationConf(int conf) {

        double deviation=0;
        int size=0;
        double average = propagationTimeAverageTotal();

        for (MessageData m : messagesList){
            if(m.providerConfId == conf){
                deviation += Math.pow(((m.ts2-m.ts1) + (m.ts4-m.ts3)) - average, 2);
                size++;
            }
        }

        deviation = deviation/size;
        deviation = Math.sqrt(deviation);

        return deviation;
    }

    public double maxPropagationTimePerConf(int conf){
        double max = 0;
        double propagTime=0;
        for (MessageData m : messagesList){
            if(m.providerConfId == conf){
                propagTime = ((m.ts2-m.ts1) + (m.ts4-m.ts3));
                if (max < propagTime){
                    max = propagTime;
                }
            }
        }
        return max;
    }

   public double minPropagationTimePerConf(int conf){
        double min = 0;
        double propagTime=0;
        for (MessageData m : messagesList){
            if(m.providerConfId == conf){
                propagTime = ((m.ts2-m.ts1) + (m.ts4-m.ts3));
                if (min > propagTime){
                    min = propagTime;
                }
            }
        }
        return min;
    }

   public double totalPacketSizeConf(int conf){
       double total=0;
        for (MessageData m : messagesList){
            if(m.providerConfId == conf){
                total += (m.payloadGo.length()+m.payloadBack.length());
            }
        }
        return total;
   }

   public double averageThroughputTotalPerConf(int conf){
       double totalPacketSize = totalPacketSize(conf);
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

     public double propagationTimeAveragePerCouple(int consumerId, int ProviderId){

        double average=0;
        int size=0;

        for (MessageData m : messagesList){
            if(Integer.decode(m.consumerId)==consumerId && Integer.decode(m.providerId)==ProviderId){
                average += ((m.ts2-m.ts1) + (m.ts4-m.ts3));
                size++;
            }
        }

        average = average/size;
        return average;
    }

    public double propagationTimeStandardDeviationPerCouple(int consumerId, int ProviderId) {

        double deviation=0;
        int size=0;
        double average = propagationTimeAverageTotal();

        for (MessageData m : messagesList){
            if(Integer.decode(m.consumerId)==consumerId && Integer.decode(m.providerId)==ProviderId){
                deviation += Math.pow(((m.ts2-m.ts1) + (m.ts4-m.ts3)) - average, 2);
                size++;
            }
        }

        deviation = deviation/size;
        deviation = Math.sqrt(deviation);

        return deviation;
    }

    public double maxPropagationTimePerCouple(int consumerId, int ProviderId){
        double max = 0;
        double propagTime=0;
        for (MessageData m : messagesList){
            if(Integer.decode(m.consumerId)==consumerId && Integer.decode(m.providerId)==ProviderId){
                propagTime = ((m.ts2-m.ts1) + (m.ts4-m.ts3));
                if (max < propagTime){
                    max = propagTime;
                }
            }
        }
        return max;
    }

   public double minPropagationTimePerCouple(int consumerId, int ProviderId){
        double min = 0;
        double propagTime=0;
        for (MessageData m : messagesList){
            if(Integer.decode(m.consumerId)==consumerId && Integer.decode(m.providerId)==ProviderId){
                propagTime = ((m.ts2-m.ts1) + (m.ts4-m.ts3));
                if (min > propagTime){
                    min = propagTime;
                }
            }
        }
        return min;
    }

   public double totalPacketSizePerCouple(int consumerId, int ProviderId){
       double total=0;
        for (MessageData m : messagesList){
            if(Integer.decode(m.consumerId)==consumerId && Integer.decode(m.providerId)==ProviderId){
                total += (m.payloadGo.length()+m.payloadBack.length());
            }
        }
        return total;
   }

   public double averageThroughputTotalPerCouple(int consumerId, int ProviderId){
       double totalPacketSize = totalPacketSizePerCouple(consumerId, ProviderId);
       double timeSimu= timeOfSimulationInMillis();

       return totalPacketSize/timeSimu;
   }

}
