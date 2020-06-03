package projet.commun;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import projet.data.Assignation;
import projet.data.Benevole;
import projet.data.Compte;
import projet.data.Poste;
import projet.data.TypePoste;


@Mapper
public interface IMapper {  
	
	
	Benevole update (@MappingTarget Benevole target, Benevole source );
	
	Compte    update(@MappingTarget Compte target, Compte source );
	
	TypePoste update( @MappingTarget TypePoste target, TypePoste source);
	
	@Mapping( target="benevole", expression="java( source.getBenevole() )" )
	Assignation update(@MappingTarget Assignation target, Assignation source);
	
	@Mapping( target="typePoste", expression="java( source.getTypePoste() )" )
	Poste update( @MappingTarget Poste target, Poste source );
	
}
