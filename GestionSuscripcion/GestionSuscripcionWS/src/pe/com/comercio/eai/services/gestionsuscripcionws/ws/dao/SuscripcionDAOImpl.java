package pe.com.comercio.eai.services.gestionsuscripcionws.ws.dao;

import java.math.BigDecimal;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;
import oracle.jdbc.OracleTypes;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.NestedRuntimeException;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import pe.com.comercio.eai.services.gestionsuscripcionws.ws.bean.ConsultaSuscripcionBean;
import pe.com.comercio.eai.services.gestionsuscripcionws.ws.bean.SuscripcionBean;
import pe.com.comercio.eai.services.gestionsuscripcionws.ws.exception.BaseException;
import pe.com.comercio.eai.services.gestionsuscripcionws.ws.exception.DBException;
import pe.com.comercio.eai.services.gestionsuscripcionws.ws.mapper.CursorSuscripcionesSqlReturnType;
import pe.com.comercio.eai.services.gestionsuscripcionws.ws.types.ActualizarSuscripcionRequest;
import pe.com.comercio.eai.services.gestionsuscripcionws.ws.types.RegistrarSuscripcionRequest;
import pe.com.comercio.eai.services.gestionsuscripcionws.ws.util.PropertiesExternos;
import pe.com.comercio.eai.services.gestionsuscripcionws.ws.util.PropertiesInternos;

@Repository
public class SuscripcionDAOImpl implements SuscripcionDAO {

	private static final Logger LOGGER = Logger.getLogger(SuscripcionDAOImpl.class);

	@Autowired
	private PropertiesExternos propertiesExternos;

	@Autowired
	@Qualifier("suscripcionDS")
	private DataSource suscripcionDS;

	SimpleJdbcCall objJdbcCall = null;
	Long ini;
	Long end;
	SimpleDateFormat formatter = new SimpleDateFormat(PropertiesInternos.FORMATO_FECHA);

