package AD.Tema1.Actividad3Corregido.persistencia;

import java.io.File;

public abstract class Archivo {
    public File archivo;

    public Archivo(File ruta) {
        this.archivo = ruta;
    }

    public abstract void abrirArchivo();

    public abstract void cerrarArchivo();

    public boolean existe() {
        return archivo.exists();
    }

    public boolean borrarFichero() {
        try {
            return archivo.delete();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean rename(String newName) {
        return archivo.renameTo(new File(newName));
    }
}
