package PSP.Tema1.Ejercicio3;

import java.util.Random;

public class App {
    public static int CENSO = 10;

    Random rnd = new Random();

    public static void main(String[] args) {
        for (int i = 0; i < CENSO; i++) {

            Votante v = new Votante();
            v.start();

        }

        for (int i = 0; i < votos.length; i++) {
            System.out.println(votos[i]);
        }
    }



}
