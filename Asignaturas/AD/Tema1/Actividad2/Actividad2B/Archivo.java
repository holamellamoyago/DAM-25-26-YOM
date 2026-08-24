package AD.Tema1.Actividad2.Actividad2B;

import java.io.File;

public abstract class Archivo {
    File fichero;

    public Archivo(String fichero) {
        this.fichero = new File(fichero);
    }

    public boolean existe() {
        return fichero.exists();
    }

    public void renonmbrar(String nuevoNombre){
        fichero.renameTo(new File(nuevoNombre));
    }

    public void borrrar() {
        fichero.delete();
    }

    abstract void abrirFichero();
    abstract void cerrarFichero();

}
