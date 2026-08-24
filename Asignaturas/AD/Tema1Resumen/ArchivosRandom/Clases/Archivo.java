package AD.Tema1Resumen.ArchivosRandom.Clases;

import java.io.File;

public abstract class Archivo {
    protected File file;
    
    public Archivo(String ruta) {
        this.file = new File(ruta);
    }
    
    public abstract void abrirarchivo();
    public abstract void cerararchivo();

    public boolean existe(){
        if (file.exists()) {
            return true;
        }

        return false;
    }

    public boolean eliminarArchivo(){
        return file.delete();
    }
}
