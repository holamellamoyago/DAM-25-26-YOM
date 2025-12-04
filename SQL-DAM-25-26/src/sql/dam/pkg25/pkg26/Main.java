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
public class Main {
    public static void main(String[] args) {
        String baseDatos = "BDEMPRESA25";
        String usuario;
        String contrasena = "abc123.";
        TipoSGBD tipo = TipoSGBD.SQLITE;
        
        usuario = switch (tipo) {
            case MYSQL -> "root";
            case SQLSERVER -> "sa";
            case SQLITE -> "";
            default -> throw new AssertionError();
        };

        Connection connection =  GestorConexion.getConnection(tipo, baseDatos,usuario,contrasena);
        
        System.out.println(GestorConexion.obtenerMetaDatos(connection));

        System.out.println(connection);
    }
}
