package PSP.Tema1.Ejercicio6.Model;

public class LineaImpar extends Linea {

    public LineaImpar() {
        super("Yo te quiero maas");
    }

    @Override
    public void run() {
        archivoController.escribir(getContenido());
        super.run();
    }

}
