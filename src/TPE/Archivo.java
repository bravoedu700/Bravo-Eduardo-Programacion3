package TPE;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Archivo {
	
	private String path;
	
	public Archivo(String path){
		this.path = path;		
	}
	
	public void setPantalla(String msj){
		System.out.println(msj);
	}
	
	public String getDurationBreakdown(long millis) {
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
	
	public void cargar(Arreglo myArray, String csvFile){
		String line = "";
        String cvsSplitBy = ";";
          	
        try {
        	BufferedReader br = new BufferedReader(new FileReader(this.path+csvFile));
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
	
	public void escribirArchivo(String resultado, String url){
		String[] lineas = resultado.split("(?<=-)");
		BufferedWriter bw = null;
		try {
			File file = new File(this.path+url);
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
	
	public void alta(Arreglo myArray){
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
        resumen += "Total Alta Ordenado: "+this.getDurationBreakdown(totalTiempo)+" -";
        
        this.escribirArchivo(resumen, "salidaAltaArregloOrdenado" + tmn + ".csv");
	}
	
}
