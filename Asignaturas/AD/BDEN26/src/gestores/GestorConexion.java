package gestores;

import java.sql.*;
import java.util.ArrayList;

import clases.*;


public class GestorConexion {

    public static Connection getConnection(TipoSGBD tipo, String baseDatos, String usuario, String contrasena) {
        System.out.println(usuario);
        String url;
        url = switch (tipo) {
            case SQLSERVER ->
                "jdbc:sqlserver://localhost:1433;" + "databaseName=" + baseDatos + ";" + "encrypt=true;"
                        + "trustServerCertificate=true";
            case MYSQL -> "jdbc:mysql://localhost:3306/" + baseDatos + "?serverTimezone=UTC";
            // "jdbc:mysql://localhost:3306/" + baseDatos;
            case SQLITE -> "jdbc:sqlite:" + baseDatos;
            default -> "";
        };
        try {
            if (tipo == TipoSGBD.SQLITE) {

                Connection con = DriverManager.getConnection(url);

                Statement stmt = con.createStatement();
                stmt.execute("PRAGMA foreign_keys = ON");
                return con;

            } else {
                return DriverManager.getConnection(url, usuario, contrasena);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }  

    public static ResultSet ejecutarConsulta(Connection conn, String sqlConsulta, Object... parametros)
            throws SQLException, SQLException {
        PreparedStatement stmt = conn.prepareStatement(sqlConsulta);

        for (int i = 0; i < parametros.length; i++) {
            stmt.setObject(i + 1, parametros[i]);
        }

        return stmt.executeQuery();
    }


    public static void setParametros(PreparedStatement ps, Object... params) throws SQLException {
        for (int i = 0; i < params.length; i++) {
            ps.setObject(i + 1, params[i]);
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

    public static boolean tablaExiste(Connection conn, String tabla) {
        try (ResultSet rs = conn.getMetaData().getTables(null, null, tabla, null)) {
            return rs.next();
        } catch (SQLException e) {
            System.out.println("Error al consultar tablas existentes");
            throw new RuntimeException(e);
        }
    }

    public static void ejecutarLoteTransacioneal(Connection conn, String... sentenciasSQL) throws SQLException {
        try {
            conn.setAutoCommit(false);
            Statement stmt = conn.createStatement();

            for (String sql : sentenciasSQL) {
                stmt.addBatch(sql);
            }

            stmt.executeBatch();
            conn.commit();

        } catch (SQLException e) {
            conn.rollback();
            throw new RuntimeException(e);
        } finally {
            conn.setAutoCommit(true);
        }
    }

    public static int ejecutarSentencia(Connection conn, String sql, Object... params) {
        try {
            PreparedStatement stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            setParametros(stmt, params);
            return (stmt.executeUpdate());

        } catch (SQLException e) {
            e.printStackTrace();
            return -99;
        }
    }


    public static void deshacerCambios(Connection conn) {
        try {
            conn.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void desactivarAutoCommit(Connection conn) {
        try {
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void activarAutoCommit(Connection conn) {
        try {
            conn.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void hacerCommit(Connection conn) {
        try {
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
