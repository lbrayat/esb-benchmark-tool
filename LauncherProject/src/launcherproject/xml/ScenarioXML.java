/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package launcherproject.xml;

import java.util.ArrayList;

/**
 *
 * @author admin
 */
public class ScenarioXML {

    /**
     * @return the configurationXML
     */
    public ConfigurationXML getConfigurationXML() {
        return configurationXML;
    }

    /**
     * @param configurationXML the configurationXML to set
     */
    public void setConfigurationXML(ConfigurationXML configurationXML) {
        this.configurationXML = configurationXML;
    }

    /**
     * @return the consumersXML
     */
    public ArrayList<Consumer> getConsumersXML() {
        return consumersXML;
    }

    /**
     * @param consumersXML the consumersXML to set
     */
    public void setConsumersXML(ArrayList<Consumer> consumersXML) {
        this.consumersXML = consumersXML;
    }

    /**
     * @return the providersXML
     */
    public ArrayList<Provider> getProvidersXML() {
        return providersXML;
    }

    /**
     * @param providersXML the providersXML to set
     */
    public void setProvidersXML(ArrayList<Provider> providersXML) {
        this.providersXML = providersXML;
    }

    public enum TypeOfActor {
        provider, consumer;
        public static TypeOfActor ParseTypeOfActor (String s) {
            if (s.equals("provider"))
                return ScenarioXML.TypeOfActor.provider;
            else
                return ScenarioXML.TypeOfActor.consumer;
        }
    }

    public class Actor {
        private TypeOfActor typeOfActor;
        private String identifier;
        private String address;

        public Actor ()
        { }

        /**
         * @return the typeOfActor
         */
        public TypeOfActor getTypeOfActor() {
            return typeOfActor;
        }

        /**
         * @param typeOfActor the typeOfActor to set
         */
        public void setTypeOfActor(TypeOfActor typeOfActor) {
            this.typeOfActor = typeOfActor;
        }

        /**
         * @return the identifier
         */
        public String getIdentifier() {
            return identifier;
        }

        /**
         * @param identifier the identifier to set
         */
        public void setIdentifier(String identifier) {
            this.identifier = identifier;
        }

        /**
         * @return the address
         */
        public String getAddress() {
            return address;
        }

        /**
         * @param address the address to set
         */
        public void setAddress(String address) {
            this.address = address;
        }
                
        @Override
        public String toString ()
        {
            StringBuilder result = new StringBuilder();
            String NEW_LINE = System.getProperty("line.separator");
            result.append(this.getClass().getName() + " Object {" + NEW_LINE);
            result.append(" typeOfActor : " + getTypeOfActor() + NEW_LINE);
            result.append(" identifier : " + getIdentifier() + NEW_LINE);
            result.append(" address : " + getAddress() + NEW_LINE);
            result.append("}");
            return result.toString();
        }
       
    }
    public class ConfigurationXML {
        private ArrayList<Actor> listActors = new ArrayList<Actor>();

        public ConfigurationXML ()
        { }

        /**
         * @return the listActors
         */
        public ArrayList<Actor> getListActors() {
            return listActors;
        }

        /**
         * @param listActors the listActors to set
         */
        public void setListActors(ArrayList<Actor> listActors) {
            this.listActors = listActors;
        }

        @Override
        public String toString () {
            StringBuilder result = new StringBuilder();
            String NEW_LINE = System.getProperty("line.separator");
            result.append(this.getClass().getName() + " Object {" + NEW_LINE);
            result.append(" listOfActors : " + getListActors() + NEW_LINE);
            result.append("}");
            return result.toString();
        }
    }
    
    public enum TrafficClass {
        BE, RT, NRT;
        public static TrafficClass ParseTrafficClass (String s) {
            if (s.equals("RT"))
                return TrafficClass.RT;
            else if (s.equals("NRT"))
                return TrafficClass.NRT;
            else
                return TrafficClass.BE;
        }
    }
    
    public class PhaseConsumer {
        private String providerId;
        private TrafficClass trafficClass;
        private int numberOfRequests;
        private int sendPeriod;
        private int packetSize;
        private long startDate;
        private int numberOfBursts;

        public PhaseConsumer ()
        { }

