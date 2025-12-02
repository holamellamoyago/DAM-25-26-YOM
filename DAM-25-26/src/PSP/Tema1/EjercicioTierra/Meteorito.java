package PSP.Tema1.EjercicioTierra;

public class Meteorito {

    private String referencia;
    private boolean taladrado;
    private Nave naveTaladrando;
    private boolean explotado;

    public Meteorito(String referencia) {
        this.referencia = referencia;
        taladrado = false;
        explotado = false;
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

        if (explotado) {
            System.out.println(
                    "La nave " + nave.getNombre() + "intento explotar a " + referencia + " pero ya esta taladrada");
            return false;
        }

        if (naveTaladrando != null) {
            System.out.println(
                    "La nave " + nave.getNombre() + "intento taladrar a: " + referencia + " pero ya esta: "
                            + naveTaladrando.getNombre());

            // Error 1 : me olvidé de poner el false
            return false;
        }

        // Tiempo que tarda en taladrar
        Thread.sleep(500);

        // Error 2 : no settear
        naveTaladrando = nave;
        taladrado = true;
        nave.necesitaRepostar = true;

        System.out.println("La nave: " + nave.getNombre() + " taladró el mete: " + referencia);

        while (!explotado) {
            synchronized (Empresa.class) {
                wait();
            }
        }

        naveTaladrando = null;
        return true;
    }

    public synchronized boolean explotar() {
        if (!taladrado || explotado) {
            return false;
        }

        explotado = true;

        synchronized (Empresa.class) {
            notifyAll();
        }

        System.out.println("El meteorito " + referencia + " fue explotado");

        return true;
    }

    public boolean isExplotado() {
        return explotado;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public boolean isTaladrado() {
        return taladrado;
    }

    public void setTaladrado(boolean taladrado) {
        this.taladrado = taladrado;
    }

    

    // private Surtidora buscarNave() {
    // for (Nave n : Empresa.getNaves()) {
    // if (n instanceof Surtidora) {
    // Surtidora surtidora = (Surtidora) n;
    // if (!surtidora.isSurtiendo()) {
    // return surtidora;
    // }

    // }
    // }

    // return null;
    // }

}