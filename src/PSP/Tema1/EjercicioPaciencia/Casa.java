package PSP.Tema1.EjercicioPaciencia;

public class Casa {
    private static final int NUM_PACIENCIA = 3;

    public static void main(String[] args) {
        Trabajador tra = new Trabajador(NUM_PACIENCIA);
        tra.start();

        for (int i = 0; i < NUM_PACIENCIA; i++) {
            System.out.println("El repartidor llama a la puerta ... ");

            // TODO Hacer
            tra.setPaciencia(tra.getPaciencia() - 1);

            System.out.println("Paciencia restante: " + tra.getPaciencia());

            //TODO trabjador
            if (tra.getPaciencia() <= 0) {
                tra.interrupt();
            }

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
