
package soa32.resources.notaFiscal;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the soa32.resources.notaFiscal package. 
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

    private final static QName _Item_QNAME = new QName("http://resource.faturamento.soa32/", "item");
    private final static QName _DeleteAll_QNAME = new QName("http://resource.faturamento.soa32/", "deleteAll");
    private final static QName _DeleteResponse_QNAME = new QName("http://resource.faturamento.soa32/", "deleteResponse");
    private final static QName _NotaFiscal_QNAME = new QName("http://resource.faturamento.soa32/", "notaFiscal");
    private final static QName _CreateResponse_QNAME = new QName("http://resource.faturamento.soa32/", "createResponse");
    private final static QName _Delete_QNAME = new QName("http://resource.faturamento.soa32/", "delete");
    private final static QName _DeleteAllResponse_QNAME = new QName("http://resource.faturamento.soa32/", "deleteAllResponse");
    private final static QName _ListResponse_QNAME = new QName("http://resource.faturamento.soa32/", "listResponse");
    private final static QName _Get_QNAME = new QName("http://resource.faturamento.soa32/", "get");
    private final static QName _Create_QNAME = new QName("http://resource.faturamento.soa32/", "create");
    private final static QName _List_QNAME = new QName("http://resource.faturamento.soa32/", "list");
    private final static QName _GetResponse_QNAME = new QName("http://resource.faturamento.soa32/", "getResponse");
    private final static QName _BaseEntity_QNAME = new QName("http://resource.faturamento.soa32/", "baseEntity");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: soa32.resources.notaFiscal
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Get }
     * 
     */
    public Get createGet() {
        return new Get();
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
     * Create an instance of {@link Create }
     * 
     */
    public Create createCreate() {
        return new Create();
    }

    /**
     * Create an instance of {@link DeleteResponse }
     * 
     */
    public DeleteResponse createDeleteResponse() {
        return new DeleteResponse();
    }

    /**
     * Create an instance of {@link DeleteAll }
     * 
     */
    public DeleteAll createDeleteAll() {
        return new DeleteAll();
    }

    /**
     * Create an instance of {@link Item }
     * 
     */
    public Item createItem() {
        return new Item();
    }

    /**
     * Create an instance of {@link CreateResponse }
     * 
     */
    public CreateResponse createCreateResponse() {
        return new CreateResponse();
    }

    /**
     * Create an instance of {@link Delete }
     * 
     */
    public Delete createDelete() {
        return new Delete();
    }

    /**
     * Create an instance of {@link DeleteAllResponse }
     * 
     */
    public DeleteAllResponse createDeleteAllResponse() {
        return new DeleteAllResponse();
    }

    /**
     * Create an instance of {@link ListResponse }
     * 
     */
    public ListResponse createListResponse() {
        return new ListResponse();
    }

    /**
     * Create an instance of {@link NotaFiscal }
     * 
     */
    public NotaFiscal createNotaFiscal() {
        return new NotaFiscal();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Item }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://resource.faturamento.soa32/", name = "item")
    public JAXBElement<Item> createItem(Item value) {
        return new JAXBElement<Item>(_Item_QNAME, Item.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteAll }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://resource.faturamento.soa32/", name = "deleteAll")
    public JAXBElement<DeleteAll> createDeleteAll(DeleteAll value) {
        return new JAXBElement<DeleteAll>(_DeleteAll_QNAME, DeleteAll.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://resource.faturamento.soa32/", name = "deleteResponse")
    public JAXBElement<DeleteResponse> createDeleteResponse(DeleteResponse value) {
        return new JAXBElement<DeleteResponse>(_DeleteResponse_QNAME, DeleteResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NotaFiscal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://resource.faturamento.soa32/", name = "notaFiscal")
    public JAXBElement<NotaFiscal> createNotaFiscal(NotaFiscal value) {
        return new JAXBElement<NotaFiscal>(_NotaFiscal_QNAME, NotaFiscal.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://resource.faturamento.soa32/", name = "createResponse")
    public JAXBElement<CreateResponse> createCreateResponse(CreateResponse value) {
        return new JAXBElement<CreateResponse>(_CreateResponse_QNAME, CreateResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Delete }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://resource.faturamento.soa32/", name = "delete")
    public JAXBElement<Delete> createDelete(Delete value) {
        return new JAXBElement<Delete>(_Delete_QNAME, Delete.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteAllResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://resource.faturamento.soa32/", name = "deleteAllResponse")
    public JAXBElement<DeleteAllResponse> createDeleteAllResponse(DeleteAllResponse value) {
        return new JAXBElement<DeleteAllResponse>(_DeleteAllResponse_QNAME, DeleteAllResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://resource.faturamento.soa32/", name = "listResponse")
    public JAXBElement<ListResponse> createListResponse(ListResponse value) {
        return new JAXBElement<ListResponse>(_ListResponse_QNAME, ListResponse.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link Create }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://resource.faturamento.soa32/", name = "create")
    public JAXBElement<Create> createCreate(Create value) {
        return new JAXBElement<Create>(_Create_QNAME, Create.class, null, value);
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

}
