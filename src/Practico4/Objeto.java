package Practico4;

public class Objeto implements Comparable<Objeto>{ 
    int peso; 
    int valor; 
    int cantidad;
    double ratio; 
     
    public Objeto(){ 
        peso = 0; 
        valor = 0; 
        cantidad = 0;
        ratio = -1; 
    } 
     
    public Objeto(int peso, int valor, int cantidad){ 
        this.peso = peso; 
        this.valor = valor; 
        this.cantidad = cantidad;
        if(peso > 0){ 
            ratio = this.valor / this.peso; 
        }else{ 
            ratio = -1; 
        } 
    } 
 
    public int compareTo(Objeto o) { 
        int res = 0; 
        if((this.ratio > o.ratio) || 
            ((this.ratio == o.ratio) && (this.peso < o.peso))){ 
            res = -1; 
        }else if((this.ratio < o.ratio) || 
                ((this.ratio == o.ratio) && (this.valor > o.valor)) || 
                ((this.ratio == o.ratio) && (this.valor == o.valor) && 
                        (this.peso < o.peso))){ 
            res = 1; 
        } 
        return res; 
    } 
     
    public String toString(){ 
        return "( Peso: " + peso + " , Valor: " + valor + " , Cantidad: " + cantidad + ", Relacion Peso-Valor:" + ratio + ")\n"; 
    } 
} 