
public class Nodo {
	
	private int valor;
	private Nodo siguiente;
	
	public Nodo(int i){
		this.valor = i;
		this.siguiente = null;
	}
	
	public void setSiguiente(Nodo n){
		this.siguiente = n;
	}
	
	public Nodo getSiguiente(){
		return this.siguiente;
	}
	
	public int getValor(){
		return this.valor;
	}
	
	public void setValor(int i){
		this.valor = i;
	}
	
}
