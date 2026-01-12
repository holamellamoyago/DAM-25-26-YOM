package PSP.Tema1.concesionario;

public class Principal {
    public static void main(String[] args) {
        Concesionario concesionario = new Concesionario();

        final int NUMERO_CLIENTES  = 10;
        Cliente[] clientes = new Cliente[NUMERO_CLIENTES];

        for (int i = 0; i < NUMERO_CLIENTES; i++) {
            clientes[i] = new Cliente(concesionario, i);
            clientes[i].start();
        }
    }
}
