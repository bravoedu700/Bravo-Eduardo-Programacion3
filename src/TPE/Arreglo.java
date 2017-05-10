package TPE;

public class Arreglo {
	private Persona[] arreglo ;
	private int cantidad;
 
 public Arreglo(int size){
	 this.arreglo = new Persona[size];
	 this.cantidad = 0;
 }
 
 public boolean empty(){
		return this.cantidad == 0;
}
 
 public void add(Persona p){
	 //pregunto si la cantidad de elementos del arreglos es menor al tamano
	 if(this.arreglo.length>this.cantidad){//si es menor lo agrego
		this.arreglo[this.cantidad] = p;
		this.cantidad++;
	 }
	 else{//si no lo redimensiono
		
		int newtm = this.arreglo.length*2;
		Persona[] auxarreglo = new Persona[newtm];
		for(int w=0;w<this.arreglo.length;w++){
			auxarreglo[w]=this.arreglo[w];
		}				
		this.arreglo = auxarreglo;
		this.arreglo[this.cantidad] = p;
		this.cantidad++;
	 }
	}
 
 public int size(){return this.cantidad;}
 
 public Persona get(int i){return arreglo[i];}
 
 public boolean contains(Persona o){
	 for(int y = 0; y<this.size(); y++){
		 if(this.get(y).equals(o))
			 return true;
	 }
	 return false;
 }
  
}
