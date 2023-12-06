package it.univaq.disim.lpo.chessgame.core.service.impl;

import it.univaq.disim.lpo.chessgame.core.datamodel.Alfiere;
import it.univaq.disim.lpo.chessgame.core.datamodel.Cavallo;
import it.univaq.disim.lpo.chessgame.core.datamodel.Pedone;
import it.univaq.disim.lpo.chessgame.core.datamodel.Pezzo;
import it.univaq.disim.lpo.chessgame.core.datamodel.Re;
import it.univaq.disim.lpo.chessgame.core.datamodel.Regina;
import it.univaq.disim.lpo.chessgame.core.datamodel.Torre;
import it.univaq.disim.lpo.chessgame.core.service.PezzoService;

public class PezzoServiceFactory {
	public static <T extends Pezzo> PezzoService<? extends Pezzo> getPezzoService(Class<T> clazz){
			
		if (clazz == Cavallo.class)
			return new CavalloServiceImpl();
		if (clazz == Pedone.class)
			return new PedoneServiceImpl();
		if (clazz == Alfiere.class)
			return new AlfiereServiceImpl();
		if (clazz == Torre.class)
			return new TorreServiceImpl();
		if (clazz == Regina.class)
			return new ReginaServiceImpl();
		if (clazz == Re.class)
			return new ReServiceImpl();

		return null;
	}

}
