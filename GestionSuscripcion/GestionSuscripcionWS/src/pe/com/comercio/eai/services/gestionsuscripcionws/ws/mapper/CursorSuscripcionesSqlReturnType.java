package pe.com.comercio.eai.services.gestionsuscripcionws.ws.mapper;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlReturnType;

import pe.com.comercio.eai.services.gestionsuscripcionws.ws.bean.SuscripcionBean;
import pe.com.comercio.eai.services.gestionsuscripcionws.ws.util.JAXBUtilitarios;

public class CursorSuscripcionesSqlReturnType  implements SqlReturnType{
	

	private static final Logger	LOGGER	= Logger.getLogger( CursorSuscripcionesSqlReturnType.class );
	private String				msgId;

	public CursorSuscripcionesSqlReturnType( String msgId ){

		super();
		this.msgId = msgId;
	}

	@SuppressWarnings( "rawtypes" )
	private RowMapper	rowMapper	= new RowMapper<SuscripcionBean>(){

		@Override
		public SuscripcionBean mapRow( ResultSet rs, int numeroFila ) throws SQLException{
	
			SuscripcionBean c = new SuscripcionBean();
			LOGGER.info( rs.getInt( "IdSuscripcion" ) );
	
			c.setIdSuscripcion( 	rs.getInt( "idSuscripcion" ) );
			c.setNombreSuscriptor( 	rs.getString( "NombreSuscriptor" ) );
			c.setTipoSuscripcion( 	rs.getString( "TipoSuscripcion" ) );
			c.setPrecio( 			rs.getDouble( "Precio" ) );
			c.setFecRegistro( 		rs.getDate( "FecRegistro" ) );
	
			LOGGER.info( msgId + "Datos de Salida del Cursor: [P_CUR_SUSCRIPCIONES] \n" + 
						JAXBUtilitarios.anyObjectToXmlText( c ) );
			return c;
	
		}
	
	};

	@SuppressWarnings( { "rawtypes", "unchecked" } )
	public Object getTypeValue( CallableStatement cs, int ix, int sqlType, String typeName ) throws SQLException{

		ResultSet rs = null;
		List lista = null;

		try{
			rs = (ResultSet)cs.getObject( ix );
			lista = new ArrayList();

			if( rs != null ){
				for( int i = 0; rs.next(); i++ ){
					lista.add( rowMapper.mapRow( rs, i ) );
				}
			}

			return lista;
		}
		catch( SQLException e ){
			LOGGER.error( "ERROR [SQLException]: " + e.getMessage() );

			if( ( e.getMessage() != null ) && ( e.getMessage().startsWith( "Cursor is closed" ) ) ){
				LOGGER.error( "[Cursor is closed]: " );

				return new ArrayList();
			}
			else{
				throw e;
			}
		}
		finally{
			if( rs != null ){
				rs.close();
			}
		}

	}

}
