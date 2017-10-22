package pe.com.comercio.eai.services.gestionsuscripcionws.ws.exception;

public class DBException extends BaseException{

	private static final long	serialVersionUID	= -4938556712560828963L;

	public DBException( Exception objException ){
		super( objException );
	}

	public DBException( String msjError ){
		super( msjError );
	}

	public DBException( String codError, String msjError, Exception objException ){
		super( codError, msjError, objException );
	}

	public DBException( String codError, String msjError, String nombreSP, String nombreBD, Exception objException ){
		super( codError, msjError, nombreSP, nombreBD, objException );
	}

}
