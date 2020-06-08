package projet.view.poste;

import javax.inject.Inject;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import jfox.javafx.util.UtilFX;
import jfox.javafx.view.IManagerGui;
import projet.data.Assignation;
import projet.data.Benevole;
import projet.data.Poste;
import projet.view.EnumView;
import projet.view.volunteer.ModelVolunteer;


public class ControllerListeAssignation {
	
	
	
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
	private ComboBox<Benevole>	comboBoxBenevole;
	@FXML
	private ComboBox<Poste>		comboBoxPoste;


	// Autres champs
	
	@Inject
	private IManagerGui			managerGui;
	@Inject
	private ModelAssignation	modelAssignation;
	@Inject
	private ModelPoste			modelPoste;
	@Inject
	private ModelVolunteer			modelBenevole;
	
	
	// Initialisation du Controller

	@FXML
	private void initialize() {
		// Data binding
		Assignation courant=modelAssignation.getCourant();
		modelAssignation.actualiserListe();
		listView.setItems( modelAssignation.getListe() );
		comboBoxBenevole.setItems( modelBenevole.getListe() );
		comboBoxBenevole.valueProperty().bindBidirectional( courant.benevoleProperty() );
		comboBoxPoste.setItems( modelPoste.getListe() );
		comboBoxPoste.valueProperty().bindBidirectional( courant.posteProperty() );
		listView.setCellFactory(  UtilFX.cellFactory( item -> item.toString() ));
		
		// Configuraiton des boutons
		listView.getSelectionModel().selectedItemProperty().addListener(
				(obs, oldVal, newVal) -> {
					configurerBoutons();
		});
		configurerBoutons();

	}
	
	public void refresh() {
		modelAssignation.actualiserListe();
		UtilFX.selectInListView( listView, modelAssignation.getCourant() );
		listView.requestFocus();
	}

	
	
	// Actions
	
	@FXML
	private void doAjouter() {
		modelAssignation.preparerAjouter();
		managerGui.showView( EnumView.AjouterAssignation);
	}

	@FXML
	private void doSearch() {
		if(comboBoxBenevole.getValue()!=null) {
			if(comboBoxPoste.getValue()!=null) {
				modelAssignation.actualiserListeSearch(comboBoxBenevole.getValue().getId(), comboBoxPoste.getValue().getId());
			}else {
				modelAssignation.actualiserListeBenevole(comboBoxBenevole.getValue().getId());
			}
		}else {
			if(comboBoxPoste.getValue()!=null) {
				modelAssignation.actualiserListePoste(comboBoxPoste.getValue().getId());
			}else {
				modelAssignation.actualiserListe();
			}
		}
	}
	
	@FXML
	private void doClear() {
		comboBoxBenevole.setValue(null);
		comboBoxPoste.setValue(null);
		refresh();
	}
	@FXML
	private void doModifier() {
		Assignation item = listView.getSelectionModel().getSelectedItem();
		if ( item == null ) {
			managerGui.showDialogError( "Aucun élément n'est sélectionné dans la liste.");
		} else {
			modelAssignation.preparerModifier(item);
			managerGui.showView( EnumView.ModifierAssignation);
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
				modelAssignation.supprimer(item);
				refresh();
			}
		}
	}
	
	@FXML
	private void doRetour() {
		managerGui.showView( EnumView.PosteListe);
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
