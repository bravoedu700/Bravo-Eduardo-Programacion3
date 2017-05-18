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

	public static String getDurationBreakdown(long millis) {
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

	public static void escribirArchivo(String resultado,String url){
		
		String[] lineas = resultado.split("(?<=-)");
		
		BufferedWriter bw = null;
		try {
			File file = new File(url);
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file);
			bw = new BufferedWriter(fw);
 
			// Escribo archivo
			for(int g=0; g<lineas.length; g++){
				bw.write(lineas[g]);
				bw.newLine();
			}

		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			try {
				if (bw != null)
					bw.close();
			} catch (Exception ex) {
				System.out.println("Error cerrando el BufferedWriter" + ex);
			}
		}
		System.gc();
	}
	
	public static void cargar(Arreglo myArray, String csvFile){
        String line = "";
        String cvsSplitBy = ";";
          	
        try {
        	BufferedReader br = new BufferedReader(new FileReader(csvFile));
        	//quito el primer elemento ya que es la cabezera 
        	line = br.readLine();
        	while ((line = br.readLine()) != null){  
        		long inicioCicloCarga = System.currentTimeMillis();
                String[] items = line.split(cvsSplitBy);
                //creo la persona con sus gustos
                Persona myPersona = new Persona(5); 
                myPersona.setId(Integer.parseInt(items[0]));
                //agrego los gustos
                for(int y=1; y<6; y++){
                	myPersona.addGusto(items[y]);
                }
                //if(!myArray.contains(myPersona))//????pregunto por repetidos????	
                myArray.add(myPersona);
                long tiempoCiclo = (System.currentTimeMillis()-inicioCicloCarga);
               
            }
        	br=null;
        	System.gc();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public static void alta(Arreglo myArray,String path){
		int tmn = myArray.size()+1;
		String resumen = "";
		long totalTiempo = 0;
		
		String csvFile = path + "dataset_insert_10000.csv";
        String line = "";
        String cvsSplitBy = ";";
        
    	long peor = 0;
    	int ciclo = 0;
    	
    	System.out.println("Resumen Alta Ordenado: ------------------------------------------");
    	resumen += "Resumen Alta Ordenado: -"; 
    	resumen += "Tiempos de Altas Ordenado: -";
    	resumen += "Ciclo Alta; tiempo -"; 
        try {
        	BufferedReader br = new BufferedReader(new FileReader(csvFile));   	       	
        	while ((line = br.readLine()) != null){	
        		long inicioCicloAlta = System.currentTimeMillis();
                String[] items = line.split(cvsSplitBy);
                //creo la persona con sus gustos
                Persona myPersona = new Persona(5); 
                myPersona.setId(Integer.parseInt(items[0]));
                //agrego los gustos
                for(int y=1; y<6; y++){
                	myPersona.addGusto(items[y]);
                }
                myArray.add(myPersona);
                long tiempoCiclo = (System.currentTimeMillis()-inicioCicloAlta);
                totalTiempo=totalTiempo+tiempoCiclo;
                resumen +=  ciclo + ";" + tiempoCiclo + " -";
                if(tiempoCiclo>peor)
                	peor=tiempoCiclo;
                ciclo++;
            }
        	br=null;
        	System.gc();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        System.out.println("Total Tiempo Ordenado: " + totalTiempo + " miliseg");
        System.out.println("Peor Alta Ordenado: " + peor + " miliseg");
        System.out.println("Promedio Alta Ordenado: " + (totalTiempo/ciclo) + " miliseg");
        System.out.println("Total Alta Ordenado: "+getDurationBreakdown(totalTiempo));
        
        resumen += "Peor Alta Ordenado: " + peor + " miliseg -";
        resumen += "Promedio Alta Ordenado: " + (totalTiempo/ciclo) + " miliseg -";
        resumen += "Total Alta Ordenado: "+getDurationBreakdown(totalTiempo)+" -";
        
        escribirArchivo(resumen,path + "salidaAltaArregloOrdenado" + tmn + ".csv");
	}
	
	public static void buscarOrdenado(Arreglo myArray, Arreglo mySearch, String path){
		int tmn = myArray.size()+1;
		String resumen = "";
		long totalSum = 0;
		long inicioBusqueda = System.currentTimeMillis();
		
        int ciclo = 0;
    	long peor = 0;
    	String resultado;
    	System.out.println("Resumen Busqueda: --------------------------------------------");
    	
    	resumen += "Resumen Busqueda Ordenada:-";
    	resumen += "Tiempos de Busquedas Ordenadas:-";
    	resumen += "Ciclo Busqueda Ordenada; Tiempo; Resultado-";
        
    	int desde = 0;
    	int hasta = myArray.size();
        while(ciclo<mySearch.size()){	
        	long inicioCicloBusqueda = System.currentTimeMillis();
        	int pos = myArray.containsRango(mySearch.get(ciclo), desde, hasta);
            if(pos > 0){//pregunto si esta 	
               	resultado = "si";
               	desde=pos;
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
        System.out.println("Peor Busqueda Ordenada: " + peor + " miliseg"); 
        System.out.println("Promedio Busqueda Ordenada: " + (totalSum/ciclo) + " miliseg");
        System.out.println("Total Busqueda Ordenada: "+getDurationBreakdown(totalSum));
        
        resumen += "Peor Busqueda Ordenada: " + peor + " miliseg -";
        resumen += "Promedio Busqueda Ordenada: " + (totalSum/ciclo) + " miliseg -";
        resumen += "Total Busqueda Oredenada: "+getDurationBreakdown(totalSum)+" -";
        
        escribirArchivo(resumen,path + "salidaBusquedaArregloOrdenado" + tmn + ".csv");
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		///Users/fernandostoessel/Downloads/datasets/
		Arreglo myArreglo = new Arreglo(10000);
		Arreglo myArregloBusqueda = new Arreglo(10000);
		String path= JOptionPane.showInputDialog("Por favor ingrese la ruta de la carpeta, para leer y escribir");

		//cargo el arreglo de busqueda
		cargar(myArregloBusqueda, path + "dataset_busqueda_10000.csv");
		//ordeno el arreglo de busqueda
		myArregloBusqueda.ordenQuickSort();
		
		//pre-cargo arreglo con 500000
		cargar(myArreglo,path + "dataset_500000.csv");
		alta(myArreglo,path);
		myArreglo.ordenQuickSort();
		buscarOrdenado(myArreglo, myArregloBusqueda, path); 
				
		//pre-cargo arreglo con 1000000
		myArreglo = null;
		System.gc();
		myArreglo = new Arreglo(10000);
		cargar(myArreglo,path + "dataset_1000000.csv");
		alta(myArreglo,path);
		myArreglo.ordenQuickSort();
		buscarOrdenado(myArreglo, myArregloBusqueda, path); 
		
		//pre-cargo arreglo con 3000000
		myArreglo = null;
		System.gc();
		myArreglo = new Arreglo(10000);
		cargar(myArreglo,path + "dataset_3000000.csv");
		alta(myArreglo,path);
		myArreglo.ordenQuickSort();
		buscarOrdenado(myArreglo, myArregloBusqueda, path); 
		
	}

}
