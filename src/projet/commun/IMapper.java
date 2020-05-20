package projet.commun;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import projet.data.Benevole;
import projet.data.Categorie;
import projet.data.Compte;
import projet.data.Memo;
import projet.data.Personne;
import projet.data.Poste;
import projet.data.Service;


@Mapper
public interface IMapper {  
	
	Poste   update  (@MappingTarget Poste target , Poste source);
	
	Benevole update (@MappingTarget Benevole target, Benevole source );
	
	Compte    update(@MappingTarget Compte target, Compte source );
	
	Categorie update( @MappingTarget Categorie target, Categorie source );

	@Mapping( target="categorie", expression="java( source.getCategorie() )" )
	Personne update( @MappingTarget Personne target, Personne source );

	@Mapping( target="categorie", expression="java( source.getCategorie() )" )
	Memo update( @MappingTarget Memo target, Memo source );

	Service update( @MappingTarget Service target, Service source );
	
}
