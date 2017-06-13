package TPE;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

public class Main4 {
	
	public static void imprimir(Arreglo myArray){
		for(int u=0; u<myArray.size(); u++){
			System.out.println(myArray.get(u).toString());	
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		///Users/fernandostoessel/Downloads/datasets/
		
		Arreglo myArreglo = new Arreglo(10000);
		Arreglo myArregloBusqueda = new Arreglo(10000);
		String path= JOptionPane.showInputDialog("Por favor ingrese la ruta de la carpeta, para leer y escribir");
		Archivo escribir = new Archivo(path);
		String resultado;
		
		//cargo el arreglo de busqueda
		escribir.cargar(myArregloBusqueda, "dataset_busqueda_10000.csv");
		//ordeno el arreglo de busqueda
		myArregloBusqueda.ordenQuickSort();
		
		
		//pre-cargo arreglo con 500000
		escribir.cargar(myArreglo, "dataset_500000.csv");
		escribir.alta(myArreglo);
		myArreglo.ordenQuickSort();
		resultado = myArreglo.buscarOrdenado(myArregloBusqueda); 
		int tmn = myArreglo.size()+1;
		String url = "salidaBusquedaArregloOrdenado" + tmn + ".csv";
		escribir.escribirArchivo(resultado, url);
		
				
		//pre-cargo arreglo con 1000000	
		escribir.cargar(myArreglo, "dataset_1000000.csv");
		escribir.alta(myArreglo);
		myArreglo.ordenQuickSort();
		resultado = myArreglo.buscarOrdenado(myArregloBusqueda);
		tmn = myArreglo.size()+1;
		url = "salidaBusquedaArregloOrdenado" + tmn + ".csv";
		escribir.escribirArchivo(resultado, url);
		
		
		//pre-cargo arreglo con 3000000
		escribir.cargar(myArreglo, "dataset_3000000.csv");
		escribir.alta(myArreglo);
		myArreglo.ordenQuickSort();
		resultado = myArreglo.buscarOrdenado(myArregloBusqueda);
		tmn = myArreglo.size()+1;
		url = "salidaBusquedaArregloOrdenado" + tmn + ".csv";
		escribir.escribirArchivo(resultado, url);
		
	}

}
