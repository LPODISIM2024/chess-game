package it.univaq.disim.lpo.chessgame.core;

import it.univaq.disim.lpo.chessgame.core.datamodel.Gioco;

import it.univaq.disim.lpo.chessgame.core.service.GiocoService;
import it.univaq.disim.lpo.chessgame.core.service.InizializzaPartitaException;

import it.univaq.disim.lpo.chessgame.core.service.impl.GiocoServiceImpl;


public class Runner {
    public static void main(String[] args) {
        GiocoService gs = new GiocoServiceImpl();
        try {
            Gioco g = gs.inizializzaPartita();
            gs.gioca(g);
            
        } catch (InizializzaPartitaException e) {
            System.out.println("Va bene non vuoi giocare");
        }
    }
}
