/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import java.util.*;
import java.sql.*;

import clases.*;
import persistencia.*;
import gestores.*;

/**
 *
 * @author usuario
 */
public class EmpresaDAO {

    private Connection conn;

    public EmpresaDAO(Connection conn) {
        this.conn = conn;
    }

    public ArrayList<Departamento> mostrarDepartamentos() {
        ArrayList<Departamento> lista = new ArrayList<>();
        String sql = "SELECT NumDepartamento, NomeDepartamento, NSSDirector FROM DEPARTAMENTO";

        try {
            ResultSet rs = GestorConexion.ejecutarConsulta(conn, sql, new ArrayList<>());

            while (rs.next()) {
                int numDepartamento = rs.getInt(1);
                String nombreDepartamento = rs.getString(2);
                String nssDirector = rs.getString(3);

                Departamento dep = new Departamento(numDepartamento, nombreDepartamento, nssDirector);
                lista.add(dep);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return lista;
    }

    public void anadirDepartamento (Departamento dp) {
        final String SQL = "INSERT INTO DEPARTAMENTO (NumDepartamento, NomeDepartamento, NSSDirector) VALUES (DEFAULT, ?, ?)";
        ArrayList<Object> infoDepa = new ArrayList<>(List.of("DEPARTAMENTO", dp.getNomeDepartamento(), dp.getNssDirector()));

        try {
//            GestorConexion.ejecutarConsulta(conn, SQL, infoDepa);
//            PreparedStatement ps = conn.prepareStatement(SQL);
//            ps.setInt(0, dp.getNumDepartamento());

            GestorConexion.insertarDatos(conn, SQL, infoDepa);

        } catch (SQLException e) {
            System.out.println("Error al insertar el departamento");
            throw new RuntimeException(e);
        }
    }

}
















