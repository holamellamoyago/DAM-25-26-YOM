package persistencia;

import dto.EmpregadoInfoProxectoDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmpresaDAO {

    //region EJERCICIO 1
    // procedimiento  que actualiza el domicilio de un empleado
    public static void cambioDomicilio(Connection con,
                                       String nss, String rua, int numero,
                                       String piso, String cp, String localidade) throws SQLException {

        CallableStatement cs =
                con.prepareCall("{ call pr_CambioDomicilio(?,?,?,?,?,?) }");

        cs.setString(1, nss);
        cs.setString(2, rua);
        cs.setInt(3, numero);
        cs.setString(4, piso);
        cs.setString(5, cp);
        cs.setString(6, localidade);

        cs.execute();
        cs.close();
    }
    //endregion

    //region EJERCICIO 2
    // obtiene datos de un proyecto usando parámetros out
    //podria devolver también un objeto proyecto o un proyectoDTO
    public static String[] datosProxecto(Connection con, int numProxecto) throws SQLException {

        CallableStatement cs =
                con.prepareCall("{ call pr_DatosProxectos(?,?,?,?) }");

        cs.setInt(1, numProxecto);

        cs.registerOutParameter(2, Types.VARCHAR);
        cs.registerOutParameter(3, Types.VARCHAR);
        cs.registerOutParameter(4, Types.VARCHAR);

        cs.execute();

        String[] datos = {
                cs.getString(2),
                cs.getString(3),
                cs.getString(4)
        };

        cs.close();
        return datos;
    }
    //endregion

    //region EJERCICIO 3
    // devuelve un resultset con los departamentos que controlan n o más proyectos
    public static ResultSet departamentosPorProxectos(Connection con, int n)
            throws SQLException {

        CallableStatement cs =
                con.prepareCall("{ call pr_DepartControlaProxec(?) }");

        cs.setInt(1, n);
        boolean tieneRS = cs.execute();

        return tieneRS ? cs.getResultSet() : null;
    }
    //endregion

    //region EJERCICIO 4
    // ejecuta una función que devuelve el número de empleados de un departamento
    public static int numeroEmpregados(Connection con, String dep)
            throws SQLException {

        CallableStatement cs =
                con.prepareCall("{ ? = call fn_nEmpDepart(?) }");

        cs.registerOutParameter(1, Types.INTEGER);
        cs.setString(2, dep);

        cs.execute();

        int total = cs.getInt(1);
        cs.close();
        return total;
    }
    //endregion

    //region EJERCICIO 6
    // obtiene empleados asociados a un proyecto y los devuelve como dto
    public static List<EmpregadoInfoProxectoDTO> obtenerEmpleadosProxecto(
            Connection con, int numProxecto) throws SQLException {

        List<EmpregadoInfoProxectoDTO> lista = new ArrayList<>();

        String sql = "{ call sp_empleados_por_proyecto(?) }";

        try (CallableStatement cs = con.prepareCall(sql)) {

            cs.setInt(1, numProxecto);

            try (ResultSet rs = cs.executeQuery()) {

                while (rs.next()) {

                    EmpregadoInfoProxectoDTO dto =
                            new EmpregadoInfoProxectoDTO(
                                    rs.getString("NSS"),
                                    rs.getString("NomeCompleto"),
                                    rs.getString("Lugar"),
                                    rs.getInt("NumDepartControla")
                            );

                    lista.add(dto);
                }
            }
        }

        return lista;
    }
    //endregion
}
