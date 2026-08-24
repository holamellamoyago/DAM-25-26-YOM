package PSP.Tema1.futbol;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class Estadio extends Thread {
    private final int NUMERO_ESPECTADORES = 50;
    private final int NUMERO_PUERTAS = 3;

    public boolean puertasAbiertas;
    private ArrayList<Puerta> puertas = new ArrayList<>();

    public int aficionadosAdentro;
    public final int MAXIMO_AFICIONADOS = 25;

    public Estadio() {
        this.puertasAbiertas = false;
        this.aficionadosAdentro = 0;

        puertas.add(new Puerta('A', this));
        puertas.add(new Puerta('B', this));
        puertas.add(new Puerta('C', this));

    }

    @Override
    public void run() {
        try {
            sleep(new Random().nextLong(5000));
            abrirPuertas();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void abrirPuertas() {
        System.out.println("Puertas abiertas");
        puertasAbiertas = true;
        notifyAll();
        System.out.println(puertas);
    }

    public synchronized Puerta escogerPuerta(Aficionado af) throws InterruptedException {

        Comparator<Puerta> comparator = new Comparator<Puerta>() {
            @Override
            public int compare(Puerta o1, Puerta o2) {
                return o1.cola.size() - o2.cola.size();
            }
        };

        puertas.sort(comparator);
        puertas.get(0).cola.add(af);
        return puertas.get(0);

    }
}
