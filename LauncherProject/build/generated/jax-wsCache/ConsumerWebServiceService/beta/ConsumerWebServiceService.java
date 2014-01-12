
package beta;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.4-b01-
 * Generated source version: 2.1
 * 
 */
@WebServiceClient(name = "ConsumerWebServiceService", targetNamespace = "http://beta/", wsdlLocation = "http://localhost:8080/ConsumerProject/ConsumerWebServiceService?wsdl")
public class ConsumerWebServiceService
    extends Service
{

    private final static URL CONSUMERWEBSERVICESERVICE_WSDL_LOCATION;
    private final static Logger logger = Logger.getLogger(beta.ConsumerWebServiceService.class.getName());

    static {
        URL url = null;
        try {
            URL baseUrl;
            baseUrl = beta.ConsumerWebServiceService.class.getResource(".");
            url = new URL(baseUrl, "http://localhost:8080/ConsumerProject/ConsumerWebServiceService?wsdl");
        } catch (MalformedURLException e) {
            logger.warning("Failed to create URL for the wsdl Location: 'http://localhost:8080/ConsumerProject/ConsumerWebServiceService?wsdl', retrying as a local file");
            logger.warning(e.getMessage());
        }
        CONSUMERWEBSERVICESERVICE_WSDL_LOCATION = url;
    }

    public ConsumerWebServiceService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public ConsumerWebServiceService() {
        super(CONSUMERWEBSERVICESERVICE_WSDL_LOCATION, new QName("http://beta/", "ConsumerWebServiceService"));
    }

    /**
     * 
     * @return
     *     returns ConsumerWebService
     */
    @WebEndpoint(name = "ConsumerWebServicePort")
    public ConsumerWebService getConsumerWebServicePort() {
        return super.getPort(new QName("http://beta/", "ConsumerWebServicePort"), ConsumerWebService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns ConsumerWebService
     */
    @WebEndpoint(name = "ConsumerWebServicePort")
    public ConsumerWebService getConsumerWebServicePort(WebServiceFeature... features) {
        return super.getPort(new QName("http://beta/", "ConsumerWebServicePort"), ConsumerWebService.class, features);
    }

}
