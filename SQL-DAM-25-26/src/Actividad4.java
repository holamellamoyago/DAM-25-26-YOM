import clases.TipoSGBD;
import gestores.GestorEmpresa;



public class Actividad4 {
    public static void main(String[] args) {
        String baseDatos = "BDEMPRESA25";
        String usuario;
        String contrasena = "abc123.";
        TipoSGBD tipo = TipoSGBD.SQLSERVER;

        usuario = switch (tipo) {
            case MYSQL -> "root";
            case SQLSERVER -> "sa";
            case SQLITE -> "";
            default -> throw new AssertionError();
        };

        GestorEmpresa gestorEmpresa = new GestorEmpresa(tipo, baseDatos, usuario, contrasena);

        gestorEmpresa.cambiarDepartamentoDeProxecto(2);
    }
}
