
package soa32.resources.notaFiscal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de create complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="create">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="entity" type="{http://resource.faturamento.soa32/}notaFiscal" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "create", propOrder = {
    "entity"
})
public class Create {

    protected NotaFiscal entity;

    /**
     * Obtém o valor da propriedade entity.
     * 
     * @return
     *     possible object is
     *     {@link NotaFiscal }
     *     
     */
    public NotaFiscal getEntity() {
        return entity;
    }

    /**
     * Define o valor da propriedade entity.
     * 
     * @param value
     *     allowed object is
     *     {@link NotaFiscal }
     *     
     */
    public void setEntity(NotaFiscal value) {
        this.entity = value;
    }

}