        @Override
        public String toString () {
            StringBuilder result = new StringBuilder();
            String NEW_LINE = System.getProperty("line.separator");
            result.append(this.getClass().getName() + " Object {" + NEW_LINE);
            result.append(" providerId : " + getProviderId() + NEW_LINE);
            result.append(" trafficClass : " + getTrafficClass() + NEW_LINE);
            result.append(" numberOfRequests : " + getNumberOfRequests() + NEW_LINE);
            result.append(" sendPeriod : " + getSendPeriod() + NEW_LINE);
            result.append(" packetSize : " + getPacketSize() + NEW_LINE);
            result.append(" dateStart : " + getStartDate() + NEW_LINE);
            result.append(" numberOfBursts : " + getNumberOfBursts() + NEW_LINE);
            result.append("}");
            return result.toString();
        }

        /**
         * @return the providerId
         */
        public String getProviderId() {
            return providerId;
        }

        /**
         * @param providerId the providerId to set
         */
        public void setProviderId(String providerId) {
            this.providerId = providerId;
        }

        /**
         * @return the trafficClass
         */
        public TrafficClass getTrafficClass() {
            return trafficClass;
        }

        /**
         * @param trafficClass the trafficClass to set
         */
        public void setTrafficClass(TrafficClass trafficClass) {
            this.trafficClass = trafficClass;
        }

        /**
         * @return the numberOfRequests
         */
        public int getNumberOfRequests() {
            return numberOfRequests;
        }

        /**
         * @param numberOfRequests the numberOfRequests to set
         */
        public void setNumberOfRequests(int numberOfRequests) {
            this.numberOfRequests = numberOfRequests;
        }

        /**
         * @return the sendPeriod
         */
        public int getSendPeriod() {
            return sendPeriod;
        }

        /**
         * @param sendPeriod the sendPeriod to set
         */
        public void setSendPeriod(int sendPeriod) {
            this.sendPeriod = sendPeriod;
        }

        /**
         * @return the packetSize
         */
        public int getPacketSize() {
            return packetSize;
        }

        /**
         * @param packetSize the packetSize to set
         */
        public void setPacketSize(int packetSize) {
            this.packetSize = packetSize;
        }

        /**
         * @return the startDate
         */
        public long getStartDate() {
            return startDate;
        }

        /**
         * @param startDate the startDate to set
         */
        public void setStartDate(long startDate) {
            this.startDate = startDate;
        }

        /**
         * @return the numberOfBursts
         */
        public int getNumberOfBursts() {
            return numberOfBursts;
        }

        /**
         * @param numberOfBursts the numberOfBursts to set
         */
        public void setNumberOfBursts(int numberOfBursts) {
            this.numberOfBursts = numberOfBursts;
        }
    }
    
    public class Consumer {
        private String identifier;
        private ArrayList<PhaseConsumer> listPhases = new ArrayList<PhaseConsumer>();

        public Consumer ()
        { }

        /**
         * @return the identifier
         */
        public String getIdentifier() {
            return identifier;
        }

        /**
         * @param identifier the identifier to set
         */
        public void setIdentifier(String identifier) {
            this.identifier = identifier;
        }

        /**
         * @return the listPhases
         */
        public ArrayList<PhaseConsumer> getListPhases() {
            return listPhases;
        }

        /**
         * @param listPhases the listPhases to set
         */
        public void setListPhases(ArrayList<PhaseConsumer> listPhases) {
            this.listPhases = listPhases;
        }

        public void addPhase (PhaseConsumer phaseConsumer)
        {
            this.listPhases.add(phaseConsumer);
        }

        @Override
        public String toString () { 
            StringBuilder result = new StringBuilder();
            String NEW_LINE = System.getProperty("line.separator");
            result.append(this.getClass().getName() + " Object {" + NEW_LINE);
            result.append(" identifier : " + getIdentifier() + NEW_LINE);
            result.append(" listPhases : " + NEW_LINE);
            for(PhaseConsumer phase : getListPhases()) {
                result.append(" phase : " + phase + NEW_LINE);
            }
            result.append("}");
            return result.toString();        
        }
    }

    public class PhaseProvider {
        private int processingTime;
        private int numberOfResponses;
        private int packetSize;
        private int percentageLoss;
        private long startDate;
        private long endDate;

        public PhaseProvider ()
        { }

        /**
         * @return the processingTime
         */
        public int getProcessingTime() {
            return processingTime;
        }

