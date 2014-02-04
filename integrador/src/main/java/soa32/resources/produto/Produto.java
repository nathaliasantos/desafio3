
package soa32.resources.produto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de produto complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="produto">
 *   &lt;complexContent>
 *     &lt;extension base="{http://resource.faturamento.soa32/}baseEntity">
 *       &lt;sequence>
 *         &lt;element name="dataValidade" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="departamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fabricante" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="itemExclusivo" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="nome" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="precoDeCusto" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="tamanho" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="urlImage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "produto", propOrder = {
    "dataValidade",
    "departamento",
    "fabricante",
    "itemExclusivo",
    "nome",
    "precoDeCusto",
    "tamanho",
    "urlImage"
})
public class Produto
    extends BaseEntity
{

    protected String dataValidade;
    protected String departamento;
    protected String fabricante;
    protected Boolean itemExclusivo;
    protected String nome;
    protected long precoDeCusto;
    protected String tamanho;
    protected String urlImage;

    /**
     * Obtém o valor da propriedade dataValidade.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataValidade() {
        return dataValidade;
    }

    /**
     * Define o valor da propriedade dataValidade.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataValidade(String value) {
        this.dataValidade = value;
    }

    /**
     * Obtém o valor da propriedade departamento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDepartamento() {
        return departamento;
    }

    /**
     * Define o valor da propriedade departamento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDepartamento(String value) {
        this.departamento = value;
    }

    /**
     * Obtém o valor da propriedade fabricante.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFabricante() {
        return fabricante;
    }

    /**
     * Define o valor da propriedade fabricante.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFabricante(String value) {
        this.fabricante = value;
    }

    /**
     * Obtém o valor da propriedade itemExclusivo.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isItemExclusivo() {
        return itemExclusivo;
    }

    /**
     * Define o valor da propriedade itemExclusivo.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setItemExclusivo(Boolean value) {
        this.itemExclusivo = value;
    }

    /**
     * Obtém o valor da propriedade nome.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o valor da propriedade nome.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNome(String value) {
        this.nome = value;
    }

    /**
     * Obtém o valor da propriedade precoDeCusto.
     * 
     */
    public long getPrecoDeCusto() {
        return precoDeCusto;
    }

    /**
     * Define o valor da propriedade precoDeCusto.
     * 
     */
    public void setPrecoDeCusto(long value) {
        this.precoDeCusto = value;
    }

    /**
     * Obtém o valor da propriedade tamanho.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTamanho() {
        return tamanho;
    }

    /**
     * Define o valor da propriedade tamanho.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTamanho(String value) {
        this.tamanho = value;
    }

    /**
     * Obtém o valor da propriedade urlImage.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUrlImage() {
        return urlImage;
    }

    /**
     * Define o valor da propriedade urlImage.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUrlImage(String value) {
        this.urlImage = value;
    }

}
