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
import projet.data.Poste;
import projet.view.EnumView;

public class ControllerPageCarte implements MapDrawer{
	
	private double x, y;
	private double largeur;
	

	public ControllerPageCarte() {
		// TODO Auto-generated constructor stub
	}
	
	// composant de la vue
	@FXML
	private TextField           heure;
	@Inject
	private IManagerGui		managerGui;
	
	public ListView<Poste>	listViewP=new ListView<Poste>();

	@FXML
	private Canvas 	canvas;
	
	@Inject
	private ModelPageCarte  mpc;
	
	
	@FXML
	private void initialize() {

		GraphicsContext gc = canvas.getGraphicsContext2D();
		drawMap(mpc, canvas);
		
		mpc.actualiserListe2();
		listViewP.setItems( mpc.getListe2() );
		listViewP.setCellFactory(  UtilFX.cellFactory( item -> item.getNom() ));
		
		
		// l'heure actuelle
		Date now= new Date();
	      
	    DateFormat df = new SimpleDateFormat("HH:mm");
	    String dateTimeString = df.format(now);
	    heure.setText(dateTimeString);
	    
	    plotDaPlots(listViewP, mpc, canvas, ControllerPagePrincipale.plotSize);
	}
	
	public void refresh() {
		mpc.actualiserListe2();
		plotDaPlots(listViewP, mpc, canvas, ControllerPagePrincipale.plotSize); 
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
	private void doPrincipale() {
		managerGui.execTask( () -> {Platform.runLater( () -> {managerGui.showView(EnumView.PagePrincipale);});});
	}

	@FXML
	private void doM() {
		managerGui.execTask( () -> {Platform.runLater( () -> {managerGui.showView(EnumView.PageMessagerie);});});
	}

	@Override
	@FXML
	public void plot() {
		plotDaPlots(listViewP, mpc, canvas, ControllerPagePrincipale.plotSize);
	}
			
	@FXML 
	public void doClickOnMap(MouseEvent e) {
		int x=(int)(e.getX());int y=(int)(e.getY());
		checkForProximityWithAPlot(x, y, listViewP, canvas, ControllerPagePrincipale.plotSize);
		plotDaPlots(listViewP, mpc, canvas, ControllerPagePrincipale.plotSize);
	}
//			@FXML
//			   private void deplacer(MouseEvent souris) {
//			      double offsetX = (souris.getX() - x)/largeur + ascenceur.getHvalue();
//			      double offsetY = (souris.getY() - y)/largeur + ascenceur.getVvalue();
//			      ascenceur.setHvalue(offsetX);
//			      ascenceur.setVvalue(offsetY);
//			      pointDeContact(souris);
//			   }

}
