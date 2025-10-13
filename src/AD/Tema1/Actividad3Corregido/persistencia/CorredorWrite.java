package AD.Tema1.Actividad3Corregido.persistencia;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import AD.Tema1.Actividad3Corregido.clases.Corredor;
import AD.Tema1.Actividad3Corregido.clases.Equipo;

public class CorredorWrite extends Archivo {
    private ObjectOutputStream out;

    public CorredorWrite(File ruta) {
        super(ruta);
    }

    @Override
    public void abrirArchivo() {
        boolean appendMode = existe() && archivo.length() > 0;

        try {
            if (appendMode) {
                out = new AppendObjectOutputStream(new BufferedOutputStream(new FileOutputStream(archivo, appendMode)));
                System.out.println("Archivo de escritura abierto: " + archivo);
            } else {
                out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(archivo, appendMode)));
                System.out.println("Archivo de escritura creado: " + archivo);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void cerrarArchivo() {
        if (out != null) {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean verificarEquipoValido(int idEquipo) {
        RandomFile randomEquipos = new RandomFile("equipos.dat");

        try {
            randomEquipos.abrirArchivo();
            Equipo equipo = randomEquipos.cogerEquipo(idEquipo);

            return equipo != null && !equipo.isBorrado();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            randomEquipos.cerrarArchivo();
        }
    }

    public boolean escribir(Corredor corredor) {
        if (out == null) {
            System.out.println("Error: el stream de escritra no existe");
            return false;
        }

        try {
            out.writeObject(corredor);
            out.flush();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void iniciarEscritura() {
        abrirArchivo();

    }

    public void cerrarEscritura() {
        cerrarArchivo();

    }

}
