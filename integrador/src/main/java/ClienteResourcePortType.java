package soa32.faturamento.resource;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.7.8
 * 2014-02-04T09:52:47.942-02:00
 * Generated source version: 2.7.8
 * 
 */
@WebService(targetNamespace = "http://resource.faturamento.soa32/", name = "ClienteResourcePortType")
@XmlSeeAlso({ObjectFactory.class})
public interface ClienteResourcePortType {

    @RequestWrapper(localName = "deleteAll", targetNamespace = "http://resource.faturamento.soa32/", className = "soa32.faturamento.resource.DeleteAll")
    @WebMethod
    @ResponseWrapper(localName = "deleteAllResponse", targetNamespace = "http://resource.faturamento.soa32/", className = "soa32.faturamento.resource.DeleteAllResponse")
    public void deleteAll(
        @WebParam(name = "ids", targetNamespace = "")
        java.util.List<java.lang.Long> ids
    );

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "get", targetNamespace = "http://resource.faturamento.soa32/", className = "soa32.faturamento.resource.Get")
    @WebMethod
    @ResponseWrapper(localName = "getResponse", targetNamespace = "http://resource.faturamento.soa32/", className = "soa32.faturamento.resource.GetResponse")
    public soa32.faturamento.resource.Cliente get(
        @WebParam(name = "id", targetNamespace = "")
        java.lang.Long id
    );

    @RequestWrapper(localName = "delete", targetNamespace = "http://resource.faturamento.soa32/", className = "soa32.faturamento.resource.Delete")
    @WebMethod
    @ResponseWrapper(localName = "deleteResponse", targetNamespace = "http://resource.faturamento.soa32/", className = "soa32.faturamento.resource.DeleteResponse")
    public void delete(
        @WebParam(name = "id", targetNamespace = "")
        java.lang.Long id
    );

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "update", targetNamespace = "http://resource.faturamento.soa32/", className = "soa32.faturamento.resource.Update")
    @WebMethod
    @ResponseWrapper(localName = "updateResponse", targetNamespace = "http://resource.faturamento.soa32/", className = "soa32.faturamento.resource.UpdateResponse")
    public soa32.faturamento.resource.Cliente update(
        @WebParam(name = "id", targetNamespace = "")
        java.lang.Long id,
        @WebParam(name = "entity", targetNamespace = "")
        soa32.faturamento.resource.Cliente entity
    );

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "list", targetNamespace = "http://resource.faturamento.soa32/", className = "soa32.faturamento.resource.List")
    @WebMethod
    @ResponseWrapper(localName = "listResponse", targetNamespace = "http://resource.faturamento.soa32/", className = "soa32.faturamento.resource.ListResponse")
    public java.util.List<soa32.faturamento.resource.Cliente> list();

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "create", targetNamespace = "http://resource.faturamento.soa32/", className = "soa32.faturamento.resource.Create")
    @WebMethod
    @ResponseWrapper(localName = "createResponse", targetNamespace = "http://resource.faturamento.soa32/", className = "soa32.faturamento.resource.CreateResponse")
    public java.lang.Long create(
        @WebParam(name = "entity", targetNamespace = "")
        soa32.faturamento.resource.Cliente entity
    );
}
