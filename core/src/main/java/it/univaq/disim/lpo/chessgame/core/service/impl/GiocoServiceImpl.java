package it.univaq.disim.lpo.chessgame.core.service.impl;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.stream.Collectors;

import com.google.common.collect.Table.Cell;

import it.univaq.disim.lpo.chessgame.core.datamodel.ColoreEnumeration;
import it.univaq.disim.lpo.chessgame.core.datamodel.Computer;
import it.univaq.disim.lpo.chessgame.core.datamodel.Giocatore;
import it.univaq.disim.lpo.chessgame.core.datamodel.Gioco;
import it.univaq.disim.lpo.chessgame.core.datamodel.Mossa;
import it.univaq.disim.lpo.chessgame.core.datamodel.Pezzo;
import it.univaq.disim.lpo.chessgame.core.datamodel.Re;
import it.univaq.disim.lpo.chessgame.core.datamodel.Umano;
import it.univaq.disim.lpo.chessgame.core.service.GiocatoreService;
import it.univaq.disim.lpo.chessgame.core.service.GiocoService;
import it.univaq.disim.lpo.chessgame.core.service.InizializzaPartitaException;
import it.univaq.disim.lpo.chessgame.core.service.ModeEnumeration;
import it.univaq.disim.lpo.chessgame.core.service.MossaNonLegittimaException;
import it.univaq.disim.lpo.chessgame.core.service.NoMosseDisponibiliException;
import it.univaq.disim.lpo.chessgame.core.service.PezzoNonTrovatoException;
import it.univaq.disim.lpo.chessgame.core.service.PezzoService;
import it.univaq.disim.lpo.chessgame.core.service.SottoScaccoException;

