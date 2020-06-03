package projet.view.maquet;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
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
	private IManagerGui					managerGui;
	@Inject
	private ModelPagePrincipale			mpp;
	
	@FXML
	private ListView<Benevole>			listView;
	@FXML
	private ListView<Poste>				listView2;
	
	@FXML
	private TextField           		heure;
	@FXML 
	private TextField 					mode;
	
	@FXML
	private Canvas 						canvas;
	
	
	private Mode m;


	public ControllerPagePrincipale() {}


	// Initialisation du Controller

	@FXML
	private void initialize() {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		
		gc.drawImage(mpp.imageCarteProperty().getValue(),0,0, canvas.getWidth(), canvas.getHeight());
		// Data binding
		listView.setItems( mpp.getListe() );
		
		listView.setCellFactory(  UtilFX.cellFactory( item -> item.getNom() ));
		
		listView2.setItems( mpp.getListe2() );
		
		listView2.setCellFactory(  UtilFX.cellFactory( item -> item.getNom() ));
		
		// l'heure actuelle
		Date now= new Date();
	    DateFormat df = new SimpleDateFormat("HH:mm");
	    String dateTimeString = df.format(now);
	    heure.setText(dateTimeString);
	    
	    setMode(Mode.observe);
	}
	
	

	public void refresh() {
		mpp.actualiserListe();
		UtilFX.selectInListView( listView, mpp.getCourant() );
		listView.requestFocus();
		mpp.actualiserListe2();
		UtilFX.selectInListView( listView2, mpp.getCourant2() );
		listView2.requestFocus();
	}
	
	



// Actions
	
	@FXML
	private void doheure() {
			    Date now= new Date();
	      
	            DateFormat df = new SimpleDateFormat("HH:mm");
	            String dateTimeString = df.format(now);
	            heure.setText(dateTimeString);
	     
	}

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
		mpp.preparerAjouter();;
		managerGui.showView( EnumView.BenevoleCreate );
	}
	
	@FXML
	private void doAjoutPoste() {
		setMode(Mode.addPlot);
//		modelpageprincipale.preparerAjouter2();
//		managerGui.showView( EnumView.PosteCreation );
	}
	
	@FXML 
	private void doClickOnMap(MouseEvent e) {
		if (m==Mode.addPlot) {
			
		}
	}
	
	
	@FXML
	private void doSupprimer() {
		Benevole item = listView.getSelectionModel().getSelectedItem();
		if ( item == null ) {
			managerGui.showDialogError( "Aucun element n'est selectionne dans la liste.");
		} else {
			boolean reponse = managerGui.showDialogConfirm( "Confirmez-vous la suppression?" );
			if ( reponse ) {
				mpp.supprimer( item );
				refresh();
			}
		}
	}
	
	@FXML
	private void doSupprimer2() {
		Poste item = listView2.getSelectionModel().getSelectedItem();
		if ( item == null ) {
			managerGui.showDialogError( "Aucun element n'est selectionne dans la liste.");
		} else {
			boolean reponse = managerGui.showDialogConfirm( "Confirmez-vous la suppresion ?" );
			if ( reponse ) {
				mpp.supprimer2( item );
				refresh();
			}
		}
	}
	
	@FXML
	private void pointDeContact(MouseEvent souris) {
		x = souris.getX();
		y = souris.getY();
    }
	
//	@FXML
//	private void deplacer(MouseEvent souris) {
//		double offsetX = (souris.getX() - x)/largeur + ascenceur.getHvalue();
//		double offsetY = (souris.getY() - y)/largeur + ascenceur.getVvalue();
//		ascenceur.setHvalue(offsetX);
//		ascenceur.setVvalue(offsetY);
//		pointDeContact(souris);
//	}
	
	
//	@FXML
//	private void Zoom() {
//        double memX = ascenceur.getHvalue();
//        double memY = ascenceur.getVvalue();
//        largeur = 400;
//        imageCarte.setFitWidth(largeur);
//        ascenceur.setHvalue(memX);
//        ascenceur.setVvalue(memY);
//	}
	

	private void setMode(Mode m2) {m=m2; mode.setText(m.getMessage());}

	

}
