package pe.com.comercio.eai.services.gestionsuscripcionws.ws.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PropertiesExternos{
	
	//CONEXION
	
	@Value( "${db.jndi.suscripcionDS}" )  
	public String	DB_SUSCRIPCION_JNDI;

	@Value( "${db.suscripcion.owner}" )
	public String	DB_SUSCRIPCION_OWNER;
	
	//Listado de SPs que se usan
	
	@Value( "${pkg.sp.gestionsuscripcion.sp_inserta_suscripcion}" )
	public String PKG_GS_SP_INSERTA_SUSCRIPCION;
	
	@Value( "${pkg.sp.gestionsuscripcion.sp_lista_suscripcion}" )
	public String PKG_GS_SP_LISTA_SUSCRIPCION;
	
	@Value( "${pkg.sp.gestionsuscripcion.sp_elimina_suscripcion}" )
	public String PKG_GS_SP_ELIMINA_SUSCRIPCION;
	
	@Value( "${pkg.sp.gestionsuscripcion.sp_actualiza_suscripcion}" )
	public String PKG_GS_SP_ACTUALIZA_SUSCRIPCION;
	
	
	//// TIME OUT
	
	@Value( "${db.timeout.execution.max.time}" )
	public int		DB_TIMEOUT_EXECUTION_MAX_TIME;

	@Value( "${db.timeout.login.max.time}" )
	public Integer	DB_TIMEOUT_LOGIN_MAX_TIME;
	
	///IDFS
	
	//Metodo registrar suscripcion
	@Value( "${registrar.suscripcion.idf0.codigo}" )
	public String	REGISTRAR_DOC_IDF_0_CODIGO;

	@Value( "${registrar.suscripcion.idf0.mensaje}" )
	public String	REGISTRAR_DOC_IDF_0_MENSAJE;
	
	@Value( "${registrar.suscripcion.idf1.codigo}" )
	public String	REGISTRAR_DOC_IDF_1_CODIGO;

	@Value( "${registrar.suscripcion.idf1.mensaje}" )
	public String	REGISTRAR_DOC_IDF_1_MENSAJE;
	
	@Value( "${registrar.suscripcion.idf2.codigo}" )
	public String	REGISTRAR_DOC_IDF_2_CODIGO;

	@Value( "${registrar.suscripcion.idf2.mensaje}" )
	public String	REGISTRAR_DOC_IDF_2_MENSAJE;
	
	//Metodo Consultar Documento
	@Value( "${listar.suscripcion.idf0.codigo}" )
	public String	LISTAR_DOC_IDF_0_CODIGO;

	@Value( "${listar.suscripcion.idf0.mensaje}" )
	public String	LISTAR_DOC_IDF_0_MENSAJE;
	
	@Value( "${listar.suscripcion.idf1.codigo}" )
	public String	LISTAR_DOC_IDF_1_CODIGO;

	@Value( "${listar.suscripcion.idf1.mensaje}" )
	public String	LISTAR_DOC_IDF_1_MENSAJE;
	
	@Value( "${listar.suscripcion.idf2.codigo}" )
	public String	LISTAR_DOC_IDF_2_CODIGO;

	@Value( "${listar.suscripcion.idf2.mensaje}" )
	public String	LISTAR_DOC_IDF_2_MENSAJE;
	
	@Value( "${listar.suscripcion.idf3.codigo}" )
	public String	LISTAR_DOC_IDF_3_CODIGO;

	@Value( "${listar.suscripcion.idf3.mensaje}" )
	public String	LISTAR_DOC_IDF_3_MENSAJE;
	
	//Metodo eliminar suscripcion
	@Value( "${eliminar.suscripcion.idf0.codigo}" )
	public String	ELIMINAR_DOC_IDF_0_CODIGO;

	@Value( "${eliminar.suscripcion.idf0.mensaje}" )
	public String	ELIMINAR_DOC_IDF_0_MENSAJE;
	
	@Value( "${eliminar.suscripcion.idf1.codigo}" )
	public String	ELIMINAR_DOC_IDF_1_CODIGO;

	@Value( "${eliminar.suscripcion.idf1.mensaje}" )
	public String	ELIMINAR_DOC_IDF_1_MENSAJE;
	
	@Value( "${eliminar.suscripcion.idf2.codigo}" )
	public String	ELIMINAR_DOC_IDF_2_CODIGO;

	@Value( "${eliminar.suscripcion.idf2.mensaje}" )
	public String	ELIMINAR_DOC_IDF_2_MENSAJE;
	
	//Metodo actualizar suscripcion
	@Value( "${actualizar.suscripcion.idf0.codigo}" )
	public String	ACTUALIZAR_DOC_IDF_0_CODIGO;

	@Value( "${actualizar.suscripcion.idf0.mensaje}" )
	public String	ACTUALIZAR_DOC_IDF_0_MENSAJE;
	
	@Value( "${actualizar.suscripcion.idf1.codigo}" )
	public String	ACTUALIZAR_DOC_IDF_1_CODIGO;

	@Value( "${actualizar.suscripcion.idf1.mensaje}" )
	public String	ACTUALIZAR_DOC_IDF_1_MENSAJE;
	
	@Value( "${actualizar.suscripcion.idf2.codigo}" )
	public String	ACTUALIZAR_DOC_IDF_2_CODIGO;

	@Value( "${actualizar.suscripcion.idf2.mensaje}" )
	public String	ACTUALIZAR_DOC_IDF_2_MENSAJE;
	
	//// IDTS - Para todos los Metodos
	
	@Value( "${suscripcion.idt1.codigo}" )
	public String	SUSCRIPCION_IDT_1_CODIGO;

	@Value( "${suscripcion.idt1.mensaje}" )
	public String	SUSCRIPCION_IDT_1_MENSAJE;

	@Value( "${suscripcion.idt2.codigo}" )
	public String	SUSCRIPCION_IDT_2_CODIGO;

	@Value( "${suscripcion.idt2.mensaje}" )
	public String	SUSCRIPCION_IDT_2_MENSAJE;

	@Value( "${suscripcion.idt3.codigo}" )
	public String	SUSCRIPCION_IDT_3_CODIGO;

	@Value( "${suscripcion.idt3.mensaje}" )
	public String	SUSCRIPCION_IDT_3_MENSAJE;

	@Value( "${suscripcion.idt4.codigo}" )
	public String	SUSCRIPCION_IDT_4_CODIGO;

	@Value( "${suscripcion.idt4.mensaje}" )
	public String	SUSCRIPCION_IDT_4_MENSAJE;

}
