package AD.Tema1.Actividad3.Ejercicio3;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class escribirTexto extends Archivo {
    BufferedWriter writter;

    public escribirTexto(String ruta) {
        super(ruta);
    }

    @Override
    public void abrirArchivo() {
        try {
            writter = new BufferedWriter(new FileWriter(super.ruta));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void cerrarArchivo() {
        try {
            writter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void escribirLinea(String linea) throws IOException {
        writter.append(linea);
    }
}
