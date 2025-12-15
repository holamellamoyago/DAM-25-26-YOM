/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import clases.Departamento;
import clases.TipoSGBD;
import gestores.GestorEmpresa;

/**
 *
 * @author usuario
 */
public class Ejercicio6 {
    public static void main(String[] args) {
        String baseDatos = "BDEMPRESA25";
        String usuario;
        String contrasena = "abc123.";
        TipoSGBD tipo = TipoSGBD.MYSQL;
        
        usuario = switch (tipo) {
            case MYSQL -> "root";
            case SQLSERVER -> "sa";
            case SQLITE -> "";
            default -> throw new AssertionError();
        };

        //Connection connection =  GestorConexion.getConnection(tipo, baseDatos,usuario,contrasena);
        //System.out.println(GestorConexion.obtenerMetaDatos(connection));
        //System.out.println(connection);


        GestorEmpresa gestorEmpresa = new GestorEmpresa(tipo, baseDatos,usuario, contrasena);
        System.out.println(gestorEmpresa.obtenerDepartamentos());

        Departamento departamento = new Departamento( "Inteligencia Artificial", "1111111");
        gestorEmpresa.anadirNuevoDepartamento(departamento);

        System.out.println();
        System.out.println(gestorEmpresa.obtenerDepartamentos());

    }
}











