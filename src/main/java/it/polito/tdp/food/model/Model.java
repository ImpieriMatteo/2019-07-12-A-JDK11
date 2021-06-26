package it.polito.tdp.food.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.food.db.FoodDao;

public class Model {
	
	private SimpleWeightedGraph<Food, DefaultWeightedEdge> grafo;
	private Map<Integer, Food> idMap;
	private List<Arco> archi;
	private FoodDao dao;
	
	public Model() {
		this.dao = new FoodDao();
	}

	public String creaGrafo(Integer numPorzioni) {
		this.grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		this.idMap = new HashMap<>();
		this.archi = new ArrayList<>();
		
		this.dao.getFoodsPerNumPorzioni(numPorzioni, this.idMap);
		Graphs.addAllVertices(this.grafo, this.idMap.values());
		this.archi = this.dao.getArchi(idMap);
		
		for(Arco a : this.archi) {
			
			if(!this.grafo.containsEdge(a.getCibo1(), a.getCibo2())) {
				
				Graphs.addEdgeWithVertices(this.grafo, a.getCibo1(), a.getCibo2(), a.getPeso());
			}
		}
		
		return String.format("GRAFO CREATO!!\n\n#VERTICI: %s\n#ARCHI: %s\n", this.grafo.vertexSet().size(), this.grafo.edgeSet().size());
	}
	
	public Collection<Food> getAllVertici() {
		return this.idMap.values();
	}

	public Map<Food, Double> getCalCongiunteMax(Food scelto) {
		Collections.sort(this.archi);
		Map<Food, Double> result = new LinkedHashMap<>();
		
		for(Arco a : this.archi) {
			
			if(a.getCibo1().equals(scelto)) {
				result.put(a.getCibo2(), a.getPeso());
			}
			else if(a.getCibo2().equals(scelto)) {
				result.put(a.getCibo1(), a.getPeso());
			}
		}
		return result;
	}

	public SimpleWeightedGraph<Food, DefaultWeightedEdge> getGrafo() {
		return grafo;
	}
}
