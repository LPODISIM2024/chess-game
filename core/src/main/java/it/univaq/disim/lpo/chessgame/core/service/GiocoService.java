package it.univaq.disim.lpo.chessgame.core.service;

import java.io.IOException;
import java.util.List;

import com.google.common.collect.Table.Cell;

import it.univaq.disim.lpo.chessgame.core.datamodel.Giocatore;
import it.univaq.disim.lpo.chessgame.core.datamodel.Gioco;
import it.univaq.disim.lpo.chessgame.core.datamodel.Mossa;
import it.univaq.disim.lpo.chessgame.core.datamodel.Pezzo;
/**
 * Questa classe definisce il gioco
 */
public interface GiocoService{
	
	public void gioca(Gioco gioco);
	/*
	 * Questo metodo inizializza la partita
	 * 
	 */
	public Gioco inizializzaPartita() throws InizializzaPartitaException;
	
	/*
	 * Questo metodo controlla lo scacco
	 * @param gioco il gioco in esecuzione.
	 * @param diTurno il giocatore di turno
	 */
	public boolean controllaGiocatoreTurnoSottoScacco (Gioco gioco, Giocatore diTurno) throws NoMosseDisponibiliException, PezzoNonTrovatoException;
	public boolean controlloScaccoMatto(Gioco gioco, Giocatore diTurno);
	public Gioco eseguiMossa(Gioco gioco, Mossa mossa) throws MossaNonLegittimaException;
	public void salvaPartita(Gioco g, String filename) throws CloneNotSupportedException, IOException, ClassNotFoundException;
	public Gioco caricaGioco(String filename) throws CloneNotSupportedException, IOException, ClassNotFoundException;
	public Cell<Integer, Integer, Pezzo> getPosizioniPezzo(Gioco gioco, Pezzo pezzo) throws PezzoNonTrovatoException;
	public Cell<Integer, Integer, Pezzo> getPosizioneReGiocatoreDiTurno(Gioco gioco) throws PezzoNonTrovatoException;
	public List<Cell<Integer, Integer, Pezzo>> getPosizioniPezziGiocatoreTurno(Gioco gioco);
	public void undo(Gioco gioco, Integer numeroMosse) throws MossaNonLegittimaException;
	public void undoLast(Gioco gioco) throws MossaNonLegittimaException;
	List<Cell<Integer, Integer, Pezzo>> getPosizioniPezzi(Gioco gioco, Giocatore giocatore);

}
