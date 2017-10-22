
package pe.com.comercio.eai.services.gestionsuscripcionws.ws.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para SuscripcionType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="SuscripcionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="IdSuscripcion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="NombreSuscriptor" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="TipoSuscripcion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Precio" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="FecRegistro" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SuscripcionType", propOrder = {
    "idSuscripcion",
    "nombreSuscriptor",
    "tipoSuscripcion",
    "precio",
    "fecRegistro"
})
public class SuscripcionType {

    @XmlElement(name = "IdSuscripcion", required = true)
    protected String idSuscripcion;
    @XmlElement(name = "NombreSuscriptor", required = true)
    protected String nombreSuscriptor;
    @XmlElement(name = "TipoSuscripcion", required = true)
    protected String tipoSuscripcion;
    @XmlElement(name = "Precio", required = true)
    protected String precio;
    @XmlElement(name = "FecRegistro", required = true)
    protected String fecRegistro;

    /**
     * Obtiene el valor de la propiedad idSuscripcion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdSuscripcion() {
        return idSuscripcion;
    }

    /**
     * Define el valor de la propiedad idSuscripcion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdSuscripcion(String value) {
        this.idSuscripcion = value;
    }

    /**
     * Obtiene el valor de la propiedad nombreSuscriptor.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreSuscriptor() {
        return nombreSuscriptor;
    }

    /**
     * Define el valor de la propiedad nombreSuscriptor.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreSuscriptor(String value) {
        this.nombreSuscriptor = value;
    }

    /**
     * Obtiene el valor de la propiedad tipoSuscripcion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoSuscripcion() {
        return tipoSuscripcion;
    }

    /**
     * Define el valor de la propiedad tipoSuscripcion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoSuscripcion(String value) {
        this.tipoSuscripcion = value;
    }

    /**
     * Obtiene el valor de la propiedad precio.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrecio() {
        return precio;
    }

    /**
     * Define el valor de la propiedad precio.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrecio(String value) {
        this.precio = value;
    }

    /**
     * Obtiene el valor de la propiedad fecRegistro.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFecRegistro() {
        return fecRegistro;
    }

    /**
     * Define el valor de la propiedad fecRegistro.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFecRegistro(String value) {
        this.fecRegistro = value;
    }

}
