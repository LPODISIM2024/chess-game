package it.univaq.disim.lpo.chessgame.core.service;

import java.util.List;

import it.univaq.disim.lpo.chessgame.core.datamodel.Giocatore;
import it.univaq.disim.lpo.chessgame.core.datamodel.Gioco;
import it.univaq.disim.lpo.chessgame.core.datamodel.Mossa;
import it.univaq.disim.lpo.chessgame.core.datamodel.Pezzo;

public interface PezzoService <T extends Pezzo>{
	List<Mossa> getMossePossibiliPezzo(T p, Gioco g, Giocatore giocatore) throws NoMosseDisponibiliException, PezzoNonTrovatoException;

	List<Mossa> getMosseDisponibiliEssenziali(T pezzo, Gioco gioco)
			throws NoMosseDisponibiliException, PezzoNonTrovatoException;
	
	List<Mossa> getMosseDisponibiliEssenziali(T pezzo, Gioco gioco, Giocatore giocatore)
			throws NoMosseDisponibiliException, PezzoNonTrovatoException;
	
}
