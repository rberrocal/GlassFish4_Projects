package pe.com.comercio.eai.services.gestionsuscripcionws.ws.services;

import java.sql.SQLTimeoutException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.stereotype.Service;

import pe.com.comercio.eai.services.gestionsuscripcionws.ws.bean.ConsultaSuscripcionBean;
import pe.com.comercio.eai.services.gestionsuscripcionws.ws.bean.SuscripcionBean;
import pe.com.comercio.eai.services.gestionsuscripcionws.ws.dao.SuscripcionDAO;
import pe.com.comercio.eai.services.gestionsuscripcionws.ws.exception.DBException;
import pe.com.comercio.eai.services.gestionsuscripcionws.ws.types.ActualizarSuscripcionRequest;
import pe.com.comercio.eai.services.gestionsuscripcionws.ws.types.ActualizarSuscripcionResponse;
import pe.com.comercio.eai.services.gestionsuscripcionws.ws.types.EliminarSuscripcionRequest;
import pe.com.comercio.eai.services.gestionsuscripcionws.ws.types.EliminarSuscripcionResponse;
import pe.com.comercio.eai.services.gestionsuscripcionws.ws.types.ListaSuscripcionesType;
import pe.com.comercio.eai.services.gestionsuscripcionws.ws.types.ListarSuscripcionRequest;
import pe.com.comercio.eai.services.gestionsuscripcionws.ws.types.ListarSuscripcionResponse;
import pe.com.comercio.eai.services.gestionsuscripcionws.ws.types.ParametrosAuditResponse;
import pe.com.comercio.eai.services.gestionsuscripcionws.ws.types.RegistrarSuscripcionRequest;
import pe.com.comercio.eai.services.gestionsuscripcionws.ws.types.RegistrarSuscripcionResponse;
import pe.com.comercio.eai.services.gestionsuscripcionws.ws.types.SuscripcionType;
import pe.com.comercio.eai.services.gestionsuscripcionws.ws.util.JAXBUtilitarios;
import pe.com.comercio.eai.services.gestionsuscripcionws.ws.util.PropertiesExternos;
import pe.com.comercio.eai.services.gestionsuscripcionws.ws.util.PropertiesInternos;

@Service
public class GestionSuscripcionServiceImpl implements GestionSuscripcionService {
	private static final Logger	LOGGER	= Logger.getLogger(GestionSuscripcionServiceImpl.class);
	
	@Autowired
	private PropertiesExternos	propertiesExternos;
	
    @Autowired
    private SuscripcionDAO	suscripcionDS;
	
	Long ini = 0L;
	Long end = 0L;	
	SimpleDateFormat formatter = new SimpleDateFormat(PropertiesInternos.FORMATO_FECHA);
	
