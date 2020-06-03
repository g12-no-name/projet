package projet.view.maquet;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import jfox.javafx.util.UtilFX;
import jfox.javafx.view.IManagerGui;
import projet.view.EnumView;

public class ControllerPageCarte {
	
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
	

	@FXML
	private Canvas 						canvas;
	
	@Inject
	private ModelPageCarte  mpp;
	
	@FXML
	private void initialize() {

		GraphicsContext gc = canvas.getGraphicsContext2D();
		
		gc.drawImage(mpp.imageCarteProperty().getValue(),0,0, canvas.getWidth(), canvas.getHeight());

		
		// l'heure actuelle
		Date now= new Date();
	      
	    DateFormat df = new SimpleDateFormat("HH:mm");
	    String dateTimeString = df.format(now);
	    heure.setText(dateTimeString);
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
	private void pointDeContact(MouseEvent souris) {
		x = souris.getX();
		y = souris.getY();
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
