package pe.com.comercio.eai.services.gestionsuscripcionws.ws.bean;

import java.io.Serializable;
import java.util.List;


public class ConsultaSuscripcionBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<SuscripcionBean> listaSuscripciones;
	private String				  codRpta;
	private String				  MsjRpta;
	
	public List<SuscripcionBean> getListaSuscripciones() {
		return listaSuscripciones;
	}
	public void setListaSuscripciones(List<SuscripcionBean> listaSuscripciones) {
		this.listaSuscripciones = listaSuscripciones;
	}
	public String getCodRpta() {
		return codRpta;
	}
	public void setCodRpta(String codRpta) {
		this.codRpta = codRpta;
	}
	public String getMsjRpta() {
		return MsjRpta;
	}
	public void setMsjRpta(String msjRpta) {
		MsjRpta = msjRpta;
	}
	
	

}
