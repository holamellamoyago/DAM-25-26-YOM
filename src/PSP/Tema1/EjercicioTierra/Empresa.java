package PSP.Tema1.EjercicioTierra;

import java.util.ArrayList;

public class Empresa {
    public static ArrayList<Nave> naves = new ArrayList<>();
    public static ArrayList<Meteorito> meteoritos = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        iniciarNaves();

    }

    private static void iniciarNaves() {
        final int NUM_METEORITOS = 10;
        final int NUM_NAVES_A = 5;
        final int NUM_NAVES_SURTIDORA = 3;

        for (int i = 0; i < NUM_METEORITOS; i++) {
            meteoritos.add(new Meteorito("C".concat(String.valueOf(i))));
        }

        for (int i = 0; i < NUM_NAVES_A; i++) {
            naves.add(new Surtidora("NA-".concat(String.valueOf(i))));
        }

        for (int i = 0; i < NUM_NAVES_SURTIDORA; i++) {
            naves.add(new Armageddon("NS-".concat(String.valueOf(i))));
        }

        for (Nave nave : naves) {
            nave.start();
        }

        for (Nave nave : naves) {
            try {
                nave.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
