
package providerpckg;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for addConf complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="addConf">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="confNumber" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="tempsTraitement" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="tailleReponse" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="packetLoss" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "addConf", propOrder = {
    "confNumber",
    "tempsTraitement",
    "tailleReponse",
    "packetLoss"
})
public class AddConf {

    protected int confNumber;
    protected long tempsTraitement;
    protected long tailleReponse;
    protected int packetLoss;

    /**
     * Gets the value of the confNumber property.
     * 
     */
    public int getConfNumber() {
        return confNumber;
    }

    /**
     * Sets the value of the confNumber property.
     * 
     */
    public void setConfNumber(int value) {
        this.confNumber = value;
    }

    /**
     * Gets the value of the tempsTraitement property.
     * 
     */
    public long getTempsTraitement() {
        return tempsTraitement;
    }

    /**
     * Sets the value of the tempsTraitement property.
     * 
     */
    public void setTempsTraitement(long value) {
        this.tempsTraitement = value;
    }

    /**
     * Gets the value of the tailleReponse property.
     * 
     */
    public long getTailleReponse() {
        return tailleReponse;
    }

    /**
     * Sets the value of the tailleReponse property.
     * 
     */
    public void setTailleReponse(long value) {
        this.tailleReponse = value;
    }

    /**
     * Gets the value of the packetLoss property.
     * 
     */
    public int getPacketLoss() {
        return packetLoss;
    }

    /**
     * Sets the value of the packetLoss property.
     * 
     */
    public void setPacketLoss(int value) {
        this.packetLoss = value;
    }

}
