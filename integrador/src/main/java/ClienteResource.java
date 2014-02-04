package soa32.faturamento.resource;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 2.7.8
 * 2014-02-04T09:52:47.988-02:00
 * Generated source version: 2.7.8
 * 
 */
@WebServiceClient(name = "ClienteResource", 
                  wsdlLocation = "http://172.16.129.98:8181/faturamento/api/clientes?wsdl",
                  targetNamespace = "http://resource.faturamento.soa32/") 
public class ClienteResource extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://resource.faturamento.soa32/", "ClienteResource");
    public final static QName ClienteResourcePort = new QName("http://resource.faturamento.soa32/", "ClienteResourcePort");
    static {
        URL url = null;
        try {
            url = new URL("http://172.16.129.98:8181/faturamento/api/clientes?wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(ClienteResource.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "http://172.16.129.98:8181/faturamento/api/clientes?wsdl");
        }
        WSDL_LOCATION = url;
    }

    public ClienteResource(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public ClienteResource(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public ClienteResource() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public ClienteResource(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public ClienteResource(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public ClienteResource(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     *
     * @return
     *     returns ClienteResourcePortType
     */
    @WebEndpoint(name = "ClienteResourcePort")
    public ClienteResourcePortType getClienteResourcePort() {
        return super.getPort(ClienteResourcePort, ClienteResourcePortType.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns ClienteResourcePortType
     */
    @WebEndpoint(name = "ClienteResourcePort")
    public ClienteResourcePortType getClienteResourcePort(WebServiceFeature... features) {
        return super.getPort(ClienteResourcePort, ClienteResourcePortType.class, features);
    }

}
