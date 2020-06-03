package projet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.sql.DataSource;

import jfox.dao.jdbc.UtilJdbc;
import projet.data.Assignation;


public class DaoAssignation {

	
	// Champs

	@Inject
	private DataSource		dataSource;
	@Inject
	private DaoPoste 		daoPoste;
	@Inject
	private DaoBenevole 	daoBenevole;


	
	// Actions

	public int inserer(Assignation assignation)  {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs 		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();

			// Insère l'equipe
			sql = "INSERT INTO assignation ( idBenevole, idPoste,heureD, heureF ) VALUES (?, ?, ?, ? )";
			stmt = cn.prepareStatement( sql, Statement.RETURN_GENERATED_KEYS  );
			stmt.setInt(	1, assignation.getBenevole().getId() );
			stmt.setInt(	2, assignation.getPoste().getId() );
			stmt.setObject(	3, assignation.getHeureD() );
			stmt.setObject(	4, assignation.getHeureF());
			stmt.executeUpdate();

			// Récupère l'identifiant généré par le SGBD
			rs = stmt.getGeneratedKeys();
			rs.next();
			assignation.setId( rs.getObject( 1, Integer.class ) );
	
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}
		
		// Retourne l'identifiant
		return assignation.getId();
	}

	
	public void modifier(Assignation assignation)  {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		String 				sql;

		try {
			cn = dataSource.getConnection();

			// Modifie l'equipe
			sql = "UPDATE assignation SET idBenevole = ?, idPoste = ?, heureD = ?, heureF = ? WHERE id =  ?";
			stmt = cn.prepareStatement( sql );
			stmt.setInt(	1, assignation.getBenevole().getId() );
			stmt.setInt(	2, assignation.getPoste().getId() );
			stmt.setObject(	3, assignation.getHeureD() );
			stmt.setObject(	4, assignation.getHeureF());
			stmt.setObject(	5, assignation.getId());
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}
	}

	
	public void supprimer(int id)  {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		String 				sql;

		try {
			cn = dataSource.getConnection();

			// Supprime l'equipe
			sql = "DELETE FROM assignation WHERE id = ? ";
			stmt = cn.prepareStatement(sql);
			stmt.setObject( 1, id );
			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}
	}

	
	public Assignation retrouver(int id)  {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs 		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();

			sql = "SELECT * FROM assignation WHERE id = ?";
            stmt = cn.prepareStatement(sql);
            stmt.setObject( 1, id);
            rs = stmt.executeQuery();

            if ( rs.next() ) {
                return construireAssignation(rs, true );
            } else {
            	return null;
            }
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}

	
	public List<Assignation> listerTout()   {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs 		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();

			sql = "SELECT * FROM assignation ORDER BY id";
			stmt = cn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			List<Assignation> assignations = new ArrayList<>();
			while (rs.next()) {
				assignations.add( construireAssignation(rs, false) );
			}
			return assignations;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}
	
	public List<Assignation> listerAssignationPoste(int idPoste)   {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs 		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();

			sql = "SELECT * FROM assignation WHERE idPoste = ? ORDER BY id";
			stmt = cn.prepareStatement(sql);
            stmt.setObject( 1, idPoste);
			rs = stmt.executeQuery();
			
			List<Assignation> assignations = new ArrayList<>();
			while (rs.next()) {
				assignations.add( construireAssignation(rs, false) );
			}
			return assignations;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}

	
//	public List<Personne> listerPourMemo( int idMemo )   {
//
//		Connection			cn		= null;
//		PreparedStatement	stmt	= null;
//		ResultSet 			rs 		= null;
//		String				sql;
//
//		try {
//			cn = dataSource.getConnection();
//
//			sql = "SELECT p.* FROM personne p" 
//				+ " INNER JOIN concerner c ON p.idpersonne = c.idpersonne" 
//				+ " WHERE c.idmemo = ?" 
//				+ " ORDER BY nom, prenom";
//			stmt = cn.prepareStatement(sql);
//			stmt.setObject( 1, idMemo ); 
//			rs = stmt.executeQuery();
//			
//			List<Personne> personnes = new ArrayList<>();
//			while (rs.next()) {
//				personnes.add( construirePersonne(rs, false) );
//			}
//			return personnes;
//
//		} catch (SQLException e) {
//			throw new RuntimeException(e);
//		} finally {
//			UtilJdbc.close( rs, stmt, cn );
//		}
//	}

    
//    public int compterPourCategorie(int idCategorie) {
//    	
//		Connection			cn		= null;
//		PreparedStatement	stmt 	= null;
//		ResultSet 			rs		= null;
//
//		try {
//			cn = dataSource.getConnection();
//            String sql = "SELECT COUNT(*) FROM personne WHERE idcategorie = ?";
//            stmt = cn.prepareStatement( sql );
//            stmt.setObject( 1, idCategorie );
//            rs = stmt.executeQuery();
//
//            rs.next();
//            return rs.getInt( 1 );
//
//		} catch (SQLException e) {
//			throw new RuntimeException(e);
//		} finally {
//			UtilJdbc.close( rs, stmt, cn );
//		}
//    }
//	
	
// Méthodes auxiliaires
	
	private Assignation construireAssignation( ResultSet rs, boolean flagComplet ) throws SQLException {

		Assignation assignation = new Assignation();
		assignation.setId(rs.getObject( "id", Integer.class ));
		assignation.setBenevole(daoBenevole.retrouver(rs.getObject( "id", Integer.class )));
		assignation.setPoste(daoPoste.retrouver(rs.getObject( "id", Integer.class )));
		assignation.setHeureD(rs.getObject("heureD", LocalTime.class));
		assignation.setHeureF(rs.getObject("heureF", LocalTime.class));
//		if ( flagComplet ) {
//			equipe.setTypeCourse(daoTypeCourse.retrouver(rs.getObject("id", Integer.class)));
//			equipe.setCatCourse(daoCategorieCourse.retrouver(rs.getObject("id", Integer.class)));
//		}
		
		return assignation;
	}

}
