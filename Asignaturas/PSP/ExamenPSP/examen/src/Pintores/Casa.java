package Pintores;

public class Casa {
    final int NUM_TABIQUES = 25;
    public Tabique[] tabiques = new Tabique[NUM_TABIQUES];
    boolean ocupada = false;

    public Casa() {
        for (int i = 0; i < tabiques.length; i++) {
            tabiques[i] = new Tabique();
        }
    }

    public synchronized boolean isOcupada() {
        return ocupada;
    }

    public synchronized void setOcupada(boolean bool) {
        this.ocupada = bool;
    }

    public synchronized void abrirPuerta(Pintor pintor) {
        while (isOcupada()) {
            System.out.println(pintor + " espera a que pueda ir a la casa");
            try {
                wait();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        setOcupada(true);

        System.out.println(pintor + " entra en la casa a pintar, " + pintor.color);

    }

    public synchronized void cerrarPuerta(Pintor pintor) {
        System.out.println(pintor + " termina de pintar la casa y se marcha " + pintor.color);
        setOcupada(false);

        notifyAll();

    }

    public synchronized void sacarFotosCasa() {
                while (isOcupada()) {
            System.out.println("Fotografo espera a que pueda ir a la casa");
            try {
                wait();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        System.out.println("Fotografo saca fotos a la casa");

        
    }

}
