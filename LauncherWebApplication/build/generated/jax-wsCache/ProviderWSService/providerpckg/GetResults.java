
package providerpckg;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getResults complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getResults">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="isFirst" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getResults", propOrder = {
    "isFirst"
})
public class GetResults {

    protected boolean isFirst;

    /**
     * Gets the value of the isFirst property.
     * 
     */
    public boolean isIsFirst() {
        return isFirst;
    }

    /**
     * Sets the value of the isFirst property.
     * 
     */
    public void setIsFirst(boolean value) {
        this.isFirst = value;
    }

}