public class GiocoServiceImpl implements GiocoService {
	@Override
	public Gioco inizializzaPartita() throws InizializzaPartitaException {
		Gioco gioco = null;
		System.out.println("Selezionare (1) nuova partita o (2) per caricare una partita esistente:");
		Integer modo = CommandLineSingleton.getInstance().readIntegerUntilPossibleValue(new Integer[] { 1, 2, 9 });
		if (modo == 2) {
			while (true) {
				System.out.println("Seleziona file da caricare");
				String valore = CommandLineSingleton.getInstance().readString();
				try {
					return caricaGioco(valore);
				} catch (ClassNotFoundException e) {
					System.out.println(e.getMessage());
				} catch (CloneNotSupportedException e) {
					System.out.println(e.getMessage());
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
			}
		}
		if (modo == 1) {

			System.out.println("Selezione modalità (1) UMANO vs COM e (2) UMANO VS UMANO");
			Integer modalita = CommandLineSingleton.getInstance().readIntegerUntilPossibleValue(new Integer[] { 1, 2 });
			Giocatore g1 = new Umano("TEMP1");
			Giocatore g2;
			if (modalita == 2)
				g2 = new Umano("TEMP2");
			else
				g2 = new Computer("TEMP2");
			System.out.print("Nome giocatore 1:");

			g1.setName(CommandLineSingleton.getInstance().readString());
			System.out.print("Colore giocatore 1 (1) Bianco (2) nero:");
			g1.setColore(CommandLineSingleton.getInstance().readIntegerUntilPossibleValue(new Integer[] { 1, 2 }) == 1
					? ColoreEnumeration.BIANCO
					: ColoreEnumeration.NERO);

			System.out.print("Nome giocatore 2:");
			g2.setName(CommandLineSingleton.getInstance().readString());
			if (g1.getColore() == ColoreEnumeration.NERO)
				g2.setColore(ColoreEnumeration.BIANCO);
			else
				g2.setColore(ColoreEnumeration.NERO);

			gioco = new Gioco(g1, g2, true);

			return gioco;
		} else
			throw new InizializzaPartitaException();

	}

	@Override
	public boolean controllaGiocatoreTurnoSottoScacco(Gioco g, Giocatore giocatore) {
		Giocatore tempg = g.getG1() == giocatore ? g.getG2() : g.getG1();
		List<Cell<Integer, Integer, Pezzo>> poss = getPosizioniPezzi(g, tempg);
		for (Cell<Integer, Integer, Pezzo> pos : poss) {
			PezzoService ps = PezzoServiceFactory.getPezzoService(pos.getValue().getClass());
			List<Mossa> mosse;
			try {
				mosse = ps.getMosseDisponibiliEssenziali(pos.getValue(), g, tempg);
				if (mosse.stream().anyMatch(z -> z.getPezzoDestinazine() == g.getRe(giocatore)))
					return true;
			} catch (NoMosseDisponibiliException e) {
				System.out.println();
			} catch (PezzoNonTrovatoException e) {

			}

		}
		return false;
	}

	@Override
	public void gioca(Gioco gioco) {
		while (true) {
			Giocatore giocatoreDiTurno = gioco.getGiocatoreDiTurno();

			System.out.println(gioco);
			GiocatoreService gs = GiocatoreServiceFactory.getPezzoService(giocatoreDiTurno.getClass());
			ModeEnumeration mode = gs.getMode();
			if (mode == ModeEnumeration.SALVA) {
				try {
					String filename = CommandLineSingleton.getInstance().readString();
					salvaPartita(gioco, filename);
				} catch (ClassNotFoundException | CloneNotSupportedException | IOException e) {
					System.out.println("E' stato impossibile salvare la partita. Ci scusiamo per il disagio;");
				}
				return;
			}
			if (mode == ModeEnumeration.ABBANDONA) {
				System.out.println(giocatoreDiTurno.getColore() + ":" + giocatoreDiTurno.getName() + " ha abbandonato");
				return;
			}
			if (mode == ModeEnumeration.GIOCA) {
				try {
					System.out.println(
							String.format("il giocatore di turno: %s scacco: %b", gioco.getGiocatoreDiTurno().getName(),
									controllaGiocatoreTurnoSottoScacco(gioco, giocatoreDiTurno)));
					Pezzo selezionato = gs.selezionaPezzo(gioco);
					Mossa mossa = gs.getMossa(selezionato, gioco);
					verificaStato(gioco);
					passaTurno(gioco);
					eseguiMossa(gioco, mossa);
				} catch (MossaNonLegittimaException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoMosseDisponibiliException e) {
					System.out.println("il pezzo non ha mosse disponibili \n\n");

				} catch (PezzoNonTrovatoException e) {
					System.out.println("il pezzo di partenza non è corretto");
				}
			}
		}
	}

	public void verificaStato(Gioco gioco) {
		// TODO implaments it
	}

	public void passaTurno(Gioco gioco) {
		if (gioco.getG1().equals(gioco.getGiocatoreDiTurno()))
			gioco.setGiocatoreDiTurno(gioco.getG2());
		else
			gioco.setGiocatoreDiTurno(gioco.getG1());
	}

	@Override
	public void salvaPartita(Gioco g, String filename)
			throws CloneNotSupportedException, IOException, ClassNotFoundException {
		FileOutputStream fos = new FileOutputStream(filename);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(g);
		oos.close();
	}

	@Override
	public Gioco caricaGioco(String filename) throws CloneNotSupportedException, IOException, ClassNotFoundException {
		FileInputStream fis = new FileInputStream(filename);
		ObjectInputStream ois = new ObjectInputStream(fis);
		Gioco b = (Gioco) ois.readObject();// down-casting object
		ois.close();
		return b;
	}

	@Override
	public Gioco eseguiMossa(Gioco gioco, Mossa mossa) throws MossaNonLegittimaException {
		Pezzo p = mossa.getPezzo();
		if (gioco.getScacchiera().get(mossa.getRigaArrivo(), mossa.getColonnaArrivo()) instanceof Re)
			throw new MossaNonLegittimaException("Non si può mangiare il re");
		gioco.getScacchiera().put(mossa.getRigaArrivo(), mossa.getColonnaArrivo(), p);
		gioco.getScacchiera().put(mossa.getRigaPartenza(), mossa.getColonnaPartenza(), null);
		if(controllaGiocatoreTurnoSottoScacco(gioco, gioco.getGiocatoreDiTurno())) {
			gioco.getScacchiera().put(mossa.getRigaArrivo(), mossa.getColonnaArrivo(), mossa.getPezzoDestinazine());
			gioco.getScacchiera().put(mossa.getRigaPartenza(), mossa.getColonnaPartenza(), mossa.getPezzo());
			throw new SottoScaccoException("Non puoi lasciare il re sotto scacco!");
		}
		gioco.getMossePartita().add(mossa);
		return null;
	}

	@Override
	public void undoLast(Gioco gioco) throws MossaNonLegittimaException {
		Mossa mossa = gioco.getMossePartita().get(gioco.getMossePartita().size() - 1);
		gioco.getScacchiera().put(mossa.getRigaArrivo(), mossa.getColonnaArrivo(), mossa.getPezzoDestinazine());
		gioco.getScacchiera().put(mossa.getRigaPartenza(), mossa.getColonnaPartenza(), mossa.getPezzo());
		gioco.getMossePartita().remove(mossa);

	}

	@Override
	public void undo(Gioco gioco, Integer numeroMosse) throws MossaNonLegittimaException {
		for (int i = 0; i < numeroMosse; i++)
			undoLast(gioco);
	}

	@Override
	public boolean controlloScaccoMatto(Gioco gioco, Giocatore diTurno) {
		List<Cell<Integer, Integer, Pezzo>> pezziGiocatoreDiTurno = getPosizioniPezzi(gioco, diTurno);
		for (Cell<Integer, Integer, Pezzo> pezzoGT : pezziGiocatoreDiTurno) {
			try {
				PezzoService ps = PezzoServiceFactory.getPezzoService(pezzoGT.getValue().getClass());
				List<Mossa> mosse = ps.getMosseDisponibiliEssenziali(pezzoGT.getValue(), gioco, diTurno);
				for(Mossa mossa : mosse) {
					eseguiMossa(gioco, mossa);
					undoLast(gioco);
				}
				return true;
			} catch (SottoScaccoException e) {
				
			} catch (PezzoNonTrovatoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoMosseDisponibiliException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MossaNonLegittimaException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public Cell<Integer, Integer, Pezzo> getPosizioniPezzo(Gioco gioco, Pezzo pezzo) throws PezzoNonTrovatoException {
		return gioco.getScacchiera().cellSet().stream().filter(z -> z == pezzo).findFirst()
				.orElseThrow(() -> new PezzoNonTrovatoException());
	}

	@Override
	public Cell<Integer, Integer, Pezzo> getPosizioneReGiocatoreDiTurno(Gioco gioco) throws PezzoNonTrovatoException {
		if (gioco.getReG1().getGiocatore() == gioco.getGiocatoreDiTurno())
			return getPosizioniPezzo(gioco, gioco.getReG2());
		else
			return getPosizioniPezzo(gioco, gioco.getReG1());

	}

	@Override
	public List<Cell<Integer, Integer, Pezzo>> getPosizioniPezziGiocatoreTurno(Gioco gioco) {
		return gioco.getScacchiera().cellSet().stream()
				.filter(z -> z.getValue() != null && z.getValue().getGiocatore() == gioco.getGiocatoreDiTurno())
				.collect(Collectors.toList());
	}

	@Override
	public List<Cell<Integer, Integer, Pezzo>> getPosizioniPezzi(Gioco gioco, Giocatore giocatore) {
		return gioco.getScacchiera().cellSet().stream()
				.filter(z -> z.getValue() != null && z.getValue().getGiocatore() == giocatore)
				.collect(Collectors.toList());
	}

}
