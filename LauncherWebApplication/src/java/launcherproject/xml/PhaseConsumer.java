package launcherproject.xml;

public class PhaseConsumer {

    private int id;
    private String providerId;
    private String providerWsdl;
    private int targetedProviderConf;
    private TrafficClass trafficClass;
    private int numberOfRequests;
    private int sendPeriod;
    private int packetSize;
    private long startDate;
    private int numberOfBursts;

    public PhaseConsumer() {
    }

    public PhaseConsumer(String mProviderWsdl, String mProviderId, int mTrafficProfile, int mNbRequests,
            int mPeriod, int mSize, long mStartTimeStamp, int mNbRepeat,
            int mTargetedProviderConf, int mid) {
        this.providerWsdl = mProviderWsdl;
        this.providerId = mProviderId;
        this.id = mid;
        this.targetedProviderConf = mTargetedProviderConf;
        this.trafficClass = trafficClass.intToTrafficClass(mTrafficProfile);
        this.numberOfRequests = mNbRequests;
        this.sendPeriod = mPeriod;
        this.packetSize = mSize;
        this.startDate = mStartTimeStamp;
        this.numberOfBursts = mNbRepeat;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        String NEW_LINE = System.getProperty("line.separator");

        result.append(this.getClass().getName() + " Object {" + NEW_LINE);
        result.append(" providerId : " + getProviderId() + NEW_LINE);
        result.append(" targetedProviderConfiguration : " + getTargetedProviderConf() + NEW_LINE);
        result.append(" trafficClass : " + getTrafficClass() + NEW_LINE);
        result.append(" numberOfRequests : " + getNumberOfRequests() + NEW_LINE);
        result.append(" sendPeriod : " + getSendPeriod() + NEW_LINE);
        result.append(" packetSize : " + getPacketSize() + NEW_LINE);
        result.append(" dateStart : " + getStartDate() + NEW_LINE);
        result.append(" numberOfBursts : " + getNumberOfBursts() + NEW_LINE);
        result.append("}");
        return result.toString();
    }

    public int getTargetedProviderConf() {
        return targetedProviderConf;
    }

    public String getProviderWsdl() {
        return providerWsdl;
    }

    public void setProviderWsdl(String providerWsdl) {
        this.providerWsdl = providerWsdl;
    }

    public void setTargetedProviderConf(int targetedProviderConf) {
        this.targetedProviderConf = targetedProviderConf;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProviderId() {
        return providerId;
    }

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
