package com.regles;

import java.util.ArrayList;

public class BaseDeRegles {

	protected ArrayList<Regles> regles;
	
	public BaseDeRegles() {
		this.regles = new ArrayList<Regles>();
	}
	
	public ArrayList<Regles> getRegles(){
		return this.regles;
	}
	
	public void setRegles(ArrayList<Regles> pRegles) {
		for (Regles regle : pRegles) {
			Regles copie = new Regles(regle.nom, regle.premisses, regle.conclusion);
			this.regles.add(copie);
		}
	}
	
	public void viderBaseDeRegles() {
		this.regles.clear();
	}
	
	public void ajouterRegle(Regles pR) {
		this.regles.add(pR);
	}
	
	public void effacerRegle(Regles pR) {
		this.regles.remove(pR);
	}
	
	public String toString() {
		String str = "Base de règles : \n";
		for (Regles regle : regles) {
			str += "	-" + regle.toString()+"\n";
		}
		
		return str;
	}
}
