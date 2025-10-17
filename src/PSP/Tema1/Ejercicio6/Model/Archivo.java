package PSP.Tema1.Ejercicio6.Model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Archivo {
    protected BufferedWriter writter;

    public Archivo(String ruta) {
        try {
            this.writter = new BufferedWriter(new FileWriter(ruta));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void escribir(String text) {
        try {
            writter.write(text);
            writter.newLine();
            writter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    
}
