package AD.Tema1.Actividad2.Actividad2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class escribirTexto extends Archivo {
    BufferedWriter writter;

    public escribirTexto(File ruta) {
        super(ruta);
    }

    @Override
    public void abrirArchivo(String archivo) {
        try {
            writter = new BufferedWriter(new FileWriter(archivo));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void cerrarArchivo(String archivo) {
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
