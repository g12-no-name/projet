package projet.data;

import java.time.LocalDate;
import java.util.Objects;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class Participant  {
	

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
	private final StringProperty	club	= new SimpleStringProperty();
	private final Property<Boolean>	capitaine		= new SimpleObjectProperty<>();
	private final Property<Boolean>	paiement		= new SimpleObjectProperty<>();
	private final Property<Boolean>	attestation		= new SimpleObjectProperty<>();
	private final Property<Boolean>	dossierMedic		= new SimpleObjectProperty<>();
	private final Property<Equipe>	equipe		= new SimpleObjectProperty<>();

	
	// Constructeurs
	
	public Participant() {
	}

	public Participant( final int id ) {
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
		Participant other = (Participant) obj;
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

	public final StringProperty clubProperty() {
		return this.club;
	}
	

	public final String getClub() {
		return this.clubProperty().get();
	}
	

	public final void setClub(final String club) {
		this.clubProperty().set(club);
	}
	

	public final Property<Boolean> capitaineProperty() {
		return this.capitaine;
	}
	

	public final Boolean getCapitaine() {
		return this.capitaineProperty().getValue();
	}
	

	public final void setCapitaine(final Boolean capitaine) {
		this.capitaineProperty().setValue(capitaine);
	}
	

	public final Property<Boolean> paiementProperty() {
		return this.paiement;
	}
	

	public final Boolean getPaiement() {
		return this.paiementProperty().getValue();
	}
	

	public final void setPaiement(final Boolean paiement) {
		this.paiementProperty().setValue(paiement);
	}
	

	public final Property<Boolean> attestationProperty() {
		return this.attestation;
	}
	

	public final Boolean getAttestation() {
		return this.attestationProperty().getValue();
	}
	

	public final void setAttestation(final Boolean attestation) {
		this.attestationProperty().setValue(attestation);
	}
	

	public final Property<Boolean> dossierMedicProperty() {
		return this.dossierMedic;
	}
	

	public final Boolean getDossierMedic() {
		return this.dossierMedicProperty().getValue();
	}
	

	public final void setDossierMedic(final Boolean dossierMedic) {
		this.dossierMedicProperty().setValue(dossierMedic);
	}
	

	public final Property<Equipe> equipeProperty() {
		return this.equipe;
	}
	

	public final Equipe getEquipe() {
		return this.equipeProperty().getValue();
	}
	

	public final void setEquipe(final Equipe equipe) {
		this.equipeProperty().setValue(equipe);
	}

}

