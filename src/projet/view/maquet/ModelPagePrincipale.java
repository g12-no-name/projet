package projet.view.maquet;

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
import projet.dao.DaoPoste;
import projet.data.Benevole;
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
	
	
	// donnees observables
	
	private final Property<Image> carte= new SimpleObjectProperty<>();
	
	private final Benevole	courant = new Benevole();
	
	private final Poste 	courant2 = new Poste();
	
	private final ObservableList<Benevole> liste = FXCollections.observableArrayList();
	
	private final ObservableList<Poste>    liste2 = FXCollections.observableArrayList();
	

	
	//private final Property<Image>	schema = new SimpleObjectProperty<>();
	

	public ModelPagePrincipale() {}
	
	
	// Initialisations
	
		@PostConstruct
	public void init() {
		//schema.addListener( obs -> flagModifSchema = true );
		chargerImages();
		actualiserListe();
		actualiserListe2();
	}
		
	// Actualisations
		
	public void actualiserListe() {
		liste.setAll(daobenevole.listerTout() );
 	}
	
	public void actualiserListe2() {
		liste2.setAll(daoposte.listerTout() );
 	}
	
	// Getters 
	
	public Property<Image> imageCarteProperty() {
		return carte;
	}
	
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
	
	public void chargerImages() {  
		String cheminCarte = "Carte.jpg"; 
		carte.setValue( new Image(getClass().getResource( cheminCarte ).toExternalForm() ) );
	}
	
	public void preparerAjouter() {
		mapper.update( courant, new Benevole() );
	}
	
	public void preparerAjouter2(double x, double y) {
		courant2.setX((int)x);courant2.setY((int)y);
		mapper.update(  courant2, new Poste()); 
	}
	
	
	public void supprimer( Benevole item ) {
		daobenevole.supprimer( item.getId() );
		mapper.update( courant, UtilFX.findNext( liste, item ) );
		
	}
//		
	public void supprimer2( Poste item ) {
		daoposte.supprimer( item.getId() );
		mapper.update( courant2, UtilFX.findNext( liste2, item ) );
	}
	
	public ObservableList<Poste> getPostes(){
		return this.getListe2();
	}


	// Methodes auxiliaires
	
//		public File getFichierSchemaCourant() {
//			String nomFichier = String.format( "%06d.jpg", courant.getId() );
//			File dossierSchemas = modelConfig.getDossierSchemas();
//			return new File( dossierSchemas, nomFichier );
//		}
//		
//		public File getFichierSchemaCourant2() {
//			String nomFichier = String.format( "%06d.jpg", courant2.getId() );
//			File dossierSchemas = modelConfig.getDossierSchemas();
//			return new File( dossierSchemas, nomFichier );
//		}

}
