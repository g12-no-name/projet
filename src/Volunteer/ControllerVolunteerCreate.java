package Volunteer;

import javax.inject.Inject;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import jfox.javafx.view.IManagerGui;
import projet.data.Benevole;
import projet.view.EnumView;

//////////////////THIS CLASS STILL TO BE MODIFIED. PLEASE TAKE NOTE OR GET LOST.

public class ControllerVolunteerCreate {

	
	// Composants de la vue
	
	@FXML
	private TextField			textFieldNom;
	@FXML
	private TextField			textFieldPrenom;
	@FXML
	private TextField			textFieldTel;
	@FXML
	private TextField			textFieldMail;
	@FXML
	private TextField			textFieldAdresse;
	@FXML
	private DatePicker			datePickerDateNaissance;
	@FXML
	private TextField			textFieldDispoD;
	@FXML
	private TextField			textFieldDispoF;
	@FXML
	private CheckBox			mineur;
	@FXML
	private CheckBox 			permisDeConduire;
	@FXML 
	private CheckBox			membership;
	
	@FXML
	private Button 				goBack;
	@FXML
	private Button 				add;
	@FXML
	private Button 				reinitialize;
	
	// Autres champs
	
	@Inject
	private IManagerGui			managerGui;
	@Inject
	private ModelVolunteer		modelV;


	// Initialisation du Controller

	@FXML
	private void initialize() {

		// Data binding
		
		Benevole courant = modelV.getCourant();
		
		textFieldNom.textProperty().bindBidirectional( courant.nomProperty() );
		textFieldPrenom.textProperty().bindBidirectional( courant.prenomProperty() );
		
		textFieldTel.textProperty().bindBidirectional( courant.numTelProperty() );
		textFieldMail.textProperty().bindBidirectional( courant.mailProperty() );
		textFieldAdresse.textProperty().bindBidirectional( courant.adresseProperty() );
		
		datePickerDateNaissance.valueProperty().bindBidirectional( courant.dateNaissanceProperty() );
		//textFieldDispoD.textProperty().bindBidirectional( courant.heureDProperty() );
		//textFieldDispoF.textProperty().bindBidirectional( courant.heureDProperty() );
		
		mineur.selectedProperty().bindBidirectional( courant.mineurProperty() );
		permisDeConduire.selectedProperty().bindBidirectional( courant.permisProperty() );
		membership.selectedProperty().bindBidirectional( courant.membreProperty() );
		
	///////////////////////////////PLZ END ME
	
	}
	
	
	// Actions
	
	@FXML
	private void doGoBack() {
		managerGui.showView( EnumView.BenevoleListe );
	}
	
	@FXML
	private void doAdd() {
		modelV.validerMiseAJour();
		managerGui.showView( EnumView.BenevoleListe );
	}
	
	@FXML 
	private void doReinitialize() {
		initialize();
	}

}
