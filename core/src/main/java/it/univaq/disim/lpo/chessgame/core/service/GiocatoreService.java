package it.univaq.disim.lpo.chessgame.core.service;

import it.univaq.disim.lpo.chessgame.core.datamodel.Giocatore;
import it.univaq.disim.lpo.chessgame.core.datamodel.Mossa;
import it.univaq.disim.lpo.chessgame.core.datamodel.Pezzo;
import it.univaq.disim.lpo.chessgame.core.datamodel.Gioco;

public interface GiocatoreService<T extends Giocatore> {

	public Mossa getMossa(Pezzo pezzo, Gioco gioco) throws NoMosseDisponibiliException, PezzoNonTrovatoException;
	public Mossa getMossa(Gioco gioco) throws NoMosseDisponibiliException, PezzoNonTrovatoException;
	public Pezzo selezionaPezzo(Gioco gioco);
    public ModeEnumeration getMode();
}
