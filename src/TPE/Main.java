package TPE;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.SortedSet;
import java.util.TreeSet;

import javax.swing.*;

public class Main {

	public static void cargar(Arreglo myArray){
		 
		JFrame frame = new JFrame("InputDialog Example #1");

	    // prompt the user to enter their name
	    String name = JOptionPane.showInputDialog(frame, "What's your name?");

	    // get the user's input. note that if they press Cancel, 'name' will be null
	    System.out.printf("The user's name is '%s'.\n", name);
	    System.exit(0);
	    
		 
		String csvFile = "/Users/fernandostoessel/Documents/facu/Bravo-Eduardo-Programacion3/src/DatosCSV/dataset_500000.csv";
        String line = "";
        String cvsSplitBy = ";";

        try {
        	BufferedReader br = new BufferedReader(new FileReader(csvFile));
        	//quito el primer elemento ya que es la cabezera 
        	line = br.readLine();
        	while ((line = br.readLine()) != null) {

                String[] items = line.split(cvsSplitBy);
                //creo la persona con sus gustos
                Persona myPersona = new Persona(5); 
                myPersona.setId(Integer.parseInt(items[0]));
                //agrego los gustos
                for(int y=1; y<6; y++){
                	myPersona.addGusto(items[y]);
                }
                if(myArray.size()==9999)
                	myArray.redimencionar(10000);
                	
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
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Arreglo myArreglo = new Arreglo(10000);
		cargar(myArreglo);
		imprimir(myArreglo);
	}

}
