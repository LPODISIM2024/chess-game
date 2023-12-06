package it.univaq.disim.lpo.chessgame.core.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;
import com.google.common.collect.Table.Cell;

import it.univaq.disim.lpo.chessgame.core.datamodel.Giocatore;
import it.univaq.disim.lpo.chessgame.core.datamodel.Gioco;
import it.univaq.disim.lpo.chessgame.core.datamodel.Mossa;
import it.univaq.disim.lpo.chessgame.core.datamodel.Pezzo;
import it.univaq.disim.lpo.chessgame.core.service.NoMosseDisponibiliException;
import it.univaq.disim.lpo.chessgame.core.service.PezzoNonTrovatoException;
import it.univaq.disim.lpo.chessgame.core.service.PezzoService;

public abstract class PezzoServiceAbstractImpl<T extends Pezzo> implements PezzoService<T> {

	protected Cell<Integer, Integer, Pezzo> getPosizionePezzo(Gioco gioco, Pezzo pezzo)
			throws PezzoNonTrovatoException {
		return gioco.getScacchiera().cellSet().stream().filter(z -> z.getValue() == pezzo).findFirst()
				.orElseThrow(() -> new PezzoNonTrovatoException());
	}

	protected Cell<Integer, Integer, Pezzo> getPosizioneReOpposto(Gioco gioco) throws PezzoNonTrovatoException {
		if (gioco.getReG1().getGiocatore() == gioco.getGiocatoreDiTurno())
			return getPosizionePezzo(gioco, gioco.getReG2());
		else
			return getPosizionePezzo(gioco, gioco.getReG1());

	}

	protected List<Cell<Integer, Integer, Pezzo>> getPosizioniGiocatoreDiTurno(Gioco gioco)
			throws PezzoNonTrovatoException {
		return getPosizioniGiocatoreDiTurno(gioco, gioco.getGiocatoreDiTurno());
	}

	protected List<Cell<Integer, Integer, Pezzo>> getPosizioniGiocatoreDiTurno(Gioco gioco, Giocatore giocatore)
			throws PezzoNonTrovatoException {
		return gioco.getScacchiera().cellSet().stream()
				.filter(z -> z.getValue() != null && z.getValue().getGiocatore() == giocatore)
				.collect(Collectors.toList());
	}
	@Override
	public List<Mossa> getMosseDisponibiliEssenziali(T pezzo, Gioco gioco)
			throws NoMosseDisponibiliException, PezzoNonTrovatoException {
		return getMosseDisponibiliEssenziali(pezzo, gioco, gioco.getGiocatoreDiTurno());
	}
	@Override
	public List<Mossa> getMosseDisponibiliEssenziali(T pezzo, Gioco gioco, Giocatore giocatore)
			throws NoMosseDisponibiliException, PezzoNonTrovatoException {
		List<Cell<Integer, Integer, Pezzo>> mosseDaScartare = getPosizioniGiocatoreDiTurno(gioco);
		mosseDaScartare.add(getPosizioneReOpposto(gioco));
		List<Mossa> res = ripulisciDaPozioneNonDisponibili(getMossePossibiliPezzo(pezzo, gioco, giocatore), mosseDaScartare);
		if (res.isEmpty())
			throw new NoMosseDisponibiliException("");
		return res;
	}
	private List<Mossa> ripulisciDaPozioneNonDisponibili(List<Mossa> mosse,
			List<Cell<Integer, Integer, Pezzo>> posizioni) {
		List<Mossa> result = Lists.newArrayList();
		for (Mossa mossa : mosse) {
			boolean bres = posizioni.stream().anyMatch(
					z -> mossa.getColonnaArrivo() == z.getColumnKey() && mossa.getRigaArrivo() == z.getRowKey());
			if (!bres)
				result.add(mossa);
		}
		return result;
//		return mosse.stream()
//				.filter(z -> posizioni.stream()
//						.anyMatch(k -> !(k.getRowKey() == z.getRigaArrivo() && k.getColumnKey() == z.getColonnaArrivo()))).collect(Collectors.toList());
	}
}
