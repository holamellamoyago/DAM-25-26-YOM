package AD.Tema1.Actividad2;

import java.io.File;

public abstract class Archivo {
    protected File ruta;


    public Archivo(File ruta) {
        this.ruta = ruta;
    }

    public abstract void abrirArchivo (String archivo);

    public abstract void cerrarArchivo (String archivo);

    public boolean existe(){
        return ruta.exists();
    }

    public boolean renombrar(String nombreNuevo){
        return ruta.renameTo(new File(nombreNuevo));
    }

    public boolean renombrar(){
        return ruta.delete();
    }
}
