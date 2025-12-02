package PSP.Tema1.Ejercicio5;


public class App {
    public static void main(String[] args) {
        desperatarPersonas();
    }

    private static void desperatarPersonas() {
        Oficina ofi = new Oficina();
        
        Empleado e1 = new Empleado("Manolo", ofi);
        Empleado e2 = new Empleado("Pepe", ofi);
        Empleado e3 = new Empleado("faustino", ofi);
        Jefe jefe = new Jefe("Perro Sanxe", ofi);
        Persona[] personas = new Persona[] { e1, e2, e3, jefe };
        
        
        ofi.setPersonas(personas);
        ofi.ficharEmpleados();




    }
}