	@Override
	public RegistrarSuscripcionResponse registrarSuscripcion(RegistrarSuscripcionRequest request) {

		String idTransaccion = request.getAuditRequest().getIdTransaccion();
		String msgTxId = "[idTransaccion =" + idTransaccion + "][registrarSuscripcion]-";
		
		ini = System.currentTimeMillis();
		end = 0L;
		LOGGER.info( "\n\n\n" + msgTxId + "Parametros de Entrada: \n" + JAXBUtilitarios.anyObjectToXmlText( request ) );

		RegistrarSuscripcionResponse response = new RegistrarSuscripcionResponse();
		ParametrosAuditResponse auditResponse = new ParametrosAuditResponse();
		auditResponse.setIdTransaccion( idTransaccion );
		
		//Como opcional la validacion de los datos de entrada
		
		String idSuscripcion 	= request.getIdSuscripcion().trim();
		String nombreSuscriptor = request.getNombreSuscriptor().trim();
		String tipoSuscripcion  = request.getTipoSuscripcion().trim();
		String precio			= request.getPrecio().trim();
		String fecRegistro		= request.getFecRegistro().trim();
		
		if(idSuscripcion.isEmpty() || nombreSuscriptor.isEmpty() || tipoSuscripcion.isEmpty() || precio.isEmpty() || fecRegistro.isEmpty()){
			LOGGER.info( "No se han llenado todos los campos obligatorios");
			auditResponse.setCodigoRespuesta( propertiesExternos.REGISTRAR_DOC_IDF_1_CODIGO );
	        auditResponse.setMensajeRespuesta( propertiesExternos.REGISTRAR_DOC_IDF_1_MENSAJE );
	        response.setAuditResponse( auditResponse );
	   		
	        LOGGER.info( msgTxId + "Parametros de Salida: \n" + JAXBUtilitarios.anyObjectToXmlText( response ) );
	   		end = System.currentTimeMillis();	
	   		LOGGER.info( msgTxId + "Tiempo que demoro en la ejecucion[" + ( end - ini ) + " ms]" );
	   		LOGGER.info( msgTxId + "=====Fin metodo seguirDocumentoExterno=====" );

			return response;
		}else{
			LOGGER.info( "Se obtuvieron todos los datos con EXITO" );
		}
		
		String invocacionPkgSP = propertiesExternos.PKG_GS_SP_INSERTA_SUSCRIPCION;
		try{
			int estadoEjecucion = suscripcionDS.invocaSP_insertaSuscripcion(msgTxId, request);
			
			if (estadoEjecucion == 0){ //Ejecucion exitosa
	        	//Seteo de datos de auditoria
		        auditResponse.setCodigoRespuesta( propertiesExternos.REGISTRAR_DOC_IDF_0_CODIGO );
		        auditResponse.setMensajeRespuesta( propertiesExternos.REGISTRAR_DOC_IDF_0_MENSAJE );
		        response.setAuditResponse( auditResponse );
			}else{
		        auditResponse.setCodigoRespuesta( propertiesExternos.REGISTRAR_DOC_IDF_2_CODIGO );
		        auditResponse.setMensajeRespuesta( propertiesExternos.REGISTRAR_DOC_IDF_2_MENSAJE );
		        response.setAuditResponse( auditResponse );
			}
				
		
		} catch( DBException e ){
			LOGGER.error( "ERROR de DBException" + e.getMessage());
			String cadenaError = (e.getObjException().getMessage()+ "\n" + e.getStackTrace());
			 			
			if(cadenaError.contains(SQLTimeoutException.class.toString().replace( "class", PropertiesInternos.CADENA_VACIA).trim())){				
				LOGGER.error( "ERROR de TIMEOUT");
				auditResponse.setCodigoRespuesta( propertiesExternos.SUSCRIPCION_IDT_1_CODIGO);
	            auditResponse.setMensajeRespuesta(propertiesExternos.SUSCRIPCION_IDT_1_MENSAJE.
	            		replaceAll( PropertiesInternos.VALOR_REPLACE_1, 
	            					PropertiesInternos.ERROR_TO_BD_REGISTRAR_SUSCRIPCION));
	            
			}else if(cadenaError.contains( CannotGetJdbcConnectionException.class.toString().replace( "class",PropertiesInternos.CADENA_VACIA).trim())){				
				LOGGER.error( "ERROR JdbcConnectionException" );				 
				auditResponse.setCodigoRespuesta(propertiesExternos.SUSCRIPCION_IDT_2_CODIGO);
				auditResponse.setMensajeRespuesta( propertiesExternos.SUSCRIPCION_IDT_2_MENSAJE
						.replaceAll( PropertiesInternos.VALOR_REPLACE_1, invocacionPkgSP)
						.replaceAll( PropertiesInternos.VALOR_REPLACE_5, PropertiesInternos.BD_NO_DISPONIBLE ) );
				
			}else{							
				LOGGER.error( "ERROR SP" );				
				auditResponse.setCodigoRespuesta( propertiesExternos.SUSCRIPCION_IDT_3_CODIGO );
				auditResponse.setMensajeRespuesta( propertiesExternos.SUSCRIPCION_IDT_3_MENSAJE);
	            auditResponse.setMensajeRespuesta( propertiesExternos.SUSCRIPCION_IDT_3_MENSAJE
	            		.replaceAll( PropertiesInternos.VALOR_REPLACE_1, invocacionPkgSP ) 
	            		.replaceAll( PropertiesInternos.VALOR_REPLACE_5, cadenaError));
	
			}			

		}
		catch( Exception e ){
			LOGGER.error( "ERROR de Exception" + e.getMessage() + "\n" + e.getStackTrace() );
			auditResponse.setCodigoRespuesta( propertiesExternos.SUSCRIPCION_IDT_4_CODIGO );
			auditResponse.setMensajeRespuesta( propertiesExternos.SUSCRIPCION_IDT_4_MENSAJE
					.replace( PropertiesInternos.VALOR_REPLACE_1, PropertiesInternos.ERROR_INTERNO + " - " + e.getMessage() )  );

		}
		finally{

			response.setAuditResponse( auditResponse );
			LOGGER.info( msgTxId + "Parametros de Salida: \n" + JAXBUtilitarios.anyObjectToXmlText( response ) );
			end = System.currentTimeMillis();

			LOGGER.info( msgTxId + "Tiempo que demoro en la ejecucion[" + ( end - ini ) + " ms]" );
			LOGGER.info( msgTxId + "=== Fin metodo consultarOpcionesConfigurables ===" );

		}

		return response;
	}

