package PSP.Tema1.Ejercicio6Corregido;

public class Pescador extends Thread {
    Archivo archivo;
    long retraso;

    public Pescador(String nombre, Archivo archivo, long retraso){
        super(nombre);
        this.archivo = archivo;
        this.retraso = retraso;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(retraso);
        } catch (Exception e) {}
        

        String linea = archivo.getLinea();

        // Aqui se hace null por si el archivo esta vacío !! 
        while (linea != null) {
            System.out.println(getName() + ": " + linea);

            try {
                Thread.sleep(retraso);
            } catch (Exception e) {}

            linea = archivo.getLinea();
        }
    }
}
