package projet.view.maquet;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import javafx.application.Platform;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import jfox.commun.exception.ExceptionValidation;
import projet.dao.DaoCompte2;
import projet.data.Compte;


public class ModelConnexion {
	
	// Logger
	public static final Logger logger = Logger.getLogger( ModelConnexion.class.getName() );
	
	
	// Données observables 
	
	// Vue connexion
	private final Compte			courant = new Compte();

	// Compte connecté
	private final Property<Compte>	compteActif = new SimpleObjectProperty<>();

	
	// Autres champs
	@Inject
	private DaoCompte2	daoCompte;
	

	// Getters 
	
	public Compte getCourant() {
		return courant;
	}
	
	public Property<Compte> compteActifProperty() {
		return compteActif;
	}
	
	public Compte getCompteActif() {
		return compteActif.getValue();
	}
	
	
	// Initialisation
	
//	@PostConstruct
//	public void init() {
//		courant.setPseudo( "    " );
//		courant.setMotDePasse( "   " );
//	}
	
	
	// Actions


	public void ouvrirSessionUtilisateur() {

		Compte compte = daoCompte.validerAuthentification(
					courant.pseudoProperty().getValue(), courant.motDePasseProperty().getValue() );
		
		if( compte == null ) {
			throw new ExceptionValidation( "Pseudo ou mot de passe invalide." );
		} else {
			Platform.runLater( () -> compteActif.setValue( compte ) );
		}
	}
	

	public void fermerSessionUtilisateur() {
		compteActif.setValue( null );
	}

}