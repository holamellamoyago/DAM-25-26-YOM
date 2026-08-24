package PSP.Tema1.oficina;

public class Principal {
    public static void main(String[] args) {
        Oficina oficina = new Oficina();
        Empleado[] empleados = new Empleado[4];

        new Jefe(oficina).start();

        for (int i = 0; i < empleados.length; i++) {
            empleados[i] = new Empleado(oficina, "Empleado " + i);
            empleados[i].start();
        }



    }
}
