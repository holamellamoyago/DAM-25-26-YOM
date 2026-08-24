package AD.Tema1.Actividad3.Actividad3Intento2.Persistencia;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import AD.Tema1.Actividad3.Actividad3Intento2.Clases.Corredor;

public class CorredoresReader extends Archivo {
    ObjectInputStream archivo;

    public CorredoresReader(String ruta) {
        super(ruta);
    }

    public Corredor leerCorredor() {
        try {
            return (Corredor) archivo.readObject();
        } catch (ClassNotFoundException | IOException e) {
            return null;
        }
    }

    public ArrayList<Corredor> listarcorredores() {
        ArrayList<Corredor> corredores = new ArrayList<>();
        Corredor corredor;

        try {
            while ((corredor = leerCorredor()) != null) {
                corredores.add(corredor);
            }

            return corredores;
        } catch (Exception e) {
            System.out.println("Error al leer todos los corredores");
            return new ArrayList<>();
        }

    }

    @Override
    public void abrirarchivo() {

        if (!existe()) {
            throw new ArithmeticException("El archivo no existe");
        }

        try {
            archivo = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)));
        } catch (IOException e) {
            archivo = null;
            e.printStackTrace();
        }
    }

    @Override
    public void cerararchivo() {
        if (archivo != null) {
            try {
                archivo.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
