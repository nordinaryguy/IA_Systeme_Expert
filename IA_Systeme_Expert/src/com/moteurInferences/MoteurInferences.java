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
	protected InterfaceFaits but;

	private int niveauMaxRegle;

	public MoteurInferences(BaseDeFaits pbdf, BaseDeRegles pbdr, InterfaceFaits pbut) {
		String str = "++++++++++++++++++++++++++++++\r\n" + 
				"+ Moteur Inférences Ordre 0+ +\r\n" + 
				"++++++++++++++++++++++++++++++";
		System.out.println(str);
		this.bdf = pbdf;
		this.bdr = pbdr;
		this.but = pbut;
	}

	public void chainageAvant() {
		//this.resoudre();
		String str = "\n---------------------------\r\n" + 
				"#  Chaînage avant lancé   #\r\n" + 
				"---------------------------\n";
		System.out.println(str);
		
		boolean butAtteint = this.trouverBut();
		Regles r = this.trouverRegle();
		while (!butAtteint && r!=null) {
			System.out.println("On applique : [" + r.getNom()+"]");
			System.out.println("On désactive : [" + r.getNom()+"] de la base de règles");
			this.bdr.effacerRegle(r);
			System.out.println("On ajoute dans la base de faits : [" + r.getConclusion().getNom() + "]\n");
			this.bdf.ajouterFait(r.getConclusion());
			System.out.println("==============================================BASE DE FAITS===================================================");
			System.out.println("La base de faits a été mise à jour. Affichage de la nouvelle base de faits (Nombre de faits : "+this.bdf.getFaits().size()+") : ");
			System.out.println(this.bdf.toString());
			System.out.println("============================================================================================================\n");
			butAtteint = this.trouverBut();
			r = this.trouverRegle();
		}

		if (butAtteint) {
			System.out.println("Fin de l'algorithme du chaînage avant. Le but [" + this.but.getNom()+ "] a été atteint.");
			System.out.println("Candidature acceptée ! Bienvenue dans notre école :)");
		}else {
			System.out.println("Fin de l'algorithme du chaînage avant. Le but [" + this.but.getNom()+ "] n'a pas été atteint.");
			System.out.println("Désolé votre candidature n'a pas été retenue. ");
		}
		
		//boolean regleApplicable = this.estApplicable(r);
	}

	private Regles trouverRegle() {
		for (Regles r : this.bdr.getRegles()) {
			System.out.println("- "+r.toString());
			boolean rApplicable = this.estApplicable(r);
			if (rApplicable) {
				return r;
			}
		}
		return null;
	}

	private boolean trouverBut() {
		InterfaceFaits butBaseDeFait = this.bdf.chercher(this.but.getNom());
		if (butBaseDeFait!=null) {
			return true;
		}else {
			return false;
		}
	}


	private boolean estApplicable(Regles pr) {
		boolean res = false;
		int nbPremisses = pr.getPremisses().size();
		int nbFaitsCorrespondans = 0;
		for (InterfaceFaits f : pr.getPremisses()) {

			if (bdf.chercher(f.getNom())!=null) {
				if (f.getNom().equals((bdf.chercher(f.getNom())).getNom())) {
					InterfaceFaits faitPresentDansBdF = bdf.chercher(f.getNom());
					//====
					if (faitPresentDansBdF instanceof FaitsEntiers || faitPresentDansBdF instanceof FaitsEntiersNiveau0) {
						int faitPresentDansBdFValeur = (int)faitPresentDansBdF.getValeur();
						int fValeur = (int) f.getValeur();
						if (!(faitPresentDansBdFValeur >= fValeur)) {
							System.out.println("La règle ["+pr.getNom()+"] n'est pas applicable \n");
							return false;

						}else {
							System.out.println("Prémisse présente dans la base de faits : [" +f.getNom()+"]");
							nbFaitsCorrespondans++;
						}
					}else {
						if (!faitPresentDansBdF.getValeur().equals(f.getValeur())) {
							System.out.println("La règle ["+pr.getNom()+"] n'est pas applicable \n");
							return false;

						}else {
							System.out.println("Prémisse présente dans la base de faits : [" +f.getNom()+"]");
							nbFaitsCorrespondans++;
						}
					}
					//====
				}
			}else {
				System.out.println("La règle ["+pr.getNom()+"] n'est pas applicable \n");
				return false;
			}

		}
		if (nbPremisses==nbFaitsCorrespondans) {
			System.out.println("La règle ["+pr.getNom()+"] est applicable");
			res = true;
		}else {
			System.out.println("La règle n'est pas applicable.");
			res = false;
		}

		return res;
	}

}
