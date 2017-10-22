package pe.com.comercio.eai.services.gestionsuscripcionws.ws.bean;

import java.util.Date;

public class SuscripcionBean {
	private int 	idSuscripcion;
	private String 	nombreSuscriptor;
	private String 	tipoSuscripcion;
	private double 	precio;
	private Date 	fecRegistro;
	
	@Override
	public String toString() {
		return "SuscripcionBean [idSuscripcion=" + idSuscripcion + ", nombreSuscriptor=" + nombreSuscriptor
				+ ", tipoSuscripcion=" + tipoSuscripcion + ", precio=" + precio + ", fecRegistro=" + fecRegistro + "]";
	}

	public int getIdSuscripcion() {
		return idSuscripcion;
	}

	public void setIdSuscripcion(int idSuscripcion) {
		this.idSuscripcion = idSuscripcion;
	}

	public String getNombreSuscriptor() {
		return nombreSuscriptor;
	}

	public void setNombreSuscriptor(String nombreSuscriptor) {
		this.nombreSuscriptor = nombreSuscriptor;
	}

	public String getTipoSuscripcion() {
		return tipoSuscripcion;
	}

	public void setTipoSuscripcion(String tipoSuscripcion) {
		this.tipoSuscripcion = tipoSuscripcion;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public Date getFecRegistro() {
		return fecRegistro;
	}

	public void setFecRegistro(Date fecRegistro) {
		this.fecRegistro = fecRegistro;
	}
	
	
}
