/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sql.dam.pkg25.pkg26;

import java.sql.*;

/**
 *
 * @author usuario
 */
public class GestorConexion {

    /*

    String driverClass = switch (tipo) {
        case SQLSERVER -> "com.icrosoft.sqlserver.jdbc.SQLSer"
    }

     */
    public static Connection getConnection(TipoSGBD tipo, String baseDatos, String usuario, String contrasena) {
        System.out.println(usuario);
        String url;
        url = switch (tipo) {
            case SQLSERVER ->
                "jdbc:sqlserver://localhost:1433;" + "databaseName=" + baseDatos + ";" + "encrypt=true;" + "trustServerCertificate=true";
            case MYSQL ->
                "jdbc:mysql://localhost:3306/" + baseDatos + "?serverTimezone=UTC";
                //"jdbc:mysql://localhost:3306/" + baseDatos;
            case SQLITE ->
                "jdbc:sqlite:" + baseDatos;
            default ->
                "";
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

}
