package practico3;

import java.util.ArrayList;
import java.util.Stack;

public class Main {

	/**
	 * @param args
	 */
	
	public static void dfs_recursivo(Grafo g){
		for(int i = 0; i<g.getNodos().size(); i++){			
			if(g.getNodos().get(i).getEstado()==1){
				dfs_recurcivo_visita(g.getNodos().get(i));
			}
		}
		return;
	}
	
	public static void dfs_recurcivo_visita(Nodo n){
		n.setEstado(2); //lo pongo en estado visitando
		
		System.out.println("Nodo: " + n.getValor());
		
		for(int i = 0; i<n.getVecinos().size(); i++){
			if(n.getVecinos().get(i).getEstado()==1){
				dfs_recurcivo_visita(n.getVecinos().get(i));
			}
		}	
	}
	
	public static boolean tieneCiclo_recursivo(Grafo g){
		for (int i = 0; i < g.getNodos().size(); i++) {
			if(g.getNodos().get(i).getEstado()==1){
				return tieneCiclo_visita(g.getNodos().get(i));
			}
		}
		return false;
	}
	
	public static boolean tieneCiclo_visita(Nodo n){
		n.setEstado(2);
		for(int i = 0; i<n.getVecinos().size(); i++){
			if(n.getVecinos().get(i).getEstado()==1){
				if(tieneCiclo_visita(n.getVecinos().get(i))){
					return true;
				}
			}	
			else{
				if(n.getVecinos().get(i).getEstado()==2){
					return true;
				}
			}
		}
		n.setEstado(3);
		return false;
	}
	
	public static void dfs_iteractivo(Grafo g){
		for(int i = 0; i<g.getNodos().size(); i++){			
			if(g.getNodos().get(i).getEstado()==1){
				dfs_iteractivo_visita(g.getNodos().get(i));
			}
		}
		return;
	}
	
	public static void dfs_iteractivo_visita(Nodo n){
	    Stack <Nodo> pila = new Stack<Nodo>();
	    pila.push(n);
	    while (!pila.empty()){
	    	Nodo nod = pila.pop();
	    	if(nod.getEstado()!=3){
	    		System.out.println("Nodo: " + nod.getValor());
	    		nod.setEstado(3);
	    		ArrayList <Nodo> ve = nod.getVecinos();
	    		for(int i = 0; i<ve.size();i++){
	    			pila.push(ve.get(i));
	    		}
	    	}
	    }
	   return; 
	}
	
	
	public static boolean tieneCiclo_iteractivo(Grafo g){
		for(int i = 0; i<g.getNodos().size(); i++){			
			if(g.getNodos().get(i).getEstado()==1){
				return tieneCiclo_iteractivo_visita(g.getNodos().get(i));
			}
		}
		return false;
	}
	
	public static boolean tieneCiclo_iteractivo_visita(Nodo n){
	    Stack <Nodo> pila = new Stack<Nodo>();
	    pila.push(n);
	    while (!pila.empty()){
	    	Nodo nod = pila.pop();
	    	if(nod.getEstado()!=3){
	    		nod.setEstado(2);
	    		ArrayList <Nodo> ve = nod.getVecinos();
	    		for(int i = 0; i<ve.size();i++){
	    			if(ve.get(i).getEstado()==1){
	    				pila.push(ve.get(i));
					}
	    			else{ 
	    				if(ve.get(i).getEstado()==2){
	    					return true;
	    				}
	    			}
	    		}
	    	}
	    }
	   return false; 
	}
	
	public static void restear_estados(Grafo f){
		for(int i = 0; i<f.getNodos().size(); i++){			
			f.getNodos().get(i).setEstado(1);
		}
		return;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		for(int y=10; y<400; y+=50){
			Grafo miGrafo = new Grafo();
			for(int i = 0; i<y; i++){
				miGrafo.addNodo(i);
			}
			
			for(int j = 0; j<y; j++){
				int n1 = (int) Math.floor(Math.random()*y);
				int n2 = (int) Math.floor(Math.random()*y);
				if(!miGrafo.esvecino(n1, n2)){
					miGrafo.addVecino(n1, n2);
				}
			}
			
			System.out.println("------GRAFO CON "+y+" Nodos------");
			
			System.out.println("------DFS Recusrcivo------");
			dfs_recursivo(miGrafo);
			
			restear_estados(miGrafo);
			
			System.out.println("------DFS Iteractivo------");
			
			dfs_iteractivo(miGrafo);
			
			restear_estados(miGrafo);
			
			if(tieneCiclo_recursivo(miGrafo)){
				System.out.println("------Tiene Ciclo (Recursivo)------");
			}
			else{
				System.out.println("------No tiene ciclo (Recursivo)------");
			}
			
			restear_estados(miGrafo);
			
			if(tieneCiclo_iteractivo(miGrafo)){
				System.out.println("------Tiene Ciclo (iteractivo)------");
			}
			else{
				System.out.println("------No tiene ciclo (iteractivo)------");
			}
		}
	}

}
