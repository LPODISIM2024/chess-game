package it.univaq.disim.lpo.chessgame.core.service.impl;

import it.univaq.disim.lpo.chessgame.core.datamodel.Computer;
import it.univaq.disim.lpo.chessgame.core.datamodel.Giocatore;
import it.univaq.disim.lpo.chessgame.core.datamodel.Umano;
import it.univaq.disim.lpo.chessgame.core.service.GiocatoreService;

public class GiocatoreServiceFactory {
	public static <T extends Giocatore> GiocatoreService<? extends Giocatore> getPezzoService (Class<T> clazz){
		if (clazz == Computer.class)
			return new ComputerServiceImpl();
		if (clazz == Umano.class)
			return new UmanoServiceImpl();
		return null;
	}

}
