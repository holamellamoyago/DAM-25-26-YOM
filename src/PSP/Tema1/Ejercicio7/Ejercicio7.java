package PSP.Tema1.Ejercicio7;

import java.util.ArrayList;
import java.util.Comparator;

public class Ejercicio7 {
    static final int NUM_EMPLEADOS = 50;
    static ArrayList<Empleado> empleados = new ArrayList<>();
    static ArrayList<Empleado> empleadosGanados = new ArrayList<>();
    static Resultado resultado = new Resultado();

    static int totalIngresos = 0;
    static int totalApuestasIndividuales = 0;

    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < NUM_EMPLEADOS; i++) {
            Empleado e = new Empleado(i);
            e.start();
            empleados.add(e);
        }

        for (Empleado e : empleados) {
            e.join();
            System.out.println(e);
            System.out.println();
        }

        mostrarResultados();

        mostrarEmpleadosGanaron();
    }

    private static void mostrarResultados() {
        System.out.println(resultado);
        mostrarIngresosTotales();
    }

    private static void mostrarIngresosTotales() {

        for (Empleado empleado : empleados) {
            totalIngresos += empleado.getDineroApostado();
            totalApuestasIndividuales += empleado.porra.getCantidadApuestas();
        }

        System.out.println("Total ingresos: " + totalIngresos);
    }

    private static void mostrarEmpleadosGanaron() {
        for (Empleado empleado : empleados) {
            if (empleado.porra.getGolesDM2() == resultado.getGolesDM2()
                    && empleado.porra.getGolesPRF() == resultado.getGolesPRF()) {
                empleadosGanados.add(empleado);
            }
        }

        System.out.println("Ganadores: ");

        for (Empleado empleado : empleadosGanados) {
            System.out.println(empleado);
            System.out.println("Gano un total de: "
                    + calcularDineroRepartirIndividualmente() * empleado.porra.getCantidadApuestas() + "€");
        }
    }

    private static int calcularDineroRepartirIndividualmente() {
        return totalIngresos / totalApuestasIndividuales;

    }
}