package Peliculas;

public class Cinefilo extends Thread {
    private Cine cine;

    public Cinefilo(int i, Cine cine) {
        super("Cinefilo" + String.valueOf(i));
        this.cine = cine;
    }

    @Override
    public void run() {

        while (true) {
            Sala sala = cine.escogerSala();

            // Controlo que no vaya a una cola sin entradas
            while (!sala.quedanEntradas(this)) {
                sala = cine.escogerSala();
            }

            // Si hay cola hace el wait
            sala.hacerCola(this);

            if (sala.comprarEntrada(this)) {
                // Si compra la entrada se sale del bucle
                
                break;
            }
        }

        // El chico va a su sala y mira la pelicula ...

    }

    @Override
    public String toString() {
        return getName();
    }

}
