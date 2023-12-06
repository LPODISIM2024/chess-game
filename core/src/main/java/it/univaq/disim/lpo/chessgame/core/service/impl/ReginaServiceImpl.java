
package it.univaq.disim.lpo.chessgame.core.service.impl;

import java.util.List;

import com.google.common.collect.Lists;
import com.google.common.collect.Table.Cell;

import it.univaq.disim.lpo.chessgame.core.datamodel.Giocatore;
import it.univaq.disim.lpo.chessgame.core.datamodel.Gioco;
import it.univaq.disim.lpo.chessgame.core.datamodel.Mossa;
import it.univaq.disim.lpo.chessgame.core.datamodel.Pezzo;
import it.univaq.disim.lpo.chessgame.core.datamodel.Regina;
import it.univaq.disim.lpo.chessgame.core.service.NoMosseDisponibiliException;
import it.univaq.disim.lpo.chessgame.core.service.PezzoNonTrovatoException;
import it.univaq.disim.lpo.chessgame.core.service.PezzoService;

public class ReginaServiceImpl extends PezzoServiceAbstractImpl<Regina> {

	@Override
	public List<Mossa> getMossePossibiliPezzo(Regina pezzo, Gioco gioco, Giocatore giocatore) throws NoMosseDisponibiliException, PezzoNonTrovatoException {
	List<Mossa> mosse = Lists.newArrayList();
		
		Cell<Integer, Integer, Pezzo> posizione = getPosizionePezzo(gioco, pezzo);
		Integer riga = posizione.getRowKey();
		Integer colonna = posizione.getColumnKey();
		for (int i = riga + 1; i <= gioco.getScacchiera().rowKeySet().size(); i++) {
			if (gioco.getScacchiera().get(i, colonna) == null) {
				Mossa m = new Mossa(riga, colonna, i, colonna, pezzo, null, giocatore);
				mosse.add(m);
			} else {
				if (gioco.getScacchiera().get(i, colonna).getGiocatore() == giocatore)
					break;
				else {
					Mossa m = new Mossa(riga, colonna, i, colonna, pezzo, gioco.getScacchiera().get(i, colonna),
							giocatore);
					mosse.add(m);
					break;
				}
			}
		}
		for (int i = riga - 1; i >= 1; i--) {
			if (gioco.getScacchiera().get(i, colonna) == null) {
				Mossa m = new Mossa(riga, colonna, i, colonna, pezzo, null,giocatore);
				mosse.add(m);
			} else {
				if (gioco.getScacchiera().get(i, colonna).getGiocatore() == giocatore)
					break;
				else {
					Mossa m = new Mossa(riga, colonna, i, colonna, pezzo, gioco.getScacchiera().get(i, colonna),
							giocatore);
					mosse.add(m);
					break;
				}
			}
		}
		for (int i = colonna + 1; i <= gioco.getScacchiera().rowKeySet().size(); i++) {
			if (gioco.getScacchiera().get(riga, i) == null) {
				Mossa m = new Mossa(riga, colonna, riga, i, pezzo, null, giocatore);
				mosse.add(m);
			} else {
				if (gioco.getScacchiera().get(riga, i).getGiocatore() == giocatore)
					break;
				else {
					Mossa m = new Mossa(riga, colonna, riga, i, pezzo, gioco.getScacchiera().get(riga, i),
							giocatore);
					mosse.add(m);
					break;
				}
			}
		}
		for (int i = colonna - 1; i >= 1; i--) {
			if (gioco.getScacchiera().get(riga, i) == null) {
				Mossa m = new Mossa(riga, colonna, riga, i, pezzo, null, giocatore);
				mosse.add(m);
			} else {
				if (gioco.getScacchiera().get(riga, i).getGiocatore() == giocatore)
					break;
				else {
					Mossa m = new Mossa(riga, colonna, riga, i, pezzo, gioco.getScacchiera().get(riga, i),
							giocatore);
					mosse.add(m);
					break;
				}
			}
		}
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
