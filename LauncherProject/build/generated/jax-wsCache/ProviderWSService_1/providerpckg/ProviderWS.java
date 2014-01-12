
package providerpckg;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.4-b01-
 * Generated source version: 2.1
 * 
 */
@WebService(name = "ProviderWS", targetNamespace = "http://providerPckg/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface ProviderWS {


    /**
     * 
     * @param arg2
     * @param arg1
     * @param arg0
     * @return
     *     returns java.lang.String
     * @throws InterruptedException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "operation", targetNamespace = "http://providerPckg/", className = "providerpckg.Operation")
    @ResponseWrapper(localName = "operationResponse", targetNamespace = "http://providerPckg/", className = "providerpckg.OperationResponse")
    public String operation(
        @WebParam(name = "arg0", targetNamespace = "")
        long arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        int arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        String arg2)
        throws InterruptedException_Exception
    ;

    /**
     * 
     * @return
     *     returns int
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "ping", targetNamespace = "http://providerPckg/", className = "providerpckg.Ping")
    @ResponseWrapper(localName = "pingResponse", targetNamespace = "http://providerPckg/", className = "providerpckg.PingResponse")
    public int ping();

    /**
     * 
     */
    @WebMethod
    @RequestWrapper(localName = "startConf", targetNamespace = "http://providerPckg/", className = "providerpckg.StartConf")
    @ResponseWrapper(localName = "startConfResponse", targetNamespace = "http://providerPckg/", className = "providerpckg.StartConfResponse")
    public void startConf();

    /**
     * 
     * @return
     *     returns int
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "endConf", targetNamespace = "http://providerPckg/", className = "providerpckg.EndConf")
    @ResponseWrapper(localName = "endConfResponse", targetNamespace = "http://providerPckg/", className = "providerpckg.EndConfResponse")
    public int endConf();

    /**
     * 
     * @param packetLoss
     * @param tailleReponse
     * @param tempsTraitement
     * @param confNumber
     * @return
     *     returns int
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "addConf", targetNamespace = "http://providerPckg/", className = "providerpckg.AddConf")
    @ResponseWrapper(localName = "addConfResponse", targetNamespace = "http://providerPckg/", className = "providerpckg.AddConfResponse")
    public int addConf(
        @WebParam(name = "confNumber", targetNamespace = "")
        int confNumber,
        @WebParam(name = "tempsTraitement", targetNamespace = "")
        long tempsTraitement,
        @WebParam(name = "tailleReponse", targetNamespace = "")
        long tailleReponse,
        @WebParam(name = "packetLoss", targetNamespace = "")
        int packetLoss);

}
