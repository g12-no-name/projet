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
	

/////////////////////////////////////////////OPENING FUNCTIONS
	
	public ControllerPagePrincipale() {}

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
	    
	    m=Mode.observe;
	    mode.setText(m.getMessage());
	}
	
	

	public void refresh() {
		mpp.actualiserListe();
		UtilFX.selectInListView( listView, mpp.getCourant() );
		listView.requestFocus();
		mpp.actualiserListe2();
		UtilFX.selectInListView( listView2, mpp.getCourant2() );
		listView2.requestFocus();
	}
	
	
////////////////////////////////////////BUTTON AND CLICK FUNCTIONS
	
	@FXML
	private void doheure() {
		Date now= new Date();
        DateFormat df = new SimpleDateFormat("HH:mm");
        String dateTimeString = df.format(now);
        heure.setText(dateTimeString);
	}

	@FXML
	private void doCarte() {
		managerGui.execTask( () -> {Platform.runLater( () -> {managerGui.showView(EnumView.PageCarte);});} );
	}
	
	@FXML
	private void doAjoutBenevole() {
		mpp.preparerAjouter();
		managerGui.showView( EnumView.BenevoleCreate );
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
	public void doClickOnMap(MouseEvent e) {
		System.out.println(m);
		if (m==Mode.addPlot) {
			m=Mode.observe;
			addPoste(e.getX(), e.getY());
		}
	}

	private void addPoste(double x, double y) {
		mpp.preparerAjouter2();
		managerGui.showView( EnumView.PosteCreation );
	}
	
////////////////////////////////GETTERS SETTERS
	
	@FXML
	public void setMode() {
		if (m==Mode.observe) {m=Mode.addPlot;}
		else if (m==Mode.addPlot) {m=Mode.observe;}
		mode.setText(m.getMessage());
	}

	

}




//@FXML
//private void deplacer(MouseEvent souris) {
//	double offsetX = (souris.getX() - x)/largeur + ascenceur.getHvalue();
//	double offsetY = (souris.getY() - y)/largeur + ascenceur.getVvalue();
//	ascenceur.setHvalue(offsetX);
//	ascenceur.setVvalue(offsetY);
//	pointDeContact(souris);
//}


//@FXML
//private void Zoom() {
//    double memX = ascenceur.getHvalue();
//    double memY = ascenceur.getVvalue();
//    largeur = 400;
//    imageCarte.setFitWidth(largeur);
//    ascenceur.setHvalue(memX);
//    ascenceur.setVvalue(memY);
//}


