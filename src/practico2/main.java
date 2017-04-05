package practico2;
public class main {
	
	public static void cargar(arreglo miArray, int n){
		/*  carga el arreglo con valores aleatorios hasta n */
		for (int i = 0; i < n; i++) 
		{
			int nuevo = (int) Math.floor(Math.random()*100);
			miArray.add(nuevo);
		}
	}

	
	public static void main( String[] args) {
		
		for(int n = 1; n < 10000; n+=500){
			arreglo miArray = new arreglo(n);
			cargar(miArray,n);					
			int avg = 0;
			long inicio, fin;	
			for (int i = 0; i < 20; i++) {
				inicio = System.nanoTime();
				miArray.ordenarSeleccion();
				fin = System.nanoTime();
				avg += ((fin - inicio) / 1000.0);
			}			
			System.out.println("Ordenar seleccion con " + n + " elementos:	Promedio de tiempo " + (avg / 20) +" milliseconds");		
		}
		

		System.out.println("");
		System.out.println("Notamos que al aumentar el tamaño de los datos procesados el tiempo crede de forma cuadratica, lo que denota una complejidad O(n2)");
		System.out.println("");

		for(int n = 1; n < 10000; n+=500){
			arreglo miArray = new arreglo(n);
			cargar(miArray,n);					
			int avg = 0;
			long inicio, fin;	
			for (int i = 0; i < 1000; i++) {
				inicio = System.nanoTime();
				miArray.quicksort();
				fin = System.nanoTime();
				avg += ((fin - inicio) / 1000.0);
			}
			System.out.println("Ordenar quick con " + n + " elementos:	Promedio de tiempo " + (avg / 1000) +" milliseconds");		
		}
		
		System.out.println("Notamos que se muestra un comportamiento mas 'natural' practicamente el doble de tiempo para el doble de datos procesados. n log(n)");
		
	}

}