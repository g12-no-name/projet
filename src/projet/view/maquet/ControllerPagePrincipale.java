package projet.view.maquet;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
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
	private ListView<Benevole>			listViewB;
	@FXML
	private ListView<Poste>				listViewP;
	
	@FXML
	private TextField           		heure;
	@FXML 
	private TextField 					mode;
	
	@FXML
	private Canvas 						canvas;
	
	private Mode m;
	
	public static int x=-100, y=-100;

/////////////////////////////////////////////OPENING FUNCTIONS
	
	public ControllerPagePrincipale() {}

	@FXML
	private void initialize() {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		
		gc.drawImage(mpp.imageCarteProperty().getValue(),0,0, canvas.getWidth(), canvas.getHeight());
		// Data binding
		listViewB.setItems( mpp.getListe() );
		listViewB.setCellFactory(  UtilFX.cellFactory( item -> item.getNom() ));
		listViewP.setItems( mpp.getListe2() );
		listViewP.setCellFactory(  UtilFX.cellFactory( item -> item.getNom() ));
		
		
		// l'heure actuelle
		Date now= new Date();
	    DateFormat df = new SimpleDateFormat("HH:mm");
	    String dateTimeString = df.format(now);
	    heure.setText(dateTimeString);
	    
	    m=Mode.observe;
	    mode.setText(m.getMessage());
	    
	    plotDaPlots();
	}

	public void refresh() {
		mpp.actualiserListe();
		UtilFX.selectInListView( listViewB, mpp.getCourant() );
		listViewB.requestFocus();
		mpp.actualiserListe2();
		UtilFX.selectInListView( listViewP, mpp.getCourant2() );
		listViewP.requestFocus();
		plotDaPlots();
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
		Benevole item = listViewB.getSelectionModel().getSelectedItem();
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
		Poste item = listViewP.getSelectionModel().getSelectedItem();
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
		if (m==Mode.addPlot) {
			m=Mode.observe;
			x=(int)e.getX();y=(int)e.getY();
			addPoste(e.getX(), e.getY());
		}
	}

	private void addPoste(double x, double y) {
		mpp.preparerAjouter2((int)x, (int)y);
		managerGui.showView( EnumView.PosteCreation );
	}
	
	private void plotDaPlots() {
		for(Poste p : mpp.getPostes()) {draw(p);}
		
	}
	
	private void draw(Poste p) {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		try {
			int x = p.getX(), y = p.getY();
			gc.setFill(Color.RED);
			gc.fillOval(x, y, 10, 10);
		} catch (Exception e) {System.out.println("tried: "+p.getNom());return;}
		
	}

////////////////////////////////GETTERS SETTERS
	
	@FXML
	public void setMode() {
		if (m==Mode.observe) {m=Mode.addPlot;}
		else if (m==Mode.addPlot) {m=Mode.observe;}
		mode.setText(m.getMessage());
	}

	public static void reinitPosition() {
		x=-100;
		y=-100;
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


