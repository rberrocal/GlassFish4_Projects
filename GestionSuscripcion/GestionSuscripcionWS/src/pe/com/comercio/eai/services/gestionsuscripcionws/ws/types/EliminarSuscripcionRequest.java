
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
    "idSuscripcion"
})
@XmlRootElement(name = "eliminarSuscripcionRequest")
public class EliminarSuscripcionRequest {

    @XmlElement(required = true)
    protected ParametrosAuditRequest auditRequest;
    @XmlElement(name = "IdSuscripcion", required = true)
    protected String idSuscripcion;

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

}
