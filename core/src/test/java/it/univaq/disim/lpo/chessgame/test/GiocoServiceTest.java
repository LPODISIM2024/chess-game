package it.univaq.disim.lpo.chessgame.test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import it.univaq.disim.lpo.chessgame.core.datamodel.ColoreEnumeration;
import it.univaq.disim.lpo.chessgame.core.datamodel.Computer;
import it.univaq.disim.lpo.chessgame.core.datamodel.Gioco;
import it.univaq.disim.lpo.chessgame.core.datamodel.Mossa;
import it.univaq.disim.lpo.chessgame.core.datamodel.Pedone;
import it.univaq.disim.lpo.chessgame.core.datamodel.Re;
import it.univaq.disim.lpo.chessgame.core.service.GiocoService;
import it.univaq.disim.lpo.chessgame.core.service.MossaNonLegittimaException;
import it.univaq.disim.lpo.chessgame.core.service.NoMosseDisponibiliException;
import it.univaq.disim.lpo.chessgame.core.service.PezzoNonTrovatoException;
import it.univaq.disim.lpo.chessgame.core.service.impl.GiocoServiceImpl;
import it.univaq.disim.lpo.chessgame.core.service.impl.PedoneServiceImpl;

public class GiocoServiceTest {
	static Gioco g;
	static Computer g1;
	static Computer g2;
	static Re reOpposto;
	static Re re;
	@BeforeAll
	public static void init() {
		g1 = new Computer("g1");
		g2 = new Computer("g2");
		g1.setColore(ColoreEnumeration.BIANCO);
		g2.setColore(ColoreEnumeration.NERO);
		reOpposto = new Re(g2, "REEOPPOSTO");
		re = new Re(g1, "REEOPPOSTO");
		g = new Gioco(g1, g2, false);
		g.setGiocatoreDiTurno(g1);
	}

	GiocoService gs = new GiocoServiceImpl();

	@Test
	public void giocoServiceUndoTest() throws NoMosseDisponibiliException, PezzoNonTrovatoException, MossaNonLegittimaException {
		PedoneServiceImpl psi = new PedoneServiceImpl();
		GiocoServiceImpl gsi = new GiocoServiceImpl();
		Pedone p1 = new Pedone(g1, "P1");
		Pedone p2 = new Pedone(g2, "P2");
		g.getScacchiera().put(3, 4, p1);
		g.getScacchiera().put(4, 4, p2);
		g.getScacchiera().put(1, 1, reOpposto);
		g.getScacchiera().put(8, 8, re);
		List<Mossa> mosse = psi.getMosseDisponibiliEssenziali(p1, g);
		gsi.eseguiMossa(g, mosse.get(0));
		gsi.undoLast(g);

	}
	@Test
	public void controlloFalseScaccoTest() throws NoMosseDisponibiliException, PezzoNonTrovatoException, MossaNonLegittimaException {
		PedoneServiceImpl psi = new PedoneServiceImpl();
		GiocoServiceImpl gsi = new GiocoServiceImpl();
		Pedone p1 = new Pedone(g1, "P1");
		Pedone p2 = new Pedone(g2, "P2");
		g.getScacchiera().put(3, 4, p1);
		g.getScacchiera().put(4, 4, p2);
		g.getScacchiera().put(1, 1, reOpposto);
		g.getScacchiera().put(8, 8, re);
		assertFalse(gsi.controllaGiocatoreTurnoSottoScacco(g, g1));
	}
	@Test
	public void controlloTrueScaccoTest() throws NoMosseDisponibiliException, PezzoNonTrovatoException, MossaNonLegittimaException {
		PedoneServiceImpl psi = new PedoneServiceImpl();
		GiocoServiceImpl gsi = new GiocoServiceImpl();
		Pedone p1 = new Pedone(g1, "P1");
		Pedone p2 = new Pedone(g2, "P2");
		g.getScacchiera().put(3, 4, p1);
		g.getScacchiera().put(4, 4, reOpposto);
		g.getScacchiera().put(8, 8, re);
		System.out.println(g);
		assertTrue(gsi.controllaGiocatoreTurnoSottoScacco(g, g2));
	}
}
