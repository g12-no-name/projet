package projet.view.maquet;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import projet.dao.DaoPoste;
import projet.data.Poste;

public class ModelPageCarte {
	
	//donnees observables
	private final Property<Image> carte= new SimpleObjectProperty<>();
	
	@Inject
	private DaoPoste       daoposte;

	private final ObservableList<Poste> liste2 = FXCollections.observableArrayList();
	
	
	

	public ModelPageCarte() {
		// TODO Auto-generated constructor stub
	}
	
	public Property<Image> imageCarteProperty() {
		return carte;
	}
	
	@PostConstruct
	public void init() {    
		chargerImages();
		actualiserListe2();
	}
	
	
	public void chargerImages() {  
		String cheminCarte = "Carte.jpg";  
		carte.setValue( new Image(  getClass().getResource( cheminCarte ).toExternalForm() ) );
	}

	public final Property<Image> carteProperty() {
		return this.carte;
	}
	

	public final Image getCarte() {
		return this.carteProperty().getValue();
	}
	

	public final void setCarte(final Image carte) {
		this.carteProperty().setValue(carte);
	}
	
	
	public ObservableList<Poste> getListe2() {
		return liste2;
	}
	
	public ObservableList<Poste> getPostes(){
		return this.getListe2();
	}
	
	public void actualiserListe2() {
		liste2.setAll(daoposte.listerTout() );
 	}

}
