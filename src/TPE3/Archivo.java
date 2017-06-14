package TPE3;

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
		
	public void cargar(GrafoGustos g, String csvFile){
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
                Nodo n1 = g.addUsuario(items[0]);
                for(int y=1; y<6; y++){    	
                	Nodo n2 = g.addGusto(items[y]);
                	g.insertarArista(n1, n2);
                }
                long tiempoCiclo = (System.currentTimeMillis()-inicioCicloCarga);               
            }
        	br=null;
        	System.gc();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}
