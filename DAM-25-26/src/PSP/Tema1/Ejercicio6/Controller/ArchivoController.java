package PSP.Tema1.Ejercicio6.Controller;

import java.io.IOException;

import PSP.Tema1.Ejercicio6.Model.Archivo;
import PSP.Tema1.Ejercicio6.Model.Linea;

public class ArchivoController extends Archivo {
    private String ultClaseEscrita = null;

    public ArchivoController() {
        super("Enamorados.txt");
    }

    public synchronized void escribirLinea(Linea linea) {

        // Quiero la últ línea sea lea , 
        // Si es impar haces un wait , si es 

        escribir(linea.getContenido());
    }

    public void cerrarArchivo() {
        try {
            writter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
