package PSP.Tema1.Parking;

public class Coches {
    
    public static void main(String[] args) throws InterruptedException {
        Cliente[] clientes = new Cliente[50];

        for (int i = 0; i < clientes.length; i++) {
            clientes[i] = new Cliente(i);
            clientes[i].start();
        }


        for (Cliente cliente : clientes) {
            cliente.join();
        }
        
    }
}
