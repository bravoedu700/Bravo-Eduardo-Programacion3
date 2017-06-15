package TPE3;

import java.util.ArrayList;
import java.util.Iterator;

public class Grafo {
	private ArrayList<Nodo> Nodos;
	
	public Grafo(){
		this.Nodos = new ArrayList<Nodo>();
	}
	public void addNodo(Nodo n){
		this.Nodos.add(n);
	}
	public void addVecino(Nodo n1, Nodo n2){
		n1.addVecino(n2);
	}
	public boolean esvecino(Nodo n1, Nodo n2){
		return n1.contains(n2);
	}
	public ArrayList<Nodo> getNodos(){
		return this.Nodos;
	}
	public boolean contains(Nodo n){
		return this.Nodos.contains(n);
	}
	public void desmarcar(){
		Iterator<Nodo> e = this.getNodos().iterator();
    	while(e.hasNext()){    		
			e.next().setEstado(1);
    	}
	}
}