	@Override
	public ListarSuscripcionResponse listarSuscripcion(ListarSuscripcionRequest request) {
		String idTransaccion = request.getAuditRequest().getIdTransaccion();
		String msgTxId = "[idTransaccion =" + idTransaccion + "][listarSuscripcion]-";
		List<SuscripcionBean> listSuscr;
		ini = System.currentTimeMillis();
		end = 0L;
		LOGGER.info( "\n\n\n" + msgTxId + "Parametros de Entrada: \n" + JAXBUtilitarios.anyObjectToXmlText( request ) );

		ListarSuscripcionResponse response = new ListarSuscripcionResponse();
		ParametrosAuditResponse auditResponse = new ParametrosAuditResponse();
		auditResponse.setIdTransaccion( idTransaccion );
		
		//Obtencion de datos de entradas validados
		String idSuscripcion 	= null != request.getIdSuscripcion() 	? request.getIdSuscripcion().trim()  	: PropertiesInternos.CADENA_VACIA;
		String nombreSuscriptor = null != request.getNombreSuscriptor() ? request.getNombreSuscriptor().trim()  : PropertiesInternos.CADENA_VACIA; 
		String fechaIni 		= null != request.getFechaIni() 		? request.getFechaIni().trim()			: PropertiesInternos.CADENA_VACIA;
		String fechaFin 		= null != request.getFechaFin() 		? request.getFechaFin().trim()			: PropertiesInternos.CADENA_VACIA;
		
		if (idSuscripcion.isEmpty() && nombreSuscriptor.isEmpty() && fechaIni.isEmpty() && fechaFin.isEmpty()){
			LOGGER.info( "No se han ingresado criterio de busqueda");
			auditResponse.setCodigoRespuesta( propertiesExternos.LISTAR_DOC_IDF_1_CODIGO );
	        auditResponse.setMensajeRespuesta( propertiesExternos.LISTAR_DOC_IDF_1_MENSAJE );
	        
	        response.setAuditResponse( auditResponse );
	   		
	        LOGGER.info( msgTxId + "Parametros de Salida: \n" + JAXBUtilitarios.anyObjectToXmlText( response ) );
	   		end = System.currentTimeMillis();	
	   		LOGGER.info( msgTxId + "Tiempo que demoro en la ejecucion[" + ( end - ini ) + " ms]" );
	   		LOGGER.info( msgTxId + "=====Fin metodo listarSuscripcion=====" );

			return response;
		}
		
		String invocacionPkgSP = propertiesExternos.PKG_GS_SP_INSERTA_SUSCRIPCION;
		
		
		try{
			ConsultaSuscripcionBean consultaSuscripcion = suscripcionDS.invocaSP_listaSuscripcion(msgTxId, 
						idSuscripcion, nombreSuscriptor, fechaIni, fechaFin);
			
			if (consultaSuscripcion.getCodRpta().equals("0")){
				listSuscr = consultaSuscripcion.getListaSuscripciones();
				
				if (null != listSuscr && listSuscr.size() > 0){
		        	//Seteo de datos de auditoria
			        auditResponse.setCodigoRespuesta( propertiesExternos.LISTAR_DOC_IDF_0_CODIGO );
			        auditResponse.setMensajeRespuesta( propertiesExternos.LISTAR_DOC_IDF_0_MENSAJE );

			        ListaSuscripcionesType listSuscripcionType = new ListaSuscripcionesType();
			        SuscripcionType suscripcionType;
			        
			        for(SuscripcionBean b : listSuscr){
			        	suscripcionType  = new SuscripcionType();
			        	suscripcionType.setIdSuscripcion(b.getIdSuscripcion() + "" );
			        	suscripcionType.setNombreSuscriptor(b.getNombreSuscriptor());
			        	suscripcionType.setTipoSuscripcion(b.getTipoSuscripcion());
			        	suscripcionType.setPrecio(b.getPrecio() + "");
			        	suscripcionType.setFecRegistro(formatter.format(b.getFecRegistro()));
			        	
			        	listSuscripcionType.getSuscripcion().add(suscripcionType);
			        }
			        
			        response.setListaSuscripcion(listSuscripcionType);
			        response.setAuditResponse( auditResponse );
				}else{
			        auditResponse.setCodigoRespuesta( propertiesExternos.LISTAR_DOC_IDF_2_CODIGO );
			        auditResponse.setMensajeRespuesta( propertiesExternos.LISTAR_DOC_IDF_2_MENSAJE );
			        response.setAuditResponse( auditResponse );
				}
			}else{
		        auditResponse.setCodigoRespuesta( propertiesExternos.LISTAR_DOC_IDF_3_CODIGO );
		        auditResponse.setMensajeRespuesta( propertiesExternos.LISTAR_DOC_IDF_3_MENSAJE );
		        response.setAuditResponse( auditResponse );
			}
			

			
		} catch( DBException e ){
			LOGGER.error( "ERROR de DBException" + e.getMessage());
			String cadenaError = (e.getObjException().getMessage()+ "\n" + e.getStackTrace());
			 			
			if(cadenaError.contains(SQLTimeoutException.class.toString().replace( "class", PropertiesInternos.CADENA_VACIA).trim())){				
				LOGGER.error( "ERROR de TIMEOUT");
				auditResponse.setCodigoRespuesta( propertiesExternos.SUSCRIPCION_IDT_1_CODIGO);
	            auditResponse.setMensajeRespuesta(propertiesExternos.SUSCRIPCION_IDT_1_MENSAJE.
	            		replaceAll( PropertiesInternos.VALOR_REPLACE_1, 
	            					PropertiesInternos.ERROR_TO_BD_REGISTRAR_SUSCRIPCION));
	            
			}else if(cadenaError.contains( CannotGetJdbcConnectionException.class.toString().replace( "class",PropertiesInternos.CADENA_VACIA).trim())){				
				LOGGER.error( "ERROR JdbcConnectionException" );				 
				auditResponse.setCodigoRespuesta(propertiesExternos.SUSCRIPCION_IDT_2_CODIGO);
				auditResponse.setMensajeRespuesta( propertiesExternos.SUSCRIPCION_IDT_2_MENSAJE
						.replaceAll( PropertiesInternos.VALOR_REPLACE_1, invocacionPkgSP)
						.replaceAll( PropertiesInternos.VALOR_REPLACE_5, PropertiesInternos.BD_NO_DISPONIBLE ) );
				
			}else{							
				LOGGER.error( "ERROR SP" );				
				auditResponse.setCodigoRespuesta( propertiesExternos.SUSCRIPCION_IDT_3_CODIGO );
				auditResponse.setMensajeRespuesta( propertiesExternos.SUSCRIPCION_IDT_3_MENSAJE);
	            auditResponse.setMensajeRespuesta( propertiesExternos.SUSCRIPCION_IDT_3_MENSAJE
	            		.replaceAll( PropertiesInternos.VALOR_REPLACE_1, invocacionPkgSP ) 
	            		.replaceAll( PropertiesInternos.VALOR_REPLACE_5, cadenaError));
	
			}			
	
		}
		catch( Exception e ){
			LOGGER.error( "ERROR de Exception" + e.getMessage() + "\n" + e.getStackTrace() );
			auditResponse.setCodigoRespuesta( propertiesExternos.SUSCRIPCION_IDT_4_CODIGO );
			auditResponse.setMensajeRespuesta( propertiesExternos.SUSCRIPCION_IDT_4_MENSAJE
					.replace( PropertiesInternos.VALOR_REPLACE_1, PropertiesInternos.ERROR_INTERNO + " - " + e.getMessage() )  );
	
		}
		finally{
	
			response.setAuditResponse( auditResponse );
			LOGGER.info( msgTxId + "Parametros de Salida: \n" + JAXBUtilitarios.anyObjectToXmlText( response ) );
			end = System.currentTimeMillis();
	
			LOGGER.info( msgTxId + "Tiempo que demoro en la ejecucion[" + ( end - ini ) + " ms]" );
			LOGGER.info( msgTxId + "=== Fin metodo consultarOpcionesConfigurables ===" );
	
		}
	
		return response;
		
	}

