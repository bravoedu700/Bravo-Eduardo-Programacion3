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
	 //pregunto si la cantidad de elementos del arreglos es menor al tamano
	 if(this.arreglo.length>this.cantidad){//si es menor lo agrego
		this.arreglo[this.cantidad] = p;
		this.cantidad++;
	 }
	 else{//si no lo redimensiono
		
		int newtm = this.arreglo.length*2;
		Object[] auxarreglo = new Object[newtm];
		for(int w=0;w<this.arreglo.length;w++){
			auxarreglo[w]=this.arreglo[w];
		}				
		this.arreglo = auxarreglo;
		this.arreglo[this.cantidad] = p;
		this.cantidad++;
	 }
	}
 
 public int size(){return this.cantidad;}
 
 public Object get(int i){return arreglo[i];}
 
 public boolean contains(Object o){
	 for(int y = 0; y<this.size(); y++){
		 if(this.get(y).equals(o))
			 return true;
	 }
	 return false;
 }
  
}
