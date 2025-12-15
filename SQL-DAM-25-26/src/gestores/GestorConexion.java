package gestores;/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.sql.*;
import java.util.ArrayList;

import clases.*;

/**
 *
 * @author usuario
 */
public class GestorConexion {

    public static Connection getConnection(TipoSGBD tipo, String baseDatos, String usuario, String contrasena) {
        System.out.println(usuario);
        String url;
        url = switch (tipo) {
            case SQLSERVER ->
                    "jdbc:sqlserver://localhost:1433;" + "databaseName=" + baseDatos + ";" + "encrypt=true;" + "trustServerCertificate=true";
            case MYSQL -> "jdbc:mysql://localhost:3306/" + baseDatos + "?serverTimezone=UTC";
            //"jdbc:mysql://localhost:3306/" + baseDatos;
            case SQLITE -> "jdbc:sqlite:" + baseDatos;
            default -> "";
        };
        try {
            if (tipo == TipoSGBD.SQLITE) {
                return DriverManager.getConnection(url);

            } else {
                return DriverManager.getConnection(url, usuario, contrasena);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static String obtenerMetaDatos(Connection con) {
        try {
            var meta = con.getMetaData();

            StringBuilder sb = new StringBuilder();
            sb.append("Driver name: " + meta.getDriverName()).append("\n");
            sb.append("Driver version: " + meta.getDriverVersion()).append("\n");
            sb.append("Producto bd: " + meta.getDatabaseProductName()).append("\n");
            sb.append("Version BD: " + meta.getDatabaseProductVersion()).append("\n");
            sb.append("URL: " + meta.getURL()).append("\n");
            sb.append("Usuario: " + meta.getUserName()).append("\n");
            return sb.toString();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ResultSet ejecutarConsulta(Connection conn, String sqlConsulta, ArrayList<Object> parametros) throws SQLException, SQLException {
        PreparedStatement stmt = conn.prepareStatement(sqlConsulta);

        for (int i = 1; i < parametros.size(); i++) {
            stmt.setObject(i, parametros.get(i));
        }

        return stmt.executeQuery();
    }

    public static void insertarDatos(Connection conn, String sqlConsulta, Object... parametros) throws SQLException, SQLException {

        try (PreparedStatement stmt = conn.prepareStatement(sqlConsulta)) {
            conn.setAutoCommit(false);
            for (int i = 0; i < parametros.length; i++) {
                stmt.setObject(i + 1, parametros[i]);
            }

            stmt.executeUpdate();
            conn.commit();

        } catch (Exception e) {
            conn.rollback();
            e.printStackTrace();
        }

    }



//    public static void insertarDatosMultiples(Connection conn, String sqlConsulta, Object... parametros) throws SQLException, SQLException {
//
//        conn.setAutoCommit(false);
//
//        try (PreparedStatement stmt = conn.prepareStatement(sqlConsulta)) {
//
//            for (int i = 0; i < parametros.length; i++) {
//                stmt.setObject(i + 1, parametros[i]);
//            }
//
//            stmt.addBatch();
//
//            stmt.executeBatch();
//            System.out.println(("Hasta qui"));
//            conn.commit();
//
//        } catch (Exception e) {
//            conn.rollback();
//            e.printStackTrace();
//        }
//
//    }


    public static void cerrarConexion(Connection conn) throws SQLException {
        if (conn != null) {
            conn.close();
        }
    }

    public static void borrarTablas(Connection conn, String... tablas) {
        try {
            conn.setAutoCommit(false);

            try (Statement stmt = conn.createStatement()) {
                for (String tabla : tablas) {
                    if (tablaExiste(conn, tabla)) {
                        stmt.addBatch("DROP TABLE " + tabla);
                    }
                }

                stmt.executeBatch();
                conn.commit();
            } catch (SQLException ex) {
                conn.rollback();
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static boolean tablaExiste(Connection conn, String tabla)  {
        try (ResultSet rs = conn.getMetaData().getTables(null, null, tabla, null)) {
            return rs.next();
        } catch (SQLException e) {
            System.out.println("Error al consultar tablas existentes");
            throw new RuntimeException(e);
        }
    }

    public static void ejecutarLoteTransacioneal(Connection conn, String... sentenciasSQL) {
        try {
            conn.setAutoCommit(false);
            Statement stmt = conn.createStatement();

            for (String sql : sentenciasSQL) {
                stmt.addBatch(sql);
            }

            stmt.executeBatch();
            conn.commit();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
