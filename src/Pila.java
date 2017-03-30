
public class Pila {
	
	private Lista miPila;
	
	public Pila(){
		this.miPila = new Lista();
	}
	
	
	

	public int ver(){
		return this.miPila.getPrimero();		
	} 
	public void sacar(){
		this.miPila.deletePrincipio();
		
	} 
	public void poner(int i){
		this.miPila.addPrincipio(i);
	}

	public boolean vacia(){
		if(this.miPila.tamanio() == 0)
			return true;
		else
			return false;
	}
	

}
