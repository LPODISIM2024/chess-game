package it.univaq.disim.lpo.chessgame.core.service.impl;

import java.util.List;

import com.google.common.collect.Lists;
import com.google.common.collect.Table.Cell;

import it.univaq.disim.lpo.chessgame.core.datamodel.Giocatore;
import it.univaq.disim.lpo.chessgame.core.datamodel.Gioco;
import it.univaq.disim.lpo.chessgame.core.datamodel.Mossa;
import it.univaq.disim.lpo.chessgame.core.datamodel.Pezzo;
import it.univaq.disim.lpo.chessgame.core.datamodel.Re;
import it.univaq.disim.lpo.chessgame.core.service.NoMosseDisponibiliException;
import it.univaq.disim.lpo.chessgame.core.service.PezzoNonTrovatoException;

public class ReServiceImpl extends PezzoServiceAbstractImpl<Re> {

	@Override
	public List<Mossa> getMossePossibiliPezzo(Re pezzo, Gioco gioco, Giocatore giocatore)
			throws NoMosseDisponibiliException, PezzoNonTrovatoException {
		List<Mossa> mosse = Lists.newArrayList();
		Cell<Integer, Integer, Pezzo> posizione = getPosizionePezzo(gioco, pezzo);
		Integer colonna = posizione.getColumnKey();
		Integer riga = posizione.getRowKey();
		if (colonna - 1 >= 1 && riga - 1 >= 1) {
			mosse.add(new Mossa(riga, colonna, riga - 1, colonna - 1, pezzo,
					gioco.getScacchiera().get(riga - 1, colonna - 1), giocatore));
		}
		if (colonna + 1 <= gioco.getScacchiera().columnKeySet().size() && riga + 1 <= gioco.getScacchiera().columnKeySet().size()) {
			mosse.add(new Mossa(riga, colonna, riga + 1, colonna + 1, pezzo,
					gioco.getScacchiera().get(riga + 1, colonna + 1), giocatore));
		}
		if (colonna - 1 >= 1) {
			mosse.add(new Mossa(riga, colonna, riga, colonna - 1, pezzo,
					gioco.getScacchiera().get(riga , colonna - 1), giocatore));
		}
		if (riga - 1 >= 1) {
			mosse.add(new Mossa(riga, colonna, riga - 1, colonna, pezzo,
					gioco.getScacchiera().get(riga - 1, colonna), giocatore));
		}
		if (colonna + 1 <= gioco.getScacchiera().columnKeySet().size()) {
			mosse.add(new Mossa(riga, colonna, riga, colonna + 1, pezzo,
					gioco.getScacchiera().get(riga, colonna + 1), giocatore));
		}
		if (riga + 1 <= gioco.getScacchiera().columnKeySet().size()) {
			mosse.add(new Mossa(riga, colonna, riga + 1, colonna, pezzo,
					gioco.getScacchiera().get(riga + 1, colonna), giocatore));
		}

		if (colonna + 1 <= gioco.getScacchiera().columnKeySet().size() && riga - 1 >= 1) {
			mosse.add(new Mossa(riga, colonna, riga - 1, colonna + 1, pezzo,
					gioco.getScacchiera().get(riga - 1, colonna + 1), giocatore));
		}
		
		if (colonna - 1 >= 1 && riga + 1 <= gioco.getScacchiera().columnKeySet().size()) {
			mosse.add(new Mossa(riga, colonna, riga + 1, colonna -1 , pezzo,
					gioco.getScacchiera().get(riga + 1, colonna - 1), giocatore));
		}
		
		if (mosse.isEmpty())
			throw new NoMosseDisponibiliException();
		return mosse;
	}

}
