package logica;

import dto.EmpregadoInfoProxectoDTO;
import persistencia.EmpresaDAO;


import java.sql.*;
import java.util.List;

public class GestorEmpresa {

    //region EJERCICIO 1
    // controla errores y muestra mensajes al usuario
    public static void ejercicio1(Connection con, String nss, String rua,
                                  int numero, String piso, String cp, String loc) {

        try {
            EmpresaDAO.cambioDomicilio(con, nss, rua, numero, piso, cp, loc);
            System.out.println("Domicilio actualizado correctamente");

        } catch (SQLException e) {
            System.err.println("Erro ao cambiar o domicilio: " + e.getMessage());
        }
    }
    //endregion

    //region EJERCICIO 2
    // recibe datos del dao y decide cómo mostrarlos
    public static void ejercicio2(Connection con, int numProx) {

        try {
            String[] datos = EmpresaDAO.datosProxecto(con, numProx);

            System.out.println("Nome: " + datos[0]);
            System.out.println("Lugar: " + datos[1]);
            System.out.println("Departamento: " + datos[2]);

        } catch (SQLException e) {
            System.err.println("Erro ao obter datos do proxecto");
        }
    }
    //endregion

    //region EJERCICIO 3
    // distingue entre operación de selección o actualización
    public static void ejercicio3(Connection con, int n) {

        try (ResultSet rs =
                     EmpresaDAO.departamentosPorProxectos(con, n)) {

            if (rs != null) {
                while (rs.next()) {
                    System.out.println(
                            rs.getInt(1) + " - " +
                                    rs.getString(2) + " (" +
                                    rs.getInt(3) + " proxectos)");
                }
                System.out.println("Operación de selección");
            } else {
                System.out.println("Operación de actualización");
            }

        } catch (SQLException e) {
            System.err.println("Erro na execución do procedemento");
        }
    }
    //endregion

    //region EJERCICIO 4
    // muestra el resultado de la función
    public static void ejercicio4(Connection con, String dep) {

        try {
            int total = EmpresaDAO.numeroEmpregados(con, dep);
            System.out.println("Número de empregados: " + total);

        } catch (SQLException e) {
            System.err.println("Erro ao executar a función");
        }
    }
    //endregion

    //region EJERCICIO 6
    // muestra empleados asociados a un proyecto
    public static void ejercicio6(Connection con, int numProxecto) {

        System.out.println("EJERCICIO 6");

        try {
            List<EmpregadoInfoProxectoDTO> lista =
                    EmpresaDAO.obtenerEmpleadosProxecto(con, numProxecto);

            if (lista.isEmpty()) {
                System.out.println("non hai empregados no proxecto");
            } else {
                for (EmpregadoInfoProxectoDTO e : lista) {
                    System.out.println(e);
                }
            }

        } catch (SQLException e) {
            System.err.println("erro ao obter empregados do proxecto" + e.getMessage());
        }
    }
    //endregion
}
