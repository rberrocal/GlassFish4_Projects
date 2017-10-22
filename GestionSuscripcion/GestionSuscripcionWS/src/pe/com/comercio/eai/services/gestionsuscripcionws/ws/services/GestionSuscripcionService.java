package pe.com.comercio.eai.services.gestionsuscripcionws.ws.services;

import pe.com.comercio.eai.services.gestionsuscripcionws.ws.types.ActualizarSuscripcionRequest;
import pe.com.comercio.eai.services.gestionsuscripcionws.ws.types.ActualizarSuscripcionResponse;
import pe.com.comercio.eai.services.gestionsuscripcionws.ws.types.EliminarSuscripcionRequest;
import pe.com.comercio.eai.services.gestionsuscripcionws.ws.types.EliminarSuscripcionResponse;
import pe.com.comercio.eai.services.gestionsuscripcionws.ws.types.ListarSuscripcionRequest;
import pe.com.comercio.eai.services.gestionsuscripcionws.ws.types.ListarSuscripcionResponse;
import pe.com.comercio.eai.services.gestionsuscripcionws.ws.types.RegistrarSuscripcionRequest;
import pe.com.comercio.eai.services.gestionsuscripcionws.ws.types.RegistrarSuscripcionResponse;

public interface GestionSuscripcionService {

	RegistrarSuscripcionResponse registrarSuscripcion(RegistrarSuscripcionRequest request);
	ListarSuscripcionResponse listarSuscripcion(ListarSuscripcionRequest request);
	EliminarSuscripcionResponse eliminarSuscripcion(EliminarSuscripcionRequest request);
	ActualizarSuscripcionResponse actualizarSuscripcion(ActualizarSuscripcionRequest request);
}
