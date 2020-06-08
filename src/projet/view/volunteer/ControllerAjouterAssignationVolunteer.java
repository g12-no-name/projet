package projet.view.volunteer;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Map;

import javax.inject.Inject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import jfox.javafx.util.UtilFX;
import jfox.javafx.view.IManagerGui;
import projet.dao.DaoAssignation;
import projet.data.Assignation;
import projet.data.Poste;
import projet.view.EnumView;
import projet.view.poste.ModelAssignation;
import projet.view.poste.ModelPoste;
import projet.view.volunteer.ModelVolunteer;

//////////////////THIS CLASS STILL TO BE MODIFIED. PLEASE TAKE NOTE OR GET LOST.

public class ControllerAjouterAssignationVolunteer {

	
	// Composants de la vue
	
	@FXML
	private TextField			textFieldHeureD;
	@FXML
	private TextField			textFieldHeureF;
	@FXML
	private TextField			textFieldPosteD;
	@FXML
	private TextField			textFieldPosteF;
	@FXML
	private TextField			textFieldBene;
	@FXML
	private ComboBox<Poste>			comboBoxPoste;
	@FXML
	private ListView<LocalTime>			listViewD;
	@FXML
	private ListView<LocalTime>			listViewF;
	
	ObservableList<LocalTime> HD=FXCollections.observableArrayList();
	ObservableList<LocalTime> HF=FXCollections.observableArrayList();
	
	// Autres champs
	
	@Inject
	private IManagerGui			managerGui;
	@Inject
	private ModelVolunteer		modelBenevole;
	@Inject
	private ModelAssignation		modelAssignation;
	@Inject
	private ModelPoste				modelPoste;
	@Inject
	private DaoAssignation	daoAssignation;
	


	// Initialisation du Controller

	@FXML
	private void initialize() {

		// Data binding
		Assignation courant=modelAssignation.getCourant();
		courant.setBenevole(modelBenevole.getCourant());
		textFieldBene.setText(courant.getBenevole().toString());
		comboBoxPoste.setItems( modelPoste.getListe() );
		comboBoxPoste.valueProperty().bindBidirectional( courant.posteProperty() );
		HD.clear();
		HF.clear();
		listViewD.setItems( HD );	
		listViewD.setCellFactory(  UtilFX.cellFactory( item -> item.toString() ));
		listViewF.setItems( HF );	
		listViewF.setCellFactory(  UtilFX.cellFactory( item -> item.toString() ));
		doActualiser();

	}
	

	// Actions
	
