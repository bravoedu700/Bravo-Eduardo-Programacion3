package Practico4;

import java.util.LinkedList; 
import java.util.List; 
  


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int peso = 23; 
        List<Objeto> listaObjetos= new LinkedList<Objeto>(); 
        listaObjetos.add(new Objeto(5,3,4)); 
        listaObjetos.add(new Objeto(3,5,2)); 
        listaObjetos.add(new Objeto(5,2,7)); 
        listaObjetos.add(new Objeto(1,8,4)); 
        listaObjetos.add(new Objeto(2,3,3)); 
        Mochila mochila = new Mochila(listaObjetos, peso); 
        System.out.println(mochila.toString()); 
        List<Objeto> solucion = mochila.resolver(); 
        System.out.println("Solución: \n " + solucion.toString() + " \n");
	}

}
