package PSP.Tema1.Ejercicio5;

public class Jefe extends Thread {
    private String nombre;

    public Jefe(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public void run() {
        
        super.run();
    }

    
    
}
