package PSP.Tema1.Aparcamiento;

public class Aparcamiento {
    final static int NUM_PLAZAS = 10;
    public static Plaza[] plazas = new Plaza[NUM_PLAZAS];

    public Aparcamiento() {
        for (int i = 0; i < NUM_PLAZAS; i++) {
            plazas[i] = new Plaza(i);
        }
    }

    public synchronized Plaza obtenerPlaza(Conductor conductor) {
        for (int i = 0; i < plazas.length; i++) {
            if (!plazas[i].ocupada) {
                plazas[i].ocupada = true;
                plazas[i].conductor = conductor;

                System.out.println(conductor + " obtenió la " + plazas[i]);
                return plazas[i];
            }
        }

        return null;
    }

    public synchronized void devolverPlaza(Conductor conductor, int numeroPlaza) {
        plazas[numeroPlaza].ocupada = false;
        System.out.println(conductor + " el conductor deja la plaza " + plazas[numeroPlaza]);
        
        plazas[numeroPlaza].conductor = null;

        notify();
    }
}
