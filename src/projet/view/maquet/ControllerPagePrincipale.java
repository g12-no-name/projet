package projet.view.maquet;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import jfox.javafx.util.UtilFX;
import jfox.javafx.view.IManagerGui;
import projet.data.Benevole;
import projet.data.Poste;
import projet.view.EnumView;

public class ControllerPagePrincipale {
	
	   private double x, y;
	   private double largeur;
	
	
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
		@FXML
		private ImageView imageCarte;
		@FXML 
		private ScrollPane ascenceur;
	
	
	
	
	

	public ControllerPagePrincipale() {
		// TODO Auto-generated constructor stub
	}
	
	
	// Initialisation du Controller

		@FXML
		private void initialize() {

			// Data binding
			imageCarte.imageProperty().bind(
					modelpageprincipale.imageCarteProperty() );
			
			listView.setItems( modelpageprincipale.getListe() );
			
			listView.setCellFactory(  UtilFX.cellFactory( item -> item.getNom() ));
			
			listView2.setItems( modelpageprincipale.getListe2() );
			
			listView2.setCellFactory(  UtilFX.cellFactory( item -> item.getNom() ));
			
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
		
		public void refresh2() {
			modelpageprincipale.actualiserListe2();
			UtilFX.selectInListView( listView2, modelpageprincipale.getCourant2() );
			listView2.requestFocus();
			
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
		private void doAjoutPoste() {
			modelpageprincipale.preparerAjouter2();;
			// managerGui.showView( EnumView.PosteAjouter );
		}
		
		@FXML
		private void doSupprimer() {
			Benevole item = listView.getSelectionModel().getSelectedItem();
			if ( item == null ) {
				managerGui.showDialogError( "Aucun element n'est selectionne dans la liste.");
			} else {
				boolean reponse = managerGui.showDialogConfirm( "Confirmez-vous la suppression�?" );
				if ( reponse ) {
					modelpageprincipale.supprimer( item );
					refresh();
				}
			}
		}
		
		@FXML
		private void doSupprimer2() {
			Poste item = listView2.getSelectionModel().getSelectedItem();
			if ( item == null ) {
				managerGui.showDialogError( "Aucun élément n'est sélectionné dans la liste.");
			} else {
				boolean reponse = managerGui.showDialogConfirm( "Confirmez-vous la suppresion ?" );
				if ( reponse ) {
					modelpageprincipale.supprimer2( item );
					refresh2();
				}
			}
		}
		
		@FXML
		   private void pointDeContact(MouseEvent souris) {
		      x = souris.getX();
		      y = souris.getY();
		   }
		
		@FXML
		   private void deplacer(MouseEvent souris) {
		      double offsetX = (souris.getX() - x)/largeur + ascenceur.getHvalue();
		      double offsetY = (souris.getY() - y)/largeur + ascenceur.getVvalue();
		      ascenceur.setHvalue(offsetX);
		      ascenceur.setVvalue(offsetY);
		      pointDeContact(souris);
		   }
		
		
		@FXML
		   private void Zoom() {
		         double memX = ascenceur.getHvalue();
		         double memY = ascenceur.getVvalue();
		         largeur = 25;
		         imageCarte.setFitWidth(largeur);
		         ascenceur.setHvalue(memX);
		         ascenceur.setVvalue(memY);
		      
		   }
		
		
	
	
	
	
}
