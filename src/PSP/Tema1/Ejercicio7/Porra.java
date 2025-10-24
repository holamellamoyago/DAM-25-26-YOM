package PSP.Tema1.Ejercicio7;

import java.util.Random;

public class Porra {
    final int MAX_GGOLES = 4;
    int golesDM2;
    int golesPRF;
    int cantidadApuestas;
    int dineroApuesta;

    Random rdm = new Random();

    public Porra() {
        this.golesDM2 = rdm.nextInt(MAX_GGOLES + 1);
        this.golesPRF = rdm.nextInt(MAX_GGOLES);
        this.cantidadApuestas = rdm.nextInt(6) + 3; // Aleatorio entre 3 y 6 
        this.dineroApuesta = 1;

    }

    @Override
    public String toString() {
        return "Goles a DM2: " + golesDM2 + ", goles a PRF: " + golesPRF + " cantidadApuestas=" + cantidadApuestas
                + ", dineroApuesta=" + dineroApuesta;
    }

    public int getGolesDM2() {
        return golesDM2;
    }

    public void setGolesDM2(int golesDM2) {
        this.golesDM2 = golesDM2;
    }

    public int getGolesPRF() {
        return golesPRF;
    }

    public void setGolesPRF(int golesPRF) {
        this.golesPRF = golesPRF;
    }

    public int getCantidadApuestas() {
        return cantidadApuestas;
    }

    public void setCantidadApuestas(int cantidadApuestas) {
        this.cantidadApuestas = cantidadApuestas;
    }

    public int getDineroApuesta() {
        return dineroApuesta;
    }

    public void setDineroApuesta(int dineroApuesta) {
        this.dineroApuesta = dineroApuesta;
    }


    

    

}
