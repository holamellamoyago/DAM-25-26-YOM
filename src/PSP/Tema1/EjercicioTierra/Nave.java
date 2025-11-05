package PSP.Tema1.EjercicioTierra;

import java.util.Random;

enum TipoNave {
    SURTIDORA, Armageddon
}

public abstract class Nave extends Thread {
    Random rdm = new Random();
    String nombre;
    TipoNave tipoNave;
    boolean respostando;

    public Nave(String nombre, TipoNave tipoNave) {
        this.nombre = nombre;
        this.tipoNave = tipoNave;
        respostando = false;
    }


    public TipoNave getTipoNave() {
        return tipoNave;
    }

    public void setTipoNave(TipoNave tipoNave) {
        this.tipoNave = tipoNave;
    }

    public synchronized boolean isRespostando() {
        return respostando;
    }

    public synchronized void setRespostando(boolean respondando) {
        this.respostando = respondando;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }



}
