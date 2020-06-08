package projet.view.volunteer;

import javax.inject.Inject;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import jfox.javafx.view.IManagerGui;
import projet.data.Assignation;
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
	private ListView<Assignation>		listView;
	
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
		String s="";
		try {s+=courant.nomProperty().get()+" ";}catch(Exception e) {}
		try {s+=courant.prenomProperty().get();}catch(Exception e) {}
		textFieldNomEtPrenom.setText(s);
		
		textFieldTel.textProperty().bindBidirectional( courant.numTelProperty() );
		textFieldMail.textProperty().bindBidirectional( courant.mailProperty() );
		textFieldAdresse.textProperty().bindBidirectional( courant.adresseProperty() );
		
		datePickerDateNaissance.valueProperty().bindBidirectional( courant.dateNaissanceProperty() );
		
		try{
			mineur.selectedProperty().bindBidirectional( courant.mineurProperty() );
		}catch(NullPointerException e) {}
		try {
		permisDeConduire.selectedProperty().bindBidirectional( courant.permisProperty() );
		}catch(NullPointerException e) {}
		try {
		membership.selectedProperty().bindBidirectional( courant.membreProperty() );
		}catch(NullPointerException e) {}
		
		s="";
		if(courant.getHeureD()!=null) {s+="A partir de: "+courant.getHeureD().getHour()+":"+courant.getHeureD().getMinute()+" ";}
		if(courant.getHeureF()!=null) {s+="jusqu'a: "+courant.getHeureF().getHour()+":"+courant.getHeureF().getMinute();}
		textFieldDispos.setText(s);
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
	
	@FXML
	public void refresh() {
		initialize();
	}
}
