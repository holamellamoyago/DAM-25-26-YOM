import clases.Departamento;
import clases.TipoSGBD;
import gestores.GestorEmpresa;

public class Ejercicio7 {
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

        //Connection connection =  GestorConexion.getConnection(tipo, baseDatos,usuario,contrasena);
        //System.out.println(GestorConexion.obtenerMetaDatos(connection));
        //System.out.println(connection);


        GestorEmpresa gestorEmpresa = new GestorEmpresa(tipo, baseDatos, usuario, contrasena);

        //gestorEmpresa.añadirTablaFamiliares();
        gestorEmpresa.añadirTablaVehiculos(tipo);



    }
}
