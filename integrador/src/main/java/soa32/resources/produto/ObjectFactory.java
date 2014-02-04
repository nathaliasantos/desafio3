
package soa32.resources.produto;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the soa32.resources.produto package. 
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

    private final static QName _Get_QNAME = new QName("http://resource.faturamento.soa32/", "get");
    private final static QName _Produto_QNAME = new QName("http://resource.faturamento.soa32/", "produto");
    private final static QName _List_QNAME = new QName("http://resource.faturamento.soa32/", "list");
    private final static QName _GetResponse_QNAME = new QName("http://resource.faturamento.soa32/", "getResponse");
    private final static QName _BaseEntity_QNAME = new QName("http://resource.faturamento.soa32/", "baseEntity");
    private final static QName _ListResponse_QNAME = new QName("http://resource.faturamento.soa32/", "listResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: soa32.resources.produto
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Produto }
     * 
     */
    public Produto createProduto() {
        return new Produto();
    }

    /**
     * Create an instance of {@link Get }
     * 
     */
    public Get createGet() {
        return new Get();
    }

    /**
     * Create an instance of {@link ListResponse }
     * 
     */
    public ListResponse createListResponse() {
        return new ListResponse();
    }

    /**
     * Create an instance of {@link List }
     * 
     */
    public List createList() {
        return new List();
    }

    /**
     * Create an instance of {@link GetResponse }
     * 
     */
    public GetResponse createGetResponse() {
        return new GetResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Get }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://resource.faturamento.soa32/", name = "get")
    public JAXBElement<Get> createGet(Get value) {
        return new JAXBElement<Get>(_Get_QNAME, Get.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Produto }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://resource.faturamento.soa32/", name = "produto")
    public JAXBElement<Produto> createProduto(Produto value) {
        return new JAXBElement<Produto>(_Produto_QNAME, Produto.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link List }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://resource.faturamento.soa32/", name = "list")
    public JAXBElement<List> createList(List value) {
        return new JAXBElement<List>(_List_QNAME, List.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://resource.faturamento.soa32/", name = "getResponse")
    public JAXBElement<GetResponse> createGetResponse(GetResponse value) {
        return new JAXBElement<GetResponse>(_GetResponse_QNAME, GetResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BaseEntity }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://resource.faturamento.soa32/", name = "baseEntity")
    public JAXBElement<BaseEntity> createBaseEntity(BaseEntity value) {
        return new JAXBElement<BaseEntity>(_BaseEntity_QNAME, BaseEntity.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://resource.faturamento.soa32/", name = "listResponse")
    public JAXBElement<ListResponse> createListResponse(ListResponse value) {
        return new JAXBElement<ListResponse>(_ListResponse_QNAME, ListResponse.class, null, value);
    }

}
