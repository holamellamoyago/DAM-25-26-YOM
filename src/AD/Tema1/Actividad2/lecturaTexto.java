package AD.Tema1.Actividad2;

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

    public lecturaTexto(File ruta) {
        super(ruta);
    }

    @Override
    public void abrirArchivo(String archivo) {
        try {
            reader = new BufferedReader(new FileReader(super.ruta));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void cerrarArchivo(String archivo) {
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String leerLinea() {
        String total = "";

        Stream<String> stream = reader.lines();
        Iterator<String> it = stream.iterator();

        while (it.hasNext()) {
            total += it.next() + ";";
        }

        return total;
    }

}
