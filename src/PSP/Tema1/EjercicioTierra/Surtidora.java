package PSP.Tema1.EjercicioTierra;

public class Surtidora extends Nave {
    Nave navesurtiendo;

    public Surtidora(String nombre) {
        super(nombre);
    }

    @Override
    public void run() {

        // Los inicio explotados y que esperén a que haya un notify
        try {
            synchronized (Empresa.class) {
                wait();
            }

            while (!Empresa.estanTodasExplotadas()) {
                // Busca uno NO EXPLOTADO, lo explota y vuelve a esperar a que haya otro notify
                Meteorito meteorito = buscarMeteoritosExplotar();

                if (meteorito != null) {
                    meteorito.explotar();
                }

                synchronized (Empresa.class) {
                    wait();
                }
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("La nave surtidora " + getNombre() + " aterriza, se terminaron los meteoritos");
    }

    private Meteorito buscarMeteoritosExplotar() {
        for (Meteorito m : Empresa.getMeteoritos()) {
            if (!m.isExplotado() && m.isTaladrado()) {
                return m;
            }
        }

        return null;

    }

    // private boolean buscarNavesParaRepostar() {
    // for (int i = 0; i < Empresa.naves.size(); i++) {
    // if (Empresa.naves.get(i).isNecesitaRepostar()) {
    // try {
    // echarGasolina(Empresa.naves.get(i));
    // System.out.println("La nave " + getNombre() + " reposto a " +
    // Empresa.naves.get(i).getNombre());
    // } catch (InterruptedException e) {
    // e.printStackTrace();
    // }
    // return true;
    // }
    // }
    // return false;
    // }

    public synchronized void echarGasolina(Nave nave) throws InterruptedException {
        navesurtiendo = nave;
        navesurtiendo.setNecesitaRepostar(true);

        sleep(200);

        navesurtiendo = null;
        navesurtiendo.setNecesitaRepostar(false);
    }

    public boolean isSurtiendo() {
        return navesurtiendo != null;
    }

}
