package PSP.Tema1.Aparcamiento;

public class Principal {
    final static int TOTAL_CONDUCTORES = 50;
    public static Conductor[] conductores = new Conductor[TOTAL_CONDUCTORES];

    public static void main(String[] args) {
        Aparcamiento aparcamiento = new Aparcamiento();
        for (int i = 0; i < TOTAL_CONDUCTORES; i++) {
            conductores[i] = new Conductor(i, aparcamiento);
            conductores[i].start();
        }


        for (Conductor plaza : conductores) {
            try {
                plaza.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
