package PSP.Tema1.Carrera;

public class Carreras {
    final static int NUM_CALLES = 8;
    public static Calle[] calles = new Calle[NUM_CALLES];
    public static int metros;

    public static void main(String[] args) throws InterruptedException {
        Carrera carrera = new Carrera();

        Juez juez = new Juez(carrera);
        juez.start();

        for (int i = 0; i < NUM_CALLES; i++) {

            calles[i] = new Calle(i, new Atleta(i, carrera));
            calles[i].getAtleta().start();

            // Calle[i] calle = new Calle(i, new Atleta(i, carrera));
            // Calle.getAtleta.start();
        }

        for (int i = 0; i < calles.length; i++) {
            try {
                calles[i].getAtleta().join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        juez.join();
    }

}
