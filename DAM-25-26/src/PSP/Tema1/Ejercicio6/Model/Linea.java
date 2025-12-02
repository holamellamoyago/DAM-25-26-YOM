package PSP.Tema1.Ejercicio6.Model;

import PSP.Tema1.Ejercicio6.Controller.ArchivoController;

public class Linea extends Thread {
    ArchivoController archivoController;
    private String contenido;

    public Linea(String linea) {
        this.contenido = linea;
    }

    @Override
    public String toString() {
        return contenido;
    }

    @Override
    public void run() {
        super.run();
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

}
