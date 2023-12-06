package it.univaq.disim.lpo.chessgame.core.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.google.common.collect.Maps;

import it.univaq.disim.lpo.chessgame.core.datamodel.Computer;
import it.univaq.disim.lpo.chessgame.core.datamodel.Giocatore;
import it.univaq.disim.lpo.chessgame.core.datamodel.Gioco;
import it.univaq.disim.lpo.chessgame.core.datamodel.Mossa;
import it.univaq.disim.lpo.chessgame.core.datamodel.Pezzo;
import it.univaq.disim.lpo.chessgame.core.service.GiocatoreService;
import it.univaq.disim.lpo.chessgame.core.service.GiocoService;
import it.univaq.disim.lpo.chessgame.core.service.ModeEnumeration;
import it.univaq.disim.lpo.chessgame.core.service.NoMosseDisponibiliException;
import it.univaq.disim.lpo.chessgame.core.service.PezzoNonTrovatoException;
import it.univaq.disim.lpo.chessgame.core.service.PezzoService;

public class UmanoServiceImpl implements GiocatoreService<Computer> {
	GiocoService gs = new GiocoServiceImpl();
	@Override
	public Mossa getMossa(Pezzo pezzo, Gioco gioco) throws NoMosseDisponibiliException, PezzoNonTrovatoException {
		PezzoService ps = PezzoServiceFactory.getPezzoService(pezzo.getClass());
		List<Mossa> mosse = ps.getMosseDisponibiliEssenziali(pezzo, gioco);
		return CommandLineSingleton.getInstance().readIntegerUntilPossibleValue(mosse);
	}

	@Override
	public Pezzo selezionaPezzo(Gioco gioco) {
		List<Pezzo> pezzi = gioco.getScacchiera().values().stream()
				.filter(p -> p != null && p.getGiocatore() == gioco.getGiocatoreDiTurno()).collect(Collectors.toList());
		System.out.println("Seleziona una dei seguenti pezzi");
		Pezzo pezzo = CommandLineSingleton.getInstance().readIntegerUntilPossibleValue(pezzi);
		System.out.println(String.format("Il giocatore %s ha selezionato il pezzo selezionato %s\n",
				gioco.getGiocatoreDiTurno().getName(), pezzo.toString()));
		return pezzo;
	}

	@Override
	public ModeEnumeration getMode() {
		System.out.println("Selezionare una delle seguanti mosse:");
		System.out.println("\t1 gioca");
		System.out.println("\t2 salva");
		System.out.println("\t3 abbandona");
		Integer scelta = CommandLineSingleton.getInstance().readIntegerUntilPossibleValue(new Integer[] { 1, 2, 3 });
		if (scelta == 1)
			return ModeEnumeration.GIOCA;
		if (scelta == 2)
			return ModeEnumeration.SALVA;

		return ModeEnumeration.ABBANDONA;
	}

	@Override
	public Mossa getMossa(Gioco gioco) throws NoMosseDisponibiliException, PezzoNonTrovatoException {
		return getMossa(selezionaPezzo(gioco), gioco);
	}

}
