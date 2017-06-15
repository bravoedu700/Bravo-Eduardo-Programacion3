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
		return ((n.valor == this.valor)&&(n.tipo == this.tipo)); 
	}
	public boolean contains(Nodo n){	
		return vecinos.contains(n);
	}
	public String toString(){
		if(this.tipo == 1)
			return "Usuario: " + this.valor;
		else
			return "Gusto: " + this.valor;
	}
}
