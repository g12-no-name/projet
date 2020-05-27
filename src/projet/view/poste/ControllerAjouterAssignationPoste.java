package projet.view.poste;

import javax.inject.Inject;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.util.converter.LocalTimeStringConverter;
import jfox.javafx.util.UtilFX;
import jfox.javafx.view.IManagerGui;
import projet.data.Assignation;
import projet.data.Poste;
import projet.data.TypePoste;
import projet.view.EnumView;

//////////////////THIS CLASS STILL TO BE MODIFIED. PLEASE TAKE NOTE OR GET LOST.

public class ControllerAjouterAssignationPoste {

	
	// Composants de la vue
	
	@FXML
	private TextField			textFieldNom;
	@FXML
	private ComboBox<TypePoste>			comboBoxType;
	@FXML
	private TextField			textFieldHeureD;
	@FXML
	private TextField			textFieldHeureF;
	@FXML
	private ListView<Assignation>			listView;
	
	
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
		textFieldHeureD.textProperty().bindBidirectional( courant.heureDProperty(), new LocalTimeStringConverter()  );
		textFieldHeureF.textProperty().bindBidirectional( courant.heureFProperty(), new LocalTimeStringConverter()  ); 
		listView.setItems(courant.getBenevoles());
		listView.setCellFactory(  UtilFX.cellFactory( item -> item.toStringBenevole() ));
	}
	
	
	// Actions
	
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
		modelPoste.validerMiseAJour();
		managerGui.showView( EnumView.PosteListe );	
	}
	
	@FXML void doModifierBenevole() {
		
	}

}
