package PSP.Tema1.EjercicioTierra;

public class Meteorito {

    private String referencia;
    private boolean taladrado;
    private Nave naveTaladrando;

    public Meteorito(String referencia) {
        this.referencia = referencia;
        taladrado = false;
    }

    @Override
    public String toString() {
        return "Meteorito: " + referencia;
    }

    // Devolverá boolean si ya esta taladrada o esta otra nave
    public synchronized boolean comenzarTaladrar(Nave nave) throws InterruptedException {
        if (taladrado) {
            System.out.println(
                    "La nave " + nave.getNombre() + "intento taladrar a " + referencia + " pero ya esta taladrada");
            return false;
        }

        if (naveTaladrando != null) {
            System.out.println(
                    "La nave " + nave.getNombre() + "intento taladrar a: " + referencia + " pero ya esta: "
                            + naveTaladrando.getNombre());
        }

        // Tiempo que tarda en taladrar
        Thread.sleep(100);
        taladrado = true;
        nave.necesitaRepostar = true;

        synchronized (Empresa.class) {
            notifyAll();
        }

        System.out.println("La nave: " + nave.getNombre() + " taladró el mete: " + referencia);
        return true;
    }

    // TODO : método poner bomba

}