package Peliculas;

import java.util.Random;

public class Cine {
    final int NUM_PELICULAS = 5; // o salas
    Sala[] salas = new Sala[NUM_PELICULAS];

    public Cine() {
        for (int i = 0; i < NUM_PELICULAS; i++) {
            salas[i] = new Sala(i, new Pelicula(i));
        }
    }

    public Sala escogerSala() {
        return salas[new Random().nextInt(NUM_PELICULAS)];
    }

    



}
