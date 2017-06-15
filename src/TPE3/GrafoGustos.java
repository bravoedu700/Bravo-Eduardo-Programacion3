package TPE3;

import java.util.Iterator;
import java.util.ArrayList;

public class GrafoGustos extends Grafo {

		
    public Nodo addUsuario(String idUsuario) {
    	Nodo usuario = new Nodo(idUsuario, 1);
    	if (!this.contains(usuario)){
            this.addNodo(usuario);
        }
    	return usuario;
    }

    public Nodo addGusto(String idGusto) {
    	Nodo gusto = new Nodo(idGusto, 2);
    	if (!this.contains(gusto)){
            this.addNodo(gusto);
        }
    	return gusto;
    }

    public void insertarArista(Nodo n1, Nodo n2) {
    	if(n1.getTipo() != n2.getTipo()){
    		if(!this.contains(n1)) 
    			this.addNodo(n1);
    		if(!this.contains(n2)) 
    			this.addNodo(n2);    		
			n1.addVecino(n2);
			n2.addVecino(n1);
    	}
     }
    
    public boolean existeGusto(String idGusto) {
    	Nodo gusto = new Nodo(idGusto, 2);
    	return this.contains(gusto);
    }

    public boolean existeUsuario(String idUsuario) {
    	Nodo usuario = new Nodo(idUsuario, 1);
    	return this.contains(usuario);
    }
    
    public boolean esGusto(Nodo n){
    	return (n.getTipo() == 2);
    }

    public boolean esUsuario(Nodo n){
    	return (n.getTipo() == 1);
    }
    
    public ArrayList<Nodo> personasGustoComun(Nodo usuario){   	
        ArrayList<Nodo> personas = new ArrayList<Nodo>();
    	Nodo n;
    	Iterator<Nodo> e = this.getNodos().iterator();
    	
		while(e.hasNext()){
			n = e.next();
			if((n != usuario)&&(n.getTipo()==1)){				
				Iterator <Nodo> gustos1 = n.getVecinos().iterator();
				int cantidadIguales = 0;
				while(gustos1.hasNext()){														
					//if(gustos.contains(gustos1.next())){
					if(gustos1.next().contains(usuario)){
						cantidadIguales=cantidadIguales+1;
						}
				}
			if(cantidadIguales >= 2)
				personas.add(n);
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
    	while(e.hasNext()){
			Nodo n = e.next();
			if(n.getTipo() == 1)
				return n;
    	}
    	return null;
    }
}