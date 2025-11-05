package PSP.Tema1.EjercicioTierra;

public class Surtidora extends Nave {
    Nave navesurtiendo;

    public Surtidora(String nombre) {
        super(nombre, TipoNave.SURTIDORA);
    }

    @Override
    public void run() {
        Meteorito meteorito = Empresa.meteoritos.get(rdm.nextInt(Empresa.meteoritos.size()));

        try {
            if (meteorito.comenzarTaladra(nombre)) {
                sleep(rdm.nextInt(100));

            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private synchronized void surtirNave(Nave nave) throws InterruptedException {
        navesurtiendo = nave;
        navesurtiendo.setRespostando(true);

        sleep(200);

        navesurtiendo = null;
        navesurtiendo.setRespostando(false);;
    }
}
