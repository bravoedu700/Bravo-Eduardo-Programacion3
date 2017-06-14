package TPE3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

public class Main {

	public static void MostrarLista(ArrayList<Nodo> listado){
		Iterator <Nodo> i = listado.iterator();
		System.out.println("Lista:");
		while(i.hasNext()){
			System.out.println(i.next().toString());
		}
		System.out.println("");

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String path= JOptionPane.showInputDialog("Por favor ingrese la ruta de la carpeta, para leer y escribir");
		Archivo miArchivo = new Archivo(path);

		GrafoGustos g = new GrafoGustos();

		miArchivo.cargar(g, "dataset_500000.csv");

		MostrarLista(g.personasGustoComun(g.usuarioAlAzar()));
		
		System.out.println(g.gustoMasGustado().toString());
		
		System.out.println(g.usuarioMasLejano(g.usuarioAlAzar()).toString());
		
	
	}

}
