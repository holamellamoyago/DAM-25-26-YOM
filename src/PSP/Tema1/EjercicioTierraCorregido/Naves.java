package PSP.Tema1.EjercicioTierraCorregido;

public class Naves {
    public static void main(String[] args) {
        final int NUM_METEORITOS = 50;
        final int NUM_NAVES_A = 2;
        final int NUM_NAVES_BS = 3;

        for (int i = 0; i < NUM_METEORITOS; i++) {
            HWWC.addMeteorito(new Meteorito("M" + String.valueOf(i)));
        }

        for (int i = 0; i < NUM_NAVES_A; i++) {
            HWWC.addNave(new NaveA("NA" + String.valueOf(i)));
        }

        for (int i = 0; i < NUM_NAVES_BS; i++) {
            HWWC.addNave(new NaveA("NBS" + String.valueOf(i)));
        }

        for (Nave n : HWWC.getNaves()) {
            n.start();
        }

        for (Nave n : HWWC.getNaves()) {
            try {
                n.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }



    }
}
