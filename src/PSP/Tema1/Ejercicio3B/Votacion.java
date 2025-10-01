package PSP.Tema1.Ejercicio3B;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Votacion {

    static int NUM_VOTANTES = 10000;
    static int NUM_PARTIDOS = 5;

    static Votante[] votantes = new Votante[NUM_VOTANTES];

    static ArrayList<Partido> partidos = new ArrayList<>(
            List.of(new Partido("PSOE"), new Partido("PP"), new Partido("VOX"), new Partido("CDM"),
                    new Partido("BNG")));

    public static void main(String[] args) {

        for (int i = 0; i < NUM_VOTANTES; i++) {
            votantes[i] = new Votante();

            votantes[i].start();

        }

        for (Votante hilo : votantes) {
            try {
                hilo.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        partidos.sort(new Comparator<Partido>() {

            @Override
            public int compare(Partido o1, Partido o2) {
                return o2.getContador() - o1.getContador();
            }

        });
        System.out.println(partidos.get(0));
    }
}
