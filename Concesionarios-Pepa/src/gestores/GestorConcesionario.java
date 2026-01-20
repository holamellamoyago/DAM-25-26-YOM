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


    public void venderVehiculo(int idCoche) {
        boolean isDisponible = conceDAO.comprobarCocheDisponible(idCoche);

        if (isDisponible) {
            System.out.println("Coche disponible venta");
        } else {
            System.out.println("El coche con el id no esta disponible " + idCoche);
        }
    }

    
}
