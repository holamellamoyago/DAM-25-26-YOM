package AD.Tema1.Actividad3.Actividad3Intento2.Persistencia;

import java.io.BufferedInputStream;
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

    public ArrayList<Corredor> listarCorredores() { 
        ArrayList<Corredor> corredors = new ArrayList<>();

            Corredor corredor = (Corredor) archivo.readObject();
            corredors.add(corredor);

            
    }

    @Override
    public void abrirarchivo() {
        try {
            archivo = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void cerararchivo() {
        try {
            archivo.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
