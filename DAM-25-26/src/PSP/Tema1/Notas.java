package PSP.Tema1;

import PSP.Tema1.EjercicioTierra.Meteorito;

public class Notas {
    public static void main(String[] args) {
        // Lectura anticipada
        Meteorito meteorito;
        while ((meteorito = getMeteorito()) != null) {
            
        }
    }

    private Meteorito getMeteorito() {
        return new Meteorito("M33");
    }
}
