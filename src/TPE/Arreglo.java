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
 
 public int containsRango(Persona o, int desde, int hasta){//funcion que me devuelve la posicion donde se encuentra en el asrreglo
	 for(int y = desde; y<hasta; y++){
		 if(this.get(y).equals(o))
			 return y;
	 }
	 return -1;
 }
 
 public void ordenQuickSort(){
		quickSort(this.arreglo, 0, this.cantidad - 1);
	} 
 
 public void quickSort(Persona arr[], int low, int high){
		if(arr.length == 0){
			return;
		}
		if (low >= high){
			return;
		}
		int middle = low+(high-low) / 2;
		Persona pivot = arr[middle];
		
		int i = low;
		int j = high;
		
		while (i < j){
			while(arr[i].getId() < pivot.getId()){
				i++;
			}
			while(arr[j].getId() > pivot.getId()){
				j--;
			}
			if(i<=j){
				Persona aux = arr[i];
				arr[i] = arr[j];
				arr[j] = aux;
				i++;
				j--;
			}
		}
		if(low < j){
			quickSort(arr, low, j);
		}
		if(high > i){
			quickSort(arr, i, high);
		}	
	}
  
}
