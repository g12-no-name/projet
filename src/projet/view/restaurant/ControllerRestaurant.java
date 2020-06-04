package projet.view.restaurant;

import projet.data.Equipe;
import projet.data.Participant;
import projet.view.restaurant.ModelRestaurant;
import projet.view.ManagerGui;
import projet.view.EnumView;

import java.util.List;

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
	private int nbRetireValue = 0;
	private Equipe equipeCourrante;
	private boolean equipeSelectionne = false;//indique si une equipe est selectionné
	private List<Participant> participantsCourant;
	
	@Inject
	private ModelRestaurant	modelRestaurant;
	@Inject
	private ManagerGui	managerGui;
	
	
	@FXML
	private void initialize() {
		
	}
	
	@FXML
    private void goBack() {
		managerGui.showView( EnumView.PagePrincipale );
    }
	
	
	
	
	
	@FXML
	private void recherche() {
		// cherche quel equipe contient les deux participant et affiche le nombre de repas qu'il peuvent retirer
		this.equipeCourrante = this.modelRestaurant.retrouverEquipe(this.participant1.getText(), this.participant2.getText());
		//
		if(this.equipeCourrante != null) {
			this.nbRepas.setText(String.valueOf(this.equipeCourrante.getNbBouffe()+2));
			//
			this.participantsCourant = this.modelRestaurant.getDaoEquipe().listParticipant(this.equipeCourrante.getId());
			String text = "Equipe:"+this.equipeCourrante.getNom()+"\n";
			text = text+"\t capitain:"+(this.participantsCourant.get(0).getNom())+" "+(this.participantsCourant.get(0).getPrenom())+" \n";
			text = text+"\t equipe:"+(this.participantsCourant.get(1).getNom())+" "+(this.participantsCourant.get(1).getPrenom())+" \n";
			//
			
			if(this.equipeCourrante.getTypeCourse().getId() == 1) {
				text = text+"\t Mini Bol d'air \n";
			}else {text = text+"\t Bol d'air \n";}
			
			switch(this.equipeCourrante.getCatCourse().getId()) {
			case 1:
				text = text+"\t mixte \n";
				break;
			case 2:
				text = text+"\t femme \n";
				break;
			case 3:
				text = text+"\t homme \n";
				break;
			case 4:
				text = text+"\t V.A.E \n";
				break;
			}
			//
			this.zoneInfo.setText(text);
			//
			this.equipeSelectionne = true;
		}else {
			this.zoneInfo.setText("Equipe non trouvee");
			this.equipeSelectionne = false;
		}
	}
	
	
	@FXML
	private void deductionRepas() {
		// retire le nombre de repas necessaire
		if(this.equipeSelectionne) {
			this.modelRestaurant.deductionRepas(this.equipeCourrante, (Integer.valueOf(this.nbRepas.getText())-this.nbRetireValue));
			//
			this.zoneInfo.setText("");
			this.nbRetire.setText("0");
			this.nbRepas.setText("");
			this.nbRetireValue = 0;
			this.equipeSelectionne = false;
			
		}
	}
	
	
	
	
	@FXML
	private void nbRetirePlus() {
		if(this.nbRetireValue<this.equipeCourrante.getNbBouffe()+2) {
			this.nbRetireValue++;
			this.nbRetire.setText(String.valueOf(this.nbRetireValue));
		}
	}
	@FXML
	private void nbRetireMoins() {
		if(this.nbRetireValue>0) {
			this.nbRetireValue--;
			this.nbRetire.setText(String.valueOf(this.nbRetireValue));
		}
		
	}
	
	
}
