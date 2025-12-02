package PSP.Tema1.EjercicioTierraCorregido;

import java.util.Random;

public class Meteorito {
    private String referencia;
    private boolean taladrado, explotado;
    private static Random rdm = new Random();

    public Meteorito(String referencia) {
        this.referencia = referencia;
        taladrado = false;
        explotado = false;
    }

    public synchronized void taladrar() {
        try {
            if (taladrado)
                return;

            Thread.sleep(rdm.nextInt(500) + 300);
            taladrado = true;
            System.out.println("Meteorito " + getReferencia() + " taladrado");

            wait();

        } catch (Exception e) {
            e.printStackTrace();
        }
        ;
    }

    public synchronized boolean explotar() {
        if (!taladrado)
            return false;

        System.out.println("Meteorito " + getReferencia() + " explotado");
        notify();

        return true;

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

    public boolean isExplotado() {
        return explotado;
    }

    public void setExplotado(boolean explotado) {
        this.explotado = explotado;
    }

}
