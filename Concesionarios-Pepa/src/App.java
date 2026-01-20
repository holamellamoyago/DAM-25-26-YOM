import clases.TipoSGBD;
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
        gestorConce.venderVehiculo(2);
    }
}
