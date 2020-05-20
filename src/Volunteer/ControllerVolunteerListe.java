package Volunteer;

import javax.inject.Inject;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import jfox.javafx.view.IManagerGui;
import projet.data.Benevole;

//////////////////THIS CLASS STILL TO BE MODIFIED. PLEASE TAKE NOTE OR GET LOST.

public class ControllerVolunteerListe {

	
	// Composants de la vue
	
	@FXML
	private ListView<Benevole> list;
	@FXML
	private Button				buttonAdd;
	@FXML
	private Button				buttonDelete;
	@FXML
	private Button				buttonObserver;
	@FXML
	private Button				buttonModifier;
	
	
	
	// Autres champs
	
	@Inject
	private IManagerGui			managerGui;
	@Inject
	private ModelVolunteer		modelVolunteer;


	// Initialisation du Controller

	@FXML
	private void initialize() {

		// Data binding
		
		//Categorie courant = modelCategorie.getCourant();
		//textFieldId.textProperty().bindBidirectional( courant.idProperty(), new IntegerStringConverter()  );
		//textFieldLibelle.textProperty().bindBidirectional( courant.libelleProperty()  );
	}
	
	
	// Actions
	
	@FXML
	private void doAdd() {
		//managerGui.showView( /*EnumView.CategorieListe*/ );
	}
	
	@FXML
	private void doDelete() {
		//modelCategorie.validerMiseAJour();
		//managerGui.showView( /*EnumView.CategorieListe*/ );
	}
	
	@FXML
	private void doModify() {
		
	}
	
	@FXML
	private void doObserve() {
		
	}

}
