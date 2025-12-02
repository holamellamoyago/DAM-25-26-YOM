package PSP.Tema1.EjercicioTierraCorregido;

public abstract class Nave extends Thread{
    public Nave (String nombre) {
        super(nombre);
    }

    abstract void trabajar();

    @Override
    public void run() {
        System.out.println(getName() + " despegando");

        while (HWWC.hayMeteoritos()) {
            trabajar();
        }

        System.out.println(getName() + " aterriza");
    }

    @Override
    public String toString() {
        return "Nave " + getName();
    }

    
}
