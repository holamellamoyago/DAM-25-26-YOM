package PSP.Tema1.Ejercicio7Ampliado;

import java.util.Random;

public class Apuesta {
    Random rdm = new Random();
    final int MAX_GGOLES = 4;

    int golesDM2;
    int golesPRF;

    int precio;

    public Apuesta() {
        this.golesDM2 = rdm.nextInt(MAX_GGOLES + 1);
        this.golesPRF = rdm.nextInt(MAX_GGOLES);
        this.precio = rdm.nextInt(10) +1 ;
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

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "\nApuesta: golesDM2 de " + golesDM2 + ", golesPRF de" + golesPRF + " (" + precio + "€)\n";
    }


    

}
