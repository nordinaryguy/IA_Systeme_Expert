package com.faits;

import java.util.ArrayList;

public class BaseDeFaits {

	protected ArrayList<InterfaceFaits> faits ;
	
	public BaseDeFaits() {
		this.faits = new ArrayList<InterfaceFaits>();
	}
	
	public ArrayList<InterfaceFaits> getFaits(){
		return this.faits;
	}
	
	public void viderBaseDeFaits() {
		this.faits.clear();
	}
	
	public void ajouterFait(InterfaceFaits pFait) {
		this.faits.add(pFait);
	}
	
	public InterfaceFaits chercher(String nom) {
		for(InterfaceFaits fait : this.faits) {
			if (fait.getNom().equals(nom)) {
				return fait;
			}
		}
		return null;
	}
	
	public Object recupererValeur(String nom) {
		for(InterfaceFaits fait : this.faits) {
			if (fait.getNom().equals(nom)) {
				return fait.getValeur();
			}
		}
		return null;
	}
	
	public String toString() {
		String str = "Base de faits : \n ";
		
		for (InterfaceFaits fait : this.faits) {
			str += "	-" + fait.toString() + "\n";
		}
		
		return str; 
	}
	
}
