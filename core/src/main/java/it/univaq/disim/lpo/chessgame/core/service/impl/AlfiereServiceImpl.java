package it.univaq.disim.lpo.chessgame.core.service.impl;

import java.util.List;

import com.google.common.collect.Lists;
import com.google.common.collect.Table.Cell;

import it.univaq.disim.lpo.chessgame.core.datamodel.Alfiere;
import it.univaq.disim.lpo.chessgame.core.datamodel.Giocatore;
import it.univaq.disim.lpo.chessgame.core.datamodel.Gioco;
import it.univaq.disim.lpo.chessgame.core.datamodel.Mossa;
import it.univaq.disim.lpo.chessgame.core.datamodel.Pezzo;
import it.univaq.disim.lpo.chessgame.core.service.NoMosseDisponibiliException;
import it.univaq.disim.lpo.chessgame.core.service.PezzoNonTrovatoException;

public class AlfiereServiceImpl extends PezzoServiceAbstractImpl<Alfiere> {

	@Override
	public List<Mossa> getMossePossibiliPezzo(Alfiere pezzo, Gioco gioco, Giocatore giocatore)
			throws NoMosseDisponibiliException, PezzoNonTrovatoException {
		List<Mossa> mosse = Lists.newArrayList();

		// LISTA MOSSE QUI
		Cell<Integer, Integer, Pezzo> posizionePezzo = getPosizionePezzo(gioco, pezzo);
		Integer colonna = posizionePezzo.getColumnKey();
		Integer riga = posizionePezzo.getRowKey();
		for (int i = 1; i <= gioco.getScacchiera().rowKeySet().size(); i++) {
			if (colonna + i > gioco.getScacchiera().rowKeySet().size() ||
					riga + i > gioco.getScacchiera().rowKeySet().size())
				break;
			else {
				if (gioco.getScacchiera().get(riga + i, colonna + i) == null) {
					Mossa m = new Mossa(riga, colonna, riga + i, colonna + i, pezzo,
							gioco.getScacchiera().get(riga + i, colonna + i), giocatore);
					mosse.add(m);
				}

				else {
					if (gioco.getScacchiera().get(riga + i, colonna + i).getGiocatore() == giocatore)
						break;
					else {
						Mossa m = new Mossa(riga, colonna, riga + i, colonna + i, pezzo,
								gioco.getScacchiera().get(riga + i, colonna + i), giocatore);
						mosse.add(m);
						break;
					}
				}

			}

		}

		for (int i = 1; i <= gioco.getScacchiera().rowKeySet().size(); i++) {
			if (colonna - i < 1 || riga - i < 1)
				break;
			else {
				if (gioco.getScacchiera().get(riga - i, colonna - i) == null) {
					Mossa m = new Mossa(riga, colonna, riga - i, colonna - i, pezzo,
							gioco.getScacchiera().get(riga - i, colonna - i), giocatore);
					mosse.add(m);
				}

				else {
					if (gioco.getScacchiera().get(riga - i, colonna - i).getGiocatore() == giocatore)
						break;
					else {
						Mossa m = new Mossa(riga, colonna, riga - i, colonna - i, pezzo,
								gioco.getScacchiera().get(riga - i, colonna - i), giocatore);
						mosse.add(m);
						break;
					}
				}

			}

		}

		for (int i = 1; i <= gioco.getScacchiera().rowKeySet().size(); i++) {
			if (colonna - i < 1 || riga + i > gioco.getScacchiera().rowKeySet().size())
				break;
			else {
				if (gioco.getScacchiera().get(riga + i, colonna - i) == null) {
					Mossa m = new Mossa(riga, colonna, riga + i, colonna - i, pezzo,
							gioco.getScacchiera().get(riga + i, colonna - i), giocatore);
					mosse.add(m);
				}

				else {
					if (gioco.getScacchiera().get(riga + i, colonna - i).getGiocatore() == giocatore)
						break;
					else {
						Mossa m = new Mossa(riga, colonna, riga + i, colonna - i, pezzo,
								gioco.getScacchiera().get(riga + i, colonna - i), giocatore);
						mosse.add(m);
						break;
					}
				}

			}

		}
		
		for (int i = 1; i <= gioco.getScacchiera().rowKeySet().size(); i++) {
			if (colonna + i > gioco.getScacchiera().columnKeySet().size() || riga - i < 1)
				break;
			else {
				if (gioco.getScacchiera().get(riga - i, colonna + i) == null) {
					Mossa m = new Mossa(riga, colonna, riga - i, colonna + i, pezzo,
							gioco.getScacchiera().get(riga - i, colonna + i), giocatore);
					mosse.add(m);
				}

				else {
					if (gioco.getScacchiera().get(riga - i, colonna + i).getGiocatore() == giocatore)
						break;
					else {
						Mossa m = new Mossa(riga, colonna, riga - i, colonna + i, pezzo,
								gioco.getScacchiera().get(riga - i, colonna + i), giocatore);
						mosse.add(m);
						break;
					}
				}

			}

		}

		if (mosse.isEmpty())
			throw new NoMosseDisponibiliException();
		return mosse;
	}

}
