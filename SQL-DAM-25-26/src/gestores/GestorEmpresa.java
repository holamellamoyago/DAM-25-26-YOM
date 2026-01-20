package gestores;

import clases.Departamento;
import clases.Empregado;
import clases.Familiar;
import clases.Proxecto;
import clases.TipoSGBD;
import persistencia.DirectorProxectos;
import persistencia.EmpresaDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GestorEmpresa {

    private Connection conn;
    private EmpresaDAO empresaDAO;

    /*
     * ObtenerDepartamentos
     * anadirDepartamento
     * anadirFamiliar
     * anadirTablaFamiliares
     * anadirTablaVehiculos
     * obtenerDepartamentosConProxectos
     * subirSueldosEmpleados
     * obtenerDepartamentoConProxectos
     * - eliminarProxecto
     * departamentoQueControlan
     * obtenerDatosProxectos
     * numeroEmpregadoDepartamento
     * obtenerTipoEmpregado
     */

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


    public void anadirFamiliar(Familiar familiar) {
        int error = empresaDAO.anadirFamiliar(familiar);

        if (error == 1) {
            System.out.println("Familiar añadido correctamente");
        } else {
            System.out.println("Error al añadir el familiar");
        }
    }

    public void eliminarProxecto(int identificador) {
        Proxecto proxecto = empresaDAO.comprobarExistenciaProxecto(identificador);

        if (proxecto == null) {
            System.out.println("No se puede eliminar el proxecto porque no existe");
            return;
        }

        try {
            conn.setAutoCommit(false);

            System.out.println("Información proxecto: \n" + proxecto);
            System.out.println(empresaDAO.obtenerEmpregadosProxecto(proxecto.getNumProxecto()));

            empresaDAO.eliminarEmpregadosDeProxecto(proxecto.getNumProxecto());
            empresaDAO.eliminarProxecto(proxecto);
            System.out.println("\nProxecto eliminado");

            conn.commit();

        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void subirSueldosEmpleados(List<String> empregados, double tantoPorCiento) {
        try {
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            for (String empregado : empregados) {

                empresaDAO.subirSueldoEmpleado(empregado, tantoPorCiento);
            }

            System.out.println("Subida de sueldo completada");

            conn.commit();
        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Departamento> departamentoQueControlan(int valor) {
        return empresaDAO.departamentoQueControlan(valor);
    }

    public void obtenerDatosProxectos(int numProxecto) {
        System.out.println(empresaDAO.obtenerDatosProxectos(numProxecto));
    }

    public int numeroEmpregadoDepartamento(String nomeDepartamento) {
        return empresaDAO.numeroEmpregadoDepartamento(nomeDepartamento);
    }

    public void obtenerTipoEmpregado(String nssempregado) {
        System.out.println(empresaDAO.obtenerTipoEmpregadoFN(nssempregado) + ": " + nssempregado);
    }

    public List<DirectorProxectos> obtenerDirectoresConProxectos() {
        //System.out.println(empresaDAO.obtenerDirectoresConProxectos());
        return empresaDAO.obtenerDirectoresConProxectos();

    }

    public List<Empregado> ejercicio4(String string) {
        System.out.println( empresaDAO.ejercicio4(string));
        return empresaDAO.ejercicio4(string);
    }

    public void cambiarDepartamentoDeProxecto(int i) {
        empresaDAO.mostrarDepartamentosConMinProxectos(i);
    }

    public void subirSueldosEmpleadosBatch(List<String> empregados, int i) {
        empresaDAO.subirSueldosEmpleadosBatch(empregados, i);
        System.out.println("Sueldos subidos a: " + empregados);
    }
}
