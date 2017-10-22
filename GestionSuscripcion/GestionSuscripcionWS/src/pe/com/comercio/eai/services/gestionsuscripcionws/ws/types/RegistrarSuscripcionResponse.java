
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
 *         &lt;element name="auditResponse" type="{http://comercio.com.pe/eai/services/GestionSuscripcionWS/ws/types}parametrosAuditResponse"/>
 *         &lt;element name="listaResponseOpcional" type="{http://comercio.com.pe/eai/services/GestionSuscripcionWS/ws/types}parametrosResponse"/>
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
    "auditResponse",
    "listaResponseOpcional"
})
@XmlRootElement(name = "registrarSuscripcionResponse")
public class RegistrarSuscripcionResponse {

    @XmlElement(required = true)
    protected ParametrosAuditResponse auditResponse;
    @XmlElement(required = true)
    protected ParametrosResponse listaResponseOpcional;

    /**
     * Obtiene el valor de la propiedad auditResponse.
     * 
     * @return
     *     possible object is
     *     {@link ParametrosAuditResponse }
     *     
     */
    public ParametrosAuditResponse getAuditResponse() {
        return auditResponse;
    }

    /**
     * Define el valor de la propiedad auditResponse.
     * 
     * @param value
     *     allowed object is
     *     {@link ParametrosAuditResponse }
     *     
     */
    public void setAuditResponse(ParametrosAuditResponse value) {
        this.auditResponse = value;
    }

    /**
     * Obtiene el valor de la propiedad listaResponseOpcional.
     * 
     * @return
     *     possible object is
     *     {@link ParametrosResponse }
     *     
     */
    public ParametrosResponse getListaResponseOpcional() {
        return listaResponseOpcional;
    }

    /**
     * Define el valor de la propiedad listaResponseOpcional.
     * 
     * @param value
     *     allowed object is
     *     {@link ParametrosResponse }
     *     
     */
    public void setListaResponseOpcional(ParametrosResponse value) {
        this.listaResponseOpcional = value;
    }

}
