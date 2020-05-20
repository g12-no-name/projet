package projet.view;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import javafx.application.Platform;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import jfox.javafx.view.IManagerGui;
import projet.commun.Roles;
import projet.data.Compte;
import projet.report.EnumReport;
import projet.report.ManagerReport;
import projet.view.systeme.ModelConnexion;


public class MenuBarAppli extends MenuBar {

	
	// Champs
	
	private Menu	menuDonnees;
	private Menu	menuEtats;
	private Menu	menuTests;
	
	private MenuItem itemDeconnecter;

	private MenuItem itemCategories;
	private MenuItem itemComptes;
	
	@Inject
	private IManagerGui 	managerGui;
	@Inject
	private ManagerReport 	managerReport;
	@Inject
	private ModelConnexion	modelConnexion;	
	
	
	// Initialisation
	
	@PostConstruct
	public void init() {

		
		// Variables de travail
		Menu menu;
		MenuItem item;
		
		
		// Manu Système
		
		menu =  new Menu( "Systeme" );
		this.getMenus().add(menu);
		
		item = new MenuItem( "Se deconnecter" );
		item.setOnAction(  (e) -> managerGui.showView( EnumView.Connexion )  );
		menu.getItems().add( item );
		itemDeconnecter = item;
		
		item = new MenuItem( "Quitter" );
		item.setOnAction(  (e) -> managerGui.exit()  );
		menu.getItems().add( item );
		
		/////////////Vues Principales
		
		menu = new Menu( "Vue Generale" );
		this.getMenus().add(menu);
		menu.setOnAction( (e) -> managerGui.showView( EnumView.PagePrincipale ) );
		
		menu = new Menu( "Vue Carte" );
		this.getMenus().add(menu);
		menu.setOnAction( (e) -> managerGui.showView( EnumView.PageCarte ) );
		
		/////////////Vues d'information
		
		menu = new Menu( "Postes" );
		this.getMenus().add(menu);
		menu.setOnAction( (e) -> managerGui.showView( EnumView.PosteListe ));
		
		menu = new Menu( "Benevoles" );
		this.getMenus().add(menu);
		menu.setOnAction( (e) -> managerGui.showView( EnumView.BenevoleListe ));
		
		
		


		// Configuration initiale du menu
		configurerMenu( modelConnexion.getCompteActif() );

		// Le changement du compte connecté modifie automatiquement le menu
		modelConnexion.compteActifProperty().addListener( (obs) -> {
					Platform.runLater( () -> configurerMenu( modelConnexion.getCompteActif() ) );
				}
			); 
		
	}

	
	// Méthodes auxiliaires
	
	private void configurerMenu( Compte compteActif  ) {

		itemDeconnecter.setDisable(true);
		
		
		
//		menuDonnees.setVisible(false);
//		itemCategories.setVisible(false);
//		itemComptes.setVisible(false);
//		menuEtats.setVisible(false);
//		menuTests.setVisible(false);
//		menuEtats.setVisible(false);
		
		if( compteActif != null ) {
			itemDeconnecter.setDisable(false);
			if( compteActif.isInRole( Roles.UTILISATEUR) ) {
				menuDonnees.setVisible(true);
				menuEtats.setVisible(true);
			}
			if( compteActif.isInRole( Roles.ADMINISTRATEUR ) ) {
				menuDonnees.setVisible(true);
				itemCategories.setVisible(true);
				itemComptes.setVisible(true);
				menuTests.setVisible(true);
			}
		}
	}
	
}


/* GLITCH WORLD
 * // Manu Etats
		
		menu =  new Menu( "Etats" );;
		this.getMenus().add(menu);
		menuEtats = menu;
		
		item = new MenuItem( "Personnes par catégorie v1" );
		item.setOnAction(  (e) ->  
				managerGui.showDialog( EnumView.EtatPersonnesParCateogire1 ) );
		menu.getItems().add( item );
		
		item = new MenuItem( "Personnes par catégorie v2" );
		item.setOnAction(  (e) ->  
				managerGui.showDialog( EnumView.EtatPersonnesParCateogire2 ) );
		menu.getItems().add( item );
		
		item = new MenuItem( "Liste des personnes (PDF)" );
		item.setOnAction(  (e) ->  
				managerReport.openFilePdf( EnumReport.PersonnesListeSimple, null ) );
		menu.getItems().add( item );
		
		item = new MenuItem( "Liste des personnes (viewer)" );
		item.setOnAction(  (e) ->  
				managerReport.showViewer( EnumReport.PersonnesListeSimple, null ) );
		menu.getItems().add( item );
		
		item = new MenuItem( "Annuaire téléphonique" );
		item.setOnAction(  (e) ->  
//				managerReport.print( EnumReport.AnnuaireTelephone, null ) );
				managerReport.showViewer( EnumReport.AnnuaireTelephone, null ) );
		menu.getItems().add( item );

		
		// Manu Tests
		
		menu =  new Menu( "Tests" );;
		this.getMenus().add(menu);
		menuTests = menu;
		
		item = new MenuItem( "DaoCategorie" );
		item.setOnAction(  (e) -> managerGui.showView( EnumView.TestDaoCategorie )  );
		menu.getItems().add( item );
		
		item = new MenuItem( "DaoMemo" );
		item.setOnAction(  (e) -> managerGui.showView( EnumView.TestDaoMemo )  );
		menu.getItems().add( item );
		
		item = new MenuItem( "DaoService" );
		item.setOnAction(  (e) -> managerGui.showView( EnumView.TestDaoService )  );
		menu.getItems().add( item );*/
