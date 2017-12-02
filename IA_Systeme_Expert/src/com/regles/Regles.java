package com.regles;

import java.util.ArrayList;
import java.util.StringJoiner;

import com.faits.InterfaceFaits;

public class Regles {

	protected String nom;
	protected ArrayList<InterfaceFaits> premisses;
	protected InterfaceFaits conclusion;
	
	public Regles(String pnom, ArrayList<InterfaceFaits> pPremisses, InterfaceFaits pconclusion) {
		this.nom = pnom;
		this.premisses = pPremisses;
		this.conclusion = pconclusion;
	}
	
	public ArrayList<InterfaceFaits> getPremisses(){
		return this.premisses;
	}
	
	public void setPremisses(ArrayList<InterfaceFaits> ppremisses) {
		this.premisses = ppremisses;
	}
	
	public InterfaceFaits getConclusion() {
		return this.conclusion;
	}
	
	public void setConclusiosn(InterfaceFaits pconclusion) {
		this.conclusion = pconclusion;
	}
	
	public String getNom() {
		return this.nom;
	}
	
	public void setNom(String pnom) {
		this.nom = pnom;
	}
	
	public String toString(){
		String str = this.nom + " : SI (" ;
		StringJoiner sj = new StringJoiner(" ET ");
		for (InterfaceFaits fait : premisses) {
			sj.add(fait.toString());
		}
		
		str += sj.toString() + ") ALORS " + this.conclusion.toString();
		
		return str;
	}
}
