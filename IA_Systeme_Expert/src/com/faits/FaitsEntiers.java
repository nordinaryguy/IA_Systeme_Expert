package com.faits;

public class FaitsEntiers implements InterfaceFaits {

	protected String nom;
	protected int valeur, niveau;
	
	public FaitsEntiers(String pnom, int pvaleur, int pniveau) {
		// TODO Auto-generated constructor stub
		this.nom = pnom;
		this.valeur = pvaleur;
		this.niveau = pniveau;
	}
	
	@Override
	public String getNom() {
		// TODO Auto-generated method stub
		return this.nom;
	}

	@Override
	public Object getValeur() {
		// TODO Auto-generated method stub
		return this.valeur;
	}

	@Override
	public int getNiveau() {
		// TODO Auto-generated method stub
		return this.niveau;
	}

	@Override
	public void setNiveau(int n) {
		// TODO Auto-generated method stub
		this.niveau = n;
	}
	
	public String toString() {
		String str = this.getNom() + " >= " + getValeur() + " (" + getNiveau() + ")";
		return str;
	}

}
