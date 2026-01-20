package gestores;

import java.sql.*;
import java.util.*;
import clases.*;
import persistencia.ConcesionarioDAO;


public class GestorConcesionario {
    private Connection conn;
    private ConcesionarioDAO conceDAO;

    public GestorConcesionario(TipoSGBD tipo, String baseDatos, String usuario, String contrasena) {
        this.conn = GestorConexion.getConnection(tipo, baseDatos, usuario, contrasena);
        this.conceDAO = new ConcesionarioDAO(conn);
    }


    public List<Marca> obtenerMarcasVehiculos() {
        List<Marca> marcas = conceDAO.obtenerMarcasVehiculos();
        System.out.println(marcas);
        return marcas; 
    }


    public void venderVehiculo(int idCoche, int idCliente, int idVendedor) {
        boolean isDisponible = conceDAO.comprobarCocheDisponible(idCoche);

        if (isDisponible) {
            GestorConexion.desactivarAutoCommit(conn);

            //conceDAO.registrarVenta();
        } else {
            System.out.println("El coche con el id no esta disponible " + idCoche);
        }
    }


    public int añadirCliente(Cliente cliente) {
        Integer id = conceDAO.comprobarExistenciaCliente(cliente);

        if (id == null) {
            System.out.println("Añadido " + cliente);
            return conceDAO.añadirCliente(cliente);
        } else {
            System.out.println("Ya existe el " + cliente);
            return id;
        }

    }

    public int añadirVendedor(Vendedor vendedor) {
        Integer id = conceDAO.comprobarExistenciaVendedor(vendedor);

        if (id == null) {
            System.out.println("Añadido " + vendedor);
            return conceDAO.añadirVendedor(vendedor);
        } else {
            System.out.println("Ya existe el " + vendedor);
            return id;
        }

    }

    
}
