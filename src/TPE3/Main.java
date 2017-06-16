package TPE3;

import java.util.ArrayList;
import java.util.Iterator;


import javax.swing.JOptionPane;

public class Main {

	public static void MostrarLista(ArrayList<Nodo> listado){
		if(listado.size()>0){
			Iterator <Nodo> i = listado.iterator();		
			System.out.println("Lista:");
			while(i.hasNext()){
				System.out.println(i.next().toString());
			}
			System.out.println("");
		}
		else{
			System.out.println("Sin USUARIOS con los mismos gustos");
		}
	}

	public static void main(String[] args){
		// TODO Auto-generated method stub  71095014
		// /Users/fernandostoessel/Downloads/datasets/
		String path= JOptionPane.showInputDialog("Por favor ingrese la ruta de la carpeta, para leer y escribir");
		//String path="/Users/fernandostoessel/Downloads/datasets/";
		
		if(path!=null){
			Archivo miArchivo = new Archivo(path);
			GrafoGustos g = new GrafoGustos();
						
			System.out.println("Inicio de Carga del Grafo: ------------------------------------------");
			miArchivo.cargar(g, "dataset_500000.csv");
			System.out.println("Fin Carga del Grafo: ------------------------------------------ \n");
	
			Nodo usuario=g.usuarioAlAzar();
			System.out.println(usuario.toString()+" utilizado para las pruebas \n");
			
			System.out.println("Inicio buscar personas con mismos gustos: ------------------------------------------");
			MostrarLista(g.personasGustoComun(usuario));
			System.out.println("Fin buscar personas con mismos gustos: ------------------------------------------ \n");
			
			System.out.println("Inicio buscar gusto mas gustado: ------------------------------------------");
			System.out.println(g.gustoMasGustado().toString());
			System.out.println("Fin buscar gusto mas gustado: ------------------------------------------ \n");
			
			System.out.println("Inicio busqueda del usuario mas lejano: ------------------------------------------");
			System.out.println(g.usuarioMasLejano(usuario).toString());
			System.out.println("Fin busqueda del usuario mas lejano: ------------------------------------------ \n");
		}
	}

}
