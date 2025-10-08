package PSP.Tema1.Ejercicio5;

public class Empleado extends Thread{
    private String nombre;

    public Empleado(String nombre){
        this.nombre = nombre;
    }

    

    @Override
    public void run() {
        
        super.run();
    }

    public String getNombre() {
        return nombre;
    }
}
