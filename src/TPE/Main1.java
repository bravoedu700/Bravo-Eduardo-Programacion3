package TPE;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Main1{
	
	public static void imprimir(Arreglo myArray){
		for(int u=0; u<myArray.size(); u++){
			System.out.println(myArray.get(u).toString());	
		}
	}
	
	public static String getDurationBreakdown(long millis) {
	    if(millis < 0) {
	      throw new IllegalArgumentException("La duraci—n debe ser mayor que cero");
	    }
	    //long days = TimeUnit.MILLISECONDS.toDays(millis);
	    //millis -= TimeUnit.DAYS.toMillis(days);
	    //long hours = TimeUnit.MILLISECONDS.toHours(millis);
	    //millis -= TimeUnit.HOURS.toMillis(hours);
	    long minutes = TimeUnit.MILLISECONDS.toMinutes(millis);
	    millis -= TimeUnit.MINUTES.toMillis(minutes);
	    long seconds = TimeUnit.MILLISECONDS.toSeconds(millis);

	    String sb = new String();
	    sb=" Minutes: " + minutes +" Seconds: " + seconds;
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
    	
    	System.out.println("Resumen Alta: ------------------------------------------");
    	resumen += "Resumen Alta: -"; 
    	resumen += "Tiempos de Altas: -";
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
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        System.out.println("Total Tiempo: " + totalTiempo + " miliseg");
        System.out.println("Peor Alta: " + peor + " miliseg");
        System.out.println("Promedio Alta: " + (totalTiempo/ciclo) + " miliseg");
        System.out.println("Total Alta: "+getDurationBreakdown(totalTiempo));
        
        resumen += "Peor Alta: " + peor + " miliseg -";
        resumen += "Promedio Alta: " + (totalTiempo/ciclo) + " miliseg -";
        resumen += "Total Alta: "+getDurationBreakdown(totalTiempo)+" -";
        
        escribirArchivo(resumen,path + "salidaAlta" + tmn + ".csv");
	}
	
	public static void buscar(Arreglo myArray, String path){
		int tmn = myArray.size()+1;
		String resumen = "";
		long totalSum = 0;
		long inicioBusqueda = System.currentTimeMillis();
		String csvFile = path + "dataset_busqueda_10000.csv";
        String line = "";
        String cvsSplitBy = ";";
        int ciclo = 0;
    	long peor = 0;
    	String resultado;
    	System.out.println("Resumen Busqueda: --------------------------------------------");
    	
    	resumen += "Resumen Busqueda:-";
    	resumen += "Tiempos de Busquedas:-";
    	resumen += "Ciclo Busqueda; Tiempo; Resultado-";
        
    	try {
        	BufferedReader br = new BufferedReader(new FileReader(csvFile));   	
        	while ((line = br.readLine()) != null){	
        		long inicioCicloBusqueda = System.currentTimeMillis();
                String[] items = line.split(cvsSplitBy);
                //System.out.println(items[0]);
                //creo la persona con sus gustos
                Persona myPersona = new Persona(5); 
                myPersona.setId(Integer.parseInt(items[0]));
                //agrego los gustos
                for(int y=1; y<6; y++){
                	myPersona.addGusto(items[y]);
                }
                if(myArray.contains(myPersona)){//pregunto si esta 	
                	resultado = "si";
                	}
                else{
                	resultado="no";
                }
                //System.out.println("ciclo: " + ciclo + " esta? " + resultado + "-");
                long tiempoCiclo = (System.currentTimeMillis()-inicioCicloBusqueda);
                resumen +=  ciclo + ";" + tiempoCiclo + ";" + resultado + "-";
                if(tiempoCiclo>peor)
                	peor=tiempoCiclo;
                ciclo++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        totalSum = (System.currentTimeMillis()-inicioBusqueda);
        System.out.println("Peor Busqueda: " + peor + " miliseg"); 
        System.out.println("Promedio Busqueda: " + (totalSum/ciclo) + " miliseg");
        System.out.println("Total Busqueda: "+getDurationBreakdown(totalSum));
        
        resumen += "Peor Busqueda: " + peor + " miliseg -";
        resumen += "Promedio Busqueda: " + (totalSum/ciclo) + " miliseg -";
        resumen += "Total Busqueda: "+getDurationBreakdown(totalSum)+" -";
        
        escribirArchivo(resumen,path + "salidaBusqueda" + tmn + ".csv");
	}
	
	public static void main(String[] args){
		// TODO Auto-generated method stub
		Arreglo myArreglo = new Arreglo(10000);
		String path = "D:/Usuarios/Edu/Descargas/datasets/";
		//String path = "/Users/fernandostoessel/Downloads/datasets/";
		
		//pre-cargo arreglo con 500000
		cargar(myArreglo,path + "dataset_500000.csv");
		alta(myArreglo,path);
		buscar(myArreglo,path);
		
		//pre-cargo arreglo con 1000000
		myArreglo = new Arreglo(10000);
		cargar(myArreglo,path + "dataset_1000000.csv");
		alta(myArreglo,path);
		buscar(myArreglo,path);
		
		//pre-cargo arreglo con 3000000
		myArreglo = new Arreglo(10000);
		cargar(myArreglo,path + "dataset_3000000.csv");
		alta(myArreglo,path);
		buscar(myArreglo,path);
		
		
	}

}
