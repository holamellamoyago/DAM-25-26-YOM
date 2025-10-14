package AD.Tema1.Actividad2.Actividad2B;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class escribirTexto extends Archivo{
    BufferedWriter writter;

    public escribirTexto(String ruta) {
        super(ruta);
    }

    @Override
    void abrirFichero()  {
        try {
            writter = new BufferedWriter(new FileWriter(fichero))    ;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    void cerrarFichero() {
        try {
            writter.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
}
