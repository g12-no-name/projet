package projet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.sql.DataSource;

import jfox.dao.jdbc.UtilJdbc;
import projet.data.Participant;


public class DaoParticipant {

	
	// Champs

	@Inject
	private DataSource		dataSource;
	@Inject
	private DaoEquipe		daoEquipe;

	
	// Actions

	public void inserer( Participant participant ) {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs 		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();
			sql = "INSERT INTO participant ( nom, prenom, dateNaissance, mail, numTel, adresse, ville, mineur, autorisationParentale, club, capitaine, paiement, attestation, dossierMedic, equipe ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			stmt = cn.prepareStatement( sql, Statement.RETURN_GENERATED_KEYS );
				stmt.setObject( 1, participant.getNom() );
				stmt.setObject( 2, participant.getPrenom() );
				stmt.setObject( 3, participant.getDateNaissance() );
				stmt.setObject( 4, participant.getMail() );
				stmt.setObject( 5, participant.getNumTel() );
				stmt.setObject( 6, participant.getAdresse() );
				stmt.setObject( 7, participant.getVille() );
				stmt.setObject( 8, participant.getMineur() );
				stmt.setObject( 9, participant.getAutorisationParentale() );
				stmt.setObject( 10, participant.getClub() );
				stmt.setObject( 11, participant.getCapitaine() );
				stmt.setObject( 12, participant.getPaiement() );
				stmt.setObject( 13, participant.getAttestation() );
				stmt.setObject( 14, participant.getDossierMedic() );
				stmt.setObject( 15, participant.getEquipe().getId() );
				stmt.executeUpdate();

				// Récupère l'identifiant généré par le SGBD
				rs = stmt.getGeneratedKeys();
				rs.next();
				participant.setId( rs.getObject( 1, Integer.class ) );
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}
	}


	public void modifier(Participant participant)  {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		String 				sql;

		try {
			cn = dataSource.getConnection();

			// Modifie l'equipe
			sql = "UPDATE participant SET nom = ?, prenom = ?, dateNaissance = ?, mail = ?, numTel = ?, adresse = ?, ville = ?, mineur = ?, autorisationParentale = ?, club = ?, capitaine = ?, paiement = ?, attestation =?, dossierMedic = ?, equipe = ? WHERE id =  ?";
			stmt = cn.prepareStatement( sql );
			stmt.setObject( 1, participant.getNom() );
			stmt.setObject( 2, participant.getPrenom() );
			stmt.setObject( 3, participant.getDateNaissance() );
			stmt.setObject( 4, participant.getMail() );
			stmt.setObject( 5, participant.getNumTel() );
			stmt.setObject( 6, participant.getAdresse() );
			stmt.setObject( 7, participant.getVille() );
			stmt.setObject( 8, participant.getMineur() );
			stmt.setObject( 9, participant.getAutorisationParentale() );
			stmt.setObject( 10, participant.getClub() );
			stmt.setObject( 11, participant.getCapitaine() );
			stmt.setObject( 12, participant.getPaiement() );
			stmt.setObject( 13, participant.getAttestation() );
			stmt.setObject( 14, participant.getDossierMedic() );
			stmt.setObject( 15, participant.getEquipe().getId() );
			stmt.setObject( 16, participant.getId());
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
			sql = "DELETE FROM participant WHERE id = ? ";
			stmt = cn.prepareStatement(sql);
			stmt.setObject( 1, id );
			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}
	}

	public void supprimerPourEquipe( int idEquipe ) {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		String 				sql;

		try {
			cn = dataSource.getConnection();

			// Supprime les participants
			sql = "DELETE FROM participant  WHERE equipe = ? ";
			stmt = cn.prepareStatement(sql);
			stmt.setObject( 1, idEquipe );
			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close(  stmt, cn );
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
	
	public Participant retrouver(int id)  {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs 		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();

			sql = "SELECT * FROM participant WHERE id = ?";
            stmt = cn.prepareStatement(sql);
            stmt.setObject( 1, id);
            rs = stmt.executeQuery();

            if ( rs.next() ) {
                return construireParticipant(rs, true );
            } else {
            	return null;
            }
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}
	
	public List<Participant> listerTout()   {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs 		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();

			sql = "SELECT * FROM participant ORDER BY nom, prenom";
			stmt = cn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			List<Participant> participants = new ArrayList<>();
			while (rs.next()) {
				participants.add( construireParticipant(rs, false) );
			}
			return participants;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}
	
	// Méthodes auxiliaires
	
	private Participant construireParticipant( ResultSet rs, Boolean flagComplet ) throws SQLException {
		Participant participant = new Participant();
		participant.setId(rs.getObject( "id", Integer.class ));
		participant.setNom(rs.getObject( "nom", String.class ));
		participant.setPrenom(rs.getObject( "prenom", String.class ));
		participant.setDateNaissance(rs.getObject("dateNaissance", LocalDate.class));
		participant.setMail(rs.getObject("mail", String.class));
		participant.setNumTel(rs.getObject("numTel", String.class));
		participant.setAdresse(rs.getObject("adresse", String.class));
		participant.setVille(rs.getObject("ville", String.class));
		participant.setMineur(rs.getObject("mineur", Boolean.class));
		participant.setAutorisationParentale(rs.getObject("autorisationParentale", Boolean.class));
		participant.setClub(rs.getObject("club", String.class));
		participant.setCapitaine(rs.getObject("capitaine", Boolean.class));
		participant.setPaiement(rs.getObject("paiement", Boolean.class));
		participant.setAttestation(rs.getObject("attestation", Boolean.class));
		participant.setDossierMedic(rs.getObject("dossierMedic", Boolean.class));
		participant.setEquipe(daoEquipe.retrouver(rs.getObject("equipe", Integer.class)));
		return participant;
	}

}
