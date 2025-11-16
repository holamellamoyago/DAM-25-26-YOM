package PSP.Tema1.Carrera;

import java.util.Random;

public class Carrera {
    private int metros;
    boolean comenzoCarrera = false;

    public Carrera() {
        if (new Random().nextInt(100) > 50) {
            this.metros = 110;
        } else {
            this.metros = 100;
        }
    }

    public synchronized void asignarCalle(Atleta atleta){
        
        for (int i = 0; i < Carreras.calles.length; i++) {
            Calle calle = Carreras.calles[i];

            if (calle.getAtleta() == null) {
                calle.setAtleta(atleta);
                return;
            }
        }
    }

    public synchronized void comenzarCarrera(){
        comenzoCarrera = true;
        System.out.println("El juez cmienza la carrera");
        notifyAll();

    }

}
