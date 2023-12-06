package it.univaq.disim.lpo.chessgame.test;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import it.univaq.disim.lpo.chessgame.core.datamodel.Alfiere;
import it.univaq.disim.lpo.chessgame.core.datamodel.Computer;
import it.univaq.disim.lpo.chessgame.core.datamodel.Gioco;
import it.univaq.disim.lpo.chessgame.core.datamodel.Mossa;
import it.univaq.disim.lpo.chessgame.core.datamodel.Pedone;
import it.univaq.disim.lpo.chessgame.core.datamodel.Re;
import it.univaq.disim.lpo.chessgame.core.datamodel.Regina;
import it.univaq.disim.lpo.chessgame.core.datamodel.Torre;
import it.univaq.disim.lpo.chessgame.core.service.NoMosseDisponibiliException;
import it.univaq.disim.lpo.chessgame.core.service.PezzoNonTrovatoException;
import it.univaq.disim.lpo.chessgame.core.service.impl.AlfiereServiceImpl;
import it.univaq.disim.lpo.chessgame.core.service.impl.PedoneServiceImpl;
import it.univaq.disim.lpo.chessgame.core.service.impl.ReServiceImpl;
import it.univaq.disim.lpo.chessgame.core.service.impl.ReginaServiceImpl;
import it.univaq.disim.lpo.chessgame.core.service.impl.TorreServiceImpl;

public class PezzoServiceTest {
	static Gioco g;
	static Computer g1;
	static Computer g2;
	@BeforeAll
	public static void init() {
		g1 =  new Computer("g1");
		g2 =  new Computer("g2");
		g = new Gioco(g1, g2, false);
	}
	@Test
	public void mosseAlfiere() {
		System.out.println("===Alfiere");
		Alfiere alf = new Alfiere(g1, "ALF1");
		AlfiereServiceImpl asi = new AlfiereServiceImpl();
		g.getScacchiera().put(3, 4, alf);
		try {
			List<Mossa> res = asi.getMossePossibiliPezzo(alf, g, g1);
			res.forEach(z -> System.out.println(z));
		} catch (NoMosseDisponibiliException e) {
			System.out.println("no mosse disponibili");
			e.printStackTrace();
		} catch (PezzoNonTrovatoException e) {
			System.out.println("pezzo non trovato");
		}
	}
	
	@Test
	public void mossePedone() {
		System.out.println("===Pedone");
		Pedone alf = new Pedone(g1, "P1");
		PedoneServiceImpl asi = new PedoneServiceImpl();
		g.getScacchiera().put(3, 4, alf);
		try {
			List<Mossa> res = asi.getMossePossibiliPezzo(alf, g, g1);
			res.forEach(z -> System.out.println(z));
		} catch (NoMosseDisponibiliException e) {
			System.out.println("no mosse disponibili");
			e.printStackTrace();
		} catch (PezzoNonTrovatoException e) {
			System.out.println("pezzo non trovato");
		}
	}

	@Test
	public void mosseTorre() {
		System.out.println("===TORRE");
		Torre alf = new Torre(g1, "Torre");
		TorreServiceImpl asi = new TorreServiceImpl();
		g.getScacchiera().put(3, 4, alf);
		try {
			List<Mossa> res = asi.getMossePossibiliPezzo(alf, g, g1);
			res.forEach(z -> System.out.println(z));
		} catch (NoMosseDisponibiliException e) {
			System.out.println("no mosse disponibili");
			e.printStackTrace();
		} catch (PezzoNonTrovatoException e) {
			System.out.println("pezzo non trovato");
		}
	}
	@Test
	public void mosseRe() {
		System.out.println("===RE");
		Re alf = new Re(g1, "Torre");
		ReServiceImpl asi = new ReServiceImpl();
		g.getScacchiera().put(3, 4, alf);
		try {
			List<Mossa> res = asi.getMossePossibiliPezzo(alf, g, g1);
			res.forEach(z -> System.out.println(z));
		} catch (NoMosseDisponibiliException e) {
			System.out.println("no mosse disponibili");
			e.printStackTrace();
		} catch (PezzoNonTrovatoException e) {
			System.out.println("pezzo non trovato");
		}
	}
	@Test
	public void mosseRegina() {
		System.out.println("===REGINA");
		Regina alf = new Regina(g1, "Torre");
		ReginaServiceImpl asi = new ReginaServiceImpl();
		g.getScacchiera().put(3, 4, alf);
		try {
			List<Mossa> res = asi.getMossePossibiliPezzo(alf, g, g1);
			res.forEach(z -> System.out.println(z));
		} catch (NoMosseDisponibiliException e) {
			System.out.println("no mosse disponibili");
			e.printStackTrace();
		} catch (PezzoNonTrovatoException e) {
			System.out.println("pezzo non trovato");
		}
	}

}
