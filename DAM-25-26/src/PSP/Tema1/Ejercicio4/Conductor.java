package PSP.Tema1.Ejercicio4;

import java.util.Random;

public class Conductor extends Thread {
    int numconductor;
    Random rdm = new Random();
    Plaza plazaOcupada;

    public Conductor(int numconductor) {
        this.numconductor = numconductor;
    }

    @Override
    public void run() {
        try {
            plazaOcupada = entrarPlaza();
            Thread.sleep(rdm.nextInt(500));
            salirPlaza();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        super.run();
    }

    private Plaza entrarPlaza() throws InterruptedException {
        boolean plazaEncontrada = false;
        Plaza plazaAsignada = null;

        synchronized (Aparcamiento.class) {
            // Busca por todo el aparcamiento
            while (!plazaEncontrada) {
                // No deja de buscar hasta que una plaza esté libre
                for (Plaza plaza : Aparcamiento.plazas) {
                    // Cuando esta libre la ocupa
                    if (plaza.isDisponible()) {
                        plaza.setDisponible(false);
                        plazaEncontrada = true;
                        System.out
                                .println("El conductor " + numconductor + " ocupó la plaza " + plaza.getNumeroPlaza());
                        return plaza;
                    }
                }
                
                // Si recorre todo el parking y no hay plazas disponbiles espera en la puerta
                if (!plazaEncontrada) {
                    Aparcamiento.class.wait();
                }

            }
        }


        return plazaAsignada;
    }

    // ¿Debe de ser sincronizado? Salir solo va a querer salir a la vez este
    // conductor , no ?
    private void salirPlaza() {

        // Esto para todos los hilos que no sean conductor? 
        
        synchronized (Aparcamiento.class){
            plazaOcupada.setDisponible(true);
            System.out.println("El conductor " + numconductor + " libero la plaza " + plazaOcupada.getNumeroPlaza());

            // Avisa al siguiente hilo para que coja la plaza
            Aparcamiento.class.notify();
        }

    }
}
