package projet.veiw.restaurant;

import projet.data.Equipe;
//import projet.dao.DaoEquipe;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


public class controllerRestaurant {
	
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
		// retire le nombre ed repas necessaire
		if(this.equipeSelectionne) {
			this.equipeCourrante.setNbBouffe(Integer.valueOf(this.nbRepas.getText())-this.nbRetireValue);
			//projet.dao.DaoEquipe.modifier(this.equipeCourrante);
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
