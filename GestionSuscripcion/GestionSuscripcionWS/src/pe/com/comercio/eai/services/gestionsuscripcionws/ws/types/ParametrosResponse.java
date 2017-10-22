
package pe.com.comercio.eai.services.gestionsuscripcionws.ws.types;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para parametrosResponse complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="parametrosResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="objetoResponseOpcional" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;attribute name="campo" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="valor" type="{http://www.w3.org/2001/XMLSchema}string" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "parametrosResponse", propOrder = {
    "objetoResponseOpcional"
})
public class ParametrosResponse {

    @XmlElement(required = true)
    protected List<ParametrosResponse.ObjetoResponseOpcional> objetoResponseOpcional;

    /**
     * Gets the value of the objetoResponseOpcional property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the objetoResponseOpcional property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getObjetoResponseOpcional().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ParametrosResponse.ObjetoResponseOpcional }
     * 
     * 
     */
    public List<ParametrosResponse.ObjetoResponseOpcional> getObjetoResponseOpcional() {
        if (objetoResponseOpcional == null) {
            objetoResponseOpcional = new ArrayList<ParametrosResponse.ObjetoResponseOpcional>();
        }
        return this.objetoResponseOpcional;
    }


    /**
     * <p>Clase Java para anonymous complex type.
     * 
     * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;attribute name="campo" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="valor" type="{http://www.w3.org/2001/XMLSchema}string" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class ObjetoResponseOpcional {

        @XmlAttribute(name = "campo")
        protected String campo;
        @XmlAttribute(name = "valor")
        protected String valor;

        /**
         * Obtiene el valor de la propiedad campo.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCampo() {
            return campo;
        }

        /**
         * Define el valor de la propiedad campo.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCampo(String value) {
            this.campo = value;
        }

        /**
         * Obtiene el valor de la propiedad valor.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getValor() {
            return valor;
        }

        /**
         * Define el valor de la propiedad valor.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setValor(String value) {
            this.valor = value;
        }

    }

}
