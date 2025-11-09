package PSP.Tema1.EjercicioTierra;

public class Armageddon extends Nave {

    public Armageddon(String nombre) {
        super(nombre);
    }

    @Override
    public void run() {
        Meteorito meteorito = Empresa.meteoritos.get(rdm.nextInt(Empresa.meteoritos.size()));

        try {
            while (!Empresa.estanTodasExplotadas()) {
                if (meteorito.comenzarTaladrar(this)) {
                    // System.out.println("Meteoritos disponibles: " + Empresa.meteoritos);

                    // Error 3 : no era explotadop
                    // meteorito.explotar();
                } else {
                    // Busca otro meteorito al que atacar
                    // Si la lista esta vacía nunca va a encontrar
                    if (Empresa.estanTodasExplotadas()) {
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
