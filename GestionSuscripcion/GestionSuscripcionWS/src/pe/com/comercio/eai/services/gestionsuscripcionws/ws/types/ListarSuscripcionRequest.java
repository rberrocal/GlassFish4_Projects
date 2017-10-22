
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
 *         &lt;element name="fechaIni" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fechaFin" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "fechaIni",
    "fechaFin",
    "listaRequestOpcional"
})
@XmlRootElement(name = "listarSuscripcionRequest")
public class ListarSuscripcionRequest {

    @XmlElement(required = true)
    protected ParametrosAuditRequest auditRequest;
    @XmlElement(name = "IdSuscripcion", required = true)
    protected String idSuscripcion;
    @XmlElement(name = "NombreSuscriptor", required = true)
    protected String nombreSuscriptor;
    @XmlElement(required = true)
    protected String fechaIni;
    @XmlElement(required = true)
    protected String fechaFin;
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
     * Obtiene el valor de la propiedad fechaIni.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaIni() {
        return fechaIni;
    }

    /**
     * Define el valor de la propiedad fechaIni.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaIni(String value) {
        this.fechaIni = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaFin.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaFin() {
        return fechaFin;
    }

    /**
     * Define el valor de la propiedad fechaFin.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaFin(String value) {
        this.fechaFin = value;
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
