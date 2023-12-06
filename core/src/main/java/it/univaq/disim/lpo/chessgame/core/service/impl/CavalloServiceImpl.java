package it.univaq.disim.lpo.chessgame.core.service.impl;

import java.util.List;

import com.google.common.collect.Lists;
import com.google.common.collect.Table.Cell;

import it.univaq.disim.lpo.chessgame.core.datamodel.Cavallo;
import it.univaq.disim.lpo.chessgame.core.datamodel.Giocatore;
import it.univaq.disim.lpo.chessgame.core.datamodel.Gioco;
import it.univaq.disim.lpo.chessgame.core.datamodel.Mossa;
import it.univaq.disim.lpo.chessgame.core.datamodel.Pezzo;
import it.univaq.disim.lpo.chessgame.core.service.PezzoNonTrovatoException;

public class CavalloServiceImpl extends PezzoServiceAbstractImpl<Cavallo> {

	@Override
	public List<Mossa> getMossePossibiliPezzo(Cavallo pezzo, Gioco gioco, Giocatore giocatore) throws PezzoNonTrovatoException {
		List<Mossa> mosse = Lists.newArrayList();
		Cell<Integer, Integer, Pezzo> posizione = getPosizionePezzo(gioco, pezzo);
		Integer riga = posizione.getRowKey();
		Integer colonna = posizione.getColumnKey();
		if (riga - 1 >= 1) {
			if (colonna - 2 >= 1)
				mosse.add(new Mossa(riga, colonna, riga - 1, colonna - 2, pezzo,
						gioco.getScacchiera().get(riga - 1, colonna - 2), giocatore));

			if (colonna + 2 <= gioco.getScacchiera().columnKeySet().size())
				mosse.add(new Mossa(riga, colonna, riga - 1, colonna + 2, pezzo,
						gioco.getScacchiera().get(riga - 1, colonna + 2), giocatore));

		}
		if (riga + 1 <= gioco.getScacchiera().rowKeySet().size()) {
			if (colonna - 2 >= 1) {
				mosse.add(new Mossa(riga, colonna, riga + 1, colonna - 2, pezzo,
						gioco.getScacchiera().get(riga + 1, colonna - 2), giocatore));
			}
			if (colonna + 2 <= gioco.getScacchiera().columnKeySet().size()) {
				mosse.add(new Mossa(riga, colonna, riga + 1, colonna + 2, pezzo,
						gioco.getScacchiera().get(riga + 1, colonna + 2), giocatore));
			}
		}

		if (riga - 2 >= 1) {
			if (colonna - 1 >= 1)
				mosse.add(new Mossa(riga, colonna, riga - 2, colonna - 1, pezzo,
						gioco.getScacchiera().get(riga - 2, colonna - 1), giocatore));

			if (colonna + 1 <= gioco.getScacchiera().columnKeySet().size())
				mosse.add(new Mossa(riga, colonna, riga - 2, colonna + 1, pezzo,
						gioco.getScacchiera().get(riga - 2, colonna + 1), giocatore));

		}
		if (riga + 2 <= gioco.getScacchiera().rowKeySet().size()) {
			if (colonna - 1 >= 1) {
				mosse.add(new Mossa(riga, colonna, riga + 2, colonna - 1, pezzo,
						gioco.getScacchiera().get(riga + 2, colonna - 1), giocatore));
			}
			if (colonna + 1 <= gioco.getScacchiera().columnKeySet().size()) {
				mosse.add(new Mossa(riga, colonna, riga + 2, colonna + 1, pezzo,
						gioco.getScacchiera().get(riga + 2, colonna + 1), giocatore));
			}
		}
		return mosse;
	}
}
