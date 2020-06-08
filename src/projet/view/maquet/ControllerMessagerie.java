package projet.view.maquet;

import javax.inject.Inject;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import jfox.javafx.util.UtilFX;
import jfox.javafx.view.IManagerGui;
import projet.view.EnumView;
import projet.view.poste.ModelPoste;
import projet.view.volunteer.ModelVolunteer;

public class ControllerMessagerie {
	
	@FXML
	private ListView<Message> list;
	
	@Inject
	private IManagerGui			managerGui;
	@Inject
	private ModelVolunteer		modelV;
	@Inject
	private ModelPoste			modelP;
	
	@FXML
	public Button PP;
	ObservableList<Message> listeM = FXCollections.observableArrayList();
	
	@FXML
	private void initialize() {
		modelP.actualiserListe();
		modelV.actualiserListe();

	// Data binding
		for(Message m : modelV.getListeMessages()) {
			listeM.add(m);
		}
		for(Message m : modelP.getListeMessages()) {
			listeM.add(m);
		}
		list.setItems( listeM );
		
		// Configuration des boutons
		
		
		list.setCellFactory( UtilFX.cellFactory( item -> item.getMessage() ) );
	}
	
	
	public void goBack(){
		managerGui.execTask( () -> {Platform.runLater( () -> {managerGui.showView(EnumView.PagePrincipale);});} );
	}
	
	
	
	public void refresh() {
		initialize();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
