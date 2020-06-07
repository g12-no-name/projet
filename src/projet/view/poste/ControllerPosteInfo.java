package projet.view.poste;

import javax.inject.Inject;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.util.converter.LocalTimeStringConverter;
import jfox.javafx.util.UtilFX;
import jfox.javafx.view.IManagerGui;
import projet.data.Assignation;
import projet.data.Poste;
import projet.view.EnumView;

//////////////////THIS CLASS STILL TO BE MODIFIED. PLEASE TAKE NOTE OR GET LOST.

public class ControllerPosteInfo {

	
	// Composants de la vue
	
	@FXML
	private TextField			textFieldNom;
	@FXML
	private TextField			textFieldType;
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
	@Inject
	private ModelAssignation modelAssignation;


	// Initialisation du Controller

	@FXML
	private void initialize() {

		// Data binding
		
		Poste courant = modelPoste.getCourant();
		textFieldNom.textProperty().bindBidirectional( courant.nomProperty()  );
		textFieldType.textProperty().bindBidirectional( courant.getTypePoste().nomProperty() );
		textFieldHeureD.textProperty().bindBidirectional( courant.heureDProperty(), new LocalTimeStringConverter()  );
		textFieldHeureF.textProperty().bindBidirectional( courant.heureFProperty(), new LocalTimeStringConverter()  ); 
		modelAssignation.actualiserListePoste(courant.getId());
		listView.setItems(modelAssignation.getListe());
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
	private void doModifier() {
		Poste courant = modelPoste.getCourant();
		modelPoste.preparerModifier(courant);
		managerGui.showView( EnumView.PosteModif );
		
	}

}