	@Override
	public int invocaSP_insertaSuscripcion(String msgTxId, RegistrarSuscripcionRequest request) throws Exception {

		String trazabilidad = msgTxId + "[invocaSP_insertaSuscripcion]-";
		LOGGER.info(trazabilidad + "===Inicio del metodo===");
		ini = 0L;
		end = 0L;
		int estadoRegistro = -1;

		try {
			String invocacionPkgSP = propertiesExternos.PKG_GS_SP_INSERTA_SUSCRIPCION;
			String idTransaccion = request.getAuditRequest().getIdTransaccion();
			String usuarioAplicacion = request.getAuditRequest().getUsuarioAplicacion();
			Date fechaRegistro	= PropertiesInternos.getStringToDateWithFomat( request.getFecRegistro(),	PropertiesInternos.FORMATO_FECHA );
			BigDecimal bd_IdSuscripcion = new BigDecimal(request.getIdSuscripcion().trim());
			suscripcionDS.setLoginTimeout(propertiesExternos.DB_TIMEOUT_EXECUTION_MAX_TIME);

			SimpleJdbcCall jdbcCall = new SimpleJdbcCall(suscripcionDS)
					.withoutProcedureColumnMetaDataAccess()
					.withSchemaName(propertiesExternos.DB_SUSCRIPCION_OWNER)
					.withProcedureName(invocacionPkgSP)
					.declareParameters(
							new SqlParameter("P_ID_SUSCRIPCION", 	Types.NUMERIC),
							new SqlParameter("P_NOMBRE_SUSCRIPTOR", Types.VARCHAR),
							new SqlParameter("P_TIPO_SUSCRIPCION", 	Types.VARCHAR),
							new SqlParameter("P_PRECIO", 			Types.NUMERIC), 
							new SqlParameter("P_FEC_REGISTRO", 		Types.DATE),
							// Parameros adicionales
							new SqlOutParameter("P_COD_RESPUESTA", Types.NUMERIC),
							new SqlOutParameter("P_MSG_RESPUESTA", Types.VARCHAR));

			LOGGER.info(trazabilidad + "Consultando BD SUSCRIPCIONES EAI con JNDI ="+ propertiesExternos.DB_SUSCRIPCION_JNDI);
			LOGGER.info(trazabilidad + "Tiempo permitido de ejecucion=" + propertiesExternos.DB_TIMEOUT_EXECUTION_MAX_TIME);
			jdbcCall.getJdbcTemplate().setQueryTimeout(propertiesExternos.DB_TIMEOUT_EXECUTION_MAX_TIME);
			LOGGER.info(trazabilidad + "Ejecutando SP : " + propertiesExternos.DB_SUSCRIPCION_OWNER + invocacionPkgSP);

			objJdbcCall = new SimpleJdbcCall(this.suscripcionDS);

			LOGGER.info("P_ID_SUSCRIPCION:\t" 	 + bd_IdSuscripcion);
			LOGGER.info("P_NOMBRE_SUSCRIPTOR:\t" + request.getNombreSuscriptor());
			LOGGER.info("P_TIPO_SUSCRIPCION:\t"  + request.getTipoSuscripcion());
			LOGGER.info("P_PRECIO:\t" 			 + request.getPrecio());
			LOGGER.info("P_FEC_REGISTRO:\t" 	 + formatter.format(fechaRegistro));

			SqlParameterSource objParametrosIN =

					new MapSqlParameterSource()
							.addValue("P_ID_SUSCRIPCION", 	bd_IdSuscripcion)
							.addValue("P_NOMBRE_SUSCRIPTOR",request.getNombreSuscriptor())
							.addValue("P_TIPO_SUSCRIPCION", request.getTipoSuscripcion())
							.addValue("P_PRECIO", 			Double.parseDouble(request.getPrecio()))
							.addValue("P_FEC_REGISTRO", 	fechaRegistro);

			Map<String, Object> resultMap = jdbcCall.execute(objParametrosIN);
			
			BigDecimal valorCero = new BigDecimal(PropertiesInternos.sVALOR_CERO);
			BigDecimal p_codRespuesta = (BigDecimal) resultMap.get("P_COD_RESPUESTA");
			String p_msjRespuesta = (String) resultMap.get("P_MSG_RESPUESTA");

			LOGGER.info("Entro al codigo de respuest:\t" + p_codRespuesta);
			LOGGER.info("Entro al mensaje de respuest:\t" + p_msjRespuesta);

			if (p_codRespuesta.compareTo(valorCero) == 0) { //Ejecucion exitosa
				estadoRegistro = 0;
			}
			LOGGER.info("estadoRegistro: " + estadoRegistro);

		} catch (NestedRuntimeException e) {
			LOGGER.error(trazabilidad + "ERROR: [NestedRuntimeException] - [" + e.getMessage() + "] ", e);
			throw new DBException(e);
		} catch (Exception e) {
			LOGGER.error(trazabilidad + "ERROR[Exception]:" + e.getMessage(), e);
			throw new BaseException(PropertiesInternos.SUSCRIPCION_CODIGO_ESTANDAR_ERROR,
					PropertiesInternos.SUSCRIPCION_MENSAJE_ESTANDAR_ERROR.replace(PropertiesInternos.nombreBD,
							propertiesExternos.DB_SUSCRIPCION_JNDI),
					e);

		} finally {
			end = System.currentTimeMillis();
			LOGGER.info(trazabilidad + "Tiempo que demoro en la ejecucion[" + (end - ini) + " ms]");
			LOGGER.info(trazabilidad + "===Fin del metodo invocaSP_insertaSuscripcion  - EAI ===");
		}

		return estadoRegistro;

	}

