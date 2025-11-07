package PSP.Tema1.EjercicioTierra2;

public class Surtidora extends Nave {
    Nave navesurtiendo;

    public Surtidora(String nombre) {
        super(nombre, TipoNave.SURTIDORA);
    }

    @Override
    public void run() {

        while (!Empresa.misionesTerminadas) {
            if (!buscarNavesParaRepostar()) {
                try {
                    synchronized(Empresa.class) {
                        Empresa.class.wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println("La nave surtidora " + getNombre() + " aterriza, se terminaron los meteoritos");
    }

    private boolean buscarNavesParaRepostar() {
        for (int i = 0; i < Empresa.naves.size(); i++) {
            if (Empresa.naves.get(i).isNecesitaRepostar()) {
                try {
                    echarGasolina(Empresa.naves.get(i));
                    System.out.println("La nave " + getNombre() + " reposto a " + Empresa.naves.get(i).getNombre());
                    return true;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    public synchronized void echarGasolina(Nave nave) throws InterruptedException {
        navesurtiendo = nave;
        navesurtiendo.setNecesitaRepostar(true);

        sleep(200);

        navesurtiendo = null;
        navesurtiendo.setNecesitaRepostar(false);
    }
}
