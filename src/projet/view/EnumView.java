package projet.view;

import javafx.scene.Scene;
import jfox.javafx.view.IEnumView;


public enum EnumView implements IEnumView {

	
	// Valeurs
	
	
	
	CompteListe			( "compte/ViewCompteListe.fxml" ),
	CompteForm			( "compte/ViewCompteForm.fxml" ),
	CategorieListe		( "personne/ViewCategorieListe.fxml" ),
	CategorieForm		( "personne/ViewCategorieForm.fxml" ),
	PersonneListe		( "personne/ViewPersonneListe.fxml" ),
	PersonneForm		( "personne/ViewPersonneForm.fxml" ),
	MemoListe			( "memo/ViewMemoListe.fxml" ),
	MemoForm			( "memo/ViewMemoForm.fxml" ),
	MemoAjoutPersonnes	( "memo/ViewMemoAjoutPersonnes.fxml" ),
	ServiceListe		( "service/ViewServiceListe.fxml" ),
	ServiceForm			( "service/ViewServiceForm.fxml" ),
	TestDaoCategorie	( "test/ViewTestDaoCategorie.fxml" ),
	TestDaoMemo			( "test/ViewTestDaoMemo.fxml" ),
	TestDaoService		( "test/ViewTestDaoService.fxml" ),
	EtatPersonnesParCateogire1	( "personne/ViewEtatPersonnesParCategorie1.fxml" ),
	EtatPersonnesParCateogire2	( "personne/ViewEtatPersonnesParCategorie2.fxml" ),

	PosteCreation		( "poste/ViewPosteCreation.fxml" ),
	PosteInfo			( "poste/ViewPosteInfo.fxml" ),
	PosteListe			( "poste/ViewPosteListe.fxml" ),
	PosteModif			( "poste/ViewPosteModif.fxml" ),

	ListeAssignationPoste		("poste/ViewListeAssignationPoste.fxml"),
	AjouterAssignationPoste		("poste/ViewAjouterAssignationPoste.fxml"),
	ModifierAssignationPoste	("poste/ViewModifierAssignationPoste.fxml"),
	
	BenevoleListe		( "volunteer/ViewVolunteerListe.fxml" ),
	BenevoleCreate		( "volunteer/ViewVolunteerCreation.fxml" ),
	BenevoleModify		( "volunteer/ViewVolunteerModif.fxml" ),
	BenevoleView		( "volunteer/ViewVolunteerInfo.fxml" ),
	
	PagePrincipale      ("maquet/ViewPagePrincipale.fxml"),
	PageCarte           ("maquet/ViewPageCarte.fxml"),
	Info				( "maquet/ViewInfo.fxml" ),
	Connexion			( "maquet/ViewConnexion.fxml" ),
	
	resto			( "restaurant/ViewRestaurant.fxml" )
	
	;

	
	// Champs
	
	private String		path;
	private Object		controller;
	private Scene		scene;

	
	// Constructeur 
	
	EnumView( String path ) {
		this.path = path;
	}

	
	// Getters & setters

	@Override
	public String getPath() {
		return path;
	}
	
	@Override
	public Object getController() {
		return controller;
	}

	@Override
	public void setController(Object controller) {
		this.controller = controller;
	}
	
	@Override
	public Scene getScene() {
		return scene;
	}
	
	@Override
	public void setScene(Scene scene) {
		this.scene = scene;
	}

}