	public ConsultaSuscripcionBean invocaSP_listaSuscripcion(String msgTxId, String idSuscripcion,
			String nombreSuscriptor, String fechaIni, String fechaFin) throws Exception{
		
		String trazabilidad = msgTxId + "[invocaSP_listaSuscripcion]-";
		LOGGER.info(trazabilidad + "===Inicio del metodo===");
		ini = 0L;
		end = 0L;
		ArrayList<SuscripcionBean> listaSuscripcion = null;
		
		//String p_codRespuesta = PropertiesInternos.CADENA_VACIA;
		//String p_msjRespuesta = PropertiesInternos.CADENA_VACIA;
		ConsultaSuscripcionBean consultaSuscripcion = new ConsultaSuscripcionBean();

		try {
			String invocacionPkgSP = propertiesExternos.PKG_GS_SP_LISTA_SUSCRIPCION;
			suscripcionDS.setLoginTimeout(propertiesExternos.DB_TIMEOUT_EXECUTION_MAX_TIME);

			SimpleJdbcCall jdbcCall = new SimpleJdbcCall(suscripcionDS)
					.withoutProcedureColumnMetaDataAccess()
					.withSchemaName(propertiesExternos.DB_SUSCRIPCION_OWNER)
					.withProcedureName(invocacionPkgSP)
					.declareParameters(
							new SqlParameter("P_FECHA_INI", 	Types.DATE),
							new SqlParameter("P_FECHA_FIN", 	Types.DATE),
							// Parameros adicionales
							new SqlOutParameter( 	"P_CUR_SUSCRIPCIONES", OracleTypes.CURSOR, null, 
								    new CursorSuscripcionesSqlReturnType( msgTxId ) ), 
							new SqlOutParameter("P_COD_RESPUESTA", Types.NUMERIC),
							new SqlOutParameter("P_MSG_RESPUESTA", Types.VARCHAR));

			LOGGER.info(trazabilidad + "Consultando BD SUSCRIPCIONES EAI con JNDI ="+ propertiesExternos.DB_SUSCRIPCION_JNDI);
			LOGGER.info(trazabilidad + "Tiempo permitido de ejecucion=" + propertiesExternos.DB_TIMEOUT_EXECUTION_MAX_TIME);
			jdbcCall.getJdbcTemplate().setQueryTimeout(propertiesExternos.DB_TIMEOUT_EXECUTION_MAX_TIME);
			LOGGER.info(trazabilidad + "Ejecutando SP : " + propertiesExternos.DB_SUSCRIPCION_OWNER + invocacionPkgSP);

			objJdbcCall = new SimpleJdbcCall(this.suscripcionDS);

			LOGGER.info("P_FECHA_INI:\t"  + fechaIni);
			LOGGER.info("P_FECHA_FIN:\t"  + fechaFin);

			SqlParameterSource objParametrosIN =

					new MapSqlParameterSource()
							.addValue("P_FECHA_INI", formatter.parse(fechaIni))
							.addValue("P_FECHA_FIN", formatter.parse(fechaFin));

			Map<String, Object> resultMap = jdbcCall.execute(objParametrosIN);
			
			BigDecimal valorCero 		= new BigDecimal(PropertiesInternos.sVALOR_CERO);
			BigDecimal p_codRespuesta 	= (BigDecimal) resultMap.get("P_COD_RESPUESTA");
			String p_msjRespuesta 		= (String) resultMap.get("P_MSG_RESPUESTA");
			List<SuscripcionBean> listSuscripcion = (List<SuscripcionBean>)resultMap.get( "P_CUR_SUSCRIPCIONES" );
			consultaSuscripcion.setListaSuscripciones(listSuscripcion);
			consultaSuscripcion.setMsjRpta(p_msjRespuesta);
			
			if (p_codRespuesta.compareTo(valorCero) == 0) { //Ejecucion exitosa
				consultaSuscripcion.setCodRpta("0");
			}else{
				consultaSuscripcion.setCodRpta("-1");
			}
			
			LOGGER.info("Entro al codigo de respuest:\t" + p_codRespuesta);
			LOGGER.info("Entro al mensaje de respuest:\t" + p_msjRespuesta);


		} catch (NestedRuntimeException e) {
			LOGGER.error(trazabilidad + "ERROR: [NestedRuntimeException] - [" + e.getMessage() + "] ", e);
			throw new DBException(e);
		} catch (Exception e) {
			LOGGER.error(trazabilidad + "ERROR[Exception]:" + e.getMessage(), e);
			throw new BaseException(PropertiesInternos.SUSCRIPCION_CODIGO_ESTANDAR_ERROR,
					PropertiesInternos.SUSCRIPCION_MENSAJE_ESTANDAR_ERROR.replace(PropertiesInternos.nombreBD,
							propertiesExternos.DB_SUSCRIPCION_JNDI),
					e);

		} finally {
			end = System.currentTimeMillis();
			LOGGER.info(trazabilidad + "Tiempo que demoro en la ejecucion[" + (end - ini) + " ms]");
			LOGGER.info(trazabilidad + "===Fin del metodo invocaSP_insertaSuscripcion  - EAI ===");
		}

		return consultaSuscripcion;

	}

