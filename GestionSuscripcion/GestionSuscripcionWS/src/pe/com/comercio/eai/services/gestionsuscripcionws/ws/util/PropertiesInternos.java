package pe.com.comercio.eai.services.gestionsuscripcionws.ws.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class PropertiesInternos{
    public static final String CADENA_VACIA 		= "";
	public static final int		codigo_DB			= -99;
	public static final String	VALOR_REPLACE_1		= "XXX";
	public static final String	VALOR_REPLACE_5		= "YYY";
	public static final String	BD_NO_DISPONIBLE	= "LA BASE DE DATOS NO ESTA DISPONIBLE";
	public static final String	ERROR_INTERNO		= "EN EL SERVICIO GestionSuscripcionWS";

	public static final String	ERROR_TO_BD_REGISTRAR_SUSCRIPCION	= "ERROR TIME OUT [EN AAAA]";
	public static final String	ERROR_TO_BD_LISTAR_SUSCRIPCION		= "ERROR TIME OUT [EN BBBB]";
	public static final String	ERROR_TO_BD_METODO3	= "ERROR TIME OUT [EN CCCC]";
	public static final String	ERROR_TO_BD_METODO4	= "ERROR TIME OUT [EN DDDD]";
	
	public static final String	SUSCRIPCION_CODIGO_ESTANDAR_ERROR		= "";
	public static final String	SUSCRIPCION_MENSAJE_ESTANDAR_ERROR	= "";
	public static final String	nombreBD								= "$nombre_BD";
	public static final String	CADENA_PUNTO							= ".";
	public static final String	CADENA_COMA								= ",";
	public static final String  FORMATO_FECHA="dd/MM/yyyy";
	public static final String  sVALOR_CERO 				= "0";

	public static Date getStringToDateWithFomat(String date,
	        String formatFecha) {
		Date fecha = null;
		try {
		SimpleDateFormat formatter = new SimpleDateFormat(formatFecha, Locale.getDefault());
		fecha = formatter.parse(date);
		} catch (Exception e) {
		}
		return fecha;
	}
	
}
