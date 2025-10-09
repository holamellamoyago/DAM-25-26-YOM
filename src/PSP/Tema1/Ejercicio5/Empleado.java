package PSP.Tema1.Ejercicio5;

public class Empleado extends Persona {

    public Empleado(String nombre, Oficina ofi) {
        super(nombre, ofi);
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public void run() {
        ofi.ficharEmpleado(this);
        super.run();
    }

}
