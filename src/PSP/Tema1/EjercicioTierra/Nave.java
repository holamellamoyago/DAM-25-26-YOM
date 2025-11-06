package PSP.Tema1.EjercicioTierra;

import java.util.Random;

enum TipoNave {
    SURTIDORA, Armageddon
}

public abstract class Nave extends Thread {
    protected Random rdm = new Random();
    protected String nombre;
    protected TipoNave tipoNave;
    protected boolean necesitaRepostar;

    public Nave(String nombre, TipoNave tipoNave) {
        this.nombre = nombre;
        this.tipoNave = tipoNave;
        necesitaRepostar = false;
    }


    public TipoNave getTipoNave() {
        return tipoNave;
    }

    public void setTipoNave(TipoNave tipoNave) {
        this.tipoNave = tipoNave;
    }

    public boolean isNecesitaRepostar() {
        return necesitaRepostar;
    }

    public void setNecesitaRepostar(boolean respondando) {
        this.necesitaRepostar = respondando;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }



}
