
public class Hilo extends Thread {

    private int numero;

    public Hilo(int numero) {
        this.numero = numero;
    }

    @Override
    public void run() {
        synchronized (ArrancarCarrera.lock) {
            try {
                // System.out.println(ArrancarCarrera.hilosEsperando);
                ArrancarCarrera.numHilosEsperando++;
                ArrancarCarrera.lock.wait();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Corredor empieza a correr");
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + numero;
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
        Hilo other = (Hilo) obj;
        if (numero != other.numero)
            return false;
        return true;
    }

}