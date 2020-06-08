package projet.view.volunteer;

import javax.inject.Inject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import jfox.commun.exception.ExceptionValidation;
import jfox.javafx.util.UtilFX;
import projet.commun.IMapper;
import projet.dao.DaoBenevole;
import projet.data.Benevole;
import projet.view.maquet.Message;


public class ModelVolunteer {
	
	
	// Données observables 
	
	private final ObservableList<Benevole> liste = FXCollections.observableArrayList();
	
	private final Benevole		courant = new Benevole();
	
	ObservableList<Message> listeM = FXCollections.observableArrayList();
	
	// Autres champs
	
    @Inject
	private IMapper		        mapper;
    @Inject
	private DaoBenevole			daoBenevole;
	
	
	// Getters 
	
	public ObservableList<Benevole> getListe() {
		return liste;
	}
	
	public Benevole getCourant() {
		return courant;
	}
/*
//	public ObservableList<Categorie> getCategories() {
//		return/* modelCategorie.getListe();*/
//	}*/

	
	// Actualisations
	
	public void actualiserListe() {
		liste.setAll( daoBenevole.listerTout() );
	}

	
	
	// Actions
	
	public void preparerAjouter() {
		//modelCategorie.actualiserListe();
		mapper.update( courant, new Benevole() );
	}
	

	public void preparerModifier( Benevole item ) {
		mapper.update( courant, daoBenevole.retrouver( item.getId() ) );
	}
	
	public void preparerAffichage( Benevole item ) {
		mapper.update( courant, daoBenevole.retrouver( item.getId() ) );
	}
	

	public void validerMiseAJour() {

		// Verifie la validite des donnees
		
		StringBuilder message = new StringBuilder();
		
		if( courant.getNom() == null || courant.getNom().isEmpty() ) {
			message.append( "\nLe nom ne doit pas etre vide." );
		} else  if ( courant.getNom().length()> 25 ) {
			message.append( "\nLe nom est trop long." );
		}

		if( courant.getPrenom() == null || courant.getPrenom().isEmpty() ) {
			message.append( "\nLe prenom ne doit pas etre vide." );
		} else if ( courant.getPrenom().length()> 25 ) {
			message.append( "\nLe prenom est trop long." );
		}
		
		if( courant.getNumTel() == null || courant.getNumTel().isEmpty() ) {
			message.append( "\nVeuillez indiquer votre num�ro de telephone." );
		} else  if ( courant.getNom().length()> 25 ) {
			message.append( "\nLe numero est trop long." );
		}

//		if( courant.getCategorie() == null ) {
//			message.append( "\nLe categorie doit etre indiquée." );
//		}
		
		if ( message.length() > 0 ) {
			throw new ExceptionValidation( message.toString().substring(1) );
		}

		
		// Effectue la mise a jour
		
		if ( courant.getId() == null ) {
			// Insertion
			courant.setId( daoBenevole.inserer( courant ) );
		} else {
			// modificiation
			daoBenevole.modifier( courant );
		}
	}
	

	public void supprimer( Benevole item ) {
		daoBenevole.supprimer( item.getId() );
		mapper.update( courant, UtilFX.findNext( liste, item ) );
	}

	public ObservableList<Message> getListeMessages() {
		for(Benevole b : liste) {
			try {
				if (b.getHeureD().isAfter(b.getHeureF())) {
					listeM.add(new Message("Les horaires du benevole "+b.getNom()+" sont erronees."));
				}
			} catch (Exception e) {}
		}
		return listeM;
	}
	

//	public void ajouterTelephone() {
//		courant.getTelephones().add( new Telephone() );
//	}
//	
//
//	public void supprimerTelephone( Telephone telephone )  {
//		courant.getTelephones().remove( telephone );
//	}
//	
}
