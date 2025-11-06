package PSP.Tema1.EjercicioTierra;

public class Armageddon extends Nave {

    public Armageddon(String nombre) {
        super(nombre, TipoNave.Armageddon);
    }

    @Override
    public void run() {
        Meteorito meteorito = Empresa.meteoritos.get(rdm.nextInt(Empresa.meteoritos.size()));

        try {
            while (Empresa.meteoritos.size() > 0) {
                if (meteorito.comenzarTaladrar(this)) {
                    Empresa.meteoritos.remove(meteorito);
                    System.out.println("Meteoritos disponibles: " + Empresa.meteoritos);

                } else {
                    // Busca otro meteorito al que atacar
                    // Si la lista esta vacía nunca va a encontrar
                    if (Empresa.meteoritos.isEmpty()) {
                        System.out.println("La nave " + nombre + " aterriza, se termianron los meteoritos");
                        return;
                    } else {
                        meteorito = Empresa.meteoritos.get(rdm.nextInt(Empresa.meteoritos.size()));
                    }
                }

            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
