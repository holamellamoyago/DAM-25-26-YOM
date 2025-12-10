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

}