	@Override
	public EliminarSuscripcionResponse eliminarSuscripcion(EliminarSuscripcionRequest request) {

		String idTransaccion = request.getAuditRequest().getIdTransaccion().trim();
		String msgTxId = "[idTransaccion =" + idTransaccion + "][eliminarSuscripcion]-";
		
		ini = System.currentTimeMillis();
		end = 0L;
		LOGGER.info( "\n\n\n" + msgTxId + "Parametros de Entrada: \n" + JAXBUtilitarios.anyObjectToXmlText( request ) );

		EliminarSuscripcionResponse response = new EliminarSuscripcionResponse();
		ParametrosAuditResponse auditResponse = new ParametrosAuditResponse();
		auditResponse.setIdTransaccion( idTransaccion );
		
		//Como opcional la validacion de los datos de entrada
		
		String idSuscripcion 	= request.getIdSuscripcion().trim();
		
		if(idSuscripcion.isEmpty() ){
			LOGGER.info( "No se han llenado todos los campos obligatorios");
			auditResponse.setCodigoRespuesta( propertiesExternos.ELIMINAR_DOC_IDF_1_CODIGO );
	        auditResponse.setMensajeRespuesta( propertiesExternos.ELIMINAR_DOC_IDF_1_MENSAJE );
	        response.setAuditResponse( auditResponse );
	   		
	        LOGGER.info( msgTxId + "Parametros de Salida: \n" + JAXBUtilitarios.anyObjectToXmlText( response ) );
	   		end = System.currentTimeMillis();	
	   		LOGGER.info( msgTxId + "Tiempo que demoro en la ejecucion[" + ( end - ini ) + " ms]" );
	   		LOGGER.info( msgTxId + "=====Fin metodo seguirDocumentoExterno=====" );

			return response;
		}else{
			LOGGER.info( "Se obtuvieron todos los datos con EXITO" );
		}
		
		String invocacionPkgSP = propertiesExternos.PKG_GS_SP_ELIMINA_SUSCRIPCION;
		try{
			int estadoEjecucion = suscripcionDS.invocaSP_eliminaSuscripcion(msgTxId, Integer.parseInt(idSuscripcion));
			
			if (estadoEjecucion == 0){ //Ejecucion exitosa
	        	//Seteo de datos de auditoria
		        auditResponse.setCodigoRespuesta( propertiesExternos.ELIMINAR_DOC_IDF_0_CODIGO );
		        auditResponse.setMensajeRespuesta( propertiesExternos.ELIMINAR_DOC_IDF_0_MENSAJE );
		        response.setAuditResponse( auditResponse );
			}else{
		        auditResponse.setCodigoRespuesta( propertiesExternos.ELIMINAR_DOC_IDF_2_CODIGO );
		        auditResponse.setMensajeRespuesta( propertiesExternos.ELIMINAR_DOC_IDF_2_MENSAJE );
		        response.setAuditResponse( auditResponse );
			}
				
		
		} catch( DBException e ){
			LOGGER.error( "ERROR de DBException" + e.getMessage());
			String cadenaError = (e.getObjException().getMessage()+ "\n" + e.getStackTrace());
			 			
			if(cadenaError.contains(SQLTimeoutException.class.toString().replace( "class", PropertiesInternos.CADENA_VACIA).trim())){				
				LOGGER.error( "ERROR de TIMEOUT");
				auditResponse.setCodigoRespuesta( propertiesExternos.SUSCRIPCION_IDT_1_CODIGO);
	            auditResponse.setMensajeRespuesta(propertiesExternos.SUSCRIPCION_IDT_1_MENSAJE.
	            		replaceAll( PropertiesInternos.VALOR_REPLACE_1, 
	            					PropertiesInternos.ERROR_TO_BD_REGISTRAR_SUSCRIPCION));
	            
			}else if(cadenaError.contains( CannotGetJdbcConnectionException.class.toString().replace( "class",PropertiesInternos.CADENA_VACIA).trim())){				
				LOGGER.error( "ERROR JdbcConnectionException" );				 
				auditResponse.setCodigoRespuesta(propertiesExternos.SUSCRIPCION_IDT_2_CODIGO);
				auditResponse.setMensajeRespuesta( propertiesExternos.SUSCRIPCION_IDT_2_MENSAJE
						.replaceAll( PropertiesInternos.VALOR_REPLACE_1, invocacionPkgSP)
						.replaceAll( PropertiesInternos.VALOR_REPLACE_5, PropertiesInternos.BD_NO_DISPONIBLE ) );
				
			}else{							
				LOGGER.error( "ERROR SP" );				
				auditResponse.setCodigoRespuesta( propertiesExternos.SUSCRIPCION_IDT_3_CODIGO );
				auditResponse.setMensajeRespuesta( propertiesExternos.SUSCRIPCION_IDT_3_MENSAJE);
	            auditResponse.setMensajeRespuesta( propertiesExternos.SUSCRIPCION_IDT_3_MENSAJE
	            		.replaceAll( PropertiesInternos.VALOR_REPLACE_1, invocacionPkgSP ) 
	            		.replaceAll( PropertiesInternos.VALOR_REPLACE_5, cadenaError));
	
			}			

		}
		catch( Exception e ){
			LOGGER.error( "ERROR de Exception" + e.getMessage() + "\n" + e.getStackTrace() );
			auditResponse.setCodigoRespuesta( propertiesExternos.SUSCRIPCION_IDT_4_CODIGO );
			auditResponse.setMensajeRespuesta( propertiesExternos.SUSCRIPCION_IDT_4_MENSAJE
					.replace( PropertiesInternos.VALOR_REPLACE_1, PropertiesInternos.ERROR_INTERNO + " - " + e.getMessage() )  );

		}
		finally{

			response.setAuditResponse( auditResponse );
			LOGGER.info( msgTxId + "Parametros de Salida: \n" + JAXBUtilitarios.anyObjectToXmlText( response ) );
			end = System.currentTimeMillis();

			LOGGER.info( msgTxId + "Tiempo que demoro en la ejecucion[" + ( end - ini ) + " ms]" );
			LOGGER.info( msgTxId + "=== Fin metodo consultarOpcionesConfigurables ===" );

		}

		return response;
	
	}

	@Override
	public ActualizarSuscripcionResponse actualizarSuscripcion(ActualizarSuscripcionRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

}
