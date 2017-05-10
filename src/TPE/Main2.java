package TPE;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Main2 {
	
	public static String getDurationBreakdown(long millis) {
	    if(millis < 0) {
	      throw new IllegalArgumentException("La duraci—n debe ser mayor que cero");
	    }
	    long minutes = TimeUnit.MILLISECONDS.toMinutes(millis);
	    millis -= TimeUnit.MINUTES.toMillis(minutes);
	    long seconds = TimeUnit.MILLISECONDS.toSeconds(millis);

	    String sb = new String();
	    sb=" Minutes: " + minutes +" Seconds: " + seconds;
	    return sb;
	}
	
	public static void escribirArchivo(String lineas[],String url){
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
        long finCarga = 0;
		long inicioCarga = System.currentTimeMillis();
		int ciclo = 0;
    	long peor = 0;
    	System.out.println("Resumen Carga en Lista al Principio: ---------------------------------------");    	
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
	
	public static void alta(Lista myList){
		String resumen[] = new String[4];
		long totalSum = 0;
		long inicioAlta = System.currentTimeMillis();
		//String csvFile = "D:/Usuarios/Edu/Descargas/datasets/dataset_insert_10000.csv";
		String csvFile = "/Users/fernandostoessel/Downloads/datasets/dataset_insert_10000.csv";
        String line = "";
        String cvsSplitBy = ";";
        int ciclo = 0;
    	long peor = 0;
    	System.out.println("Resumen Alta: ------------------------------------------");
    	resumen[0]="Resumen Alta Lista agregando al principio: -----------------------------";
        try {
        	BufferedReader br = new BufferedReader(new FileReader(csvFile));   	
        	while ((line = br.readLine()) != null){	
        		long inicioCicloAlta = System.currentTimeMillis();
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
                long tiempoCiclo = (System.currentTimeMillis()-inicioCicloAlta);
                //System.out.println("ciclo: " + ciclo + " tiempo" + tiempoCiclo);
                if(tiempoCiclo>peor)
                	peor=tiempoCiclo;
                ciclo++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        totalSum = (System.currentTimeMillis()-inicioAlta);
        System.out.println("Peor Alta: " + peor + " miliseg"); 
        System.out.println("Promedio Alta: " + (totalSum/ciclo) + " miliseg");
        System.out.println("Total Alta: "+getDurationBreakdown(totalSum));
        
        resumen[1]="Peor Alta: " + peor + " miliseg";
        resumen[2]="Promedio Alta: " + (totalSum/ciclo) + " miliseg";
        resumen[3]="Total Alta: "+getDurationBreakdown(totalSum);
        
        escribirArchivo(resumen,"/Users/fernandostoessel/Downloads/datasets/salidaAltaLista.csv");
	}
	
	public static void buscar(Lista myList){
		String resumen[] = new String[4];
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
    	resumen[0]="Resumen Busqueda Lista agregando al principio: -----------------------";
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
                if(myList.contains(myPersona)){//pregunto si esta 	
                	resultado = "si";
                	}
                else{
                	resultado="no";
                }
                System.out.println("ciclo: " + ciclo + " esta? " + resultado);
                long tiempoCiclo = (System.currentTimeMillis()-inicioCicloBusqueda);
                //System.out.println("ciclo: " + ciclo + " tiempo" + tiempoCiclo);
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
        
        resumen[1]="Peor Busqueda: " + peor + " miliseg";
        resumen[2]="Promedio Busqueda: " + (totalSum/ciclo) + " miliseg";
        resumen[3]="Total Busqueda: "+getDurationBreakdown(totalSum);
        
        escribirArchivo(resumen,"/Users/fernandostoessel/Downloads/datasets/salidaBusquedaLista.csv");
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Lista mylista = new Lista();
		cargar(mylista,"/Users/fernandostoessel/Downloads/datasets/dataset_500000.csv");
		alta(mylista);
		buscar(mylista);
	}

}
