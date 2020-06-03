package projet.view.poste;

import javax.inject.Inject;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import jfox.javafx.util.UtilFX;
import jfox.javafx.view.IManagerGui;
import projet.data.Assignation;
import projet.data.Poste;
import projet.view.EnumView;


public class ControllerListeAssignationPoste {
	
	
	// Composants de la vue

	@FXML
	private ListView<Assignation>		listView;
	@FXML
	private Button				buttonModifier;
	@FXML
	private Button				buttonSupprimer;
	@FXML
	private Button				buttonAjouter;
	@FXML
	private Label 				nomPoste;


	// Autres champs
	
	@Inject
	private IManagerGui			managerGui;
	@Inject
	private ModelPoste			modelPoste;
	
	
	// Initialisation du Controller

	@FXML
	private void initialize() {
		Poste courant =modelPoste.getCourant();
		// Data binding
		listView.setItems( modelPoste.getCourant().getBenevoles() );
		nomPoste.textProperty().bind(courant.nomProperty());
		
		listView.setCellFactory(  UtilFX.cellFactory( item -> item.toStringBenevole() ));
		
		// Configuraiton des boutons
		listView.getSelectionModel().selectedItemProperty().addListener(
				(obs, oldVal, newVal) -> {
					configurerBoutons();
		});
		configurerBoutons();

	}
	
	public void refresh() {
		listView.requestFocus();
	}

	
	// Actions
	
	@FXML
	private void doAjouter() {
		modelPoste.preparerAjouter();;
		managerGui.showView( EnumView.AjouterAssignationPoste);
	}

	@FXML
	private void doModifier() {
		Assignation item = listView.getSelectionModel().getSelectedItem();
		if ( item == null ) {
			managerGui.showDialogError( "Aucun élément n'est sélectionné dans la liste.");
		} else {
			
			managerGui.showView( EnumView.ModifierAssignationPoste );
		}
	}

	@FXML
	private void doSupprimer() {
		Assignation item = listView.getSelectionModel().getSelectedItem();
		if ( item == null ) {
			managerGui.showDialogError( "Aucun élément n'est sélectionné dans la liste.");
		} else {
			boolean reponse = managerGui.showDialogConfirm( "Confirmez-vous la suppresion ?" );
			if ( reponse ) {
				modelPoste.getCourant().getBenevoles().remove(item);
				refresh();
			}
		}
	}
	
	@FXML
	private void doRetour() {
		managerGui.showView( EnumView.ListeAssignationPoste);
	}
	
	@FXML
	private void doAccueil() {
		managerGui.showView( EnumView.PagePrincipale);
	}
	
	// Gestion des évènements

	// Clic sur la liste
	@FXML
	private void gererClicSurListe( MouseEvent event ) {
		if (event.getButton().equals(MouseButton.PRIMARY)) {
			if (event.getClickCount() == 2) {
				if ( listView.getSelectionModel().getSelectedIndex() == -1 ) {
					managerGui.showDialogError( "Aucun élément n'est sélectionné dans la liste.");
				} else {
					doModifier();
				}
			}
		}
	}

	
	// Méthodes auxiliaires
	
	private void configurerBoutons() {
		
    	if( listView.getSelectionModel().getSelectedItems().isEmpty() ) {
			buttonModifier.setDisable(true);
			buttonSupprimer.setDisable(true);
			buttonAjouter.setDisable(false);
		} else {
			buttonModifier.setDisable(false);
			buttonSupprimer.setDisable(false);
			buttonAjouter.setDisable(false);
		}
	}

}
