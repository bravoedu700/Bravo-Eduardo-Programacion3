package TPE;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

public class Main2{
	
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
	}

	public static void cargar(Lista myList, String csvFile){
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
                myList.addPrincipio(myPersona);
                long tiempoCiclo = (System.currentTimeMillis()-inicioCicloCarga);
            }
        	
        } catch (IOException e) {
            e.printStackTrace();
        }        
	}
	
	public static void alta(Lista myList, String path){
		int tmn = myList.getTamanio()+1;
		String resumen = "";
		long totalTiempo = 0;
		String csvFile = path + "dataset_insert_10000.csv";
        String line = "";
        String cvsSplitBy = ";";
        int ciclo = 0;
    	long peor = 0;
    	System.out.println("Resumen Alta: ------------------------------------------");
    	resumen += "Resumen Alta Lista agregando al principio: -";
    	resumen += "Tiempos de Altas: -";
    	resumen += "Ciclo Alta; tiempo -"; 
        try {
        	BufferedReader br = new BufferedReader(new FileReader(csvFile));   	
        	while ((line = br.readLine()) != null){	
        		long inicioAlta = System.currentTimeMillis();
                String[] items = line.split(cvsSplitBy);
                //System.out.println(items[0]);
                //creo la persona con sus gustos
                Persona myPersona = new Persona(5); 
                myPersona.setId(Integer.parseInt(items[0]));
                //agrego los gustos
                for(int y=1; y<6; y++){
                	myPersona.addGusto(items[y]);
                }
                myList.addPrincipio(myPersona);
                long tiempoCiclo = (System.currentTimeMillis()-inicioAlta);
                totalTiempo=totalTiempo+tiempoCiclo;
                resumen +=  ciclo + ";" + tiempoCiclo + " -";
                if(tiempoCiclo>peor)
                	peor=tiempoCiclo;
                ciclo++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
       
        System.out.println("Peor Alta: " + peor + " miliseg"); 
        System.out.println("Promedio Alta: " + (totalTiempo/ciclo) + " miliseg");
        System.out.println("Total Alta: "+getDurationBreakdown(totalTiempo));
        
        resumen += "Peor Alta: " + peor + " miliseg -";
        resumen += "Promedio Alta: " + (totalTiempo/ciclo) + " miliseg -";
        resumen += "Total Alta: "+getDurationBreakdown(totalTiempo) + "-";
        
        escribirArchivo(resumen,path + "salidaAltaListaPrincipio"+ tmn +".csv");
	}
	
	public static void buscar(Lista myList, String path){
		int tmn = myList.getTamanio()+1;
		String resumen = "";
		long totalTiempo = 0;
		String csvFile = path + "dataset_busqueda_10000.csv";
        String line = "";
        String cvsSplitBy = ";";
        int ciclo = 0;
    	long peor = 0;
    	String resultado;
    	System.out.println("Resumen Busqueda: --------------------------------------------");
    	resumen += "Resumen Busqueda Lista agregando al principio: -";
    	resumen += "Tiempos de Busquedas:-";
    	resumen += "Ciclo Busqueda; Tiempo; Resultado-";
        try {
        	BufferedReader br = new BufferedReader(new FileReader(csvFile));   	
        	while ((line = br.readLine()) != null){	
        		long inicioBusqueda = System.currentTimeMillis();
                String[] items = line.split(cvsSplitBy);
                //System.out.println(items[0]);
                //creo la persona con sus gustos
                Persona myPersona = new Persona(5); 
                myPersona.setId(Integer.parseInt(items[0]));
                //agrego los gustos
                for(int y=1; y<6; y++){
                	myPersona.addGusto(items[y]);
                }
                if(myList.contains(myPersona)){//pregunto si esta 	
                	resultado = "si";
                	}
                else{
                	resultado="no";
                }
                
                long tiempoCiclo = (System.currentTimeMillis()-inicioBusqueda);
                totalTiempo=totalTiempo+tiempoCiclo;
                resumen +=  ciclo + ";" + tiempoCiclo + ";" + resultado + "-";
                if(tiempoCiclo>peor)
                	peor=tiempoCiclo;
                ciclo++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        System.out.println("Peor Busqueda: " + peor + " miliseg"); 
        System.out.println("Promedio Busqueda: " + (totalTiempo/ciclo) + " miliseg");
        System.out.println("Total Busqueda: "+getDurationBreakdown(totalTiempo));
        
        resumen += "Peor Busqueda: " + peor + " miliseg -";
        resumen += "Promedio Busqueda: " + (totalTiempo/ciclo) + " miliseg -";
        resumen += "Total Busqueda: "+getDurationBreakdown(totalTiempo)+" -";
        
        escribirArchivo(resumen,path + "salidaBusquedaListaPrincipio" + tmn + ".csv");
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String path= JOptionPane.showInputDialog("Por favor ingrese la ruta de la carpeta, para leer y escribir");
		
		Lista mylista = new Lista();
		cargar(mylista,path + "dataset_500000.csv");
		alta(mylista,path);
		buscar(mylista,path);
		
		mylista = null;
		System.gc();
		mylista = new Lista();
		cargar(mylista,path + "dataset_1000000.csv");
		alta(mylista,path);
		buscar(mylista,path);
		
		mylista = null;
		System.gc();
		mylista = new Lista();
		cargar(mylista,path + "dataset_3000000.csv");
		alta(mylista,path);
		buscar(mylista,path);
	}

}
