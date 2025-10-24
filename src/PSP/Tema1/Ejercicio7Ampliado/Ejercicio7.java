package PSP.Tema1.Ejercicio7Ampliado;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

public class Ejercicio7 {
    static final int NUM_EMPLEADOS = 10;
    static ArrayList<Empleado> empleados = new ArrayList<>();
    static Set<Empleado> empleadosGanados = new TreeSet<>();
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
            totalApuestasIndividuales += empleado.apuestas.size();
        }

        System.out.println("Total ingresos: " + totalIngresos);
    }

    private static void mostrarEmpleadosGanaron() {

        for (Empleado empleado : empleados) {

            for (Apuesta apuesta : empleado.apuestas) {
                if (apuesta.getGolesDM2() == resultado.getGolesDM2()
                        && apuesta.getGolesPRF() == resultado.getGolesPRF()) {
                    empleadosGanados.add(empleado);
                }
            }
        }

        System.out.println("Ganadores: " + empleadosGanados.size());

        int dineroIndividual = calcularDineroRepartirIndividualmente();
        System.out.println("Dinero a repartir individualmente " + dineroIndividual);

        for (Empleado empleado : empleadosGanados) {
            System.out.println();

            System.out.println(empleado);

            System.out.println("Gano un total de: "
                    + dineroIndividual * calcularDineroGanado(empleado) + "€");

        }
    }

    private static int calcularDineroRepartirIndividualmente() {
        return totalIngresos / totalApuestasIndividuales;

    }

    private static int calcularDineroGanado(Empleado e) {
        int totalDineroGanado = 0;

        for (Apuesta apuesta : e.apuestas) {
            // multiplicarlo
            if (apuesta.getGolesDM2() == resultado.getGolesDM2()
                    && apuesta.getGolesPRF() == resultado.getGolesPRF()) {
                totalDineroGanado += apuesta.getPrecio();
            }
        }

        return totalDineroGanado;
    }
}