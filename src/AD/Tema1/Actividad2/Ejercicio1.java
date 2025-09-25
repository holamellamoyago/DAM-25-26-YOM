package AD.Tema1.Actividad2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Ejercicio1 {
    public static void main(String[] args) {
        String[] archivos = new String[3];
        archivos[0] = "archivo0.txt";
        archivos[1] = "archivo1.txt";
        archivos[2] = "archivo2.txt";
        archivos[3] = "archivo3.txt";

        for (String string : archivos) {
            FileReader file;
            
            try {
                file = new FileReader(string);


            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            BufferedReader in = new BufferedReader(file);
        }
    }
}
