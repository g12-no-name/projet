package projet.view.restaurant;

import javax.inject.Inject;

import projet.dao.DaoEquipe;
import projet.data.Equipe;


public class ModelRestaurant {
	@Inject
	private DaoEquipe daoEquipe;
	
	
	
	
	
	
	
	
	
	
	
	public void deductionRepas(Equipe e,int val) {
		e.setNbBouffe(val);
		daoEquipe.modifier(e);
	}
	
	
	
	
	
	
	
}
