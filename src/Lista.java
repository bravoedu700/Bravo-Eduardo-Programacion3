
public class Lista {

	private int tamanio = 0;
		
	private Nodo primero;
	private Nodo ultimo;
	
	public Lista(){	
		this.primero = null;
		this.ultimo = null;
			
	}
	
	
	public void addPrincipio(int i){
		
		Nodo nuevo = new Nodo(i);

		if(this.primero == null){
			this.primero = nuevo;
			this.ultimo = nuevo;
		}
		else
		{
			nuevo.setSiguiente(this.primero);
			this.primero = nuevo;
		}
		this.tamanio++;
	}
	
	public void addFinal(int i){

		Nodo nuevo = new Nodo(i);

		if(this.primero == null){
			this.primero = nuevo;
			this.ultimo = nuevo;			
		}
		else
		{
			this.ultimo.setSiguiente(nuevo);
			this.ultimo = nuevo;
		}
		this.tamanio++;		
	}
	
	public int tamanio(){
		return tamanio;
	}
	
	public int buscar(int posicion){
		if(posicion < this.tamanio){
			Nodo resultado = this.primero;
			for(int i = 0; i< posicion; i++)
				resultado = resultado.getSiguiente();
			return resultado.getValor();
		}
		return -1;		
	}
	
	public int getPrimero(){
		return this.primero.getValor();
	}
	
	public int getUltimo(){
		return this.ultimo.getValor();
	}
	
	public void deletePrincipio(){
		this.primero = this.primero.getSiguiente();
		this.tamanio--;
	}
}
