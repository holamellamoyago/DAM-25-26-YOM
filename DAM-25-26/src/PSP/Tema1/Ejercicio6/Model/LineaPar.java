package PSP.Tema1.Ejercicio6.Model;

public class LineaPar extends Linea {

    public LineaPar() {
        super("Yo te quiero mucho");
    }

    @Override
    public void run() {
        archivoController.escribir(getContenido());
        super.run();
    }

}
