package PSP.Tema1.EjercicioTierraCorregido;

public class NaveBS extends Nave {

    public NaveBS(String nombre) {
        super(nombre);
    }

    @Override
    void trabajar() {
        HWWC.explotar();
    }
    
}
