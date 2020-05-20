package projet.view.maquet;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import jfox.javafx.util.UtilFX;
import jfox.javafx.view.IManagerGui;
import projet.data.Benevole;
import projet.data.Memo;
import projet.data.Poste;
import projet.view.EnumView;
import projet.view.memo.ModelMemo;

public class ControllerPagePrincipale {
	
	
	// Composant de la vue	
		@Inject
		private IManagerGui		managerGui;
		@Inject
		private ModelPagePrincipale			modelpageprincipale;
		@FXML
		private ListView<Benevole>		listView;
		@FXML
		private ListView<Poste>		listView2;
		@FXML
		private TextField           heure;
	
	
	
	
	

	public ControllerPagePrincipale() {
		// TODO Auto-generated constructor stub
	}
	
	
	// Initialisation du Controller

		@FXML
		private void initialize() {

			// Data binding
			listView.setItems( modelpageprincipale.getListe() );
			
			listView.setCellFactory(  UtilFX.cellFactory( item -> item.getNom() ));
			
			// l'heure actuelle
			Date now= new Date();
		      
		       DateFormat df = new SimpleDateFormat("HH:mm:ss.SSS");
		        String dateTimeString = df.format(now);
		        heure.setText(dateTimeString);
		}
		
		public void refresh() {
			modelpageprincipale.actualiserListe();
			UtilFX.selectInListView( listView, modelpageprincipale.getCourant() );
			listView.requestFocus();
		}

	
	
	// Actions
	
		@FXML
		private void doCarte() {
			managerGui.execTask( () -> {
				Platform.runLater( () -> {
	        			managerGui.showView(EnumView.PageCarte);
	            }) ;
			} );
		}
		
		@FXML
		private void doAjoutBenevole() {
			modelpageprincipale.preparerAjouter();;
			// managerGui.showView( EnumView.BenevoleAjouter );
		}
		
		@FXML
		private void doSupprimer() {
			Benevole item = listView.getSelectionModel().getSelectedItem();
			if ( item == null ) {
				managerGui.showDialogError( "Aucun élément n'est sélectionné dans la liste.");
			} else {
				boolean reponse = managerGui.showDialogConfirm( "Confirmez-vous la suppresion ?" );
				if ( reponse ) {
					modelpageprincipale.supprimer( item );
					refresh();
				}
			}
		}
		
		@FXML
		private void doChangerListe() {
			// Data binding
				listView2.setItems( modelpageprincipale.getListe2() );
						
				listView2.setCellFactory(  UtilFX.cellFactory( item -> item.getNom() ));
				
				modelpageprincipale.actualiserListe2();
				UtilFX.selectInListView( listView2, modelpageprincipale.getCourant2() );
				listView2.requestFocus();
						
		}
	
	
	
	
	
}
