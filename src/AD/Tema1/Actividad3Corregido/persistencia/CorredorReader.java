package AD.Tema1.Actividad3Corregido.persistencia;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import AD.Tema1.Actividad3Corregido.*;
import AD.Tema1.Actividad3Corregido.clases.Corredor;

public class CorredorReader extends Archivo {

    public CorredorReader(File ruta) {
        super(ruta);
    }

    ObjectInputStream in;

    @Override
    public void abrirArchivo() {
        if (ruta.exists()) {
            try {
                in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(ruta)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Corredor leer() {
        try {
            return (Corredor) in.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void cerrarArchivo() {
        if (in != null) {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public int obtenerultDorsal() {
        int ultDorsal = 0;

        abrirArchivo();

        if (in == null) {
            return 0;
        }

        Corredor c;

        try {
            while ((c = leer()) != null) {
                ultDorsal = c.getDorsal();
            }
        } catch (Exception e) {
            // TODO: handle exception
        } finally {
            cerrarArchivo();
        }

        return ultDorsal;
    }

    public Corredor buscarDorsal(int dorsal) {
        Corredor encontrado = null;

        abrirArchivo();

        if (in == null) {
            return null;
        }

        Corredor c;

        try {
            while ((c = leer()) != null && encontrado == null) {
                if (c.getDorsal() == dorsal) {
                    encontrado = c;
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
        } finally {
            cerrarArchivo();
        }

        return encontrado;
    }

    public ArrayList<Corredor> obtenerTodosCorredores() {
        ArrayList<Corredor> corredores = new ArrayList<>();
        if (in == null) {
            return new ArrayList<>();
        }

        Corredor c;
        while ((c = leer()) != null) {
            corredores.add(c);
        }

        return corredores;
    }

    // public Iterable<Corredor> leerIterativo() {
    // abrirArchivo();
    // if (in == null) {
    // return new ArrayList<>();
    // }

    // return () -> new java.util.Iterator<Corredor>() {
    // Corredor siguiente = leer();
    // boolean fin = (siguiente == null);

    // @Override
    // public boolean hasNext() {
    // // TODO Auto-generated method stub
    // throw new UnsupportedOperationException("Unimplemented method 'hasNext'");
    // }
    // @Override
    // public Corredor next() {
    // // TODO Auto-generated method stub
    // throw new UnsupportedOperationException("Unimplemented method 'next'");
    // }
    // };
    // }

}
