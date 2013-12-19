
package beta;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for configurePhase complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="configurePhase">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="recepientAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="phaseNumber" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="NumberOfrequests" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="sendingPeriod" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="payloadSize" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="repetitions" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="startDate" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "configurePhase", propOrder = {
    "recepientAddress",
    "phaseNumber",
    "numberOfrequests",
    "sendingPeriod",
    "payloadSize",
    "repetitions",
    "startDate"
})
public class ConfigurePhase {

    protected String recepientAddress;
    protected int phaseNumber;
    @XmlElement(name = "NumberOfrequests")
    protected int numberOfrequests;
    protected int sendingPeriod;
    protected int payloadSize;
    protected int repetitions;
    protected long startDate;

    /**
     * Gets the value of the recepientAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRecepientAddress() {
        return recepientAddress;
    }

    /**
     * Sets the value of the recepientAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRecepientAddress(String value) {
        this.recepientAddress = value;
    }

    /**
     * Gets the value of the phaseNumber property.
     * 
     */
    public int getPhaseNumber() {
        return phaseNumber;
    }

    /**
     * Sets the value of the phaseNumber property.
     * 
     */
    public void setPhaseNumber(int value) {
        this.phaseNumber = value;
    }

    /**
     * Gets the value of the numberOfrequests property.
     * 
     */
    public int getNumberOfrequests() {
        return numberOfrequests;
    }

    /**
     * Sets the value of the numberOfrequests property.
     * 
     */
    public void setNumberOfrequests(int value) {
        this.numberOfrequests = value;
    }

    /**
     * Gets the value of the sendingPeriod property.
     * 
     */
    public int getSendingPeriod() {
        return sendingPeriod;
    }

    /**
     * Sets the value of the sendingPeriod property.
     * 
     */
    public void setSendingPeriod(int value) {
        this.sendingPeriod = value;
    }

    /**
     * Gets the value of the payloadSize property.
     * 
     */
    public int getPayloadSize() {
        return payloadSize;
    }

    /**
     * Sets the value of the payloadSize property.
     * 
     */
    public void setPayloadSize(int value) {
        this.payloadSize = value;
    }

    /**
     * Gets the value of the repetitions property.
     * 
     */
    public int getRepetitions() {
        return repetitions;
    }

    /**
     * Sets the value of the repetitions property.
     * 
     */
    public void setRepetitions(int value) {
        this.repetitions = value;
    }

    /**
     * Gets the value of the startDate property.
     * 
     */
    public long getStartDate() {
        return startDate;
    }

    /**
     * Sets the value of the startDate property.
     * 
     */
    public void setStartDate(long value) {
        this.startDate = value;
    }

}
