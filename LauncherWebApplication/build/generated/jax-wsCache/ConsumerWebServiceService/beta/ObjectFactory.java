
package beta;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the beta package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ConfigurePhaseResponse_QNAME = new QName("http://beta/", "configurePhaseResponse");
    private final static QName _EndConfiguration_QNAME = new QName("http://beta/", "endConfiguration");
    private final static QName _StartScenario_QNAME = new QName("http://beta/", "startScenario");
    private final static QName _EndConfigurationResponse_QNAME = new QName("http://beta/", "endConfigurationResponse");
    private final static QName _StartConfigurationResponse_QNAME = new QName("http://beta/", "startConfigurationResponse");
    private final static QName _ConfigurePhase_QNAME = new QName("http://beta/", "configurePhase");
    private final static QName _GetResultsResponse_QNAME = new QName("http://beta/", "getResultsResponse");
    private final static QName _StartConfiguration_QNAME = new QName("http://beta/", "startConfiguration");
    private final static QName _GetResults_QNAME = new QName("http://beta/", "getResults");
    private final static QName _PingResponse_QNAME = new QName("http://beta/", "pingResponse");
    private final static QName _Ping_QNAME = new QName("http://beta/", "ping");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: beta
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link StartConfigurationResponse }
     * 
     */
    public StartConfigurationResponse createStartConfigurationResponse() {
        return new StartConfigurationResponse();
    }

    /**
     * Create an instance of {@link PingResponse }
     * 
     */
    public PingResponse createPingResponse() {
        return new PingResponse();
    }

    /**
     * Create an instance of {@link EndConfigurationResponse }
     * 
     */
    public EndConfigurationResponse createEndConfigurationResponse() {
        return new EndConfigurationResponse();
    }

    /**
     * Create an instance of {@link GetResultsResponse }
     * 
     */
    public GetResultsResponse createGetResultsResponse() {
        return new GetResultsResponse();
    }

    /**
     * Create an instance of {@link StartScenario }
     * 
     */
    public StartScenario createStartScenario() {
        return new StartScenario();
    }

    /**
     * Create an instance of {@link StartConfiguration }
     * 
     */
    public StartConfiguration createStartConfiguration() {
        return new StartConfiguration();
    }

    /**
     * Create an instance of {@link EndConfiguration }
     * 
     */
    public EndConfiguration createEndConfiguration() {
        return new EndConfiguration();
    }

    /**
     * Create an instance of {@link GetResults }
     * 
     */
    public GetResults createGetResults() {
        return new GetResults();
    }

    /**
     * Create an instance of {@link ConfigurePhaseResponse }
     * 
     */
    public ConfigurePhaseResponse createConfigurePhaseResponse() {
        return new ConfigurePhaseResponse();
    }

    /**
     * Create an instance of {@link ConfigurePhase }
     * 
     */
    public ConfigurePhase createConfigurePhase() {
        return new ConfigurePhase();
    }

    /**
     * Create an instance of {@link Ping }
     * 
     */
    public Ping createPing() {
        return new Ping();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConfigurePhaseResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://beta/", name = "configurePhaseResponse")
    public JAXBElement<ConfigurePhaseResponse> createConfigurePhaseResponse(ConfigurePhaseResponse value) {
        return new JAXBElement<ConfigurePhaseResponse>(_ConfigurePhaseResponse_QNAME, ConfigurePhaseResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EndConfiguration }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://beta/", name = "endConfiguration")
    public JAXBElement<EndConfiguration> createEndConfiguration(EndConfiguration value) {
        return new JAXBElement<EndConfiguration>(_EndConfiguration_QNAME, EndConfiguration.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StartScenario }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://beta/", name = "startScenario")
    public JAXBElement<StartScenario> createStartScenario(StartScenario value) {
        return new JAXBElement<StartScenario>(_StartScenario_QNAME, StartScenario.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EndConfigurationResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://beta/", name = "endConfigurationResponse")
    public JAXBElement<EndConfigurationResponse> createEndConfigurationResponse(EndConfigurationResponse value) {
        return new JAXBElement<EndConfigurationResponse>(_EndConfigurationResponse_QNAME, EndConfigurationResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StartConfigurationResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://beta/", name = "startConfigurationResponse")
    public JAXBElement<StartConfigurationResponse> createStartConfigurationResponse(StartConfigurationResponse value) {
        return new JAXBElement<StartConfigurationResponse>(_StartConfigurationResponse_QNAME, StartConfigurationResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConfigurePhase }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://beta/", name = "configurePhase")
    public JAXBElement<ConfigurePhase> createConfigurePhase(ConfigurePhase value) {
        return new JAXBElement<ConfigurePhase>(_ConfigurePhase_QNAME, ConfigurePhase.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetResultsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://beta/", name = "getResultsResponse")
    public JAXBElement<GetResultsResponse> createGetResultsResponse(GetResultsResponse value) {
        return new JAXBElement<GetResultsResponse>(_GetResultsResponse_QNAME, GetResultsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StartConfiguration }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://beta/", name = "startConfiguration")
    public JAXBElement<StartConfiguration> createStartConfiguration(StartConfiguration value) {
        return new JAXBElement<StartConfiguration>(_StartConfiguration_QNAME, StartConfiguration.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetResults }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://beta/", name = "getResults")
    public JAXBElement<GetResults> createGetResults(GetResults value) {
        return new JAXBElement<GetResults>(_GetResults_QNAME, GetResults.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PingResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://beta/", name = "pingResponse")
    public JAXBElement<PingResponse> createPingResponse(PingResponse value) {
        return new JAXBElement<PingResponse>(_PingResponse_QNAME, PingResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Ping }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://beta/", name = "ping")
    public JAXBElement<Ping> createPing(Ping value) {
        return new JAXBElement<Ping>(_Ping_QNAME, Ping.class, null, value);
    }

}
