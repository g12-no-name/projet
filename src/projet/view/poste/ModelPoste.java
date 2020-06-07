package projet.view.poste;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import jfox.commun.exception.ExceptionValidation;
import jfox.javafx.util.UtilFX;
import projet.commun.IMapper;
import projet.dao.DaoPoste;
import projet.data.Assignation;
import projet.data.Poste;
import projet.data.TypePoste;


public class ModelPoste  {
	
	
	// Donnees observables 
	
	private final ObservableList<Poste> liste = FXCollections.observableArrayList(); 
	
	private final Poste	courant = new Poste();

	
	// Autres champs
    @Inject
	private IMapper			mapper;
    @Inject
	private DaoPoste			daoPoste;
    @Inject
    private ModelTypePoste		modelTypePoste;
	
    
	// Initialisations
	
	@PostConstruct
	public void init() {}
	
	
	// Getters 
	
	public ObservableList<Poste> getListe() {
		return liste;
	}
	
	public Poste getCourant() {
		return courant;
	}

	
	public ObservableList<TypePoste> getTypePoste() {
		return modelTypePoste.getListe();
	}
	
	// Actualisations
	
	public void actualiserListe() {
		liste.setAll( daoPoste.listerTout() );
 	}


	// Actions
	
	public void preparerAjouter() {
		modelTypePoste.actualiserListe();
		mapper.update( courant, new Poste() );
		
	}
	
	public void prepAddFromPP(double x ,double y) {
		modelTypePoste.actualiserListe();
		mapper.update( courant, new Poste() );
		courant.setX((int)x);
		courant.setY((int)y);
	}
	
	public void preparerModifier( Poste item ) {
		modelTypePoste.actualiserListe();
		mapper.update( courant, daoPoste.retrouver( item.getId() ) );
	}
	
	
	public void validerMiseAJour() {

		// Vérifie la validité des données
		
		StringBuilder message = new StringBuilder();

		if( courant.getNom() == null || courant.getNom().isEmpty() ) {
			message.append( "\nLe titre ne doit pas etre vide." );
		} else  if ( courant.getNom().length()> 50 ) {
			message.append( "\nLe titre est trop long : 50 maxi." );
		}

		if ( message.length() > 0 ) {
			throw new ExceptionValidation( message.toString().substring(1) );
		}
		
		
		// Effectue la mise à jour
		
		if ( courant.getId() == null ) {
			// Insertion
			courant.setId( daoPoste.inserer( courant ) );
		} else {
			// modficiation
			daoPoste.modifier( courant );
		}
		
	}
	
	
	public void supprimer( Poste item ) {
		
		daoPoste.supprimer( item.getId() );
		mapper.update( courant, UtilFX.findNext( liste, item ) );
		
		//getFichierSchemaCourant().delete();
	}

	
	public void supprimerBenevole( Assignation item ) {
		courant.getBenevoles().remove(item);
	}
	
	public void ajouterBenevole( Assignation item ) {
		courant.getBenevoles().add(item);
	}
	
	
}
