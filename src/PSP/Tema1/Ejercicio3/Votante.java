package PSP.Tema1.Ejercicio3;

import java.util.Random;

public class Votante extends Thread {

     final static int NUM_MAX_PARTIDOS = 5;
    public  static Contador[] votos = new Contador[NUM_MAX_PARTIDOS];

    @Override
    public void run() {
        
            // Thread.sleep(rdm.nextInt(500));

            // int num = rdm.nextInt(App.NUM_MAX_PARTIDOS);
            // int comodin = contador.votos[num] +1 ;

            // synchronized (app) {
            // app.votarPartido(num, comodin);
            // }

            // contador.votarPartido(num, comodin);

            votarPartido(i, MAX_PRIORITY);



        
    }

    public synchronized static void votarPartido(int i, int valor) {

        votos[i].aumentarcontador();

    }

}
