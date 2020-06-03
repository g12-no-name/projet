package projet.view.poste;

import java.time.LocalTime;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import jfox.commun.exception.ExceptionValidation;
import jfox.javafx.util.UtilFX;
import projet.commun.IMapper;
import projet.dao.DaoAssignation;
import projet.data.Assignation;
import projet.data.Poste;
import projet.view.volunteer.ModelVolunteer;

public class ModelAssignation {

	// Données observables

	private final ObservableList<Assignation> liste = FXCollections.observableArrayList();

	private Assignation courant = new Assignation();

	// Autres champs
	@Inject
	private IMapper mapper;
	@Inject
	private DaoAssignation daoAssignation;
	@Inject
	private ModelVolunteer modelVolunteer;

	// Initialisations

	@PostConstruct
	public void init() {
	}

	// Getters

	public ObservableList<Assignation> getListe() {
		return liste;
	}

	public Assignation getCourant() {
		return courant;
	}

	// Actualisations

	public void actualiserListe() {
		liste.setAll(daoAssignation.listerAssignationPoste(ControllerPosteModif.dernier));
	}

	// Actions

	public void preparerAjouter() {
		modelVolunteer.actualiserListe();
		mapper.update(courant, new Assignation());

	}

	public void preparerModifier(Poste item) {
		modelVolunteer.actualiserListe();
		mapper.update(courant, daoAssignation.retrouver(item.getId()));
	}

	public boolean verifDispo() {
		courant.getBenevole().ModificationDispo();
		if(!courant.getBenevole().getDisponible().isEmpty()) {
			for (Map.Entry<LocalTime, LocalTime> dispo : courant.getBenevole().getDisponible().entrySet()) {
				if (((courant.getHeureD().isAfter(dispo.getKey())) || (courant.getHeureD().equals(dispo.getKey())))
						&& ((courant.getHeureF().isBefore(dispo.getValue()))
								|| (courant.getHeureF().equals(dispo.getValue())))) {
					return true;
				}
			}
		}
		return false;
	}

	public void validerMiseAJour() {

		// Vérifie la validité des données

		StringBuilder message = new StringBuilder();

		if (courant.getBenevole() == null) {
			message.append("\nIl doit y avoir un benevole.");
		} else {
			if (!verifDispo()) {
				message.append("\nLe benevole doit être disponible");
			}
		}

		if (message.length() > 0) {
			throw new ExceptionValidation(message.toString().substring(1));
		}

		// Effectue la mise à jour

		if (courant.getId() == null) {
			// Insertion
			courant.setId(daoAssignation.inserer(courant));
		} else {
			// modficiation
			daoAssignation.modifier(courant);
		}

	}

	public void supprimer(Assignation item) {

		daoAssignation.supprimer(item.getId());
		mapper.update(courant, UtilFX.findNext(liste, item));

		// getFichierSchemaCourant().delete();
	}
}
