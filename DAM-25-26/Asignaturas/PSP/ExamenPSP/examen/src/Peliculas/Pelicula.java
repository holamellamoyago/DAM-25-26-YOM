package Peliculas;

import java.util.ArrayList;
import java.util.Random;

public class Pelicula {
    private String pelicula;
    ArrayList<Cinefilo> cinefilos = new ArrayList<>();

    public Pelicula(int pelicula) {
        this.pelicula = "Pelicula" + String.valueOf(pelicula);
    }

    public synchronized void anhadirCinefilo(Cinefilo cinefilo) {
        cinefilos.add(cinefilo);
    }



}
