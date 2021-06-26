package it.polito.tdp.food.model;

public class Evento implements Comparable<Evento>{
	
	private Food cibo;
	private Double tempo;
	private Integer stazione;

	public Evento(Food cibo, Double tempo, Integer stazione) {
		this.tempo = tempo;
		this.cibo = cibo;
		this.stazione = stazione;
	}

	public Double getTempo() {
		return tempo;
	}

	public void setTempo(Double tempo) {
		this.tempo = tempo;
	}

	public Food getCibo() {
		return cibo;
	}

	public void setCibo(Food cibo) {
		this.cibo = cibo;
	}

	public Integer getStazione() {
		return stazione;
	}

	public void setStazione(Integer stazione) {
		this.stazione = stazione;
	}

	@Override
	public int compareTo(Evento other) {
		return this.tempo.compareTo(other.tempo);
	};

}
