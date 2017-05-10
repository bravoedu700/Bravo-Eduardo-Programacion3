package TPE;


public class Lista {
	private int tamanio = 0;	
	private Nodo primero;
	private Nodo ultimo;
	
	public Lista(){	
		this.primero = null;
		this.ultimo = null;	
	}
	
	
	public void addPrincipio(Persona p){
		
		Nodo nuevo = new Nodo(p);
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
	
	public void addFinal(Persona p){

		Nodo nuevo = new Nodo(p);

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
	
	public int getTamanio(){
		return tamanio;
	}
	
	public Persona buscar(int posicion){
		if(posicion < this.tamanio){
			Nodo resultado = this.primero;
			for(int i = 0; i< posicion; i++)
				resultado = resultado.getSiguiente();
			return resultado.getValor();
		}
		return null;		
	}
	
	public Persona getPrimero(){
		return this.primero.getValor();
	}
	
	public Persona getUltimo(){
		return this.ultimo.getValor();
	}
	
	public boolean contains(Persona p){
		Nodo resultado = this.primero;
		while(resultado!=null){
			if(resultado.getValor().equals(p))
				return true;
			else
				resultado = resultado.getSiguiente();
		}
		return false;
	}
	
	public void deletePrincipio(){
		this.primero = this.primero.getSiguiente();
		this.tamanio--;
	}
}
