import java.util.ArrayList;
import java.util.List;

import clases.TipoSGBD;
import gestores.GestorEmpresa;

public class Actividad3 {
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
        // gestorEmpresa.obtenerDatosProxectos(1);
        // gestorEmpresa.departamentoQueControlan(2);
        gestorEmpresa.obtenerTipoEmpregado("0010010");
        gestorEmpresa.obtenerTipoEmpregado("1341431");
        gestorEmpresa.obtenerTipoEmpregado("11");


        final List<String> empregados = new ArrayList<>(List.of("0010010", "0110010", "0999900"));
        gestorEmpresa.subirSueldosEmpleadosBatch(empregados, 20);


    }
}
