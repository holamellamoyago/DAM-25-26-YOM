package PSP.Tema1.Ejercicio7;

import java.util.Random;

public class Resultado {
    Random rdm = new Random();
    final int MAX_GGOLES = 4;

    int golesDM2;
    int golesPRF;

    public Resultado() {
        this.golesDM2 = rdm.nextInt(MAX_GGOLES + 1);
        this.golesPRF = rdm.nextInt(MAX_GGOLES);

    }

        @Override
    public String toString() {
        return "Resultado: goles de DM2: " + golesDM2 + ", goles a de PRF: " + golesPRF;
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



    


}