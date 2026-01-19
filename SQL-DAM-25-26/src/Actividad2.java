import java.util.ArrayList;
import java.util.List;

import clases.Familiar;
import clases.TipoSGBD;
import gestores.GestorEmpresa;

public class Actividad2 {
    public static void main(String[] args) {
        String baseDatos = "BDEMPRESA25";
        String usuario;
        String contrasena = "abc123.";
        TipoSGBD tipo = TipoSGBD.SQLSERVER;

        usuario = switch (tipo) {
            case MYSQL -> "root";
            case SQLSERVER -> "sa";
            case SQLITE -> "";
            default -> throw new AssertionError();
        };

        // Connection connection = GestorConexion.getConnection(tipo,
        // baseDatos,usuario,contrasena);
        // System.out.println(GestorConexion.obtenerMetaDatos(connection));
        // System.out.println(connection);

        GestorEmpresa gestorEmpresa = new GestorEmpresa(tipo, baseDatos, usuario, contrasena);

        // Ejercicio 1. Visualizar o número e nome dos departamentos que teñen proxectos
        // asignados.
        gestorEmpresa.obtenerDepartamentoConProxectos();

        // Ejercicio 2. Visualizar o número e nome, nombre e apelidos do director dos
        // departamentos que teñen proxectos asignados
        gestorEmpresa.obtenerDirectoresConProxectos();

        // Ejericicio 3. Visualizar o NSS, o nome e apelidos e a idade dos empregados da
        // empresa.

        // Ejercicio 4. Dado o nome dun departamento, visualizar os empregados que
        // traballan nese departamento especificando se se trata dun empregado fixo ou
        // temporal.
        gestorEmpresa.ejercicio4("PERSOAL");

        /*
         * Ejercicio 5.
         * Dado o nome dun proxecto e unha localidade, visualizar os empregados fixos
         * que traballan nese proxecto e
         * que pertencen á localidade indicada. Mostrar: NSS, nome completo, salario e
         * nome do departamento no que
         * traballan.
         */

        /*
         * Ejercicio 6.
         * Visualizar, para cada departamento, o número de empregados fixos e o número
         * de empregados temporais
         * que traballan nel.
         */

        // gestorEmpresa.mostrarDepartamentosSalarioMayorQue();

        Familiar familiar = new Familiar();
        familiar.setNssEmpregado("0010010");
        familiar.setNss("0010033");
        familiar.setNombre("Yago");
        familiar.setApelido1("Otero");

        // gestorEmpresa.anadirFamiliar(familiar);

        // Ejercicio 4.
        // gestorEmpresa.eliminarProxecto(11);

        gestorEmpresa.subirSueldosEmpleados(new ArrayList<>(List.of("0110010")), 20);

    }
}
