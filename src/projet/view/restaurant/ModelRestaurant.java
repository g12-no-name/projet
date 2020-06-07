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
			String s1=listeEquipe.get(i).getNom().toLowerCase();
			String s2=participant1.toLowerCase()+"_"+participant2.toLowerCase();
			String s3=participant2.toLowerCase()+"_"+participant1.toLowerCase();
			if( s1.equals(s2) || s1.equals(s3)) {
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
	
	public DaoEquipe getDaoEquipe() {return this.daoEquipe;}
	
}
