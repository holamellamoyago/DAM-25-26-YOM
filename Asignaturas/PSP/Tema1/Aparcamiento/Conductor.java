package PSP.Tema1.aparcamiento;

import java.util.Random;

public class Conductor extends Thread {
    private int id;
    private boolean estanciaTerminada;
    private Aparcamiento aparcamiento;

    public Conductor(int id, Aparcamiento aparcamiento) {
        this.aparcamiento = aparcamiento;
        this.estanciaTerminada = false;
        this.id = id;
    }

    @Override
    public void run() {

        while (!estanciaTerminada) {
            
            if (aparcamiento.ocuparPlaza(this)) {
                try {
                    System.out.println("El conductor " + id + " ocupa plaza de aparcamiento.");
                    System.out.println(aparcamiento.toString());

                    sleep(new Random().nextInt(500));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                estanciaTerminada = true;
                aparcamiento.dejarPlaza(this);
                System.out.println("El conductor " + id + " dejó su plaza de aparcamiento.");

                synchronized (aparcamiento) {
                    aparcamiento.notify();
                }

            } else {
                synchronized (aparcamiento) {
                    try {
                        aparcamiento.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Conductor other = (Conductor) obj;
        if (id != other.id)
            return false;
        return true;
    }

}
