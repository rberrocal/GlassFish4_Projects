package pe.com.comercio.eai.services.gestionsuscripcionws.ws.dao;

import java.util.ArrayList;

import pe.com.comercio.eai.services.gestionsuscripcionws.ws.bean.ConsultaSuscripcionBean;
import pe.com.comercio.eai.services.gestionsuscripcionws.ws.bean.SuscripcionBean;
import pe.com.comercio.eai.services.gestionsuscripcionws.ws.types.ActualizarSuscripcionRequest;
import pe.com.comercio.eai.services.gestionsuscripcionws.ws.types.RegistrarSuscripcionRequest;

public interface SuscripcionDAO {
	
	public int invocaSP_insertaSuscripcion(String msgTxId, RegistrarSuscripcionRequest request) throws Exception;

	public ConsultaSuscripcionBean invocaSP_listaSuscripcion(String msgTxId, String idSuscripcion,
			String nombreSuscriptor, String fechaIni, String fechaFin) throws Exception; 
	
	public int invocaSP_eliminaSuscripcion(String msgTxId, int idSuscripcion) throws Exception;
	
	public int invocaSP_actualizaSuscripcion(String msgTxId, ActualizarSuscripcionRequest request) throws Exception;

}
