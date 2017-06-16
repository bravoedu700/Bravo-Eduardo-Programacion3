package TPE3;

import java.util.Iterator;
import java.util.ArrayList;

public class GrafoGustos extends Grafo {

		
    public Nodo addUsuario(String idUsuario) {
    	Nodo usuario = new Nodo(idUsuario, 1);
    	if (!this.getNodos().contains(usuario)){
            this.addNodo(usuario);
        }
    	return usuario;
    }

    public Nodo addGusto(String idGusto) {
    	Nodo gusto = new Nodo(idGusto, 2);
    	//System.out.println("gusto a agregar:" + gusto.getValor()+ " tipo:" + gusto.getTipo());
    	boolean noEsta = true;
    	for(int h=0; h < this.getNodos().size(); h++){
    		if(this.getNodos().get(h).equals(gusto)){
    			noEsta=false;
    			gusto = this.getNodos().get(h);
    			h = this.getNodos().size();
    		}
    	}   	
    	if(noEsta)
           this.addNodo(gusto);
       
    	return gusto;
    	
    }

    public void insertarArista(Nodo n1, Nodo n2) {
    	if(n1.getTipo() != n2.getTipo()){
    		if(!this.getNodos().contains(n1)) 
    			this.addNodo(n1);
    		if(!this.getNodos().contains(n2)) 
    			this.addNodo(n2);    		
			n1.addVecino(n2);
			n2.addVecino(n1);
    	}
     }
    
    public boolean existeGusto(String idGusto) {
    	Nodo gusto = new Nodo(idGusto, 2);
    	return this.getNodos().contains(gusto);
    }

    public boolean existeUsuario(String idUsuario) {
    	Nodo usuario = new Nodo(idUsuario, 1);
    	return this.getNodos().contains(usuario);
    }
    
    public boolean esGusto(Nodo n){
    	return (n.getTipo() == 2);
    }

    public boolean esUsuario(Nodo n){
    	return (n.getTipo() == 1);
    }
    
    public ArrayList<Nodo> personasGustoComun(Nodo usuario){   	
        ArrayList<Nodo> personas = new ArrayList<Nodo>();
    	int cantidadIguales = 0;
    	for(int g=0; g<this.getNodos().size();g++){
    		if(( !this.getNodos().get(g).equals(usuario) ) && (this.getNodos().get(g).getTipo()==1)){
    			//System.out.println(this.getNodos().get(g)+"\n");
    			for(int r=0; r < usuario.getVecinos().size(); r++){
    				if(usuario.getVecinos().get(r).containsVecino(this.getNodos().get(g))){
						cantidadIguales++;
					}
    			}
    		if(cantidadIguales >= 2)
    			personas.add(this.getNodos().get(g));
    		}
    	}
        return personas;
    }
    
    
    public Nodo gustoMasGustado(){
    	Nodo masGustado = null;
    	int cantidad = 0;
    	Iterator<Nodo> e = this.getNodos().iterator();
    	while(e.hasNext()){
			Nodo n = e.next();
			if(esGusto(n)){
				if(n.getVecinos().size() > cantidad){
					masGustado = n;
					cantidad = n.getVecinos().size();
				}
			}
    	}
    	return masGustado;
    }

   
    
    public Nodo usuarioMasLejano(Nodo usuario){
    	Nodo masLejano = null;
    	int arcos = 0;
    	Iterator<Nodo> e = this.getNodos().iterator();
    	while(e.hasNext()){    		
			Nodo n = e.next();
			if(!esGusto(n)){
				int cantAuxiliar = contarArcosRecursivo(usuario,n,0);
				this.desmarcar();
				if (arcos < cantAuxiliar){
					arcos = cantAuxiliar;
					masLejano = n;
				}
			}
    	}
    	return masLejano;
    }
     
    
    public int contarArcosRecursivo(Nodo nInicial, Nodo nFinal, int cantidad){	
    	if(nInicial.getVecinos().contains(nFinal)){
			return cantidad;
		}
		else{
			if(todosVisitados(nInicial)){
				return -1;
			}
			else{
				Iterator<Nodo> e = nInicial.getVecinos().iterator();
		    	while(e.hasNext()){
					Nodo n = e.next();
					if(n.getEstado() != 2){
		    			n.setEstado(2);
		    			return contarArcosRecursivo(n,nFinal,cantidad+1);
					}
		    	}
		    	return -1;
			}
		}
    }
    
    public boolean todosVisitados(Nodo nodo){
    	Iterator<Nodo> e = nodo.getVecinos().iterator();
    	while(e.hasNext()){
			Nodo n = e.next();
			if(n.getEstado() == 1) return false; 
    	}
    	return true;
    }
    
    public Nodo usuarioAlAzar(){
    	Iterator<Nodo> e = this.getNodos().iterator();
    	int i = 0;
    	while(e.hasNext()){
			Nodo n = e.next();
			if((n.getTipo() == 1)){
				i++;
				if(i==30)
					return n;
			}
    	}
    	return null;
    }
    
    public String toString(){
    	String resultado="";
    	for(int f=0; f<this.getNodos().size(); f++){
    		resultado +=  this.getNodos().get(f).toString() + "\n";
    		for(int r=0; r<this.getNodos().get(f).getVecinos().size(); r++){
    			resultado +=  "         " + this.getNodos().get(f).getVecinos().get(r).toString() + "\n";
    		}
    	}
    	return resultado;
    } 
}