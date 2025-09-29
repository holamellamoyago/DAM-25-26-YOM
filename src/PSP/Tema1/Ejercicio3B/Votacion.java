package PSP.Tema1.Ejercicio3B;

import java.util.ArrayList;
import java.util.List;

public class Votacion {

    static int NUM_VOTANTES = 10000;
    static int NUM_PARTIDOS = 5;

    static ArrayList<Partido> partidos = new ArrayList<>(
            List.of(new Partido(), new Partido(), new Partido(), new Partido(), new Partido()));

    public static void main(String[] args) {
        Votante votante;

        for (int i = 0; i < NUM_VOTANTES; i++) {
            votante = new Votante();
            votante.run();
        }

        for (Partido e : partidos) {
            System.out.println(e.toString());
        }
    }
}
