
package pe.com.comercio.eai.services.gestionsuscripcionws.ws.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para anonymous complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="auditRequest" type="{http://comercio.com.pe/eai/services/GestionSuscripcionWS/ws/types}parametrosAuditRequest"/>
 *         &lt;element name="IdSuscripcion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="NombreSuscriptor" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="TipoSuscripcion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Precio" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="FecRegistro" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="listaRequestOpcional" type="{http://comercio.com.pe/eai/services/GestionSuscripcionWS/ws/types}parametrosRequest"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "auditRequest",
    "idSuscripcion",
    "nombreSuscriptor",
    "tipoSuscripcion",
    "precio",
    "fecRegistro",
    "listaRequestOpcional"
})
@XmlRootElement(name = "actualizarSuscripcionRequest")
public class ActualizarSuscripcionRequest {

    @XmlElement(required = true)
    protected ParametrosAuditRequest auditRequest;
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
    @XmlElement(required = true)
    protected ParametrosRequest listaRequestOpcional;

    /**
     * Obtiene el valor de la propiedad auditRequest.
     * 
     * @return
     *     possible object is
     *     {@link ParametrosAuditRequest }
     *     
     */
    public ParametrosAuditRequest getAuditRequest() {
        return auditRequest;
    }

    /**
     * Define el valor de la propiedad auditRequest.
     * 
     * @param value
     *     allowed object is
     *     {@link ParametrosAuditRequest }
     *     
     */
    public void setAuditRequest(ParametrosAuditRequest value) {
        this.auditRequest = value;
    }

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

    /**
     * Obtiene el valor de la propiedad listaRequestOpcional.
     * 
     * @return
     *     possible object is
     *     {@link ParametrosRequest }
     *     
     */
    public ParametrosRequest getListaRequestOpcional() {
        return listaRequestOpcional;
    }

    /**
     * Define el valor de la propiedad listaRequestOpcional.
     * 
     * @param value
     *     allowed object is
     *     {@link ParametrosRequest }
     *     
     */
    public void setListaRequestOpcional(ParametrosRequest value) {
        this.listaRequestOpcional = value;
    }

}
