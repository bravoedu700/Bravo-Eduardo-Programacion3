package TPE;

//import javax.swing.*;
 
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Main1 {
	
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
		String lineas[] = new String[1200];
		
		lineas = resultado.split("|");
		
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
        long finCarga = 0;
		long inicioCarga = System.currentTimeMillis();
		int ciclo = 0;
    	long peor = 0;
    	System.out.println("Resumen Carga en Arreglo: ---------------------------------------");    	
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
                if(tiempoCiclo>peor)
                	peor=tiempoCiclo;
                ciclo++;
            }
        	
        } catch (IOException e) {
            e.printStackTrace();
        }
        finCarga = (System.currentTimeMillis()-inicioCarga);
        System.out.println("Peor Carga: " + peor + " miliseg"); 
        System.out.println("Promedio Alta: " + (finCarga/ciclo) + " miliseg");
        System.out.println("Total Alta: "+getDurationBreakdown(finCarga));
        
	}
		
	public static void alta(Arreglo myArray){
		String resumen = "";
		long totalSum = 0;
		//String csvFile = "D:/Usuarios/Edu/Descargas/datasets/dataset_insert_10000.csv";
		String csvFile = "/Users/fernandostoessel/Downloads/datasets/dataset_insert_10000.csv";
        String line = "";
        String cvsSplitBy = ";";
        
    	long peor = 0;
    	int ciclo = 0;
    	
    	System.out.println("Resumen Alta: ------------------------------------------");
    	resumen += "Resumen Alta: ------------------------------------------ |"; 
    	resumen += "Tiempos de Altas: ------------------------------------------ |";
    	resumen += "Ciclo Alta; tiempo |";
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
                totalSum=totalSum+tiempoCiclo;
                resumen +=  ciclo + ";" + tiempoCiclo + " |";
                if(tiempoCiclo>peor)
                	peor=tiempoCiclo;
                ciclo++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        System.out.println("Peor Alta: " + peor + " miliseg"); 
        System.out.println("Promedio Alta: " + (totalSum/ciclo) + " miliseg");
        System.out.println("Total Alta: "+getDurationBreakdown(totalSum));
        
        resumen += "Peor Alta: " + peor + " miliseg |";
        resumen += "Promedio Alta: " + (totalSum/ciclo) + " miliseg |";
        resumen += "Total Alta: "+getDurationBreakdown(totalSum)+" |";
        
        escribirArchivo(resumen,"/Users/fernandostoessel/Downloads/datasets/salidaAlta.csv");
	}
	
	public static void buscar(Arreglo myArray){
		String resumen = "";
		long totalSum = 0;
		long inicioBusqueda = System.currentTimeMillis();
		//String csvFile = "D:/Usuarios/Edu/Descargas/datasets/dataset_insert_10000.csv";
		String csvFile = "/Users/fernandostoessel/Downloads/datasets/dataset_busqueda_10000.csv";
        String line = "";
        String cvsSplitBy = ";";
        int ciclo = 0;
    	long peor = 0;
    	String resultado;
    	System.out.println("Resumen Busqueda: --------------------------------------------");
    	
    	resumen += "Resumen Busqueda: ------------------------------------------ |";
    	resumen += "Tiempos de Busquedas: ------------------------------------------ |";
    	
    	resumen += "Ciclo Busqueda; Riempo; Resultado |";
        
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
                System.out.println("ciclo: " + ciclo + " esta? " + resultado);
                long tiempoCiclo = (System.currentTimeMillis()-inicioCicloBusqueda);
                
                resumen +=  ciclo + ";" + tiempoCiclo + ";" + resultado + " |";
                
                
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
        
        resumen += "Peor Busqueda: " + peor + " miliseg |";
        resumen += "Promedio Busqueda: " + (totalSum/ciclo) + " miliseg |";
        resumen += "Total Busqueda: "+getDurationBreakdown(totalSum)+"|";
        
        escribirArchivo(resumen,"/Users/fernandostoessel/Downloads/datasets/salidaBusqueda.csv");
	}
	
	public static void main(String[] args){
		// TODO Auto-generated method stub
		Arreglo myArreglo = new Arreglo(10000);
		//pre-cargo arreglo con 500000
		//cargar(myArreglo,"D:/Usuarios/Edu/Descargas/datasets/dataset_500000.csv");
		cargar(myArreglo,"/Users/fernandostoessel/Downloads/datasets/dataset_500000.csv");
		alta(myArreglo);
		//buscar(myArreglo);
		//imprimir(myArreglo);	
	}

}
