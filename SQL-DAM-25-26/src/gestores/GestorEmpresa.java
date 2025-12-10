package gestores;

import clases.TipoSGBD;
import persistencia.EmpresaDAO;

import java.sql.Connection;

public class GestorEmpresa {

    private Connection conn;
    private EmpresaDAO empresaDAO;

    public GestorEmpresa(TipoSGBD tipo, String baseDatos, String usuario, String contrasena) {
        this.conn = GestorConexion.getConnection(tipo, baseDatos, usuario, contrasena);
        empresaDAO = new EmpresaDAO(conn);
    }

    public void obtenerDepartamentos() {
        System.out.println(empresaDAO.mostrarDepartamentos());
    }


}
