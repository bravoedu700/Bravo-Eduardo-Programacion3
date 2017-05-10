package TPE;

public class Persona {
	private int id;
	private String[] gutos;
	private int cantgus;
		
	public Persona(int size){
		this.gutos = new String[size];
		this.cantgus=0;
	}
	
	public String getGusto(int i){
		return this.gutos[i];
	}
	
	public void addGusto(String g){
		int repetido=0;
		for(int i=0; i<this.cantgus; i++){
			if(this.getGusto(i).equals(g))
				repetido=1;
		}
		if(repetido==0){
			this.gutos[this.cantgus]=g;
			this.cantgus++;
		}
			
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String toString(){
		String res = "ID: "+this.getId()+" GUSTOS: ";
		for(int r=0;r<5;r++){
			res+= '|' + this.getGusto(r);
		}
		return res;
	}
	
	public boolean equals(Persona p){
		//System.out.println("esta: " + this.id + " biene " + p.getId());
		if(this.id==p.getId())
			return true;
		else
			return false;
	}
	
		
}