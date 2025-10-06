package AD.Tema1.Actividad3.persistencia;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import AD.Tema1.Actividad3.dominio.Corredor;

public class EscribirCorredores {
    private static ObjectOutputStream out;
    private static ArrayList<Corredor> corredores = LeerCorredores.leerCorredores();

    private static void abrirArchivo() {
        try {
            out = new ObjectOutputStream(new FileOutputStream("Corredores.dat"));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void cerrarArchivo() {
        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void escribirCorredores() {
        abrirArchivo();

        try {
            out.writeObject(corredores);
        } catch (IOException e) {
            e.printStackTrace();
        }

        cerrarArchivo();
    }

    public static void anhadirCorredor(Corredor corredor) { 
        corredores.add(corredor);
        escribirCorredores();
    }

    public static void anhadirCorredores(ArrayList<Corredor> corredores) { 
        for (Corredor corredorIndividual : corredores) {
            corredores.add(corredorIndividual);
        }
        escribirCorredores();
    }



}
