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
import projet.data.Poste;


public class DaoPoste {

	
	// Champs

	@Inject
	private DataSource		dataSource;
	@Inject
	private DaoTypePoste 	daoTypePoste;


	
	// Actions

	public int inserer(Poste poste)  {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs 		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();

			// Insère l'equipe
			sql = "INSERT INTO poste ( nom, typePoste ,heureD, heureF ) VALUES ( ?, ?, ?, ?, ? )";
			stmt = cn.prepareStatement( sql, Statement.RETURN_GENERATED_KEYS  );
			stmt.setString(	1, poste.getNom() );
			stmt.setInt(	2, poste.getTypePoste().getId() );
			stmt.setObject(	3, poste.getHeureD() );
			stmt.setObject(	4, poste.getHeureF());
			stmt.executeUpdate();

			// Récupère l'identifiant généré par le SGBD
			rs = stmt.getGeneratedKeys();
			rs.next();
			poste.setId( rs.getObject( 1, Integer.class ) );
	
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}
		
		// Retourne l'identifiant
		return poste.getId();
	}

	
	public void modifier(Poste poste)  {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		String 				sql;

		try {
			cn = dataSource.getConnection();

			// Modifie l'equipe
			sql = "UPDATE poste SET nom = ?, typePoste = ?, heureD = ?, heureF = ? WHERE id =  ?";
			stmt = cn.prepareStatement( sql );
			stmt.setString(	1, poste.getNom() );
			stmt.setInt(	2, poste.getTypePoste().getId() );
			stmt.setObject(	3, poste.getHeureD() );
			stmt.setObject(	4, poste.getHeureF());
			stmt.setObject(	5, poste.getId());
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
			sql = "DELETE FROM poste WHERE id = ? ";
			stmt = cn.prepareStatement(sql);
			stmt.setObject( 1, id );
			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}
	}

	
	public Poste retrouver(int id)  {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs 		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();

			sql = "SELECT * FROM poste WHERE id = ?";
            stmt = cn.prepareStatement(sql);
            stmt.setObject( 1, id);
            rs = stmt.executeQuery();

            if ( rs.next() ) {
                return construirePoste(rs, true );
            } else {
            	return null;
            }
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}

	
	public List<Poste> listerTout()   {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs 		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();

			sql = "SELECT * FROM poste ORDER BY nom";
			stmt = cn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			List<Poste> postes = new ArrayList<>();
			while (rs.next()) {
				postes.add( construirePoste(rs, false) );
			}
			return postes;

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
	
	private Poste construirePoste( ResultSet rs, boolean flagComplet ) throws SQLException {

		Poste poste = new Poste();
		poste.setId(rs.getObject( "id", Integer.class ));
		poste.setNom(rs.getObject( "nom", String.class ));
		poste.setTypePoste(daoTypePoste.retrouver(rs.getObject( "typePoste", Integer.class )));
		poste.setHeureD(rs.getObject("heureD", LocalTime.class));
		poste.setHeureF(rs.getObject("heureF", LocalTime.class));
//		if ( flagComplet ) {
//			equipe.setTypeCourse(daoTypeCourse.retrouver(rs.getObject("id", Integer.class)));
//			equipe.setCatCourse(daoCategorieCourse.retrouver(rs.getObject("id", Integer.class)));
//		}
		
		return poste;
	}

}
