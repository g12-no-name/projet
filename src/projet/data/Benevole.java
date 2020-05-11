package projet.data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class Benevole  {
	

	// Données observables
	
	private final Property<Integer>	id		= new SimpleObjectProperty<>();
	private final StringProperty	nom	= new SimpleStringProperty();
	private final StringProperty	prenom	= new SimpleStringProperty();
	private final Property<LocalDate>	dateNaissance		= new SimpleObjectProperty<>();
	private final StringProperty	mail	= new SimpleStringProperty();
	private final StringProperty	numTel	= new SimpleStringProperty();
	private final StringProperty	adresse	= new SimpleStringProperty();
	private final StringProperty	ville	= new SimpleStringProperty();
	private final Property<Boolean>	mineur		= new SimpleObjectProperty<>();
	private final Property<Boolean>	autorisationParentale		= new SimpleObjectProperty<>();
	private final Property<Boolean>	permis		= new SimpleObjectProperty<>();
	private final Property<Boolean>	membre		= new SimpleObjectProperty<>();
	private final Property<LocalTime>	heureD		= new SimpleObjectProperty<>();
	private final Property<LocalTime>	HeureF		= new SimpleObjectProperty<>();
	private final ObservableList<Poste> postes = FXCollections.observableArrayList();

	
	// Constructeurs
	
	public Benevole() {
	}

	public Benevole( final int id ) {
		setId(id);

	}
	
	
	// Getters et Setters

	
	// toString()
	
	@Override
	public String toString() {
		return getNom();
	}
	
	
	// hashCode() & equals()

	@Override
	public int hashCode() {
		return Objects.hash(id.getValue() );
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Benevole other = (Benevole) obj;
		return Objects.equals(id.getValue(), other.id.getValue() );
	}

	public final Property<Integer> idProperty() {
		return this.id;
	}
	

	public final Integer getId() {
		return this.idProperty().getValue();
	}
	

	public final void setId(final Integer id) {
		this.idProperty().setValue(id);
	}
	

	public final StringProperty nomProperty() {
		return this.nom;
	}
	

	public final String getNom() {
		return this.nomProperty().get();
	}
	

	public final void setNom(final String nom) {
		this.nomProperty().set(nom);
	}
	

	public final StringProperty prenomProperty() {
		return this.prenom;
	}
	

	public final String getPrenom() {
		return this.prenomProperty().get();
	}
	

	public final void setPrenom(final String prenom) {
		this.prenomProperty().set(prenom);
	}
	

	public final Property<LocalDate> dateNaissanceProperty() {
		return this.dateNaissance;
	}
	

	public final LocalDate getDateNaissance() {
		return this.dateNaissanceProperty().getValue();
	}
	

	public final void setDateNaissance(final LocalDate dateNaissance) {
		this.dateNaissanceProperty().setValue(dateNaissance);
	}
	

	public final StringProperty mailProperty() {
		return this.mail;
	}
	

	public final String getMail() {
		return this.mailProperty().get();
	}
	

	public final void setMail(final String mail) {
		this.mailProperty().set(mail);
	}
	

	public final StringProperty numTelProperty() {
		return this.numTel;
	}
	

	public final String getNumTel() {
		return this.numTelProperty().get();
	}
	

	public final void setNumTel(final String numTel) {
		this.numTelProperty().set(numTel);
	}
	

	public final StringProperty adresseProperty() {
		return this.adresse;
	}
	

	public final String getAdresse() {
		return this.adresseProperty().get();
	}
	

	public final void setAdresse(final String adresse) {
		this.adresseProperty().set(adresse);
	}
	

	public final StringProperty villeProperty() {
		return this.ville;
	}
	

	public final String getVille() {
		return this.villeProperty().get();
	}
	

	public final void setVille(final String ville) {
		this.villeProperty().set(ville);
	}
	

	public final Property<Boolean> mineurProperty() {
		return this.mineur;
	}
	

	public final Boolean getMineur() {
		return this.mineurProperty().getValue();
	}
	

	public final void setMineur(final Boolean mineur) {
		this.mineurProperty().setValue(mineur);
	}
	

	public final Property<Boolean> autorisationParentaleProperty() {
		return this.autorisationParentale;
	}
	

	public final Boolean getAutorisationParentale() {
		return this.autorisationParentaleProperty().getValue();
	}
	

	public final void setAutorisationParentale(final Boolean autorisationParentale) {
		this.autorisationParentaleProperty().setValue(autorisationParentale);
	}

	public final Property<Boolean> permisProperty() {
		return this.permis;
	}
	

	public final Boolean getPermis() {
		return this.permisProperty().getValue();
	}
	

	public final void setPermis(final Boolean permis) {
		this.permisProperty().setValue(permis);
	}
	

	public final Property<Boolean> membreProperty() {
		return this.membre;
	}
	

	public final Boolean getMembre() {
		return this.membreProperty().getValue();
	}
	

	public final void setMembre(final Boolean membre) {
		this.membreProperty().setValue(membre);
	}
	

	public final Property<LocalTime> heureDProperty() {
		return this.heureD;
	}
	

	public final LocalTime getHeureD() {
		return this.heureDProperty().getValue();
	}
	

	public final void setHeureD(final LocalTime heureD) {
		this.heureDProperty().setValue(heureD);
	}
	

	public final Property<LocalTime> HeureFProperty() {
		return this.HeureF;
	}
	

	public final LocalTime getHeureF() {
		return this.HeureFProperty().getValue();
	}
	

	public final void setHeureF(final LocalTime HeureF) {
		this.HeureFProperty().setValue(HeureF);
	}
	
	public ObservableList<Poste> getPostes() {
		return postes;
	}
	
}

