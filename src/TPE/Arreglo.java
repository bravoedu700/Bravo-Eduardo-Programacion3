package TPE;

public class Arreglo {
	private Object[] arreglo;
	private int cantidad;
 
 public Arreglo(int size){
	 this.arreglo = new Object[size];
	 this.cantidad = 0;
 }
 
 public boolean empty(){
		return this.cantidad == 0;
}
 
 public void add(Object p){

		this.arreglo[this.cantidad] = p;
		this.cantidad++;
	}
 
 public int size(){return this.cantidad;}
 public Object get(int i){return arreglo[i];}
 
 public void redimencionar(int newsize){
	 
	 Object[] tempArray = new Object[newsize];
	 System.arraycopy(this.arreglo, 0, tempArray, 0, newsize);
	 this.arreglo=tempArray;
	 
 }
 
}
