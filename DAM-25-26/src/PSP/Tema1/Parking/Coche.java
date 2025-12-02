package PSP.Tema1.Parking;

import java.util.Random;

public class Coche {
    String modelo;
    int unidades;
    int contadorClientes;
    boolean ocupado;
    Cliente comprador;

    public Coche(String modelo, int unidades) {
        this.modelo = modelo;
        this.unidades = unidades;
        this.contadorClientes = 0;
        ocupado = false;
    }

    public synchronized boolean probarCoche(Cliente cliente) {
        Random rdm = new Random();
        try {
            System.out.println(cliente + " esta mirando el coche " + this);
            Thread.sleep(20);
        } catch (Exception e) {
        }

        if (rdm.nextInt(100) < ++contadorClientes) {
            return true;
        }

        return false;
    }

    // Sincronizado?
    public synchronized void comprarCoche(Cliente cliente) {
        comprador = cliente;

        System.out.println(cliente + " ha comprado el " + this);
    }

    public synchronized void desOcuparCoche() {
        ocupado = false;
    }

    @Override
    public String toString() {
        return modelo;
    }

    // TODO Compra y cuando estoy decidiendo que no molesten

}
