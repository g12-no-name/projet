package projet.view.maquet;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import jfox.javafx.util.UtilFX;
import jfox.javafx.view.IManagerGui;
import projet.view.EnumView;

public class ControllerPageCarte {

	public ControllerPageCarte() {
		// TODO Auto-generated constructor stub
	}
	
	// composant de la vue
	@FXML
	private TextField           heure;
	@Inject
	private IManagerGui		managerGui;
	
	@FXML
	private void initialize() {

		
		// l'heure actuelle
		Date now= new Date();
	      
	       DateFormat df = new SimpleDateFormat("HH:mm:ss.SSS");
	        String dateTimeString = df.format(now);
	        heure.setText(dateTimeString);
	}
	
	
	// Actions
	
			@FXML
			private void doPrincipale() {
				managerGui.execTask( () -> {
					Platform.runLater( () -> {
		        			managerGui.showView(EnumView.PagePrincipale);
		            }) ;
				} );
			}

}
