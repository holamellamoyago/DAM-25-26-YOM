package Peliculas;

public class Principal {

    public static void main(String[] args) {
        final int NUM_CINEFILOS = 10;

        Cinefilo[] cinefilos = new Cinefilo[NUM_CINEFILOS];
        Cine cine = new Cine();

        for (int i = 0; i < cinefilos.length; i++) {
            cinefilos[i] = new Cinefilo(i, cine);
            cinefilos[i].start();
        }

        for (Cinefilo cinefilo : cinefilos) {
            try {
                cinefilo.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Una línea por cada pelicula con toidois los cinefilos
        for (Sala sala : cine.salas) {
            System.out.println();
            String aforo = "(" + sala.pelicula.cinefilos.size() + " / " + sala.AFORO_SALA + ")";
            System.out.println("Estas mirando la " + sala.pelicula + aforo);
            System.out.println(sala.pelicula.cinefilos);
        }

        System.out.println();
        for (Sala sala : cine.salas) {
            System.out.println("Quedaron de la " + sala + " " + sala.aforoSinEntrada + " clientes sin entrada");
        }

    }
}
