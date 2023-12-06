package it.univaq.disim.lpo.chessgame.core.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;
import com.google.common.collect.Table.Cell;

import it.univaq.disim.lpo.chessgame.core.datamodel.Giocatore;
import it.univaq.disim.lpo.chessgame.core.datamodel.Gioco;
import it.univaq.disim.lpo.chessgame.core.datamodel.Mossa;
import it.univaq.disim.lpo.chessgame.core.datamodel.Pedone;
import it.univaq.disim.lpo.chessgame.core.datamodel.Pezzo;
import it.univaq.disim.lpo.chessgame.core.service.NoMosseDisponibiliException;
import it.univaq.disim.lpo.chessgame.core.service.PezzoNonTrovatoException;

public class PedoneServiceImpl extends PezzoServiceAbstractImpl<Pedone> {

	@Override
	public List<Mossa> getMossePossibiliPezzo(Pedone pezzo, Gioco gioco, Giocatore giocatore)
			throws NoMosseDisponibiliException, PezzoNonTrovatoException {
		List<Mossa> mosse = Lists.newArrayList();
		Cell<Integer, Integer, Pezzo> indice = getPosizionePezzo(gioco, pezzo);
		Integer riga = indice.getRowKey();
		Integer colonna = indice.getColumnKey();
		if (pezzo.getGiocatore() == gioco.getG1()) {
			if (riga + 1 > 8)
				return mosse;
			Pezzo nuovaPosizione = gioco.getScacchiera().get(riga + 1, colonna);
			if (nuovaPosizione == null || nuovaPosizione.getGiocatore() != pezzo.getGiocatore())
				// rigaPartenza, colonnaPartenza, rigaArrivo, colonnaArrivo, pezzo,
				// pezzoDestinazine, Giocatore giocatore
				mosse.add(new Mossa(riga, colonna, riga + 1, colonna, pezzo,
						gioco.getScacchiera().get(riga + 1, colonna), giocatore));

		}
		if (pezzo.getGiocatore() == gioco.getG2()) {
			if (riga - 1 < 1)
				return mosse;
			Pezzo nuovaPosizione = gioco.getScacchiera().get(riga + -1, colonna);
			if (nuovaPosizione == null || nuovaPosizione.getGiocatore() != pezzo.getGiocatore()) {
				mosse.add(new Mossa(riga, colonna, riga - 1, colonna, pezzo,
						gioco.getScacchiera().get(riga + 1, colonna), giocatore));
			}
		}
		if (mosse.isEmpty())
			throw new NoMosseDisponibiliException();
		return mosse;
	}
}
