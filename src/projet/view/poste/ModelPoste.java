package projet.view.poste;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import jfox.commun.exception.ExceptionValidation;
import jfox.javafx.util.UtilFX;
import projet.commun.IMapper;
import projet.dao.DaoAssignation;
import projet.dao.DaoPoste;
import projet.data.Assignation;
import projet.data.Benevole;
import projet.data.Poste;
import projet.data.TypePoste;
import projet.view.maquet.Message;


public class ModelPoste  {
	
	
	// Donnees observables 
	
	private final ObservableList<Poste> liste = FXCollections.observableArrayList(); 
	private final ObservableList<Assignation> listeA = FXCollections.observableArrayList(); 
	
	private final Poste	courant = new Poste();

	
	// Autres champs
    @Inject
	private IMapper			mapper;
    @Inject
	private DaoPoste			daoPoste;
    @Inject
	private DaoAssignation			daoAssignation;
    @Inject
    private ModelTypePoste		modelTypePoste;
    @Inject
    private ModelAssignation	modelAssignation;
    ObservableList<Message> listeM = FXCollections.observableArrayList();
	
    
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
	
	public ObservableList<Assignation> getListeA() {
		return listeA;
	}
	
	// Actualisations
	
	public void actualiserListe() {
		liste.setAll( daoPoste.listerTout() );
 	}
	
	public void actualiserListeA() {
		listeA.setAll( daoAssignation.listerAssignationPoste(courant.getId()) );
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
		modelAssignation.actualiserListePoste(item.getId());
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


	public ObservableList<Message> getListeMessages() {
		for(Poste p : liste) {
			try {p.getBenevoles().get(0);
			}catch(Exception e) {listeM.add(new Message("Le poste " + p.getNom()+" n'a pas de benevoles."));}
			try {
				int x=p.getX();
				if (x<=0) {
					listeM.add(new Message("Le poste " + p.getNom()+" n'a pas de position sur la carte."));
				}
			}catch(Exception e) {listeM.add(new Message("Le poste " + p.getNom()+" n'a pas de position sur la carte."));}
		}
		return listeM;
	}
	
	
}
