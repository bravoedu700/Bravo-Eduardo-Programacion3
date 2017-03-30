
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Pila unapila = new Pila();
		
        System.out.println("Pila:");

        if(unapila.vacia())
            System.out.println("Pila Vacia");
        else
            System.out.println("Tiene elementos");
		unapila.poner(44);
        System.out.println("Agregue el valor " + unapila.ver());

        unapila.poner(53);
        System.out.println("Agregue el valor " + unapila.ver());
        System.out.println("La tapa es: " + unapila.ver());

        unapila.poner(22);
        System.out.println("Agregue el valor " + unapila.ver());

        unapila.poner(55);
        System.out.println("Agregue el valor " + unapila.ver());

        unapila.poner(89);
        System.out.println("Agregue el valor " + unapila.ver());
		
        if(unapila.vacia())
            System.out.println("Pila Vacia");
        else
            System.out.println("Tiene elementos");

        System.out.println("La tapa es: " + unapila.ver());
        unapila.sacar();
        System.out.println("La tapa es: " + unapila.ver());
        
        unapila.sacar();
        System.out.println("La tapa es: " + unapila.ver());

        unapila.sacar();
        System.out.println("La tapa es: " + unapila.ver());

        unapila.poner(35);
        System.out.println("Agregue el valor " + unapila.ver());
        System.out.println("La tapa es: " + unapila.ver());
        
        unapila.sacar();
        System.out.println("La tapa es: " + unapila.ver());

        unapila.sacar();
        System.out.println("La tapa es: " + unapila.ver());
        	
        if(unapila.vacia())
            System.out.println("Pila Vacia");
        else
            System.out.println("Tiene elementos");

        unapila.sacar();

        if(unapila.vacia())
            System.out.println("Pila Vacia");
        else
            System.out.println("Tiene elementos");
        
	}

}
