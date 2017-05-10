package TPE;

//import javax.swing.*;
 
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Main {
	
	public static String getDurationBreakdown(long millis) {
	    if(millis < 0) {
	      throw new IllegalArgumentException("Duration must be greater than zero!");
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

	public static void cargar(Arreglo myArray, String csvFile){
	
        String line = "";
        String cvsSplitBy = ";";

        try {
        	BufferedReader br = new BufferedReader(new FileReader(csvFile));
        	//quito el primer elemento ya que es la cabezera 
        	line = br.readLine();
        	while ((line = br.readLine()) != null) {        		
                String[] items = line.split(cvsSplitBy);
                //System.out.println(items[0]);
                //creo la persona con sus gustos
                Persona myPersona = new Persona(5); 
                myPersona.setId(Integer.parseInt(items[0]));
                //agrego los gustos
                for(int y=1; y<6; y++){
                	myPersona.addGusto(items[y]);
                }
                //if(!myArray.contains(myPersona))//????pregunto por repetidos????	
                myArray.add(myPersona);
                                
            }
        	
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public static void imprimir(Arreglo myArray){
		for(int u=0; u<myArray.size(); u++){
			System.out.println(myArray.get(u).toString());	
		}
	}
	
	public static void alta(Arreglo myArray){
		long totalSum = 0;
		
		long inicioAlta = System.currentTimeMillis();
		
		String csvFile = "D:/Usuarios/Edu/Descargas/datasets/dataset_insert_10000.csv";
        String line = "";
        String cvsSplitBy = ";";
        
        int ciclo = 0;
    	long peor = 0;
    	
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
                if(!myArray.contains(myPersona))//pregunto por si ya no esta agregado	
                	myArray.add(myPersona);
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
        System.out.println("peor: " + peor + " miliseg"); 
        System.out.println("promedio: " + (totalSum/ciclo) + " miliseg");
        System.out.println("total: "+getDurationBreakdown(totalSum));
        
	}
	
	public static void buscar(Arreglo myArray){
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Arreglo myArreglo = new Arreglo(10000);
		//pre-cargo arreglo con 500000
		cargar(myArreglo,"D:/Usuarios/Edu/Descargas/datasets/dataset_500000.csv");		
		alta(myArreglo);
		buscar(myArreglo);
		
		
		//imprimir(myArreglo);
	}

}
