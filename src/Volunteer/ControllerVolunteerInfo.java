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

public class ControllerVolunteerInfo {

	
	// Composants de la vue
	
	@FXML
	private TextField			textFieldNomEtPrenom;
	@FXML
	private TextField			textFieldTel;
	@FXML
	private TextField			textFieldMail;
	@FXML
	private TextField			textFieldAdresse;
	@FXML
	private DatePicker			datePickerDateNaissance;
	@FXML
	private TextField			textFieldDispos;
	@FXML
	private CheckBox			mineur;
	@FXML
	private CheckBox 			permisDeConduire;
	@FXML 
	private CheckBox			membership;
	
	
	@FXML
	private Button 				goBack;
	@FXML
	private Button 			    modify;
	
	// Autres champs
	
	@Inject
	private IManagerGui			managerGui;
	@Inject
	private ModelVolunteer      modelV;


	// Initialisation du Controller

	@FXML
	private void initialize() {

		// Data binding
		
		Benevole courant = modelV.getCourant();
		textFieldNomEtPrenom.setText(courant.getNom()+" "+courant.getPrenom());
		textFieldTel.setText( courant.getNumTel() );
		textFieldMail.setText( courant.getMail() );
		textFieldAdresse.setText( courant.getAdresse() );
		datePickerDateNaissance.setValue( courant.getDateNaissance() );
		textFieldDispos.setText( "A partir de "+courant.getHeureD().toString()+" jusqu'à "+courant.getHeureF().toString() );
		mineur.setSelected( courant.getMineur() );
		permisDeConduire.setSelected( courant.getPermis() );
		membership.setSelected( courant.getMembre() );
	}
	
	
	// Actions
	
	@FXML
	private void doGoBack() {
		managerGui.showView( EnumView.BenevoleListe );
	}
	
	@FXML
	private void doModify() {
		managerGui.showView( EnumView.BenevoleModify );
	}

}