        /**
         * @param processingTime the processingTime to set
         */
        public void setProcessingTime(int processingTime) {
            this.processingTime = processingTime;
        }

        /**
         * @return the numberOfResponses
         */
        public int getNumberOfResponses() {
            return numberOfResponses;
        }

        /**
         * @param numberOfResponses the numberOfResponses to set
         */
        public void setNumberOfResponses(int numberOfResponses) {
            this.numberOfResponses = numberOfResponses;
        }

        /**
         * @return the packetSize
         */
        public int getPacketSize() {
            return packetSize;
        }

        /**
         * @param packetSize the packetSize to set
         */
        public void setPacketSize(int packetSize) {
            this.packetSize = packetSize;
        }

        /**
         * @return the percentageLoss
         */
        public int getPercentageLoss() {
            return percentageLoss;
        }

        /**
         * @param percentageLoss the percentageLoss to set
         */
        public void setPercentageLoss(int percentageLoss) {
            this.percentageLoss = percentageLoss;
        }

        /**
         * @return the startDate
         */
        public long getStartDate() {
            return startDate;
        }

        /**
         * @param startDate the startDate to set
         */
        public void setStartDate(long startDate) {
            this.startDate = startDate;
        }

        /**
         * @return the endDate
         */
        public long getEndDate() {
            return endDate;
        }

        /**
         * @param endDate the endDate to set
         */
        public void setEndDate(long endDate) {
            this.endDate = endDate;
        }

        @Override
        public String toString () {
            StringBuilder result = new StringBuilder();
            String NEW_LINE = System.getProperty("line.separator");
            result.append(this.getClass().getName() + " Object {" + NEW_LINE);
            result.append(" processingTime : " + getProcessingTime() + NEW_LINE);
            result.append(" numberOfResponses : " + getNumberOfResponses() + NEW_LINE);
            result.append(" packetSize : " + getPacketSize() + NEW_LINE);
            result.append(" percentageLoss : " + getPercentageLoss() + NEW_LINE);
            result.append(" dateStart : " + getStartDate() + NEW_LINE);
            result.append(" dateEnd : " + getEndDate() + NEW_LINE);
            result.append("}");
            return result.toString();
        }
    }

    public class Provider {
        private String identifier;
        private ArrayList<PhaseProvider> listPhases = new ArrayList<PhaseProvider>();

        public Provider ()
        { }

        /**
         * @return the identifier
         */
        public String getIdentifier() {
            return identifier;
        }

        /**
         * @param identifier the identifier to set
         */
        public void setIdentifier(String identifier) {
            this.identifier = identifier;
        }

        /**
         * @return the listPhases
         */
        public ArrayList<PhaseProvider> getListPhases() {
            return listPhases;
        }

        /**
         * @param listPhases the listPhases to set
         */
        public void setListPhases(ArrayList<PhaseProvider> listPhases) {
            this.listPhases = listPhases;
        }

        public void addPhase (PhaseProvider phaseProvider)
        {
            this.listPhases.add(phaseProvider);
        }

        @Override
        public String toString () {
            StringBuilder result = new StringBuilder();
            String NEW_LINE = System.getProperty("line.separator");
            result.append(this.getClass().getName() + " Object {" + NEW_LINE);
            result.append(" identifier : " + getIdentifier() + NEW_LINE);
            result.append(" listPhases : " + NEW_LINE);
            for(PhaseProvider phase : getListPhases()) {
                result.append(" phase : " + phase + NEW_LINE);
            }
            result.append("}");
            return result.toString();
        }
    }

    private ConfigurationXML configurationXML;
    private ArrayList<Consumer> consumersXML = new ArrayList<Consumer>();
    private ArrayList<Provider> providersXML = new ArrayList<Provider>();

    @Override
    public String toString () {
        StringBuilder result = new StringBuilder();
        String NEW_LINE = System.getProperty("line.separator");
        result.append(this.getClass().getName() + " Object {" + NEW_LINE);
        result.append(" configurationXML : " + getConfigurationXML() + NEW_LINE);
        result.append(" consumers : " + getConsumersXML() + NEW_LINE);
        result.append(" providers : " + getProvidersXML() + NEW_LINE );
        result.append("}");
        return result.toString();
    }
}