	// Gestion des évènements
		// Clic sur la liste
//		@FXML
//		private void gererClicSurListe( MouseEvent event ) {
//			if (event.getButton().equals(MouseButton.PRIMARY)) {
//				
//					if ( listViewB.getSelectionModel().getSelectedIndex() == -1 ) {
//						managerGui.showDialogError( "Aucun élément n'est sélectionné dans la liste.");
//					} else {
//						modelAssignation.getCourant().setBenevole(listViewB.getSelectionModel().getSelectedItem());
//						modelAssignation.getCourant().getBenevole().ModificationDispo();
//						if(!modelAssignation.getCourant().getBenevole().getDisponible().isEmpty()) {
//							for(Map.Entry<LocalTime, LocalTime> h:modelAssignation.getCourant().getBenevole().getDisponible().entrySet()) {
//								HD.add(h.getKey());
//								HF.add(h.getValue());
//							}
//							listViewD.setItems(HD);
//							listViewD.setCellFactory(  UtilFX.cellFactory( item -> item.toString() ));
//							listViewF.setItems(HF);
//							listViewF.setCellFactory(  UtilFX.cellFactory( item -> item.toString() ));
//							listViewD.requestFocus();
//							listViewF.requestFocus();
//						
//						}
//						if(!bene.getDisponible().isEmpty()) {
//							TableColumn<HashMap.Entry<LocalTime, LocalTime>, String> colonne1 = new TableColumn<>("Key");
//							colonne1.setCellValueFactory( new Callback<TableColumn.CellDataFeatures<HashMap.Entry<LocalTime, LocalTime>, String>, ObservableValue<String>>(){
//								 @Override
//								 public ObservableValue<String> call(TableColumn.CellDataFeatures<HashMap.Entry<LocalTime,LocalTime>, String> p){
//									 return new SimpleStringProperty(p.getValue().getKey().toString());
//								 }
//							});
//							TableColumn<HashMap.Entry<LocalTime, LocalTime>, String> colonne2 = new TableColumn<>("Value");
//							colonne2.setCellValueFactory( new Callback<TableColumn.CellDataFeatures<HashMap.Entry<LocalTime, LocalTime>, String>, ObservableValue<String>>(){
//								 @Override
//								 public ObservableValue<String> call(TableColumn.CellDataFeatures<HashMap.Entry<LocalTime,LocalTime>, String> p){
//									 return new SimpleStringProperty(p.getValue().getValue().toString());
//								 }
//							});
//							
//							ObservableList<HashMap.Entry<LocalTime,LocalTime>> items = FXCollections.observableArrayList(bene.getDisponible().entrySet());
//							table=new TableView<>(items);
//							table.getColumns().setAll(colonne1, colonne2);
//							table.setVisible(true);
//						}else {
//							table.setVisible(false);
//						}
//					}
//			}
//		}
		
		private boolean actualiserHeureDDansModele() {
			// Modifie le statut en fct du bouton radio selectionné
			String texte = textFieldHeureD.getText();
			boolean b=true;
			System.out.println(texte);
			try {
				LocalTime heure= LocalTime.parse(texte);
				System.out.println(heure.toString());
				modelAssignation.getCourant().setHeureD(heure);
			}catch(DateTimeParseException e) {
				b=false;
				System.out.println("erreur d");
				StringBuilder message = new StringBuilder();
				message.append("\nEcrivez heure debut sous le format hh:mm");
			};
			return b;
		}
		
		private boolean actualiserHeureFDansModele() {
			// Modifie l'heure dans le modele en fonction du texte
			String texte = textFieldHeureF.getText();
			boolean b=true;
			System.out.println(texte);
			try {
				LocalTime heure= LocalTime.parse(texte);
				System.out.println(heure.toString());
				modelAssignation.getCourant().setHeureF(heure);
			}catch(DateTimeParseException e) {
				b=false;
				System.out.println("erreur f");
				StringBuilder message = new StringBuilder();
				message.append("\nEcrivez heure fin sous le format hh:mm");
			};
			return b;
		}
		
	@FXML
	private void doRetour() {
		managerGui.showView( EnumView.ListeAssignation);
	}
	
	@FXML
	private void doAccueil() {
		managerGui.showView( EnumView.PagePrincipale);
	}
	
	@FXML
	private void doValider() {
		if(actualiserHeureDDansModele()) {
			if(actualiserHeureFDansModele()) {
				modelAssignation.validerMiseAJour();
				managerGui.showView( EnumView.ListeAssignation);	
			}
		}	
	}
	
	@FXML void doActualiser() {
		Assignation courant=modelAssignation.getCourant();
		courant.getBenevole().ModificationDispo(daoAssignation.listerAssignationBenevole(courant.getBenevole().getId()));
		HD.clear();
		HF.clear();
		for(Map.Entry<LocalTime, LocalTime> h:courant.getBenevole().getDisponible().entrySet())
		{
			HD.add(h.getKey());
			HF.add(h.getValue());
		}
		listViewD.refresh();
		listViewF.refresh();
	}
	
	@FXML void doActualiserHoraires(){
		textFieldPosteD.setText(modelAssignation.getCourant().getPoste().getHeureD().toString());
		textFieldPosteF.setText(modelAssignation.getCourant().getPoste().getHeureF().toString());
	}

}
