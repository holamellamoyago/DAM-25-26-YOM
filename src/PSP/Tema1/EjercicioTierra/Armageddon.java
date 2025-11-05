package PSP.Tema1.EjercicioTierra;

public class Armageddon extends Nave {
    boolean trabajoTerminado = false;

    public Armageddon(String nombre) {
        super(nombre, TipoNave.Armageddon);
    }

    @Override
    public void run() {
        Meteorito meteorito = Empresa.meteoritos.get(rdm.nextInt(Empresa.meteoritos.size()));

        try {
            while (!trabajoTerminado) {
                if (meteorito.comenzarTaladra(this)) {
                    sleep(rdm.nextInt(300));
    
                    solicitarSurtir();

                    trabajoTerminado = true;
    
                } else{
                    // Busca otro meteorito al que atacar
                    // Si la lista esta vacía nunca va a encontrar
                    if (Empresa.meteoritos.isEmpty()) {
                        System.out.println("La nave " + nombre + " aterriza, se termianron los meteoritos");
                        trabajoTerminado = true;
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


    private void solicitarSurtir(){
        
    }
}
