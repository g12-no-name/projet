package projet.view.restaurant;

import java.util.List;

import javax.inject.Inject;

import projet.dao.DaoEquipe;
import projet.data.Equipe;


public class ModelRestaurant {
	@Inject
	private DaoEquipe daoEquipe;
	
	
	
	
	
	
	
	
	public Equipe retrouverEquipe(String participant1, String participant2) {
		Equipe temp = null;
		//
		List<Equipe> listeEquipe = daoEquipe.listerTout();
		for(int i=0;i<listeEquipe.size();i++) {
			if(listeEquipe.get(i).getNom() == participant1+"_"+participant2 || listeEquipe.get(i).getNom() == participant2+"_"+participant1) {
				temp = listeEquipe.get(i);
			}
		}
		//
		return temp;
	}
	
	
	public void deductionRepas(Equipe e,int val) {
		e.setNbBouffe(val);
		daoEquipe.modifier(e);
	}
	
	
}
