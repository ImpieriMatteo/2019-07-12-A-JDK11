package it.polito.tdp.food.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Simulatore {
	
	private Integer numStazioniLavoro;
	private Model model;
	
	private PriorityQueue<Evento> queue;
	
	private List<Food> cibiPreparati;
	private Integer numCibiPreparati;
	private Double tempoTOT;
	
	public void init(Integer k, Food partenza, Model model) {
		this.model = model;
		this.queue = new PriorityQueue<>();
		this.cibiPreparati = new ArrayList<>();
		
		this.numStazioniLavoro = k;
		
		this.numCibiPreparati = 0;
		this.tempoTOT = 0.0;
		
		Integer numStaz = 1;
		Map<Food, Double> vicini = this.model.getCalCongiunteMax(partenza);
		for(Food f : vicini.keySet()) {
			
			if(numStaz<=this.numStazioniLavoro) {
				this.queue.add(new Evento(f, vicini.get(f), numStaz));
				this.cibiPreparati.add(f);
				numStaz++;
			}
			else 
				break;
		}
	}
	
	public void simula() {
		
		while(!this.queue.isEmpty()) {
			
			Evento e = this.queue.poll();
			
			this.tempoTOT = e.getTempo();
			this.numCibiPreparati++;
			
			Map<Food, Double> adiacentiMax = this.model.getCalCongiunteMax(e.getCibo());
			Double tempoProssimoCibo = 0.0;
			Food prossimoCibo = null;
			
			for(Food f : adiacentiMax.keySet()) {
				
				if(!this.cibiPreparati.contains(f)) {
					prossimoCibo = f;
					this.cibiPreparati.add(f);
					tempoProssimoCibo = adiacentiMax.get(f);
					break;
				}
			}
			
			if(prossimoCibo != null) {
				this.queue.add(new Evento(prossimoCibo, e.getTempo()+tempoProssimoCibo, e.getStazione()));
			}			
		}
	}

	public Integer getNumCibiPreparati() {
		return numCibiPreparati;
	}

	public Double getTempoTOT() {
		return tempoTOT;
	}

}
