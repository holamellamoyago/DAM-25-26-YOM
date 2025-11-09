package PSP.Tema1.EjercicioTierra;

import java.util.Random;

public abstract class Nave extends Thread {
    protected Random rdm = new Random();
    protected String nombre;
    protected boolean necesitaRepostar;

    public Nave(String nombre) {
        this.nombre = nombre;
        necesitaRepostar = false;
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
