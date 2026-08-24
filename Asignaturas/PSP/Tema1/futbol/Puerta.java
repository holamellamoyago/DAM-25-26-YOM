package PSP.Tema1.futbol;

import java.util.ArrayList;

public class Puerta {
    public boolean puertaAbierta;
    private char letra;
    public ArrayList<Aficionado> cola;
    private Estadio estadio;

    public Puerta(char letra, Estadio estadio) {
        this.puertaAbierta = false;
        this.letra = letra;
        this.cola = new ArrayList<>();
        this.estadio = estadio;
    }

    @Override
    public String toString() {
        return "Puerta: " + letra + ", cola: " + cola.size();
    }

    public synchronized void entrarAlEstadio(Aficionado af) throws InterruptedException {
        while (estadio.aficionadosAdentro >= estadio.MAXIMO_AFICIONADOS) {
            // System.out.println(af + " espera a que haya un hueco para entrar");
            wait();
        }

        estadio.aficionadosAdentro++;
        System.out.println(af + " entra adentro del estadio por la " + this);
    }

    public synchronized void salirDelEstadio(Aficionado af) {
        estadio.aficionadosAdentro--;
        System.out.println(af + " sale del estadio");
        notifyAll();
    }

}
