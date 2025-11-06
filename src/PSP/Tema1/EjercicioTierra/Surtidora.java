package PSP.Tema1.EjercicioTierra;

public class Surtidora extends Nave {
    Nave navesurtiendo;

    public Surtidora(String nombre) {
        super(nombre, TipoNave.SURTIDORA);
    }

    @Override
    public void run() {

        while (!Empresa.meteoritos.isEmpty()) {
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
    }

    private boolean buscarNavesParaRepostar() {
        for (int i = 0; i < Empresa.naves.size(); i++) {
            if (Empresa.naves.get(i).isNecesitaRepostar()) {
                try {
                    echarGasolina(Empresa.naves.get(i));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return true;
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
