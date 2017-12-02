package com.moteurInferences;

import com.faits.BaseDeFaits;
import com.faits.FaitsEntiers;
import com.faits.FaitsEntiersNiveau0;
import com.faits.InterfaceFaits;
import com.regles.BaseDeRegles;
import com.regles.Regles;

public class MoteurInferences {

	protected BaseDeFaits bdf;
	protected BaseDeRegles bdr;
	
	private int niveauMaxRegle;
	
	public MoteurInferences(BaseDeFaits pbdf, BaseDeRegles pbdr) {
		this.bdf = pbdf;
		this.bdr = pbdr;
	}
	
	public void chainageAvant() {
		this.resoudre();
	}
	
	
//Pour chainage avant ------------------------------------------------------------- 
	private int estApplicable(Regles pr) {
		int niveauMax = -1;
		
		for (InterfaceFaits f : pr.getPremisses()) {
			InterfaceFaits faitTrouve = bdf.chercher(f.getNom());
			if (faitTrouve == null) {
				faitTrouve = f;
				bdf.ajouterFait(faitTrouve);
			}
			if (faitTrouve instanceof FaitsEntiers || faitTrouve instanceof FaitsEntiersNiveau0) {
				int faitTrouverValeur = (int)faitTrouve.getValeur();
				int fValeur = (int) f.getValeur();
				if (!(faitTrouverValeur >= fValeur)) {
					return -1;
					
				}else {
					niveauMax = Math.max(niveauMax, faitTrouve.getNiveau());
				}
			}else {
				if (!faitTrouve.getValeur().equals(f.getValeur())) {
					return -1;
					
				}else {
					niveauMax = Math.max(niveauMax, faitTrouve.getNiveau());
				}
			}
		}
		return niveauMax;
	}
	
	private Regles trouverRegle(BaseDeRegles bdrLocale) {
		for (Regles r : bdrLocale.getRegles()) {
			int niveau = this.estApplicable(r);
			if (niveau != -1) {
				niveauMaxRegle = niveau;
				return r;
			}
		}
		return null;
	}
	
	private void resoudre() {
		//On copie toutes les règles 
		BaseDeRegles bdrLocale = new BaseDeRegles();
		bdrLocale.setRegles(bdr.getRegles());
		
		//On vide la base de faits
		bdf.viderBaseDeFaits();
		
		//Tant qu'il existe des regles a appliquer
		Regles r = trouverRegle(bdrLocale);
		while (r!=null) {
			//Appliquer la regle
			InterfaceFaits nouveauFait = r.getConclusion();
			nouveauFait.setNiveau(niveauMaxRegle+1);
			bdf.ajouterFait(nouveauFait);
			bdrLocale.effacerRegle(r);
			r = trouverRegle(bdrLocale);
		}
		System.out.println(bdf.toString());
	}
}
