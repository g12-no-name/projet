package projet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;
import javax.sql.DataSource;

import jfox.dao.jdbc.UtilJdbc;
import projet.data.TypePoste;


public class DaoTypePoste {

	
	// Champs

	@Inject
	private DataSource		dataSource;

	
	// Actions

	public int inserer( TypePoste typePoste ) {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();
			sql = "INSERT INTO typePoste ( nom ) VALUES( ? ) ";
			stmt = cn.prepareStatement( sql, Statement.RETURN_GENERATED_KEYS );
			stmt.setObject( 1, typePoste.getNom() );
			stmt.executeUpdate();

			// Récupère l'identifiant généré par le SGBD
			rs = stmt.getGeneratedKeys();
			rs.next();
			typePoste.setId( rs.getObject( 1, Integer.class) );
			return typePoste.getId();
	
		} catch ( SQLException e ) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}


	public void modifier( TypePoste typePoste ) {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		String				sql;

		try {
			cn = dataSource.getConnection();
			sql = "UPDATE typePoste SET nom = ? WHERE id =  ?";
			stmt = cn.prepareStatement( sql );
			stmt.setObject( 1, typePoste .getNom() );
			stmt.setObject( 2, typePoste.getId() );
			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}
	}


	public void supprimer( int id ) {

		Connection			cn 		= null;
		PreparedStatement	stmt 	= null;
		String				sql;

		try {
			cn = dataSource.getConnection();
			sql = "DELETE FROM typePoste WHERE id = ? ";
			stmt = cn.prepareStatement( sql );
			stmt.setObject( 1, id );
			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}
	}

	
	public TypePoste retrouver( int id ) {

		Connection			cn 		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();
			sql = "SELECT * FROM typePoste WHERE id = ?";
			stmt = cn.prepareStatement( sql );
			stmt.setObject(1, id);
			rs = stmt.executeQuery();

			if ( rs.next() ) {
				return construireTypePoste( rs );
			} else {
				return null;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}


	public List<TypePoste> listerTout() {

		Connection			cn 		= null;
		PreparedStatement	stmt 	= null;
		ResultSet 			rs		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();
			sql = "SELECT * FROM typePoste ORDER BY nom";
			stmt = cn.prepareStatement( sql );
			rs = stmt.executeQuery();

			List<TypePoste> typePostes = new LinkedList<>();
			while (rs.next()) {
				typePostes.add( construireTypePoste( rs ) );
			}
			return typePostes;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}
	
	
	// Méthodes auxiliaires
	
	private TypePoste construireTypePoste( ResultSet rs ) throws SQLException {
		TypePoste typePoste = new TypePoste();
		typePoste.setId( rs.getObject( "id", Integer.class ) );
		typePoste.setNom( rs.getObject( "nom", String.class ) );
		return typePoste;
	}

}
