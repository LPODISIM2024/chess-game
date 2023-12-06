package it.univaq.disim.lpo.chessgame.core.service.impl;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import it.univaq.disim.lpo.chessgame.core.datamodel.Gioco;
import it.univaq.disim.lpo.chessgame.core.datamodel.Mossa;
import it.univaq.disim.lpo.chessgame.core.datamodel.Pezzo;
import it.univaq.disim.lpo.chessgame.core.datamodel.Umano;
import it.univaq.disim.lpo.chessgame.core.service.GiocatoreService;
import it.univaq.disim.lpo.chessgame.core.service.ModeEnumeration;
import it.univaq.disim.lpo.chessgame.core.service.NoMosseDisponibiliException;
import it.univaq.disim.lpo.chessgame.core.service.PezzoNonTrovatoException;
import it.univaq.disim.lpo.chessgame.core.service.PezzoService;

public class ComputerServiceImpl implements GiocatoreService<Umano> {
	private Random random = new Random();
	@Override
	public Mossa getMossa(Pezzo pezzo, Gioco gioco) throws NoMosseDisponibiliException, PezzoNonTrovatoException {
		PezzoService ps = PezzoServiceFactory.getPezzoService(pezzo.getClass());
		List<Mossa> mosse = ps.getMosseDisponibiliEssenziali(pezzo, gioco);
		return mosse.get(random.ints(0, mosse.size()).findFirst().getAsInt());
	}

	@Override
	public Pezzo selezionaPezzo(Gioco gioco) {
		List<Pezzo> pezzi = gioco.getScacchiera().values().stream()
				.filter(p -> p != null && p.getGiocatore() == gioco.getGiocatoreDiTurno()).collect(Collectors.toList());
		Pezzo selezione = pezzi.get(random.ints(0, pezzi.size()) .findFirst().getAsInt());
		System.out.println(String.format("Il giocatore %s ha selezionato il pezzo selezionato %s\n",
				gioco.getGiocatoreDiTurno().getName(), selezione.toString()));
		return selezione;
	}
	@Override
	public ModeEnumeration getMode() {
		return ModeEnumeration.GIOCA;
	}

	@Override
	public Mossa getMossa(Gioco gioco) throws NoMosseDisponibiliException, PezzoNonTrovatoException {
		return getMossa(selezionaPezzo(gioco), gioco);
	}


	

	





}
