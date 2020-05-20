package projet.view.maquet;

import javax.inject.Inject;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import jfox.javafx.view.IManagerGui;
import projet.view.EnumView;


public class ControllerInfo {
	
	
	// Composants de la vue
	
	@FXML
	private Label		labelTitre;
	@FXML
	private Label		labelMessage;

	
	// Autres champs
	
	@Inject
	private ModelInfo	modelInfo;
	@Inject
	private IManagerGui		managerGui;
	
	
	// Initialisation
	
	@FXML
	private void initialize() {
		
		// Data binding
		labelTitre.textProperty().bind( modelInfo.titreProperty() );
		labelMessage.textProperty().bind( modelInfo.messageProperty() );
		
	}
	
	@FXML
	private void doAccueil() {
		managerGui.showView(EnumView.PagePrincipale);
//		managerGui.execTask( () -> {Platform.runLater( () -> {managerGui.showView(EnumView.PagePrincipale);}) ;
//		} );
	}
	

}
