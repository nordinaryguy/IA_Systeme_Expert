package com.moteurInferences;

import java.util.ArrayList;

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
				"+ Moteur Inf�rences Ordre 0+ +\r\n" + 
				"++++++++++++++++++++++++++++++";
		System.out.println(str);
		this.bdf = pbdf;
		this.bdr = pbdr;
		this.but = pbut;
	}

	public void chainageAvant() {
		String str = "\n---------------------------\r\n" + 
				"#  Cha�nage avant lanc�   #\r\n" + 
				"---------------------------\n";
		System.out.println(str);

		boolean butAtteint = this.trouverBut();
		Regles r = this.trouverRegle();
		while (!butAtteint && r!=null) {
			System.out.println("On applique : [" + r.getNom()+"]");
			System.out.println("On d�sactive : [" + r.getNom()+"] de la base de r�gles");
			this.bdr.effacerRegle(r);
			System.out.println("On ajoute dans la base de faits : [" + r.getConclusion().getNom() + "]\n");
			this.bdf.ajouterFait(r.getConclusion());
			System.out.println("==============================================BASE DE FAITS===================================================");
			System.out.println("La base de faits a �t� mise � jour. Affichage de la nouvelle base de faits (Nombre de faits : "+this.bdf.getFaits().size()+") : ");
			System.out.println(this.bdf.toString());
			System.out.println("============================================================================================================\n");
			butAtteint = this.trouverBut();
			r = this.trouverRegle();
		}

		if (butAtteint) {
			System.out.println("Fin de l'algorithme du cha�nage avant. Le but [" + this.but.getNom()+ "] a �t� atteint.");
			System.out.println("Candidature accept�e ! Bienvenue dans notre �cole :)");
		}else {
			System.out.println("Fin de l'algorithme du cha�nage avant. Le but [" + this.but.getNom()+ "] n'a pas �t� atteint.");
			System.out.println("D�sol� votre candidature n'a pas �t� retenue. ");
		}
	}


	public boolean chainagearriere(InterfaceFaits F) {

		// Cas o� le but est deja dans la base de faits initialement.
		if (this.bdf.chercher(F.getNom())!=null) {
			return true;
		}else {
			ArrayList<Regles> ER = new ArrayList<Regles>();
			for (Regles regle : this.bdr.getRegles()) {
				if (regle.getConclusion().equals(F)) {
					ER.add(regle);
					System.out.println(regle.toString());
				}
			}
			boolean valide;
			do {
				valide = true;
				if (ER.size()==0) {
					break;
				}
				Regles R = ER.get(0);
				ER.remove(R);
				for (InterfaceFaits Fr : R.getPremisses()) {
					valide = valide && this.chainagearriere(Fr);
				}
			} while (valide || ER!=null);
			if (valide && ER.size()==0) {
				System.out.println("Ajout dans la base de faits de la conclusion : ["+F.getNom()+"]");
				this.bdf.ajouterFait(F);
				System.out.println("==============================================BASE DE FAITS===================================================");
				System.out.println("La base de faits a �t� mise � jour. Affichage de la nouvelle base de faits (Nombre de faits : "+this.bdf.getFaits().size()+") : ");
				System.out.println(this.bdf.toString());
				System.out.println("============================================================================================================\n");
				if (this.but.equals(F)) {
					System.out.println("Fin de l'algorithme du cha�nage arri�re. Le but [" + this.but.getNom()+ "] a  �t� atteint.");
					System.out.println("Candidature accept�e ! Bienvenue dans notre �cole :)");
				}
			}else {
				System.out.println("Fin de l'algorithme du cha�nage arri�re. Le but [" + F.getNom()+ "] n'a pas �t� atteint.");
				System.out.println("D�sol� votre candidature n'a pas �t� retenue. ");
				return false;
			}
			return valide;
		}
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
							System.out.println("La r�gle ["+pr.getNom()+"] n'est pas applicable \n");
							return false;

						}else {
							System.out.println("Pr�misse pr�sente dans la base de faits : [" +f.getNom()+"]");
							nbFaitsCorrespondans++;
						}
					}else {
						if (!faitPresentDansBdF.getValeur().equals(f.getValeur())) {
							System.out.println("La r�gle ["+pr.getNom()+"] n'est pas applicable \n");
							return false;

						}else {
							System.out.println("Pr�misse pr�sente dans la base de faits : [" +f.getNom()+"]");
							nbFaitsCorrespondans++;
						}
					}
					//====
				}
			}else {
				System.out.println("La r�gle ["+pr.getNom()+"] n'est pas applicable \n");
				return false;
			}

		}
		if (nbPremisses==nbFaitsCorrespondans) {
			System.out.println("La r�gle ["+pr.getNom()+"] est applicable");
			res = true;
		}else {
			System.out.println("La r�gle n'est pas applicable.");
			res = false;
		}

		return res;
	}

}
