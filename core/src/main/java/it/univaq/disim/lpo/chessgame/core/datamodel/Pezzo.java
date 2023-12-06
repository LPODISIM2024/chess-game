package it.univaq.disim.lpo.chessgame.core.datamodel;

import java.io.Serializable;

public class Pezzo implements Serializable {
	private Giocatore giocatore;
	private String name;
	private boolean promosso;
	
	public Pezzo(Giocatore giocatore, String name) {
		this.giocatore = giocatore;
		this.name = name;
	}

	public Giocatore getGiocatore() {
		return giocatore;
	}

	public void setGiocatore(Giocatore giocatore) {
		this.giocatore = giocatore;
	}

	public ColoreEnumeration getColore() {
		return giocatore.getColore();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return String.format("%s_%s", name, giocatore.getColore() == ColoreEnumeration.BIANCO ? "B" : "N");
	}

	public boolean isPromosso() {
		return promosso;
	}

	public void setPromosso(boolean promosso) {
		this.promosso = promosso;
	}
}
