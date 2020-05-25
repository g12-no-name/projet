package projet.view.restaurant;

import projet.data.Equipe;
import projet.view.restaurant.ModelRestaurant;

import javax.inject.Inject;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


public class ControllerRestaurant {
	
	@FXML
	private TextField participant1;
	@FXML
	private TextField participant2;
	@FXML
	private TextArea zoneInfo;
	@FXML
	private TextField nbRepas;
	@FXML
	private Label nbRetire;
	//
	private int nbRetireValue =	0;
	private Equipe equipeCourrante;
	private boolean equipeSelectionne = false;//indique si une equipe est selectionné
	
	@Inject
	private ModelRestaurant	modelRestaurant;
	
	
	
	@FXML
	private void initialize() {
		
	}
	
	@FXML
	private void recherche() {
		// cherche quel equipe contient les deux participant et affiche le nombre de repas qu'il peuvent retirer
		
		//this.equipeCourrante = ;
		this.nbRepas.setText(String.valueOf(this.equipeCourrante.getNbBouffe()+2));
		
		this.equipeSelectionne = true;
		
	}
	
	
	@FXML
	private void deductionRepas() {
		// retire le nombre de repas necessaire
		if(this.equipeSelectionne) {
			this.modelRestaurant.deductionRepas(this.equipeCourrante, (Integer.valueOf(this.nbRepas.getText())-this.nbRetireValue));
			//
			this.equipeSelectionne = false;
		}
	}
	
	
	
	
	@FXML
	private void nbRetirePlus() {
		this.nbRetireValue++;
		this.nbRetire.setText(String.valueOf(this.nbRetireValue));
	}
	@FXML
	private void nbRetireMoins() {
		this.nbRetireValue++;
		this.nbRetire.setText(String.valueOf(this.nbRetireValue));
	}
	
	
}
