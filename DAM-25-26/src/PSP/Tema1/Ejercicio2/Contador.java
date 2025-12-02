package PSP.Tema1.Ejercicio2;

public class Contador {
    private int contador;
    

    public Contador(int contaodr) {
        this.contador = contaodr;
    }

    public int aumentarcontador(){
        contador++;
        System.out.println("Se aumento " + contador);
        return contador;
    }

    @Override
    public String toString() {
        return "Contador [contador=" + contador + "]";
    }


    
}
