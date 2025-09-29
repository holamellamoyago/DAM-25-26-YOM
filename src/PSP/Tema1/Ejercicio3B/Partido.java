package PSP.Tema1.Ejercicio3B;

public class Partido {
    private int contador;

    public Partido() {
        this.contador = 0;
    }

    public synchronized void sumarVoto(){
        contador++;
    }

    @Override
    public String toString() {
        return "Partido " + contador + "]";
    }

    

}
