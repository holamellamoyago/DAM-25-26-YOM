package Pintores;

public class Pintor extends Thread {
    String color;
    Casa casa;

    public Pintor(int i, Casa casa) {
        super("Pintor" + String.valueOf(i));
        this.color = "Color" + String.valueOf(i);
        this.casa = casa;
    }

    @Override
    public void run() {
        casa.abrirPuerta(this);

        pintarTabique();

        casa.cerrarPuerta(this);
    }

    @Override
    public String toString() {
        return getName();
    }

    private void pintarTabique() {
        for (Tabique ta : casa.tabiques) {
            ta.pintado = true;
            ta.pintor = this;

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
            }
        }
    }





}
