package projet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.sql.DataSource;

import jfox.dao.jdbc.UtilJdbc;
import projet.data.Benevole;


public class DaoBenevole {

	
	// Champs

	@Inject
	private DataSource		dataSource;

	
	// Actions

	public int inserer( Benevole benevole ) {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs 		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();
			sql = "INSERT INTO benevole ( nom, prenom, dateNaissance, mail, numTel, adresse, ville, mineur, autorisationParentale, club, capitaine, paiement, attestation, dossierMedic, equipe ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
			stmt = cn.prepareStatement( sql, Statement.RETURN_GENERATED_KEYS );
				stmt.setObject( 1, benevole.getNom() );
				stmt.setObject( 2, benevole.getPrenom() );
				stmt.setObject( 3, benevole.getDateNaissance() );
				stmt.setObject( 4, benevole.getMail() );
				stmt.setObject( 5, benevole.getNumTel() );
				stmt.setObject( 6, benevole.getAdresse() );
				stmt.setObject( 7, benevole.getVille() );
				stmt.setObject( 8, benevole.getMineur() );
				stmt.setObject( 9, benevole.getAutorisationParentale() );
				stmt.setObject( 10, benevole.getPermis() );
				stmt.setObject( 11, benevole.getMembre() );
				stmt.setObject( 12, benevole.getHeureD() );
				stmt.setObject( 13, benevole.getHeureF() );
				stmt.executeUpdate();

				// Récupère l'identifiant généré par le SGBD
				rs = stmt.getGeneratedKeys();
				rs.next();
				benevole.setId( rs.getObject( 1, Integer.class ) );
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}
		return benevole.getId();
	}


	public void modifier(Benevole benevole)  {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		String 				sql;

		try {
			cn = dataSource.getConnection();

			// Modifie l'equipe
			sql = "UPDATE benevole SET nom = ?, prenom = ?, dateNaissance = ?, mail = ?, numTel = ?, adresse = ?, ville = ?, mineur = ?, autorisationParentale = ?, permis = ?, membre = ?, heureD = ?, heureF =? WHERE id =  ?";
			stmt = cn.prepareStatement( sql );
			stmt.setObject( 1, benevole.getNom() );
			stmt.setObject( 2, benevole.getPrenom() );
			stmt.setObject( 3, benevole.getDateNaissance() );
			stmt.setObject( 4, benevole.getMail() );
			stmt.setObject( 5, benevole.getNumTel() );
			stmt.setObject( 6, benevole.getAdresse() );
			stmt.setObject( 7, benevole.getVille() );
			stmt.setObject( 8, benevole.getMineur() );
			stmt.setObject( 9, benevole.getAutorisationParentale() );
			stmt.setObject( 10, benevole.getPermis() );
			stmt.setObject( 11, benevole.getMembre() );
			stmt.setObject( 12, benevole.getHeureD() );
			stmt.setObject( 13, benevole.getHeureF() );
			stmt.setObject( 14, benevole.getId());
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

			// Supprime le personne
			sql = "DELETE FROM benevole WHERE id = ? ";
			stmt = cn.prepareStatement(sql);
			stmt.setObject( 1, id );
			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}
	}


//	public List<Telephone> listerPourPersonne( Personne personne ) {
//
//		Connection			cn		= null;
//		PreparedStatement	stmt	= null;
//		ResultSet 			rs 		= null;
//		String				sql;
//
//		try {
//			cn = dataSource.getConnection();
//
//			sql = "SELECT * FROM telephone WHERE idpersonne = ? ORDER BY libelle";
//			stmt = cn.prepareStatement(sql);
//			stmt.setObject( 1, personne.getId() );
//			rs = stmt.executeQuery();
//
//			List<Telephone> telephones = new ArrayList<>();
//			while (rs.next()) {
//				telephones.add( construireTelephone( rs, personne ) );
//			}
//			return telephones;
//
//		} catch (SQLException e) {
//			throw new RuntimeException(e);
//		} finally {
//			UtilJdbc.close( rs, stmt, cn );
//		}
//	}
	
	public Benevole retrouver(int id)  {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs 		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();

			sql = "SELECT * FROM benevole WHERE id = ?";
            stmt = cn.prepareStatement(sql);
            stmt.setObject( 1, id);
            rs = stmt.executeQuery();

            if ( rs.next() ) {
                return construireBenevole(rs, true );
            } else {
            	return null;
            }
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}
	
	public List<Benevole> listerTout()   {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs 		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();

			sql = "SELECT * FROM benevole ORDER BY nom, prenom";
			stmt = cn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			List<Benevole> benevoles = new ArrayList<>();
			while (rs.next()) {
				benevoles.add( construireBenevole(rs, false) );
			}
			return benevoles;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}
	
	// Méthodes auxiliaires
	
	private Benevole construireBenevole( ResultSet rs, Boolean flagComplet ) throws SQLException {
		Benevole benevole = new Benevole();
		benevole.setId(rs.getObject( "id", Integer.class ));
		benevole.setNom(rs.getObject( "nom", String.class ));
		benevole.setPrenom(rs.getObject( "prenom", String.class ));
		benevole.setDateNaissance(rs.getObject("dateNaissance", LocalDate.class));
		benevole.setMail(rs.getObject("mail", String.class));
		benevole.setNumTel(rs.getObject("numTel", String.class));
		benevole.setAdresse(rs.getObject("adresse", String.class));
		benevole.setVille(rs.getObject("ville", String.class));
		benevole.setMineur(rs.getObject("mineur", Boolean.class));
		benevole.setAutorisationParentale(rs.getObject("autorisationParentale", Boolean.class));
		benevole.setPermis(rs.getObject("permis", Boolean.class));
		benevole.setMembre(rs.getObject("membre", Boolean.class));
		benevole.setHeureD(rs.getObject("heureD", LocalTime.class));
		benevole.setHeureF(rs.getObject("heureF", LocalTime.class));
		return benevole;
	}

}
