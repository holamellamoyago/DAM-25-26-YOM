import java.util.ArrayList;
import java.util.List;

import clases.TipoSGBD;
import gestores.*;
import clases.*;

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

        Familiar familiar = new Familiar();
        familiar.setNssEmpregado("0010010");
        familiar.setNss("0010033");
        familiar.setNombre("Yago");
        familiar.setApelido1("Otero");

        gestorEmpresa.anadirFamiliar(familiar);


    }
}
