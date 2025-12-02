package PSP.Tema1.Parking;

import java.util.ArrayList;
import java.util.List;

public class Concesionario {
    private static Coche ateca = new Coche("Ateza", 3);
    private static Coche ibiza = new Coche("Ibiza", 7);
    private static Coche leon = new Coche("Leon", 10);

    final static ArrayList<Coche> coches = new ArrayList<>(List.of(ateca, ibiza, leon));

    public static synchronized Coche agendarCoche() {
        for (Coche coche : coches) {
            if (!coche.ocupado) {
                coche.ocupado = true;
                return coche;
            }
        }

        return null;
    }

    // TODO sincronizar coche 

    public static synchronized boolean quedanCoches() {
        if (coches.isEmpty()) {
            return false;
        }

        return true;
    }

    public static synchronized void quitarCocheComprado(Coche coche) {
        coches.remove(coche);
    }

}
