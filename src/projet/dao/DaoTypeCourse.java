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
import projet.data.TypeCourse;


public class DaoTypeCourse {

	
	// Champs

	@Inject
	private DataSource		dataSource;

	
	// Actions

	public int inserer( TypeCourse typeCourse ) {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();
			sql = "INSERT INTO typeCourse ( nom ) VALUES( ? ) ";
			stmt = cn.prepareStatement( sql, Statement.RETURN_GENERATED_KEYS );
			stmt.setObject( 1, typeCourse.getNom() );
			stmt.executeUpdate();

			// Récupère l'identifiant généré par le SGBD
			rs = stmt.getGeneratedKeys();
			rs.next();
			typeCourse.setId( rs.getObject( 1, Integer.class) );
			return typeCourse.getId();
	
		} catch ( SQLException e ) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}


	public void modifier( TypeCourse typeCourse ) {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		String				sql;

		try {
			cn = dataSource.getConnection();
			sql = "UPDATE typeCourse SET nom = ? WHERE id =  ?";
			stmt = cn.prepareStatement( sql );
			stmt.setObject( 1, typeCourse .getNom() );
			stmt.setObject( 2, typeCourse.getId() );
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
			sql = "DELETE FROM typeCourse WHERE id = ? ";
			stmt = cn.prepareStatement( sql );
			stmt.setObject( 1, id );
			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}
	}

	
	public TypeCourse retrouver( int id ) {

		Connection			cn 		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();
			sql = "SELECT * FROM typeCourse WHERE id = ?";
			stmt = cn.prepareStatement( sql );
			stmt.setObject(1, id);
			rs = stmt.executeQuery();

			if ( rs.next() ) {
				return construireTypeCourse( rs );
			} else {
				return null;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}


	public List<TypeCourse> listerTout() {

		Connection			cn 		= null;
		PreparedStatement	stmt 	= null;
		ResultSet 			rs		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();
			sql = "SELECT * FROM typeCourse ORDER BY nom";
			stmt = cn.prepareStatement( sql );
			rs = stmt.executeQuery();

			List<TypeCourse> typeCourses = new LinkedList<>();
			while (rs.next()) {
				typeCourses.add( construireTypeCourse( rs ) );
			}
			return typeCourses;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}
	
	
	// Methodes auxiliaires
	
	private TypeCourse construireTypeCourse( ResultSet rs ) throws SQLException {
		TypeCourse typeCourse = new TypeCourse();
		typeCourse.setId( rs.getObject( "id", Integer.class ) );
		typeCourse.setNom( rs.getObject( "nom", String.class ) );
		return typeCourse;
	}

}
