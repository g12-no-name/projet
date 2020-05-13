package projet.data;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Equipe {


	// Données observables
	
	private final Property<Integer>		id			= new SimpleObjectProperty<>();
	private final StringProperty		nom	 		= new SimpleStringProperty();
	private final Property<Integer>		nbBouffe	= new SimpleObjectProperty<>();
	private final Property<TypeCourse>		typeCourse	= new SimpleObjectProperty<>();
	private final Property<CategorieCourse>		catCourse	= new SimpleObjectProperty<>();
		;
	
	
	// Constructeurs
	
	public Equipe() {
	}
	
	public Equipe( int id, String nom, int nbBouffe, TypeCourse typeCourse, CategorieCourse catCourse ) {
		setId(id);
		setNom(nom);
		setNbBouffe(nbBouffe);
		setTypeCourse(typeCourse);
		setCatCourse(catCourse);
	}
	
	


	

	
//	// toString()
//	
//	@Override
//	public String toString() {
//		return getNom() + " " + getPrenom();
//	}
//	
//	
//	// hashCode() & equals()
//
//	@Override
//	public int hashCode() {
//		return Objects.hash(id.getValue() );
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		Equipe other = (Equipe) obj;
//		return Objects.equals(id.getValue(), other.id.getValue() );
//	}

	// Getters & setters
	
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
	

	public final Property<Integer> nbBouffeProperty() {
		return this.nbBouffe;
	}
	

	public final Integer getNbBouffe() {
		return this.nbBouffeProperty().getValue();
	}
	

	public final void setNbBouffe(final Integer nbBouffe) {
		this.nbBouffeProperty().setValue(nbBouffe);
	}
	

	public final Property<TypeCourse> typeCourseProperty() {
		return this.typeCourse;
	}
	

	public final TypeCourse getTypeCourse() {
		return this.typeCourseProperty().getValue();
	}
	

	public final void setTypeCourse(final TypeCourse typeCourse) {
		this.typeCourseProperty().setValue(typeCourse);
	}
	

	public final Property<CategorieCourse> catCourseProperty() {
		return this.catCourse;
	}
	

	public final CategorieCourse getCatCourse() {
		return this.catCourseProperty().getValue();
	}
	

	public final void setCatCourse(final CategorieCourse catCourse) {
		this.catCourseProperty().setValue(catCourse);
	}
	
	
}
