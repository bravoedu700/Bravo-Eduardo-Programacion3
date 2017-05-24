package practico5;

import java.util.ArrayList;

public class main {

	
	
	public static void  ejercicio1(int valores[], int binarios[], int posicion ,  ArrayList<int[]> solucion, int aguja){
		
		// no es hoja
		if(posicion < valores.length){
			// poda	
			int suma = 0;
			for(int i=0; i<posicion; i++){
				if(binarios[i] == 1){
					suma = suma + valores[i];
				}
			}
			if(suma <= aguja){
				int nuevaposicion = posicion+1;
				binarios[posicion] = 0;	
				ejercicio1(valores, binarios,nuevaposicion,solucion,aguja);
				binarios[posicion] = 1;
				ejercicio1(valores, binarios,nuevaposicion,solucion,aguja);
			}
		}
		else
		{
			int suma = 0;
			for(int i=0; i<valores.length; i++){
				if(binarios[i] == 1){
					suma = suma + valores[i];

				}
			}
			if(suma == aguja){
				solucion.add(binarios);
				mostrarSolucion(valores,  binarios);
				System.out.println();				
			}

		}		
	}
	public static void cargarArreglo(int arreglo[]){
		
		for(int i=0; i<arreglo.length;i++){
			arreglo[i] = (int) (Math.random() * 100) + 1;
		}
		
	}
	
	public static void mostrarSolucion(int valores[], int binarios[]){
		
		for(int i=0; i<valores.length; i++){
			if(binarios[i] == 1){
				System.out.print(valores[i]);
				System.out.print("+");
			}
		}
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int binarios[] = new int[100];		

		ArrayList<int[]> solucion = new ArrayList<int[]>();
		
		int[] valores = new int[100];
		
		cargarArreglo(valores);
		
		int aguja = 50;
		
		int posicion=0;
		
		ejercicio1(valores, binarios,posicion,solucion,aguja);
		
	}

}
