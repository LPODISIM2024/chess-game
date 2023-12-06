package it.univaq.disim.lpo.chessgame.core.datamodel;

import java.io.Serializable;
import java.util.List;

import com.google.common.collect.ArrayTable;
import com.google.common.collect.Lists;
import com.google.common.collect.Table;

public class Gioco implements Serializable {
	private List<Mossa> mosse;
	private Giocatore g1, g2;
	private Giocatore giocatoreDiTurno;
	private Table<Integer, Integer, Pezzo> scacchiera;
//	private Re reG1;
//	private Re reG2;

	public Gioco() {
		super();
	}

	public Gioco(Giocatore g1, Giocatore g2, boolean inzializza) {
		List<Integer> universityRowTable = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8);
		List<Integer> courseColumnTables = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8);
		scacchiera = ArrayTable.create(courseColumnTables, universityRowTable);
		if (inzializza) {
//			reG1 = new Re(g1, "R1");
			scacchiera.put(1, 1, new Torre(g1, "T1"));
			scacchiera.put(1, 2, new Cavallo(g1, "C1"));
			scacchiera.put(1, 3, new Alfiere(g1, "A1"));
			scacchiera.put(1, 4, new Re(g1, "R1"));
			scacchiera.put(1, 5, new Regina(g1, "r2"));
			scacchiera.put(1, 6, new Alfiere(g1, "A2"));
			scacchiera.put(1, 7, new Cavallo(g1, "C2"));
			scacchiera.put(1, 8, new Torre(g1, "T2"));

			scacchiera.put(2, 1, new Pedone(g1, "P1"));
			scacchiera.put(2, 2, new Pedone(g1, "P2"));
			scacchiera.put(2, 3, new Pedone(g1, "P3"));
			scacchiera.put(2, 4, new Pedone(g1, "P4"));
			scacchiera.put(2, 5, new Pedone(g1, "P5"));
			scacchiera.put(2, 6, new Pedone(g1, "P6"));
			scacchiera.put(2, 7, new Pedone(g1, "P7"));
			scacchiera.put(2, 8, new Pedone(g1, "P8"));

			scacchiera.put(7, 1, new Pedone(g2, "P1"));
			scacchiera.put(7, 2, new Pedone(g2, "P2"));
			scacchiera.put(7, 3, new Pedone(g2, "P3"));
			scacchiera.put(7, 4, new Pedone(g2, "P4"));
			scacchiera.put(7, 5, new Pedone(g2, "P5"));
			scacchiera.put(7, 6, new Pedone(g2, "P6"));
			scacchiera.put(7, 7, new Pedone(g2, "P7"));
			scacchiera.put(7, 8, new Pedone(g2, "P8"));

			scacchiera.put(8, 1, new Torre(g2, "T1"));
			scacchiera.put(8, 2, new Cavallo(g2, "C1"));
			scacchiera.put(8, 3, new Alfiere(g2, "A1"));
			scacchiera.put(8, 4, new Regina(g2, "r2"));
			scacchiera.put(8, 5, new Re(g2, "R1"));
			scacchiera.put(8, 6, new Alfiere(g2, "A2"));
			scacchiera.put(8, 7, new Cavallo(g2, "C2"));
			scacchiera.put(8, 8, new Torre(g2, "T2"));
		}
		this.g1 = g1;
		this.g2 = g2;
		giocatoreDiTurno = g1;
		mosse = Lists.newArrayList();
	}

	public Table<Integer, Integer, Pezzo> getScacchiera() {
		return scacchiera;
	}

	public void setScacchiera(Table<Integer, Integer, Pezzo> scacchiera) {
		this.scacchiera = scacchiera;
	}

	public Giocatore getG1() {
		return g1;
	}

	public void setG1(Giocatore g1) {
		this.g1 = g1;
	}

	public Giocatore getG2() {
		return g2;
	}

	public void setG2(Giocatore g2) {
		this.g2 = g2;
	}

	public Giocatore getGiocatoreDiTurno() {
		return giocatoreDiTurno;
	}

	public void setGiocatoreDiTurno(Giocatore giocatoreDiTurno) {
		this.giocatoreDiTurno = giocatoreDiTurno;
	}

	public List<Mossa> getMossePartita() {
		return mosse;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("Giocatore %s Colore %s, Giocatore %s Colore %s", g1.getName(), g1.getColore().name(),
				g2.getName(), g2.getColore().name()));
		sb.append("\n");
		sb.append(String.format("Giuocatore di turno: %s-%s \n\n", giocatoreDiTurno.getName(),
				giocatoreDiTurno.getColore().name()));
		for (Integer colonna : scacchiera.columnKeySet()) {
			sb.append(String.format("\t%d", colonna));
		}
		sb.append("\n");
		for (Integer riga : scacchiera.rowKeySet()) {
			sb.append("\n");
			sb.append(riga);
			for (Integer colonna : scacchiera.columnKeySet()) {
				sb.append("\t");
				sb.append(scacchiera.get(riga, colonna) != null ? scacchiera.get(riga, colonna) : "    ");
			}
			sb.append("\n");
		}

		return sb.toString();
	}

	public Re getReG1() {
		return (Re) scacchiera.values().stream().filter(x -> x instanceof Re && x.getGiocatore()  == g1).findFirst().get();
	}

	public Re getReG2() {
		return (Re) scacchiera.values().stream().filter(x -> x instanceof Re && x.getGiocatore()  == g2).findFirst().get();
	}
	public Re getReGiocatoreDiTurno() {
		return (Re) scacchiera.values().stream().filter(x -> x instanceof Re && x.getGiocatore()  == giocatoreDiTurno).findFirst().get();
	}

	public Re getRe(Giocatore giocatore) {
		return (Re) scacchiera.values().stream().filter(x -> x instanceof Re && x.getGiocatore()  == giocatore).findFirst().get();
	}
	public Giocatore getGiocatoreNonDiTurno() {
		return giocatoreDiTurno == g1 ? g2: g1;
	}
}
