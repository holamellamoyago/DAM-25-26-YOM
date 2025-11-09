package PSP.Tema1.EjercicioTierraCorregido;

public class NaveA extends Nave{

    public NaveA(String nombre) {
        super(nombre);
    }

    @Override
    void trabajar() {
        while (HWWC.hayMeteoritos()) {
            HWWC.taladrar();
        }
    }
    
}
