package PSP.Tema1.Ejercicio5;

public class Jefe extends Persona {

    public Jefe(String nombre, Oficina ofi) {
        super(nombre, ofi);
    }


    public String getNombre() {
        return nombre;
    }

    @Override
    public void run() {

        ofi.ficharJefe(this);

        super.run();
    }

}
