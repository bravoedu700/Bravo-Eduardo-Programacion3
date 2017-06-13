package TPE;

import java.util.concurrent.TimeUnit;

public class Arreglo {
	private Persona[] arreglo ;
	private int cantidad;
	private boolean ordenado = false;
 
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
 
 public void ordenQuickSort(){
	quickSort(this.arreglo, 0, this.cantidad - 1);
	this.setOrdenado(true);	
	} 
 
 public void quickSort(Persona arr[], int low, int high){
	if(arr.length == 0){return;}
	if (low >= high){return;}
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
	if(low < j){quickSort(arr, low, j);}
	if(high > i){quickSort(arr, i, high);}
	
	}
 
 public String getDurationBreakdown(long millis) {
	    if(millis < 0) {
	      throw new IllegalArgumentException("La duraci—n debe ser mayor que cero");
	    }
	    
	    long minutes = TimeUnit.MILLISECONDS.toMinutes(millis);
	    millis -= TimeUnit.MINUTES.toMillis(minutes);
	    long seconds = TimeUnit.MILLISECONDS.toSeconds(millis);
	    millis -= TimeUnit.SECONDS.toMillis(seconds);
	    
	    String sb = new String();
	    sb=" Minutes: " + minutes +" Seconds: " + seconds + " Milisec: " + millis;
	    return sb;
	}
 
 public String buscarOrdenado(Arreglo mySearch){
	
	if(!this.isOrdenado()){
		this.ordenQuickSort();
	}
	
	String resumen = "";
	long totalSum = 0;
	long inicioBusqueda = System.currentTimeMillis();
	int ciclo = 0;
	long peor = 0;
	String resultado;
 		
	resumen += "Resumen Busqueda Ordenada:-";
	resumen += "Tiempos de Busquedas Ordenadas:-";
	resumen += "Ciclo Busqueda Ordenada; Tiempo; Resultado-";
    
	int desde = 0;
	int hasta = this.size();
	while(ciclo<mySearch.size()){	
		long inicioCicloBusqueda = System.currentTimeMillis();
		int pos = this.busquedaBinaria(mySearch.get(ciclo), desde, hasta);
		if(pos > 0){//pregunto si esta 	
			resultado = "si";
           	desde=pos; //empieso a buscar desde aca ya que si los arreglos estan ordenados
           	}
		else{
           	resultado="no";
		}
        
		long tiempoCiclo = (System.currentTimeMillis()-inicioCicloBusqueda);
		resumen +=  ciclo + ";" + tiempoCiclo + ";" + resultado + "-";
		if(tiempoCiclo>peor)
			peor=tiempoCiclo;
		ciclo++;
	}
	  
    totalSum = (System.currentTimeMillis()-inicioBusqueda);
    //System.out.println("Peor Busqueda Ordenada: " + peor + " miliseg"); 
    //System.out.println("Promedio Busqueda Ordenada: " + (totalSum/ciclo) + " miliseg");
    //System.out.println("Total Busqueda Ordenada: "+this.getDurationBreakdown(totalSum));
     
    resumen += "Peor Busqueda Ordenada: " + peor + " miliseg -";
    resumen += "Promedio Busqueda Ordenada: " + (totalSum/ciclo) + " miliseg -";
    resumen += "Total Busqueda Oredenada: "+this.getDurationBreakdown(totalSum)+" -";
    
    return resumen;
}

public boolean isOrdenado() {
	return ordenado;
}

public void setOrdenado(boolean ordenado) {
	this.ordenado = ordenado;
}
 
public int busquedaBinaria(Persona o, int inf, int sup){
	int centro; 
	while(inf<=sup){ 
		centro=(sup+inf)/2; 
		if(this.get(centro).compareTo(o)==0) return centro; 
		else 
			if(this.get(centro).compareTo(o) == 1 )
				{ sup=centro-1; } 
			else 
				{ inf=centro+1; } 
	} 
	return -1; 
}

}
