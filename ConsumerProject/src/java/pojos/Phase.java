/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pojos;

import java.text.DateFormat;
import java.util.Calendar;

/**
 *
 * @author marc
 */
public class Phase {

    int idPhase;
    int recepientID;
    String recepientAddress;
    int numberOfRequests;
    int sendingPeriod;
    int payloadSize;
    int repetitions;
    Calendar startDate;
    int targetConfig;



    public Phase(int idPhase, int providerID, String recepientAddress, int numberOfRequests, int sendingPeriod,
            int payloadSize, int repetitions, Calendar startDate, int targetedProviderConfiguration) {
        this.idPhase = idPhase;
        this.recepientAddress = recepientAddress;
        this.numberOfRequests = numberOfRequests;
        this.sendingPeriod = sendingPeriod;
        this.payloadSize = payloadSize;
        this.repetitions = repetitions;
        this.startDate = startDate;
        this.targetConfig = targetedProviderConfiguration;
        this.recepientID = providerID;
    }

    public int getIdPhase() {
        return idPhase;
    }

    public int getNumberOfRequests() {
        return numberOfRequests;
    }

    public int getPayloadSize() {
        return payloadSize;
    }

    public int getTargetConfig() {
        return targetConfig;
    }

    public void setTargetConfig(int targetConfig) {
        this.targetConfig = targetConfig;
    }

    public String getRecepientAddress() {
        return recepientAddress;
    }

    public int getRepetitions() {
        return repetitions;
    }

    public int getRecepientID(){
        return recepientID;
    }

    public int getSendingPeriod() {
        return sendingPeriod;
    }

    public Calendar getStartDate() {
        return startDate;
    }

    public void setNumberOfRequests(int numberOfRequests) {
        this.numberOfRequests = numberOfRequests;
    }

    public void setPayloadSize(int payloadSize) {
        this.payloadSize = payloadSize;
    }

    public void setRecepientAddress(String recepientAddress) {
        this.recepientAddress = recepientAddress;
    }

    public void setRepetitions(int repetitions) {
        this.repetitions = repetitions;
    }

    public void setRecepientID(int id){
        this.recepientID = id;
    }

    public void setSendingPeriod(int sendingPeriod) {
        this.sendingPeriod = sendingPeriod;
    }

    public void setStartDate(Calendar startDate) {
        this.startDate = startDate;
    }

    @Override
    public String toString(){


        return "Phase parameters: \n   recepient address="+this.getRecepientAddress()+
                "\n   payload size="+this.getPayloadSize()+
                 "\n   repetitions="+this.getRepetitions()+
                  "\n   sending period="+this.getSendingPeriod()+
                   "\n   start date="+this.getStartDate().get(Calendar.HOUR_OF_DAY)+"h "+this.getStartDate().get(Calendar.MINUTE)+"m "+this.getStartDate().get(Calendar.SECOND)+"s "+
                    "\n   number of requests="+this.getNumberOfRequests();
    }



}
