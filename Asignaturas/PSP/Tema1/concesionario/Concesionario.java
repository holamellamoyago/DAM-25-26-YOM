package PSP.Tema1.concesionario;

import java.util.ArrayList;
import java.util.Arrays;

public class Concesionario {
    private final int TOTAL_LEON = 10;
    private final int TOTAL_IBIZA = 7;
    private final int TOTAL_AZTECA = 3;

    // Coche[] coches = new Coche[TOTAL_AZTECA + TOTAL_IBIZA + TOTAL_LEON];
    ArrayList<Coche> coches = new ArrayList<>();
    private boolean vendedorOcupado;

    public Concesionario() {

        this.vendedorOcupado = false;
        int ultPosc = 0;

        for (int i = 0; i < TOTAL_LEON; i++) {
            coches.add(new Coche("Leon " + i));
            ultPosc++;
        }

        for (int i = 0; i < TOTAL_IBIZA; i++) {
            coches.add(new Coche("Ibiza " + i));
            ultPosc++;
        }

        for (int i = 0; i < TOTAL_AZTECA; i++) {
            coches.add(new Coche("Azteca " + i));
            ultPosc++;
        }
    }

    public synchronized Coche agendarCita(Cliente cliente) throws InterruptedException {
        for (Coche coche : coches) {
            if (coche.cliente == null) {
                System.out.println("El " + cliente + " agenda cita para el " + coche);
                return coche;
            }
        }

        return null;
    }

    public synchronized void liberarCoche(Cliente cliente, Coche coche) {
        coche.cliente = null;
        System.out.println("El " + cliente + " libera el " + coche);
        notify();
    }

    public synchronized void comprarCoche(Cliente cliente, Coche coche) {
        coches.remove(coche);
        System.out.println(cliente + " compró el " + coche);
    }
}
