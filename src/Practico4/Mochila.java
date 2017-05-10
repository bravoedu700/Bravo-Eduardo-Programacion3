package Practico4;

import java.util.Collections; 
import java.util.LinkedList; 
import java.util.List; 
 
public class Mochila { 
    private List<Objeto> listaObjetos; 
    private int pesoMaximo; 
     
    public Mochila(){ 
        listaObjetos = new LinkedList<Objeto>(); 
        pesoMaximo = 0; 
    } 
     
    public Mochila(List<Objeto> listaObjetos, int pesoMaximo){ 
        this.listaObjetos = new LinkedList<Objeto>(listaObjetos);  
        this.pesoMaximo = pesoMaximo; 
    } 
     
    public List<Objeto> resolver(){ 
        List<Objeto> res  = new LinkedList<Objeto>(); 
        Collections.sort(listaObjetos); 
//        System.out.println(listaObjetos); 
        int totalPeso = 0; 
        while(!listaObjetos.isEmpty() && totalPeso < pesoMaximo){ 
            Objeto o = listaObjetos.get(0); 
            if((totalPeso + o.peso <= pesoMaximo)&&(o.cantidad>0)){ 
                res.add(o); 
                o.cantidad--;
                listaObjetos.remove(0);
                totalPeso += o.peso; 
            }else{ 
                listaObjetos.remove(0); 
            } 
        } 
         
        return res; 
    } 
     
    public String toString(){ 
        String res = "Peso máximo: " + pesoMaximo + "\nLista de objetos: "; 
        res += listaObjetos.toString(); 
         
        return res; 
    } 
} 