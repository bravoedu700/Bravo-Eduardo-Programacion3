package TPE3;

import java.util.ArrayList;

public class Nodo {
	private String valor;
	private int tipo;
	private int estado;
	private ArrayList<Nodo> vecinos;
	
	public Nodo(String v, int tipo){
		this.valor=v;
		this.estado=1; // 1==BLANCO, 2==AMARILLO, 3==NEGRO
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
	
	
	
}
