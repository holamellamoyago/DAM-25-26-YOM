package PSP.Tema1Repaso.Ejercicio3;

public class Ejercicio3Repaso {
    static final int CENSO = 10;
    static Hilo votante;
    static Partido[] partidos = { new Partido(), new Partido(), new Partido() };
    static Hilo[] votantes = new Hilo[CENSO];

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < CENSO; i++) {
            votante = new Hilo();
            votantes[i] = votante;
            votante.start();
        }

        for (Hilo hilo : votantes) {
            hilo.join();
        }

        for (Partido p : partidos) {
            System.out.println(p);
        }

    }
}
