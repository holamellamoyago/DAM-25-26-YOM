package AD.Tema1.Actividad3Corregido.persistencia;

import java.io.File;

public abstract class Archivo {
    public File ruta;

    public Archivo(File ruta) {
        this.ruta = ruta;
    }
    public abstract void abrirArchivo();
    public abstract void cerrarArchivo();

    public boolean existe (){
        return ruta.exists();
    }

    public boolean borrarFichero() {
        return ruta.delete();
    }

    public boolean rename(String newName) {
        return ruta.renameTo(new File(newName));
    }
}
