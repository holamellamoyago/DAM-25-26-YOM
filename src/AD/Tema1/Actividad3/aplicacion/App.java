package AD.Tema1.Actividad3.aplicacion;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDate;

import AD.Tema1.Actividad3.dominio.Corredor;
import AD.Tema1.Actividad3.dominio.Fondista;
import AD.Tema1.Actividad3.dominio.Velocista;

public class App {
    private static String nombreArchivo = "Corredores.dat";

    Corredor corredor1 = new Velocista("Juan Pérez", LocalDate.of(2000, 5, 12), 1, 10.34f);
    Corredor corredor2 = new Fondista("Ana Gómez", LocalDate.of(1995, 3, 22), 2, 42.195f);
    Corredor corredor3 = new Velocista("Carlos Ruiz", LocalDate.of(2002, 11, 30), 3, 9.75f);
    Corredor corredor4 = new Fondista("María López", LocalDate.of(2000, 7, 15), 1, 21.097f);
    Corredor corredor5 = new Velocista("Pedro García", LocalDate.of(1995, 8, 5), 1, 11.20f);
    Corredor corredor6 = new Fondista("Laura Martínez", LocalDate.of(2002, 9, 10), 4, 35.00f);

    public static void main(String[] args) {

    }

    private void guardarCorredor(Corredor corredor) {

        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(nombreArchivo));
            DataOutputStream fout = new DataOutputStream(out);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
