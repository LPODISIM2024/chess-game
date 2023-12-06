package it.univaq.disim.lpo.chessgame.core.datamodel;

import java.io.Serializable;

public class Mossa implements Serializable {

	public Mossa(Integer rigaPartenza, Integer colonnaPartenza, Integer rigaArrivo, Integer colonnaArrivo, Pezzo pezzo,
			Pezzo pezzoDestinazine, Giocatore giocatore) {
		super();
		this.rigaPartenza = rigaPartenza;
		this.colonnaPartenza = colonnaPartenza;
		this.rigaArrivo = rigaArrivo;
		this.colonnaArrivo = colonnaArrivo;
		this.pezzo = pezzo;
		this.pezzoDestinazione = pezzoDestinazine;
		this.giocatore = giocatore;
	}

	private Integer rigaPartenza;
	private Integer colonnaPartenza;
	private Integer rigaArrivo;
	private Integer colonnaArrivo;
	private Pezzo pezzo;
	private Pezzo pezzoDestinazione;
	private Giocatore giocatore;

	public Giocatore getGiocatore() {
		return giocatore;
	}

	public void setGiocatore(Giocatore giocatore) {
		this.giocatore = giocatore;
	}

	public Integer getRigaPartenza() {
		return rigaPartenza;
	}

	public void setRigaPartenza(Integer rigaPartenza) {
		this.rigaPartenza = rigaPartenza;
	}

	public Integer getColonnaPartenza() {
		return colonnaPartenza;
	}

	public void setColonnaPartenza(Integer colonnaPartenza) {
		this.colonnaPartenza = colonnaPartenza;
	}

	public Integer getRigaArrivo() {
		return rigaArrivo;
	}

	public void setRigaArrivo(Integer rigaArrivo) {
		this.rigaArrivo = rigaArrivo;
	}

	public Integer getColonnaArrivo() {
		return colonnaArrivo;
	}

	public void setColonnaArrivo(Integer colonnaArrivo) {
		this.colonnaArrivo = colonnaArrivo;
	}

	public Pezzo getPezzo() {
		return pezzo;
	}

	public void setPezzo(Pezzo pezzo) {
		this.pezzo = pezzo;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Mossa))
			return false;
		if (((Mossa) obj).getColonnaArrivo() != colonnaArrivo)
			return false;
		if (((Mossa) obj).getColonnaPartenza() != colonnaPartenza)
			return false;
		if (((Mossa) obj).getRigaArrivo() != rigaArrivo)
			return false;
		if (((Mossa) obj).getRigaPartenza() != rigaPartenza)
			return false;
		if (((Mossa) obj).getPezzo() != pezzo)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return String.format("PEZZO %s MOSSA DA %d:%d a %d:%d MANGiA %s", pezzo.getName(), rigaPartenza, colonnaPartenza,
				rigaArrivo, colonnaArrivo, pezzoDestinazione!=null?pezzoDestinazione.getName():"ninete");
	}

	public Pezzo getPezzoDestinazine() {
		return pezzoDestinazione;
	}

	public void setPezzoDestinazine(Pezzo pezzoDestinazine) {
		this.pezzoDestinazione = pezzoDestinazine;
	}

}
