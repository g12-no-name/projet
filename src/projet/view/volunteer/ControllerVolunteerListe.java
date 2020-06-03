package projet.view.volunteer;

import javax.inject.Inject;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import jfox.javafx.util.UtilFX;
import jfox.javafx.view.IManagerGui;
import projet.data.Benevole;
import projet.view.EnumView;

//////////////////THIS CLASS STILL TO BE MODIFIED. PLEASE TAKE NOTE OR GET LOST.

public class ControllerVolunteerListe {

	
	// Composants de la vue
	
	@FXML
	private ListView<Benevole> list;
	@FXML
	private Button				buttonAdd;
	@FXML
	private Button				buttonDelete;
	@FXML
	private Button				buttonObserver;
	@FXML
	private Button				buttonModifier;
	@FXML 
	private Button				buttonGetBack;
	
	
	
	// Autres champs
	
	@Inject
	private IManagerGui			managerGui;
	@Inject
	private ModelVolunteer		modelV;


	// Initialisation du Controller

	@FXML
	private void initialize() {

		// Data binding
				list.setItems( modelV.getListe() );
				
				// Configuration des boutons
				list.getSelectionModel().selectedItemProperty().addListener(
						(obs, oldVal, newVal) -> {
							configurerBoutons();
				});
				configurerBoutons();
				
				list.setCellFactory( UtilFX.cellFactory( item -> item.getNom() ) );
	}
	
	
	// Actions
	
	@FXML
	private void doAdd() {
		modelV.preparerAjouter();;
		managerGui.showView( EnumView.BenevoleCreate );
	}
	
	@FXML
	private void doDelete() {
		if ( managerGui.showDialogConfirm( "Confirmez-vous la suppresion ?" ) ) {
			modelV.supprimer( list.getSelectionModel().getSelectedItem() );
			refresh();
		}
	}
	
	@FXML
	private void doModify() {
		modelV.preparerModifier( list.getSelectionModel().getSelectedItem() );
		managerGui.showView( EnumView.BenevoleModify );
	}
	
	@FXML
	private void doObserve() {
		modelV.preparerAffichage( list.getSelectionModel().getSelectedItem() );
		managerGui.showView( EnumView.BenevoleView );
	}
	
	@FXML
	private void goBack() {
		managerGui.showView( EnumView.PagePrincipale );
	}
	
	// Gestion des evenements

	// Clic sur la liste
	@FXML
	private void gererClicSurListe( MouseEvent event ) {
		if (event.getButton().equals(MouseButton.PRIMARY)) {
			if (event.getClickCount() == 2) {
				if ( list.getSelectionModel().getSelectedIndex() == -1 ) {
					managerGui.showDialogError( "Aucun element n'est selectionne dans la liste.");
				} else {
					doObserve();
				}
			}
		}
	}

	
	// Methodes auxiliaires
	
	private void configurerBoutons() {
		
    	if( list.getSelectionModel().getSelectedItems().isEmpty() ) {
			buttonModifier.setDisable(true);
			buttonDelete.setDisable(true);
		} else {
			buttonModifier.setDisable(false);
			buttonDelete.setDisable(false);
		}
	}

	
	public void refresh() {
		modelV.actualiserListe();
		UtilFX.selectInListView( list, modelV.getCourant() );
		list.requestFocus();
	}

}
