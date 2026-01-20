import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;

import clases.Cliente;
import clases.TipoSGBD;
import clases.Vendedor;
import gestores.GestorConcesionario;

public class App {
    public static void main(String[] args) throws Exception {
        String baseDatos = "Concesionario";
        String usuario;
        String contrasena = "abc123.";
        TipoSGBD tipo = TipoSGBD.SQLSERVER;

        usuario = switch (tipo) {
            case MYSQL -> "root";
            case SQLSERVER -> "sa";
            case SQLITE -> "";
            default -> throw new AssertionError();
        };

        GestorConcesionario gestorConce = new GestorConcesionario(tipo, baseDatos, usuario, contrasena);

        gestorConce.obtenerMarcasVehiculos();

        Cliente cliente = new Cliente();
        cliente.setNombre("Yago");
        cliente.setCiudad("Vigo");
        cliente.setFecha(Date.valueOf(LocalDate.now()));
        int idCliente = gestorConce.añadirCliente(cliente);


        Vendedor vendedor = new Vendedor();
        vendedor.setNombre("Angel Gaitán");
        vendedor.setComision(20);
        vendedor.setZona("Madrid");
        int idVendedor = gestorConce.añadirVendedor(vendedor);

        gestorConce.venderVehiculo(2);
    }
}
