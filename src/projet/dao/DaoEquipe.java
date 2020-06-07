package projet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.sql.DataSource;

import jfox.dao.jdbc.UtilJdbc;
import projet.data.Equipe;
import projet.data.Participant;
import projet.dao.DaoParticipant;

public class DaoEquipe {

	
	// Champs

	@Inject
	private DataSource		dataSource;
	@Inject
	private DaoCategorieCourse daoCategorieCourse;
	@Inject
	private DaoTypeCourse daoTypeCourse;
	@Inject
	private DaoParticipant daoParticipant;


	
	// Actions

	public int inserer(Equipe equipe)  {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs 		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();

			// Insère l'equipe
			sql = "INSERT INTO equipe ( nom, nbBouffe,typeCourse, catCourse ) VALUES ( ?, ?, ?, ? )";
			stmt = cn.prepareStatement( sql, Statement.RETURN_GENERATED_KEYS  );
			stmt.setString(	1, equipe.getNom() );
			stmt.setInt(	2, equipe.getNbBouffe() );
			stmt.setInt(	3, equipe.getTypeCourse().getId() );
			stmt.setInt(	4, equipe.getCatCourse().getId() );
			stmt.executeUpdate();

			// Récupère l'identifiant généré par le SGBD
			rs = stmt.getGeneratedKeys();
			rs.next();
			equipe.setId( rs.getObject( 1, Integer.class ) );
	
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}
		
		// Retourne l'identifiant
		return equipe.getId();
	}

	
	public void modifier(Equipe equipe)  {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		String 				sql;

		try {
			cn = dataSource.getConnection();

			// Modifie l'equipe
			sql = "UPDATE equipe SET nom = ?, nbBouffe = ?, typeCourse = ?, catCourse = ? WHERE id =  ?";
			stmt = cn.prepareStatement( sql );
			stmt.setObject( 1, equipe.getNom() );
			stmt.setObject( 2, equipe.getNbBouffe() );
			stmt.setObject( 3, equipe.getTypeCourse().getId() );
			stmt.setObject( 4, equipe.getCatCourse().getId() );
			stmt.setObject( 5, equipe.getId() );
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

		// Supprime les participants
		daoParticipant.supprimerPourEquipe( id );

		try {
			cn = dataSource.getConnection();

			// Supprime l'equipe
			sql = "DELETE FROM equipe WHERE id = ? ";
			stmt = cn.prepareStatement(sql);
			stmt.setObject( 1, id );
			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}
	}

	
	public Equipe retrouver(int id)  {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs 		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();

			sql = "SELECT * FROM equipe WHERE id = ?";
            stmt = cn.prepareStatement(sql);
            stmt.setObject( 1, id);
            rs = stmt.executeQuery();

            if ( rs.next() ) {
                return construireEquipe(rs, true );
            } else {
            	return null;
            }
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}

	
	public List<Equipe> listerTout()   {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs 		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();

			sql = "SELECT * FROM equipe ORDER BY nom";
			stmt = cn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			List<Equipe> equipes = new ArrayList<>();
			while (rs.next()) {
				equipes.add( construireEquipe(rs, true) );
			}
			return equipes;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}
	
	
	public List<Participant> listParticipant(int idEquipe){
		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs 		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();

			sql = "SELECT * FROM participant WHERE equipe = ?";
            stmt = cn.prepareStatement(sql);
            stmt.setObject(1, idEquipe);
            rs = stmt.executeQuery();
			
			List<Participant> participants = new ArrayList<>();
			while (rs.next()) {
				participants.add( daoParticipant.construireParticipant(rs, true));
			}
			return participants;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
		
		
		//
		
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
	
// Methodes auxiliaires
	
	private Equipe construireEquipe( ResultSet rs, boolean flagComplet ) throws SQLException {

		Equipe equipe = new Equipe();
		equipe.setId(rs.getObject( "id", Integer.class ));
		equipe.setNom(rs.getObject( "nom", String.class ));
		equipe.setNbBouffe(rs.getObject( "nbBouffe", Integer.class ));
		if ( flagComplet ) {
			equipe.setTypeCourse(daoTypeCourse.retrouver(rs.getObject("id", Integer.class)));
			equipe.setCatCourse(daoCategorieCourse.retrouver(rs.getObject("id", Integer.class)));
		}
		
		return equipe;
	}

}
