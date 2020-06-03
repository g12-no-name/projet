package projet.view.poste;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import javax.inject.Inject;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import jfox.javafx.view.IManagerGui;
import projet.data.Poste;
import projet.data.TypePoste;
import projet.view.EnumView;

//////////////////THIS CLASS STILL TO BE MODIFIED. PLEASE TAKE NOTE OR GET LOST.

public class ControllerPosteCreation {

	// Composants de la vue
	
	@FXML
	private TextField			textFieldNom;
	@FXML
	private ComboBox<TypePoste>			comboBoxType;
	@FXML
	private TextField			textFieldHeureD;
	@FXML
	private TextField			textFieldHeureF;
//	@FXML
//	private ListView<Assignation>			listView;
//	
	
	// Autres champs
	
	@Inject
	private IManagerGui			managerGui;
	@Inject
	private ModelPoste		modelPoste;


	// Initialisation du Controller

	@FXML
	private void initialize() {

		// Data binding
		Poste courant = modelPoste.getCourant();
		textFieldNom.textProperty().bindBidirectional( courant.nomProperty()  );
		comboBoxType.setItems( modelPoste.getTypePoste() );
		comboBoxType.valueProperty().bindBidirectional( courant.typePosteProperty() );
		//textFieldHeureD.textProperty().bindBidirectional( courant.heureDProperty(), new LocalTimeStringConverter()  );
		//textFieldHeureF.textProperty().bindBidirectional( courant.heureFProperty(), new LocalTimeStringConverter()  ); 
//		listView.setItems(courant.getBenevoles());
//		listView.setCellFactory(  UtilFX.cellFactory( item -> item.toStringBenevole() ));
	}
	
	
	// Actions
	
	private boolean actualiserHeureDDansModele() {
		// Modifie le statut en fct du bouton radio selectionné
		String texte = textFieldHeureD.getText();
		boolean b=true;
		System.out.println(texte);
		try {
			LocalTime heure= LocalTime.parse(texte);
			System.out.println(heure.toString());
			modelPoste.getCourant().setHeureD(heure);
		}catch(DateTimeParseException e) {
			b=false;
			System.out.println("erreur d");
			StringBuilder message = new StringBuilder();
			message.append("\nEcrivez heure debut sous le format hh:mm");
		};
		return b;
	}
	
	private boolean actualiserHeureFDansModele() {
		// Modifie l'heure dans le modele en fonction du texte
		String texte = textFieldHeureF.getText();
		boolean b=true;
		System.out.println(texte);
		try {
			LocalTime heure= LocalTime.parse(texte);
			System.out.println(heure.toString());
			modelPoste.getCourant().setHeureF(heure);
		}catch(DateTimeParseException e) {
			b=false;
			System.out.println("erreur f");
			StringBuilder message = new StringBuilder();
			message.append("\nEcrivez heure fin sous le format hh:mm");
		};
		return b;
	}

//	private void actualiserHeureDDansVue() {
//		// Selectionne le bouton radio correspondant au statut
//		String texte=modelPoste.getCourant().getHeureD().toString();
//		textFieldHeureD.setText(texte);
//	}
	
//	private void actualiserHeureFDansVue() {
//		// Selectionne le bouton radio correspondant au statut
//		String texte=modelPoste.getCourant().getHeureF().toString();
//		textFieldHeureF.setText(texte);
//	}
	
	@FXML
	private void doRetour() {
		managerGui.showView( EnumView.PosteListe);
	}
	
	@FXML
	private void doAccueil() {
		managerGui.showView( EnumView.PagePrincipale);
	}
	
	@FXML
	private void doValider() {
		if(actualiserHeureDDansModele()) {
			if(actualiserHeureFDansModele()) {
				modelPoste.validerMiseAJour();
				managerGui.showView( EnumView.PosteListe );	
			}
		}
	}
	
//	@FXML void doModifierBenevole() {
//		managerGui.showView( EnumView.ListeAssignationPoste );	
//	}

}
