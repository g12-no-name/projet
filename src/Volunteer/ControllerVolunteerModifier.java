package Volunteer;

import javax.inject.Inject;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import jfox.javafx.view.IManagerGui;

//////////////////THIS CLASS STILL TO BE MODIFIED. PLEASE TAKE NOTE OR GET LOST.

public class ControllerVolunteerModifier {

	
	// Composants de la vue
	
	@FXML
	private TextField			textFieldNom;
	@FXML
	private TextField			textFieldTel;
	@FXML
	private TextField			textFieldMail;
	@FXML
	private TextField			textFieldAdresse;
	@FXML
	private TextField			textFieldDateNaissance;
	@FXML
	private TextField			textFieldDispo;
	@FXML
	private CheckBox			mineur;
	@FXML
	private CheckBox 			permisDeConduire;
	@FXML 
	private CheckBox			membership;
	
	@FXML
	private Button 				goBack;
	@FXML
	private Button 				confirm;
	@FXML
	private Button 				reinitialize;
	
	// Autres champs
	
	@Inject
	private IManagerGui			managerGui;
	@Inject
	private ModelVolunteer	modelVolunteer;


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
	private void doGoBack() {
		//managerGui.showView( /*EnumView.CategorieListe*/ );
	}
	
	@FXML
	private void doConfirm() {
		//modelCategorie.validerMiseAJour();
		//managerGui.showView( /*EnumView.CategorieListe*/ );
	}

	@FXML 
	private void doReinitialize() {
		
	}
}
