package projet.view.poste;

import javax.inject.Inject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import jfox.commun.exception.ExceptionValidation;
import projet.commun.IMapper;
import projet.dao.DaoTypePoste;

import projet.data.TypePoste;



public class ModelTypePoste  {
	
	
	// Données observables 
	
	private final ObservableList<TypePoste> liste = FXCollections.observableArrayList(); 
	
	private final TypePoste	courant = new TypePoste();

	
	// Autres champs
    @Inject
	private IMapper			mapper;
    @Inject
	private DaoTypePoste	daoTypePoste;
    
	
	
	// Getters 
	
	public ObservableList<TypePoste> getListe() {
		return liste;
	}
	
	public TypePoste getCourant() {
		return courant;
	}
	
	
	// Actualisations
	
	public void actualiserListe() {
		liste.setAll( daoTypePoste.listerTout() );
 	}


	// Actions
	
	public void preparerAjouter() {
		mapper.update( courant, new TypePoste() );
	}
	
	public void preparerModifier( TypePoste item ) {
		mapper.update( courant, daoTypePoste.retrouver( item.getId() ) );
	}
	
	
	public void validerMiseAJour() {

		// Vérifie la validité des données
		
		StringBuilder message = new StringBuilder();

		if( courant.getNom() == null || courant.getNom().isEmpty() ) {
			message.append( "\nLe nom ne doit pas être vide." );
		} else  if ( courant.getNom().length()> 25 ) {
			message.append( "\nLe nom est trop long : 25 maxi." );
		}
		
		if ( message.length() > 0 ) {
			throw new ExceptionValidation( message.toString().substring(1) );
		}
		
		
		// Effectue la mise à jour
		
		if ( courant.getId() == null ) {
			// Insertion
			courant.setId( daoTypePoste.inserer( courant ) );
		} else {
			// modficiation
			daoTypePoste.modifier( courant );
		}
	}
	
	
//	public void supprimer( TypePoste item ) {
//		
//		// Vérifie l'abence de postes rattachées à la catégorie
//		if ( daoPersonne.compterPourTypePoste( item.getId() ) != 0 ) {
//			throw new ExceptionValidation( "Des personnes sont rattachées à cette catégorie.." ) ;
//		}
//		
//		// Vérifie l'abence de mémos rattaches à la catégorie
//		if ( daoMemo.compterPourTypePoste( item.getId() ) != 0 ) {
//			throw new ExceptionValidation( "Des mémos sont rattachés à cette catégorie.." ) ;
//		}
//		
//		daoTypePoste.supprimer( item.getId() );
//		mapper.update( courant, UtilFX.findNext( liste, item ) );
//	}
	
}
