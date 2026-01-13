package gestores;

import clases.Departamento;
import clases.Familiar;
import clases.TipoSGBD;
import persistencia.EmpresaDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GestorEmpresa {

    private Connection conn;
    private EmpresaDAO empresaDAO;

    public GestorEmpresa(TipoSGBD tipo, String baseDatos, String usuario, String contrasena) {
        this.conn = GestorConexion.getConnection(tipo, baseDatos, usuario, contrasena);
        empresaDAO = new EmpresaDAO(conn);
    }

    public ArrayList<Departamento> obtenerDepartamentos() {
        return empresaDAO.mostrarDepartamentos();
    }

    public void anadirNuevoDepartamento(Departamento departamento) {
        if (obtenerDepartamentos().contains(departamento)) {
            System.out.println("Departamento ya existe");
            return;
        }

        // empresaDAO.anadirDepartamento(departamento);
        empresaDAO.anadirDepartamento(departamento);
        System.out.println("Departamento anadido");
    }

    public void añadirTablaFamiliares() {
        final String NOMBRE_TABLA = "FAMILIAR";

        if (empresaDAO.comprobarExistenciaTabla(NOMBRE_TABLA)) {
            System.out.println("Ya existe la tabla " + NOMBRE_TABLA);
            return;
        }

        try {
            empresaDAO.crearTablaFamiliar();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Tabla añadida " + NOMBRE_TABLA);
    }

    public void añadirTablaVehiculos(TipoSGBD tipo) {
        empresaDAO.añadirTablaVehiculos();
    }

    public void obtenerDepartamentoConProxectos() {
        empresaDAO.obtenerDepartamentoConProxectos();
    }

    // public void mostrarDepartamentosSalarioMayorQue() {
    // empresaDAO.mostrarDepartamentosSalarioMayorQue("200");
    // }

    public void anadirFamiliar(Familiar familiar) {
        int error = empresaDAO.anadirFamiliar(familiar);

        if (error == 1) {
            System.out.println("Familiar añadido correctamente");
        } else {
            System.out.println("Error al añadir el familiar");
        }
    }
}
