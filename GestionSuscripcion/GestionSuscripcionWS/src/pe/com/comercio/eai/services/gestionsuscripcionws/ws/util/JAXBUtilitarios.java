package pe.com.comercio.eai.services.gestionsuscripcionws.ws.util;

import java.io.StringWriter;
import java.util.HashMap;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;
import org.apache.log4j.Logger;
import org.apache.xmlbeans.XmlObject;

/**
 * @author Richard Berrocal.
 * @clase: JAXBUtilitarios.java
 * @descripcion Clase util para el servicio.
 * @author_company: minedu.
 * @fecha_de_creacion: 26-04-2017.
 * @fecha_de_ultima_actualizacion: dd-mm-yyyy.
 * @version 1.0
 */
public class JAXBUtilitarios{

	private static Logger						logger		= Logger.getLogger( JAXBUtilitarios.class );
	@SuppressWarnings( "rawtypes" )
	private static HashMap<Class, JAXBContext>	mapContexts	= new HashMap<Class, JAXBContext>();

	/**
	 * Contexto de la clase
	 * @param Clase
	 * java
	 * @return JAXBContext
	 */
	@SuppressWarnings( "rawtypes" )
	private static JAXBContext obtainJaxBContextFromClass( Class clas ){

		JAXBContext context;
		context = mapContexts.get( clas );

		if( context == null ){
			try{
				context = JAXBContext.newInstance( clas );
				mapContexts.put( clas, context );
			}
			catch( Exception e ){
				logger.error( "Error creando JAXBContext:" + e );
			}
		}
		return context;
	}

	/**
	 * Convertir request/response a Texto
	 * @param Objeto
	 * Java
	 * @return String
	 */
	@SuppressWarnings( { "unchecked", "rawtypes" } )
	public static String anyObjectToXmlText( Object objJaxB ){
		String commandoRequestEnXml = null;

		JAXBContext context;

		try{
			context = obtainJaxBContextFromClass( objJaxB.getClass() );
			Marshaller marshaller = context.createMarshaller();
			StringWriter xmlWriter = new StringWriter();
			marshaller.marshal( new JAXBElement( new QName( "", objJaxB.getClass().getName() ), objJaxB.getClass(), objJaxB ), xmlWriter );
			XmlObject xmlObj = XmlObject.Factory.parse( xmlWriter.toString() );
			commandoRequestEnXml = xmlObj.toString();
		}
		catch( Exception e ){
			logger.error( "Error parseando object to xml:" + e );
		}
		return commandoRequestEnXml;
	}

	/**
	 * Metodo que permite obtener la representacion XML de un objeto JAX-B *
	 * @param objJaxB
	 * Objeto JAX-B
	 * @return XML Text
	 */
	public static String getXmlTextFromJaxB( Object objJaxB ){
		String commandoRequestEnXml = "";

		JAXBContext context;

		try{
			context = JAXBContext.newInstance( objJaxB.getClass() );
			Marshaller marshaller = context.createMarshaller();
			StringWriter xmlWriter = new StringWriter();
			marshaller.marshal( objJaxB, xmlWriter );

			XmlObject xmlObj = XmlObject.Factory.parse( xmlWriter.toString() );

			commandoRequestEnXml = xmlObj.toString();

		}
		catch( Exception e ){
			logger.error( "Error parsendo objeto a XML:", e );
		}

		return commandoRequestEnXml;
	}
	

	

}
