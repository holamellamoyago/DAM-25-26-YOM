package PSP.Tema1.Parking;

import java.util.Random;

public class Cliente extends Thread {
    private String nombre;

    public Cliente(int numero) {
        this.nombre = "Cliente".concat(String.valueOf(numero));
    }

    @Override
    public void run() {
        // Agenda la cita
        Coche cocheMirando = Concesionario.agendarCoche();

        while (Concesionario.quedanCoches()) {
            if (cocheMirando != null) {
                if (cocheMirando.probarCoche(this)) {
                    cocheMirando.comprarCoche(this);
                    break;
                } else {
                    cocheMirando.desOcuparCoche();
                    cocheMirando = Concesionario.agendarCoche();
                }
            }
        }

    }

    @Override
    public String toString() {
        return nombre;
    }


    

}
