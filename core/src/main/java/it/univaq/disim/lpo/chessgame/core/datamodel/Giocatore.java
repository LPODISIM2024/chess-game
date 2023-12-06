package it.univaq.disim.lpo.chessgame.core.datamodel;

import java.io.Serializable;

public abstract class Giocatore implements Serializable{
    private String name;
    private ColoreEnumeration colore;
    
    public Giocatore(String name) {
    	this.name = name;
    }
    
    public ColoreEnumeration getColore() {
        return colore;
    }
    public void setColore(ColoreEnumeration colore) {
        this.colore = colore;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
}
