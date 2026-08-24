package PSP.Tema1.aparcamiento;

public class Aparcamiento {
    private final int PLAZAS_TOTALES = 4;
    private Conductor[] plazas;

    public Aparcamiento() {
        this.plazas = new Conductor[PLAZAS_TOTALES];
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("Aparcamiento: \n");

        for (int i = 0; i < plazas.length; i++) {
            if (plazas[i] != null) {
                str.append("[OCUPADA] | ");
            } else {
                str.append("[LIBRE] | ");
            }
        }

        return str.toString();
    }

    public synchronized boolean ocuparPlaza(Conductor conductor) {
        for (int i = 0; i < plazas.length; i++) {
            if (plazas[i] == null) {
                plazas[i] = conductor;
                return true;
            }
        }

        return false;
    }

    public void dejarPlaza(Conductor conductor) {
        for (int i = 0; i < plazas.length; i++) {
            if (plazas[i] == conductor) {
                plazas[i] = null;
                return;
            }
        }

        System.out.println("Problemas al dejar la plaza del conductor " + conductor);
    }

}
