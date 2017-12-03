package com.main;

import java.util.ArrayList;

import com.faits.BaseDeFaits;
import com.faits.FaitsBooleens;
import com.faits.FaitsEntiers;
import com.faits.FaitsEntiersNiveau0;
import com.faits.InterfaceFaits;
import com.gui.SEGUI;
import com.moteurInferences.MoteurInferences;
import com.regles.BaseDeRegles;
import com.regles.Regles;

public class MainProgram {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//SEGUI segui = new SEGUI();
		String initDatas = "++++++++++++++++++++++++++++++\r\n" + 
				"+     Données initiales      +\r\n" + 
				"++++++++++++++++++++++++++++++";
		System.out.println(initDatas);
		
		//Faits de niveau 0 entres en dur : 
		InterfaceFaits fait1 = new FaitsEntiersNiveau0("lv1etLv2", 17, 0);
		InterfaceFaits fait2 = new FaitsEntiersNiveau0("moyPC", 18, 0);
		InterfaceFaits fait3 = new FaitsEntiersNiveau0("moyMath", 18, 0);
		InterfaceFaits fait4 = new FaitsEntiersNiveau0("moySI", 16, 0);
		InterfaceFaits fait5 = new FaitsBooleens("eleveInteresse", true, 0);
		
		//Ajout des faits initiaux dans la base de faits en dur :
		BaseDeFaits bdf = new BaseDeFaits();
		bdf.ajouterFait(fait1);
		bdf.ajouterFait(fait2);
		bdf.ajouterFait(fait3);
		bdf.ajouterFait(fait4);
		bdf.ajouterFait(fait5);
		
		
		//Regles :
		//premisses pour chaque regle :
		InterfaceFaits premisselv1lv2 = new FaitsEntiers("lv1etLv2", 15, 0);
		InterfaceFaits premisseBacSOk = new FaitsBooleens("bacSOk", true, 2);
		InterfaceFaits premissemoyBacS = new FaitsEntiers("moyBacS", 10, 1);
		InterfaceFaits premissemoyPC = new FaitsEntiers("moyPC", 15, 0);
		InterfaceFaits premissemoyMath = new FaitsEntiers("moyMath", 15, 0);
		InterfaceFaits premissemoySI = new FaitsEntiers("moySI", 15, 0);
		InterfaceFaits premisseQuestions = new FaitsEntiers("questionsPoseesEntretien", 4, 1);
		InterfaceFaits premisseEleveInteresse = new FaitsBooleens("eleveInteresse", true, 0);
		InterfaceFaits premisseMotive = new FaitsBooleens("motive", true, 2);
		InterfaceFaits premisseLanguesOk = new FaitsBooleens("niveauLanguesOk", true, 3);
		InterfaceFaits premisseSciencesOk = new FaitsBooleens("niveauSciencesOk", true, 3);
		InterfaceFaits premisseEntretienOk = new FaitsBooleens("entretienOk", true, 3);
		

		ArrayList<InterfaceFaits> premissesR1 = new ArrayList<InterfaceFaits>();
		ArrayList<InterfaceFaits> premissesR2 = new ArrayList<InterfaceFaits>();
		ArrayList<InterfaceFaits> premissesR3 = new ArrayList<InterfaceFaits>();
		ArrayList<InterfaceFaits> premissesR4 = new ArrayList<InterfaceFaits>();
		ArrayList<InterfaceFaits> premissesR5 = new ArrayList<InterfaceFaits>();
		ArrayList<InterfaceFaits> premissesR6 = new ArrayList<InterfaceFaits>();
		ArrayList<InterfaceFaits> premissesR7 = new ArrayList<InterfaceFaits>();
		ArrayList<InterfaceFaits> premissesR8 = new ArrayList<InterfaceFaits>();
		
		premissesR1.add(premisselv1lv2);
		premissesR1.add(premisseBacSOk);
		
		premissesR2.add(premissemoyBacS);
		
		premissesR3.add(premisselv1lv2);
		premissesR3.add(premissemoyPC);
		premissesR3.add(premissemoyMath);
		premissesR3.add(premissemoySI);
		
		premissesR4.add(premissemoySI);
		premissesR4.add(premissemoyPC);
		premissesR4.add(premissemoyMath);
		
		premissesR5.add(premisseEleveInteresse);
		
		premissesR6.add(premisseQuestions);
		
		premissesR7.add(premisseMotive);
		
		premissesR8.add(premisseLanguesOk);
		premissesR8.add(premisseSciencesOk);
		premissesR8.add(premisseEntretienOk);
		
		//conclusion pour chaque regle : 
		InterfaceFaits conclusionR1 = premisseLanguesOk;
		InterfaceFaits conclusionR2 = premisseBacSOk;
		InterfaceFaits conclusionR3 = premissemoyBacS;
		InterfaceFaits conclusionR4 = premisseSciencesOk;
		InterfaceFaits conclusionR5 = premisseQuestions;
		InterfaceFaits conclusionR6 = premisseMotive;
		InterfaceFaits conclusionR7 = premisseEntretienOk;
		InterfaceFaits conclusionR8 = new FaitsBooleens("accepte", true, 4);
		
		//regles :
		Regles r1 = new Regles("R1", premissesR1, conclusionR1);
		Regles r2 = new Regles("R2", premissesR2, conclusionR2);
		Regles r3 = new Regles("R3", premissesR3, conclusionR3);
		Regles r4 = new Regles("R4", premissesR4, conclusionR4);
		Regles r5 = new Regles("R5", premissesR5, conclusionR5);
		Regles r6 = new Regles("R6", premissesR6, conclusionR6);
		Regles r7 = new Regles("R7", premissesR7, conclusionR7);
		Regles r8 = new Regles("R8", premissesR8, conclusionR8);
		
		//Ajout des regles dans la base de regles 
		BaseDeRegles bdr = new BaseDeRegles();
		bdr.ajouterRegle(r1);
		bdr.ajouterRegle(r2);
		bdr.ajouterRegle(r3);
		bdr.ajouterRegle(r4);
		bdr.ajouterRegle(r5);
		bdr.ajouterRegle(r6);
		bdr.ajouterRegle(r7);
		bdr.ajouterRegle(r8);
		
		//Affichage des bases : 
		System.out.println(bdf.toString());
		System.out.println(bdr.toString());
		
		//Création du moteurs
		MoteurInferences moteur = new MoteurInferences(bdf, bdr, conclusionR8);
		
		//Lancement du chainage avant 
		//moteur.chainageAvant();
		
		//Lancement chainage arrière 
		String str = "\n---------------------------\r\n" + 
				"#  Chaînage arrière lancé   #\r\n" + 
				"---------------------------\n";
		System.out.println(str);
		if (moteur.chainagearriere(conclusionR8)) {
			System.out.println("SUCCES!");
		}else {
			System.out.println("ECHEC");
		}
		
	}
}
