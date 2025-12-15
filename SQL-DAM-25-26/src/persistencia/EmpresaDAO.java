/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import java.util.*;
import java.sql.*;

import clases.*;
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

    public int obtenerSiguienteCodigo(String nombreTabla) {
        final String SQL = "SELECT count(*) FROM " + nombreTabla;
        int codigo;

        try {
            ResultSet rs = GestorConexion.ejecutarConsulta(conn, SQL, new ArrayList<>());

            rs.next();
            codigo = rs.getInt(1);
            return codigo + 1;

        } catch (SQLException e) {
            System.out.println("Problemas al ejecutar la consulta de códigos");
            throw new RuntimeException(e);
        }


    }

    public void anadirDepartamento(Departamento dp) {
        final String SQL = "INSERT INTO DEPARTAMENTO (NumDepartamento, NomeDepartamento, NSSDirector) VALUES (?, ?, ?)";

        try {

            dp.setNumDepartamento(obtenerSiguienteCodigo("DEPARTAMENTO"));
            GestorConexion.insertarDatos(conn, SQL, dp.getNumDepartamento(), dp.getNomeDepartamento(), dp.getNssDirector());

        } catch (SQLException e) {
            System.out.println("Error al insertar el departamento");
            throw new RuntimeException(e);
        }
    }

    public void insertarProxecto(Proxecto p) {
        p.setNumProxecto(obtenerSiguienteCodigo("PROXECTO"));
        final String SQL = "INSERT INTO PROXECTO +" +
                " (NumProxecto, NomeProxecto, Lugar, NumDepartControla)" +
                "VALUES (?, ?, ?, ?)";

        try {
            GestorConexion.insertarDatos(conn, SQL, p.getNumProxecto(), p.getNomeProxecto(), p.getLugar(), p.getNumDepartControla());
        } catch (SQLException e) {
            System.out.println("Problemas al insertar en proyecto");
            throw new RuntimeException(e);
        }
    }

    public void crearTablaFamiliar() {
        if (GestorConexion.tablaExiste(conn, "FAMILIARES")){
            System.out.println("Ya existe la tabla FAMILIARES");
            return;
        }



        String familiar = "" +
                "CREATE TABLE FAMILIAR " +
                "NSS_empregado VARCHAR(15) NOT NULL," +
                "Numero SMALLINT NOT NULL AUTO_INCREMENT" +
                "";
        //DataNacemento DATE,


        String pkFamiliar = "" +
                "ALTER TABLE FAMILIAR " +
                "ADD CONSTRAINT PK_FAMILIAR";

        String uqFamiliar = "" +
                "ALTER TABLE FAMILIAR" +
                "ADD CONSTRAINT UQ_FAMILIAR_NSS UNIQUE (----)";

        // TODO checksexxo
        /*

         */

    }

    public void crearTablaFamiliar_SQLite() {
        //  Hayq ue hace otra por que no tiene ALTER

        /*
         * Es todo lo mismo que la anterior pero en esta ya se lo añadimos todo al final directamenteç:
         *     sexo TEXT NOT NULL,
         * CONSTRAINT PK , UK, FK ....
         * */
    }


}
















