package practico2;
public class arreglo {

	private int[] arreglo;
	private int[] aux;
	private int cantidad;
 
 public arreglo(int size){
	 this.arreglo = new int[size];
	 this.cantidad = 0;
 }
 
 public boolean empty(){
		return this.cantidad == 0;
}
 
 public void add(int valor){

		this.arreglo[this.cantidad] = valor;
		this.cantidad++;
	}

 public int lenght(){
	return this.cantidad;
 }
 
 
 public boolean search(int needle)
 {
	 if (this.cantidad > 0){
		 for(int i = 0; i < this.lenght(); i++){
			 if(this.arreglo[i]==needle)
				 return true;
		 }
		 return false;
	 }
	 else
		 return false;
 }
 


 public void ordenarSeleccion()
 {
	for(int i=0; i<this.cantidad-1;i++)
	{
        for(int j=i+1; j<this.cantidad; j++)
        {
            if(this.arreglo[i] > this.arreglo[j])
            {
                int aux = this.arreglo[i];
                this.arreglo[i] = this.arreglo[j];
                this.arreglo[j] = aux;
            }
        }
    }
 }



 
 
public void quicksort(){
	quickSort(this.arreglo, 0, this.cantidad - 1);
} 

public void quickSort(int arr[], int low, int high){
	
	if(arr.length == 0){
		return;
	}
	if (low >= high){
		return;
	}
	int middle = low+(high-low) / 2;
	
	int pivot = arr[middle];
	
	int i = low;
	int j = high;
	
	while (i < j){
		while(arr[i] < pivot){
			i++;
		}
		while(arr[j] > pivot){
			j--;
		}
		if(i<=j){
			int aux = arr[i];
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