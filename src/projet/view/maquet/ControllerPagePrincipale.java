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

public class ControllerPagePrincipale implements MapDrawer{
	
	@Inject
	private IManagerGui					managerGui;
	@Inject
	public ModelPagePrincipale			mpp;
	
	@FXML
	private ListView<Benevole>			listViewB;
	@FXML
	public ListView<Poste>				listViewP;
	
	@FXML
	private TextField           		heure;
	@FXML 
	private TextField 					mode;
	
	@FXML
	private Canvas 						canvas;
	
	private Mode m;
	
	public static int xStaticForTransmission=-100, yStaticForTransmission=-100;
	
	public static int plotSize=10;

/////////////////////////////////////////////OPENING FUNCTIONS
	
	public ControllerPagePrincipale() {}

	@FXML
	private void initialize() {
		drawMap(mpp, canvas);
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
	    
	    plotDaPlots(listViewP, mpp, canvas, plotSize);
	}

	public void refresh() {
		mpp.actualiserListe();
		UtilFX.selectInListView( listViewB, mpp.getCourant() );
		listViewB.requestFocus();
		mpp.actualiserListe2();
		UtilFX.selectInListView( listViewP, mpp.getCourant2() );
		listViewP.requestFocus();
		plotDaPlots(listViewP, mpp, canvas, plotSize); 
		mode.setText(m.getMessage());
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
			xStaticForTransmission=(int)(e.getX()*1000/canvas.getWidth());
			yStaticForTransmission=(int)(e.getY()*1000/canvas.getHeight());
			addPoste();
		}else if(m==Mode.observe) {
			int x=(int)(e.getX());int y=(int)(e.getY());
			checkForProximityWithAPlot(x, y, listViewP, canvas, plotSize);
		}else if(m==Mode.modifyPlot) {
			if (listViewP.getSelectionModel().getSelectedItem()!=null) {
				xStaticForTransmission=(int)(e.getX()*1000/canvas.getWidth());
				yStaticForTransmission=(int)(e.getY()*1000/canvas.getHeight());
				Poste courant = listViewP.getSelectionModel().getSelectedItem();
				courant.setX(xStaticForTransmission);
				courant.setY(yStaticForTransmission);
				if ( courant.getId() == null ) {
					courant.setId( mpp.inserer( courant ) );
				} else {
					// modificiation
					mpp.modifier( courant );
				}
			}else {
				managerGui.showDialogError("Veuillez sélectionner un poste");
			}
		}
		plotDaPlots(listViewP, mpp, canvas, plotSize);
	}

	

	private void addPoste() {
		managerGui.showView( EnumView.PosteCreation );
	}
	
	private void modifyPoste() {
		managerGui.showView(EnumView.PosteModif);
	}
	
	/////////////////////////////////////METHODS THAT DRAW
	
	

	//////////////////////////////////////GETTERS SETTERS
	
	@FXML
	public void setMode() {
		if (m==Mode.observe) {m=Mode.addPlot;}
		else {m=Mode.observe;}
		mode.setText(m.getMessage());
	}
	
	@FXML 
	public void modeModif() {
		if (m==Mode.modifyPlot) {m=Mode.observe;}
		else {m=Mode.modifyPlot;}
		mode.setText(m.getMessage());
	}
	
	@FXML
	public static void reinitPosition() {
		xStaticForTransmission=-100;
		yStaticForTransmission=-100;
	}

	@Override
	@FXML
	public void plot() {
		plotDaPlots(listViewP, mpp, canvas, plotSize);
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


