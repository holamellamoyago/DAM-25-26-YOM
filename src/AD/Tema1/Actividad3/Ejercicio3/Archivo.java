package AD.Tema1.Actividad3.Ejercicio3;

import java.io.File;

public abstract class Archivo {
    File ruta;

    public Archivo(String ruta) {
        this.ruta = new File(ruta);
    }

    public abstract void abrirArchivo ();

    public abstract void cerrarArchivo ();

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
