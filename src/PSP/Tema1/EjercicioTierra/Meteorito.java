package PSP.Tema1.EjercicioTierra;

public class Meteorito {

    private String referencia;
    private boolean taladrada;
    private Nave naveTaladrando;

    public Meteorito(String referencia) {
        this.referencia = referencia;
        taladrada = false;
    }

    @Override
    public String toString() {
        return "Meteorito: " + referencia;
    }

    // Devolverá boolean si ya esta taladrada o esta otra nave
    public synchronized boolean comenzarTaladra(Nave nave) {
        if (taladrada) {
            System.out.println("La nave " + nave.getNombre() + "intento taladrar pero ya esta taladrada");
            return false;
        }

        if (naveTaladrando != null) {
            System.out.println("La nave " + nave.getNombre() + "intento taladrar pero ya esta: " + naveTaladrando.getNombre());
        }

        



        return true;
    }

    // TODO : método poner bomba

}