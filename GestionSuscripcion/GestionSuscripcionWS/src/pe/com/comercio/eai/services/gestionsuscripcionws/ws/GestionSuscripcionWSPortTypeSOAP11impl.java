package pe.com.comercio.eai.services.gestionsuscripcionws.ws;

import javax.annotation.PostConstruct;
import javax.jws.WebService;
import javax.xml.ws.BindingType;
import javax.xml.ws.soap.SOAPBinding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import pe.com.comercio.eai.services.gestionsuscripcionws.ws.services.GestionSuscripcionService;
import pe.com.comercio.eai.services.gestionsuscripcionws.ws.types.ActualizarSuscripcionRequest;
import pe.com.comercio.eai.services.gestionsuscripcionws.ws.types.ActualizarSuscripcionResponse;
import pe.com.comercio.eai.services.gestionsuscripcionws.ws.types.EliminarSuscripcionRequest;
import pe.com.comercio.eai.services.gestionsuscripcionws.ws.types.EliminarSuscripcionResponse;
import pe.com.comercio.eai.services.gestionsuscripcionws.ws.types.ListarSuscripcionRequest;
import pe.com.comercio.eai.services.gestionsuscripcionws.ws.types.ListarSuscripcionResponse;
import pe.com.comercio.eai.services.gestionsuscripcionws.ws.types.RegistrarSuscripcionRequest;
import pe.com.comercio.eai.services.gestionsuscripcionws.ws.types.RegistrarSuscripcionResponse;


@WebService( endpointInterface = "pe.com.comercio.eai.services.gestionsuscripcionws.ws.GestionSuscripcionWSPortType" )
@BindingType( value = SOAPBinding.SOAP11HTTP_BINDING )
public class GestionSuscripcionWSPortTypeSOAP11impl implements GestionSuscripcionWSPortType{

	@Autowired
	private GestionSuscripcionService gestionSuscripcion;
	
	// --------------------- INYECCION [CLASE WS PRINCIPAL]
	@PostConstruct
	public void init(){
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext( this );
	}
	
	public RegistrarSuscripcionResponse registrarSuscripcion(RegistrarSuscripcionRequest request){
		return gestionSuscripcion.registrarSuscripcion( request );
	}
	
	public ListarSuscripcionResponse listarSuscripcion(ListarSuscripcionRequest request) {
		return gestionSuscripcion.listarSuscripcion( request );
	}

	@Override
	public EliminarSuscripcionResponse eliminarSuscripcion(EliminarSuscripcionRequest request) {
		return gestionSuscripcion.eliminarSuscripcion(request);
	}

	@Override
	public ActualizarSuscripcionResponse actualizarSuscripcion(ActualizarSuscripcionRequest request) {
		return gestionSuscripcion.actualizarSuscripcion(request);
	}
	
}
