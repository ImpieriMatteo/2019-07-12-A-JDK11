package it.polito.tdp.food.model;

public class Arco implements Comparable<Arco>{
	
	private Food cibo1;
	private Food cibo2;
	private Double peso;
	
	public Arco(Food cibo1, Food cibo2, Double peso) {
		this.cibo1 = cibo1;
		this.cibo2 = cibo2;
		this.peso = peso;
	}

	public Food getCibo1() {
		return cibo1;
	}

	public void setCibo1(Food cibo1) {
		this.cibo1 = cibo1;
	}

	public Food getCibo2() {
		return cibo2;
	}

	public void setCibo2(Food cibo2) {
		this.cibo2 = cibo2;
	}

	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cibo1 == null) ? 0 : cibo1.hashCode());
		result = prime * result + ((cibo2 == null) ? 0 : cibo2.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Arco other = (Arco) obj;
		if (cibo1 == null) {
			if (other.cibo1 != null)
				return false;
		} else if (!cibo1.equals(other.cibo1))
			return false;
		if (cibo2 == null) {
			if (other.cibo2 != null)
				return false;
		} else if (!cibo2.equals(other.cibo2))
			return false;
		return true;
	}

	@Override
	public int compareTo(Arco other) {
		return this.peso.compareTo(other.peso);
	}

}
