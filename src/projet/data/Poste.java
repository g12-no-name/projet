package projet.data;

import java.time.LocalTime;
import java.util.Objects;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class Poste  {
	

	// Donnees observables
	
	private final Property<Integer>	id		= new SimpleObjectProperty<>();
	private final StringProperty	nom	= new SimpleStringProperty();
	private final Property<TypePoste> typePoste=new SimpleObjectProperty<>();
	private final Property<LocalTime> heureD=new SimpleObjectProperty<>();
	private final Property<LocalTime> heureF=new SimpleObjectProperty<>();
	private final ObservableList<Assignation> benevoles = FXCollections.observableArrayList();
	
	private final Property<Integer> x = new SimpleObjectProperty<>();
	private final Property<Integer> y = new SimpleObjectProperty<>();
	
	// Constructeurs
	
	public Poste() {
	}

	public Poste( final int id, final String nom, final TypePoste typePoste, final LocalTime heureD, final LocalTime heureF, final int x, final int y) {
		setId(id);
		setNom(nom);
		setTypePoste(typePoste);
		setHeureD(heureD);
		setHeureF(heureF);
		setX(x);
		setY(y);
	}
	
	
	

	
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
		Poste other = (Poste) obj;
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
	

	public final Property<TypePoste> typePosteProperty() {
		return this.typePoste;
	}
	

	public final TypePoste getTypePoste() {
		return this.typePosteProperty().getValue();
	}
	

	public final void setTypePoste(final TypePoste typePoste) {
		this.typePosteProperty().setValue(typePoste);
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
	
	public ObservableList<Assignation> getBenevoles() {
		return benevoles;}

	public final Property<Integer> xProperty() {
		return this.x;
	}
	

	public final Integer getX() {
		return this.xProperty().getValue();
	}
	

	public final void setX(final Integer x) {
		this.xProperty().setValue(x);
	}
	

	public final Property<Integer> yProperty() {
		return this.y;
	}
	

	public final Integer getY() {
		return this.yProperty().getValue();
	}
	

	public final void setY(final Integer y) {
		this.yProperty().setValue(y);
	}
	
	

}

