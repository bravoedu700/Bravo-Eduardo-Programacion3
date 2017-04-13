package practico3;

import java.util.ArrayList;

public class Grafo {
	private ArrayList<Nodo> Nodos;
	
	public Grafo(){
		this.Nodos = new ArrayList<Nodo>();
	}
	
	public void addNodo(int i){
		Nodo n = new Nodo(i);
		this.Nodos.add(n);
	}
	
	public void addVecino(int n1, int n2){
		this.getNodos().get(n1).addVecino(this.getNodos().get(n2));
	}
	
	public boolean esvecino(int n1, int n2){
		return this.getNodos().get(n1).getVecinos().contains(n2);
	}
	
	public ArrayList<Nodo> getNodos(){
		return this.Nodos;
	}
	
	
	
}
