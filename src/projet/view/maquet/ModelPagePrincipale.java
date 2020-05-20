package projet.view.maquet;

import java.io.File;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import jfox.javafx.util.UtilFX;
import projet.commun.IMapper;
import projet.dao.DaoBenevole;
import projet.dao.DaoPersonne;
import projet.dao.DaoPoste;
import projet.data.Benevole;
import projet.data.Memo;
import projet.data.Poste;
import projet.view.systeme.ModelConfig;


public class ModelPagePrincipale {
	
	//autre champs 
	
	@Inject
	private DaoBenevole		daobenevole;
	@Inject
	private DaoPoste       daoposte;
	@Inject
	private IMapper			mapper;
	@Inject
    private ModelConfig		modelConfig;
	
	
	// donnés observables
	
	private final Benevole	courant = new Benevole();
	
	private final Poste 	courant2 = new Poste();
	
	private final ObservableList<Benevole> liste = FXCollections.observableArrayList();
	
	private final ObservableList<Poste>    liste2 = FXCollections.observableArrayList();
	
	private final Property<Image>	schema = new SimpleObjectProperty<>();
	
	private boolean		flagModifSchema;

	public ModelPagePrincipale() {
		// TODO Auto-generated constructor stub
	}
	
	
	// Initialisations
	
		@PostConstruct
		public void init() {
			schema.addListener( obs -> flagModifSchema = true );
		}
		
	// Actualisations
		
		public void actualiserListe() {
			liste.setAll(daobenevole.listerTout() );
	 	}
		
		public void actualiserListe2() {
			liste2.setAll(daoposte.listerTout() );
	 	}
		
		// Getters 
		
		public ObservableList<Benevole> getListe() {
			return liste;
		}
		
		public ObservableList<Poste>   getListe2() {
			return liste2;
		}
		
		public Benevole getCourant() {
			return courant;
		}
		
		public Poste getCourant2() {
			return courant2;
		}
		
	// Action
		
		public void preparerAjouter() {
			mapper.update( courant, new Benevole() );
			schema.setValue(null);
			flagModifSchema = false;
		}
		
		public void preparerAjouter2() {
			mapper.update( courant2, new Poste() );
			schema.setValue(null);
			flagModifSchema = false;
		}
		
		public void supprimer( Benevole item ) {
			
			daobenevole.supprimer( item.getId() );
			mapper.update( courant, UtilFX.findNext( liste, item ) );
			
			getFichierSchemaCourant().delete();
		}
		
       public void supprimer2( Poste item ) {
			
			daoposte.supprimer( item.getId() );
			mapper.update( courant2, UtilFX.findNext( liste2, item ) );
			
			getFichierSchemaCourant().delete();
		}
		
		// Méthodes auxiliaires
		
		public File getFichierSchemaCourant() {
			String nomFichier = String.format( "%06d.jpg", courant.getId() );
			File dossierSchemas = modelConfig.getDossierSchemas();
			return new File( dossierSchemas, nomFichier );
		}
		
		public File getFichierSchemaCourant2() {
			String nomFichier = String.format( "%06d.jpg", courant2.getId() );
			File dossierSchemas = modelConfig.getDossierSchemas();
			return new File( dossierSchemas, nomFichier );
		}

}
