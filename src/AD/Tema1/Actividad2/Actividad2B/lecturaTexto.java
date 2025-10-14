package AD.Tema1.Actividad2.Actividad2B;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class lecturaTexto extends Archivo {
    BufferedReader reader; 

    public lecturaTexto(String ruta) {
        super(ruta);
    }

    @Override
    void abrirFichero()  {
        try {
            reader = new BufferedReader(new FileReader(fichero))    ;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    void cerrarFichero() {
        try {
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String leerLinea(){
        String linea = null;

        try {
            linea = reader.readLine();
        } catch (Exception e) {
        }

        return linea;
    }

    
}
