import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import clases.*;
import gestores.GestorExposicion;

public class Ejercicio1 {
    public static void main(String[] args) throws Exception {
        String baseDatos = "BDEXPOSICION26";
        String usuario;
        String contrasena = "abc123.";
        TipoSGBD tipo = TipoSGBD.SQLSERVER;

        usuario = switch (tipo) {
            case MYSQL -> "root";
            case SQLSERVER -> "sa";
            case SQLITE -> "";
            default -> throw new AssertionError();
        };

        System.out.println("\nEJERICIO 1:\n");
        GestorExposicion gestorExposicion = new GestorExposicion(tipo, baseDatos, usuario, contrasena);
        gestorExposicion.ejercicio1();

        System.out.println("\nEJERICIO 2:\n");
        Fotografo fotografo = gestorExposicion.obtenerfotografoPorCodigo(1);
        Exposicion exposicion = gestorExposicion.obtenerExposicionPorCodigo(1);

        FotografiaDocumental fd = new FotografiaDocumental();
        fd.setNome("Fotografia documental 1");
        fd.setMedidas("10x10");
        fd.setData(Date.valueOf(LocalDate.now()));
        fd.setColor('S');
        fd.setTipo("PERSONAS");

        FotografiaArtistica fa = new FotografiaArtistica();
        fa.setNome("Fotografia documental 1");
        fa.setMedidas("10x10");
        fa.setData(Date.valueOf(LocalDate.now()));
        fa.setColor('S');
        fa.setEncuadre("Encuadre X");
        fa.setComposicion("Composicion X");

        // Caso 1 : el que todo debería de ir bien
        gestorExposicion.darDeAltaColeccionFotografias(fotografo, exposicion, new ArrayList<>(List.of(fd, fa)));

        // Caso 2: inexistencia de la exposicion
        // exposicion = gestorExposicion.obtenerExposicionPorCodigo(999);
        // gestorExposicion.darDeAltaColeccionFotografias(fotografo, exposicion, new
        // ArrayList<>(List.of(fd,fa)));

        // Caso 3: inexistencia del fotografo
        // fotografo = gestorExposicion.obtenerfotografoPorCodigo(999);
        // gestorExposicion.darDeAltaColeccionFotografias(fotografo, exposicion, new
        // ArrayList<>(List.of(fd,fa)));

        // Caso 4: errror de fuerza mayor
        // fa.setNome(null);
        // gestorExposicion.darDeAltaColeccionFotografias(fotografo, exposicion, new
        // ArrayList<>(List.of(fd,fa)));

        System.out.println("\nEJERICIO 3:\n");
        gestorExposicion.trasladarFoografias("LA FOSA", "INVISIBLE");

    }
}
