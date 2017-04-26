package Practico4;

import java.util.Collections; 
import java.util.LinkedList; 
import java.util.List; 
 
public class Mochila { 
    private List<Objeto> listaObjetos; 
    private int pesoMáximo; 
     
    public Mochila(){ 
        listaObjetos = new LinkedList<Objeto>(); 
        pesoMáximo = 0; 
    } 
     
    public Mochila(List<Objeto> listaObjetos, int pesoMáximo){ 
        this.listaObjetos = new LinkedList<Objeto>(listaObjetos);  
        this.pesoMáximo = pesoMáximo; 
    } 
     
    public List<Objeto> resolver(){ 
        List<Objeto> res  = new LinkedList<Objeto>(); 
        Collections.sort(listaObjetos); 
//        System.out.println(listaObjetos); 
        int totalPeso = 0; 
        while(!listaObjetos.isEmpty() && totalPeso < pesoMáximo){ 
            Objeto o = listaObjetos.get(0); 
            if((totalPeso + o.peso <= pesoMáximo)&&(o.cantidad>0)){ 
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
        String res = "Peso máximo: " + pesoMáximo + "\nLista de objetos: "; 
        res += listaObjetos.toString(); 
         
        return res; 
    } 
} 