	@Override
	public int invocaSP_eliminaSuscripcion(String msgTxId, int idSuscripcion) throws Exception {

		String trazabilidad = msgTxId + "[invocaSP_eliminaSuscripcion]-";
		LOGGER.info(trazabilidad + "===Inicio del metodo===");
		ini = 0L;
		end = 0L;
		int estadoRegistro = -1;

		try {
			String invocacionPkgSP = propertiesExternos.PKG_GS_SP_ELIMINA_SUSCRIPCION;

			suscripcionDS.setLoginTimeout(propertiesExternos.DB_TIMEOUT_EXECUTION_MAX_TIME);

			SimpleJdbcCall jdbcCall = new SimpleJdbcCall(suscripcionDS)
					.withoutProcedureColumnMetaDataAccess()
					.withSchemaName(propertiesExternos.DB_SUSCRIPCION_OWNER)
					.withProcedureName(invocacionPkgSP)
					.declareParameters(
							new SqlParameter("P_ID_SUSCRIPCION", 	Types.NUMERIC),
							// Parameros de Salida
							new SqlOutParameter("P_COD_RESPUESTA", Types.NUMERIC),
							new SqlOutParameter("P_MSG_RESPUESTA", Types.VARCHAR));

			LOGGER.info(trazabilidad + "Consultando BD SUSCRIPCIONES EAI con JNDI ="+ propertiesExternos.DB_SUSCRIPCION_JNDI);
			LOGGER.info(trazabilidad + "Tiempo permitido de ejecucion=" + propertiesExternos.DB_TIMEOUT_EXECUTION_MAX_TIME);
			jdbcCall.getJdbcTemplate().setQueryTimeout(propertiesExternos.DB_TIMEOUT_EXECUTION_MAX_TIME);
			LOGGER.info(trazabilidad + "Ejecutando SP : " + propertiesExternos.DB_SUSCRIPCION_OWNER + invocacionPkgSP);

			objJdbcCall = new SimpleJdbcCall(this.suscripcionDS);

			LOGGER.info("P_ID_SUSCRIPCION:\t" 	 + idSuscripcion);

			SqlParameterSource objParametrosIN =

					new MapSqlParameterSource()
							.addValue("P_ID_SUSCRIPCION", 	idSuscripcion);

			Map<String, Object> resultMap = jdbcCall.execute(objParametrosIN);
			
			BigDecimal valorCero = new BigDecimal(PropertiesInternos.sVALOR_CERO);
			BigDecimal p_codRespuesta = (BigDecimal) resultMap.get("P_COD_RESPUESTA");
			String p_msjRespuesta = (String) resultMap.get("P_MSG_RESPUESTA");

			LOGGER.info("Entro al codigo de respuest:\t" + p_codRespuesta);
			LOGGER.info("Entro al mensaje de respuest:\t" + p_msjRespuesta);

			if (p_codRespuesta.compareTo(valorCero) == 0) { //Ejecucion exitosa
				estadoRegistro = 0;
			}
			LOGGER.info("estadoRegistro: " + estadoRegistro);

		} catch (NestedRuntimeException e) {
			LOGGER.error(trazabilidad + "ERROR: [NestedRuntimeException] - [" + e.getMessage() + "] ", e);
			throw new DBException(e);
		} catch (Exception e) {
			LOGGER.error(trazabilidad + "ERROR[Exception]:" + e.getMessage(), e);
			throw new BaseException(PropertiesInternos.SUSCRIPCION_CODIGO_ESTANDAR_ERROR,
					PropertiesInternos.SUSCRIPCION_MENSAJE_ESTANDAR_ERROR.replace(PropertiesInternos.nombreBD,
							propertiesExternos.DB_SUSCRIPCION_JNDI),
					e);

		} finally {
			end = System.currentTimeMillis();
			LOGGER.info(trazabilidad + "Tiempo que demoro en la ejecucion[" + (end - ini) + " ms]");
			LOGGER.info(trazabilidad + "===Fin del metodo invocaSP_eliminaSuscripcion  - EAI ===");
		}

		return estadoRegistro;

	
	}

