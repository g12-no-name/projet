package projet.data;

import java.time.LocalTime;
import java.util.Objects;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;


public class Assignation  {
	

	// Données observables
	
	private final Property<Integer>		id		= new SimpleObjectProperty<>();
	private final Property<Benevole> benevole	= new SimpleObjectProperty<>();
	private final Property<Poste>	poste		= new SimpleObjectProperty<>();
	private final Property<LocalTime>	heureD	= new SimpleObjectProperty<>();
	private final Property<LocalTime>	heureF	= new SimpleObjectProperty<>();
	
	
	// Constructeurs
	
	public Assignation() {
	}

	public Assignation( final int id, final Benevole benevole, final Poste poste, final LocalTime heureD, final LocalTime heureF) {
		setId(id);
		setBenevole(benevole);
		setPoste(poste);
		setHeureD(heureD);
		setHeureF(heureF);
	}
	
	public String toStringBenevole() {
		
		 String text = getBenevole().getNom()+" "+getBenevole().getPrenom()+" de "+getHeureD().toString()+" à "+getHeureF().toString();
		 return text;
	}

	public String toStringPoste() {
		
		String text = getPoste().toString()+" de "+getHeureD().toString()+" à "+getHeureF().toString();
		return text; 
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String text= getPoste().getNom().toString()+" -> "+getBenevole().getNom().toString()+" -> "+getHeureD().toString()+"/"+getHeureF().toString();
		return text;
	}
	
	public String test(){
		return "test num"+getId();
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
		Assignation other = (Assignation) obj;
		return Objects.equals(id.getValue(), other.id.getValue() );
	}

	
	// Getters et Setters
	
	public final Property<Integer> idProperty() {
		return this.id;
	}
	

	public final Integer getId() {
		return this.idProperty().getValue();
	}
	

	public final void setId(final Integer id) {
		this.idProperty().setValue(id);
	}
	

	public final Property<Benevole> benevoleProperty() {
		return this.benevole;
	}
	

	public final Benevole getBenevole() {
		return this.benevoleProperty().getValue();
	}
	

	public final void setBenevole(final Benevole benevole) {
		this.benevoleProperty().setValue(benevole);
	}
	

	public final Property<Poste> posteProperty() {
		return this.poste;
	}
	

	public final Poste getPoste() {
		return this.posteProperty().getValue();
	}
	

	public final void setPoste(final Poste poste) {
		this.posteProperty().setValue(poste);
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
	

	public final Property<LocalTime> heureFProperty() {
		return this.heureF;
	}
	

	public final LocalTime getHeureF() {
		return this.heureFProperty().getValue();
	}
	

	public final void setHeureF(final LocalTime heureF) {
		this.heureFProperty().setValue(heureF);
	}
	
	
	
}

