package gestores;

import clases.Departamento;
import clases.TipoSGBD;
import persistencia.EmpresaDAO;

import java.sql.Connection;
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

        empresaDAO.anadirDepartamento(departamento);
        System.out.println("Departamento anadido");
    }




}
