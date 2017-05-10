package TPE;

public class Nodo {
	
	private Persona valor;
	private Nodo siguiente;
	
	public Nodo(Persona p){
		this.valor = p;
		this.siguiente = null;
	}
	
	public void setSiguiente(Nodo n){
		this.siguiente = n;
	}
	
	public Nodo getSiguiente(){
		return this.siguiente;
	}
	
	public Persona getValor(){
		return this.valor;
	}
	
	public void setValor(Persona p){
		this.valor = p;
	}
	
}
