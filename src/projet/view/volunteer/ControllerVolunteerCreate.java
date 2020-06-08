package projet.view.volunteer;

import java.time.LocalTime;

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
		if (!textFieldNom.getText().equals("test")) {
			try {
				String hourD, hourF, minD, minF;
				if (textFieldDispoD.textProperty().get().isEmpty()) {
					hourD = "0";
					minD = "0";
				} else {
					hourD = textFieldDispoD.textProperty().get().split(":")[0];
					minD = textFieldDispoD.textProperty().get().split(":")[1];
				}
				if (textFieldDispoF.textProperty().get().isEmpty()) {
					hourF = "23";
					minF = "59";
				} else {
					hourF = textFieldDispoF.textProperty().get().split(":")[0];
					minF = textFieldDispoF.textProperty().get().split(":")[1];
				}
				Integer hd = Integer.parseInt(hourD);
				Integer md = Integer.parseInt(minD);
				Integer hf = Integer.parseInt(hourF);
				Integer mf = Integer.parseInt(minF);
				if (hd < 0 || hf < 0 || hd > 23 || hf > 23 || md < 0 || mf < 0 || md > 59 || mf > 59) {
					managerGui.showDialogError("Veuillez verifier la validite des horaires rentrées");
					return;
				}
				modelV.getCourant().setHeureD(LocalTime.of(hd, md));
				modelV.getCourant().setHeureF(LocalTime.of(hf, mf));

			} catch (Exception e) {
				e.printStackTrace();
				managerGui.showDialogError("Veuillez rentrer les horaires au format: 'hh:mm'.");
				return;
			}
			modelV.validerMiseAJour();
			managerGui.showView(EnumView.BenevoleListe);
		}else {
			//fast fill in
			textFieldNom.setText(nomBuilder());
			textFieldPrenom.setText(prenomBuilder());
			textFieldTel.setText(numTelBuilder());
			textFieldMail.setText(mailBuilder());
			textFieldAdresse.setText(adresseBuilder());
			textFieldDispoD.setText(dispoDBuilder());
			textFieldDispoF.setText(dispoFBuilder());
		}
	}
	
	@FXML 
	private void doReinitialize() {
		initialize();
	}
	
	
	////////////////////////FAST FILL IN
	private String nomBuilder() {
		String noms [] = {"Guy", "Dude", "Girl", "Friendly", "Cat", "EstebanSauvage", "Mii", "Helicoptere", "Name", "Student"};
		int n = (int)(Math.random()*10);
		return "Random"+noms[n];
	}

	private String prenomBuilder() {
		return "Number#" + (int)(Math.random()*9000+1000);
	}
	
	private String numTelBuilder() {
		String s = "06"+(int)(Math.random()*10000000);
		for (int i=0; i<10-s.length(); i++) {
			s+=(int)(Math.random()*10);
		}
		return s;
	}

	private String mailBuilder() {
		String mail [] = {"Gamer", "Xx_Detroyer", "cesteban", "gmail", "tacos", "EstebanSauvage", "DontMailMe", "StopFastFilling", "YOLO", "theCakeIsALie"};
		int n = (int)(Math.random()*10);
		return mail[n]+(int)(Math.random()*10)+""+(int)(Math.random()*10)+""+(int)(Math.random()*10)+"@gmail.com";
	}

	private String adresseBuilder() {
		String rue [] = {" de L'arnaque", " de la Flemme", " de l'Esteban", " de la Procrastination", " des petites fleurs des champs", " des Ruelles", " Dangereuse", " de l'Epicerie", " tout court", ""};
		int n = (int)(Math.random()*10);
		return "Rue"+rue[n];
	}

	private String dispoDBuilder() {
		return (int)(Math.random()*12)+":"+(int)(Math.random()*60);
	}

	private String dispoFBuilder() {
		return (int)(Math.random()*10+13)+":"+(int)(Math.random()*60);
	}
}
