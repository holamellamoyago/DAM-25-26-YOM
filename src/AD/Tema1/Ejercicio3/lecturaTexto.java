package AD.Tema1.Ejercicio3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.stream.Stream;

public class lecturaTexto extends Archivo {
    private BufferedReader reader;

    public lecturaTexto(String ruta) {
        super(ruta);
    }

    @Override
    public void abrirArchivo() {
        try {
            reader = new BufferedReader(new FileReader(super.ruta));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void cerrarArchivo() {
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String leerLinea() {
        String linea = null;

        try {
            linea = reader.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return linea;
    }

}
