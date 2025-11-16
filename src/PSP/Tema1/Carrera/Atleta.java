package PSP.Tema1.Carrera;

public class Atleta extends Thread{
    private Carrera carrera;
    boolean asignado = false;

    public Atleta (int i, Carrera carrera) {
        super("Atleta" + String.valueOf(i));
        this.carrera = carrera;
    }

    @Override
    public void run() {
        // carrera.asignarCalle(this);

        synchronized (carrera){
            try {
                carrera.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(this + " cooomienza la carrera! ");
        }


    }

    public boolean isAsignado() {
        return asignado;
    }

    public void setAsignado(boolean asignado) {
        this.asignado = asignado;
    }

    @Override
    public String toString() {
        return getName();
    }


    

    
}
