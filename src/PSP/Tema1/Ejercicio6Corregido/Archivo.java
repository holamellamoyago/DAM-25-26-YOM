package PSP.Tema1.Ejercicio6Corregido;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Archivo {
    BufferedReader archivo ; 

    public Archivo(String ruta) {
        try {
            this.archivo = new BufferedReader(new FileReader(ruta));
        } catch (FileNotFoundException e) {e.printStackTrace();}
    }

    public synchronized String getLinea() {
        notify();
        String linea;
        try {
            if ((linea = archivo.readLine()) != null) {
                try {
                    wait();
                } catch (Exception e) {}
            }
            return linea;
        } catch (Exception e) {
            return null;
        }
    }

    
}
