
package soa32.resources.notaFiscal;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de notaFiscal complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="notaFiscal">
 *   &lt;complexContent>
 *     &lt;extension base="{http://resource.faturamento.soa32/}baseEntity">
 *       &lt;sequence>
 *         &lt;element name="itens" type="{http://resource.faturamento.soa32/}item" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="numero" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="pedido" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="status" type="{http://resource.faturamento.soa32/}status" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "notaFiscal", propOrder = {
    "itens",
    "numero",
    "pedido",
    "status"
})
public class NotaFiscal
    extends BaseEntity
{

    @XmlElement(nillable = true)
    protected List<Item> itens;
    protected Long numero;
    protected Long pedido;
    protected Status status;

    /**
     * Gets the value of the itens property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the itens property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getItens().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Item }
     * 
     * 
     */
    public List<Item> getItens() {
        if (itens == null) {
            itens = new ArrayList<Item>();
        }
        return this.itens;
    }

    /**
     * Obtém o valor da propriedade numero.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getNumero() {
        return numero;
    }

    /**
     * Define o valor da propriedade numero.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setNumero(Long value) {
        this.numero = value;
    }

    /**
     * Obtém o valor da propriedade pedido.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getPedido() {
        return pedido;
    }

    /**
     * Define o valor da propriedade pedido.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setPedido(Long value) {
        this.pedido = value;
    }

    /**
     * Obtém o valor da propriedade status.
     * 
     * @return
     *     possible object is
     *     {@link Status }
     *     
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Define o valor da propriedade status.
     * 
     * @param value
     *     allowed object is
     *     {@link Status }
     *     
     */
    public void setStatus(Status value) {
        this.status = value;
    }

}
