
package providerpckg;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the providerpckg package. 
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

    private final static QName _InterruptedException_QNAME = new QName("http://providerPckg/", "InterruptedException");
    private final static QName _Ping_QNAME = new QName("http://providerPckg/", "ping");
    private final static QName _EndConf_QNAME = new QName("http://providerPckg/", "endConf");
    private final static QName _PingResponse_QNAME = new QName("http://providerPckg/", "pingResponse");
    private final static QName _AddConfResponse_QNAME = new QName("http://providerPckg/", "addConfResponse");
    private final static QName _OperationResponse_QNAME = new QName("http://providerPckg/", "operationResponse");
    private final static QName _EndConfResponse_QNAME = new QName("http://providerPckg/", "endConfResponse");
    private final static QName _AddConf_QNAME = new QName("http://providerPckg/", "addConf");
    private final static QName _StartConf_QNAME = new QName("http://providerPckg/", "startConf");
    private final static QName _StartConfResponse_QNAME = new QName("http://providerPckg/", "startConfResponse");
    private final static QName _Operation_QNAME = new QName("http://providerPckg/", "operation");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: providerpckg
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link InterruptedException }
     * 
     */
    public InterruptedException createInterruptedException() {
        return new InterruptedException();
    }

    /**
     * Create an instance of {@link EndConfResponse }
     * 
     */
    public EndConfResponse createEndConfResponse() {
        return new EndConfResponse();
    }

    /**
     * Create an instance of {@link StartConf }
     * 
     */
    public StartConf createStartConf() {
        return new StartConf();
    }

    /**
     * Create an instance of {@link StartConfResponse }
     * 
     */
    public StartConfResponse createStartConfResponse() {
        return new StartConfResponse();
    }

    /**
     * Create an instance of {@link EndConf }
     * 
     */
    public EndConf createEndConf() {
        return new EndConf();
    }

    /**
     * Create an instance of {@link AddConfResponse }
     * 
     */
    public AddConfResponse createAddConfResponse() {
        return new AddConfResponse();
    }

    /**
     * Create an instance of {@link OperationResponse }
     * 
     */
    public OperationResponse createOperationResponse() {
        return new OperationResponse();
    }

    /**
     * Create an instance of {@link Operation }
     * 
     */
    public Operation createOperation() {
        return new Operation();
    }

    /**
     * Create an instance of {@link PingResponse }
     * 
     */
    public PingResponse createPingResponse() {
        return new PingResponse();
    }

    /**
     * Create an instance of {@link AddConf }
     * 
     */
    public AddConf createAddConf() {
        return new AddConf();
    }

    /**
     * Create an instance of {@link Ping }
     * 
     */
    public Ping createPing() {
        return new Ping();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InterruptedException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://providerPckg/", name = "InterruptedException")
    public JAXBElement<InterruptedException> createInterruptedException(InterruptedException value) {
        return new JAXBElement<InterruptedException>(_InterruptedException_QNAME, InterruptedException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Ping }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://providerPckg/", name = "ping")
    public JAXBElement<Ping> createPing(Ping value) {
        return new JAXBElement<Ping>(_Ping_QNAME, Ping.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EndConf }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://providerPckg/", name = "endConf")
    public JAXBElement<EndConf> createEndConf(EndConf value) {
        return new JAXBElement<EndConf>(_EndConf_QNAME, EndConf.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PingResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://providerPckg/", name = "pingResponse")
    public JAXBElement<PingResponse> createPingResponse(PingResponse value) {
        return new JAXBElement<PingResponse>(_PingResponse_QNAME, PingResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddConfResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://providerPckg/", name = "addConfResponse")
    public JAXBElement<AddConfResponse> createAddConfResponse(AddConfResponse value) {
        return new JAXBElement<AddConfResponse>(_AddConfResponse_QNAME, AddConfResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OperationResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://providerPckg/", name = "operationResponse")
    public JAXBElement<OperationResponse> createOperationResponse(OperationResponse value) {
        return new JAXBElement<OperationResponse>(_OperationResponse_QNAME, OperationResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EndConfResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://providerPckg/", name = "endConfResponse")
    public JAXBElement<EndConfResponse> createEndConfResponse(EndConfResponse value) {
        return new JAXBElement<EndConfResponse>(_EndConfResponse_QNAME, EndConfResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddConf }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://providerPckg/", name = "addConf")
    public JAXBElement<AddConf> createAddConf(AddConf value) {
        return new JAXBElement<AddConf>(_AddConf_QNAME, AddConf.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StartConf }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://providerPckg/", name = "startConf")
    public JAXBElement<StartConf> createStartConf(StartConf value) {
        return new JAXBElement<StartConf>(_StartConf_QNAME, StartConf.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StartConfResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://providerPckg/", name = "startConfResponse")
    public JAXBElement<StartConfResponse> createStartConfResponse(StartConfResponse value) {
        return new JAXBElement<StartConfResponse>(_StartConfResponse_QNAME, StartConfResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Operation }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://providerPckg/", name = "operation")
    public JAXBElement<Operation> createOperation(Operation value) {
        return new JAXBElement<Operation>(_Operation_QNAME, Operation.class, null, value);
    }

}
