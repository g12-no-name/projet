package projet.view.maquet;

import javax.annotation.PostConstruct;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;
import jfox.commun.exception.ExceptionValidation;

public class ModelPageCarte {
	
	//données observables
	private final Property<Image> carte= new SimpleObjectProperty<>();

	public ModelPageCarte() {
		// TODO Auto-generated constructor stub
	}
	
	public Property<Image> imageCarteProperty() {
		return carte;
	}
	
	@PostConstruct
	public void init() {    
		chargerImages();
	}
	
	
	public void chargerImages() {  
		String cheminCarte = "Images/Carte.png";  
		carte.setValue( new Image(        
				getClass().getResource( cheminCarte ).toExternalForm() ) );
	}
	
	

}
