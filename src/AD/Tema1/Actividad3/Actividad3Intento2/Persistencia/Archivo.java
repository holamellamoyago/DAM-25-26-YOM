package AD.Tema1.Actividad3.Actividad3Intento2.Persistencia;

import java.io.File;

public abstract class Archivo {
    File file;
    
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
}