	@Override
	public int invocaSP_actualizaSuscripcion(String msgTxId, ActualizarSuscripcionRequest request) throws Exception {

		String trazabilidad = msgTxId + "[invocaSP_actualizaSuscripcion]-";
		LOGGER.info(trazabilidad + "===Inicio del metodo===");
		ini = 0L;
		end = 0L;
		int estadoRegistro = -1;

		try {
			String invocacionPkgSP 		= propertiesExternos.PKG_GS_SP_ACTUALIZA_SUSCRIPCION;
			String idTransaccion 		= request.getAuditRequest().getIdTransaccion();
			String usuarioAplicacion 	= request.getAuditRequest().getUsuarioAplicacion();
			Date fechaRegistro			= PropertiesInternos.getStringToDateWithFomat( request.getFecRegistro(),	
															PropertiesInternos.FORMATO_FECHA );
			BigDecimal bd_IdSuscripcion = new BigDecimal(request.getIdSuscripcion().trim());
			suscripcionDS.setLoginTimeout(propertiesExternos.DB_TIMEOUT_EXECUTION_MAX_TIME);

			SimpleJdbcCall jdbcCall = new SimpleJdbcCall(suscripcionDS)
					.withoutProcedureColumnMetaDataAccess()
					.withSchemaName(propertiesExternos.DB_SUSCRIPCION_OWNER)
					.withProcedureName(invocacionPkgSP)
					.declareParameters(
							new SqlParameter("P_ID_SUSCRIPCION", 	Types.NUMERIC),
							new SqlParameter("P_NOMBRE_SUSCRIPTOR", Types.VARCHAR),
							new SqlParameter("P_TIPO_SUSCRIPCION", 	Types.VARCHAR),
							new SqlParameter("P_PRECIO", 			Types.NUMERIC), 
							new SqlParameter("P_FEC_REGISTRO", 		Types.DATE),
							// Parameros adicionales
							new SqlOutParameter("P_COD_RESPUESTA", Types.NUMERIC),
							new SqlOutParameter("P_MSG_RESPUESTA", Types.VARCHAR));

			LOGGER.info(trazabilidad + "Consultando BD SUSCRIPCIONES EAI con JNDI ="+ propertiesExternos.DB_SUSCRIPCION_JNDI);
			LOGGER.info(trazabilidad + "Tiempo permitido de ejecucion=" + propertiesExternos.DB_TIMEOUT_EXECUTION_MAX_TIME);
			jdbcCall.getJdbcTemplate().setQueryTimeout(propertiesExternos.DB_TIMEOUT_EXECUTION_MAX_TIME);
			LOGGER.info(trazabilidad + "Ejecutando SP : " + propertiesExternos.DB_SUSCRIPCION_OWNER + invocacionPkgSP);

			objJdbcCall = new SimpleJdbcCall(this.suscripcionDS);

			LOGGER.info("P_ID_SUSCRIPCION:\t" 	 + bd_IdSuscripcion);
			LOGGER.info("P_NOMBRE_SUSCRIPTOR:\t" + request.getNombreSuscriptor());
			LOGGER.info("P_TIPO_SUSCRIPCION:\t"  + request.getTipoSuscripcion());
			LOGGER.info("P_PRECIO:\t" 			 + request.getPrecio());
			LOGGER.info("P_FEC_REGISTRO:\t" 	 + formatter.format(fechaRegistro));

			SqlParameterSource objParametrosIN =

					new MapSqlParameterSource()
							.addValue("P_ID_SUSCRIPCION", 	bd_IdSuscripcion)
							.addValue("P_NOMBRE_SUSCRIPTOR",request.getNombreSuscriptor())
							.addValue("P_TIPO_SUSCRIPCION", request.getTipoSuscripcion())
							.addValue("P_PRECIO", 			Double.parseDouble(request.getPrecio()))
							.addValue("P_FEC_REGISTRO", 	fechaRegistro);

			Map<String, Object> resultMap = jdbcCall.execute(objParametrosIN);
			
			BigDecimal valorCero = new BigDecimal(PropertiesInternos.sVALOR_CERO);
			BigDecimal p_codRespuesta = (BigDecimal) resultMap.get("P_COD_RESPUESTA");
			String p_msjRespuesta = (String) resultMap.get("P_MSG_RESPUESTA");

			LOGGER.info("Entro al codigo de respuest:\t" + p_codRespuesta);
			LOGGER.info("Entro al mensaje de respuest:\t" + p_msjRespuesta);

			if (p_codRespuesta.compareTo(valorCero) == 0) { //Ejecucion exitosa
				estadoRegistro = 0;
			}
			LOGGER.info("estadoRegistro: " + estadoRegistro);

		} catch (NestedRuntimeException e) {
			LOGGER.error(trazabilidad + "ERROR: [NestedRuntimeException] - [" + e.getMessage() + "] ", e);
			throw new DBException(e);
		} catch (Exception e) {
			LOGGER.error(trazabilidad + "ERROR[Exception]:" + e.getMessage(), e);
			throw new BaseException(PropertiesInternos.SUSCRIPCION_CODIGO_ESTANDAR_ERROR,
					PropertiesInternos.SUSCRIPCION_MENSAJE_ESTANDAR_ERROR.replace(PropertiesInternos.nombreBD,
							propertiesExternos.DB_SUSCRIPCION_JNDI),
					e);

		} finally {
			end = System.currentTimeMillis();
			LOGGER.info(trazabilidad + "Tiempo que demoro en la ejecucion[" + (end - ini) + " ms]");
			LOGGER.info(trazabilidad + "===Fin del metodo invocaSP_insertaSuscripcion  - EAI ===");
		}

		return estadoRegistro;

	
	} 

}
