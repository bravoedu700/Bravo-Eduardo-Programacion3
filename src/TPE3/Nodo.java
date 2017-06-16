package TPE3;

import java.util.ArrayList;

public class Nodo {
	private String valor;
	private int tipo;
	private int estado;
	private ArrayList<Nodo> vecinos;
	public Nodo(String v,int tipo){
		this.valor=v;
		this.setTipo(tipo); //1=Persona 2=Gusto
		this.estado=1; //1==BLANCO, 2==AMARILLO, 3==NEGRO
		this.setTipo(tipo);
		vecinos = new ArrayList<Nodo>();
	}
	public String getValor(){
		return this.valor;
	}
	public int getEstado(){
		return this.estado;
	}
	public void setEstado(int i){
		this.estado=i;
	}
	public ArrayList<Nodo> getVecinos(){
		return this.vecinos;
	}
	public void addVecino(Nodo n){
		this.vecinos.add(n);
	}
	public int getTipo() {
		return tipo;
	}
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	
	public boolean equals(Nodo n){
		return ((this.valor.equals(n.valor))&&(n.tipo == this.tipo)); 
	}
	
	public boolean containsVecino(Nodo n){	
		for(int y=0; y<this.vecinos.size(); y++){		
			if(this.vecinos.get(y).equals(n)){
				return true;
			}
		}
		return false;
	}
	
	public String toString(){
		if(this.tipo == 1)
			return "Usuario: " + this.valor;
		else
			return "Gusto: " + this.valor;
	}
}
