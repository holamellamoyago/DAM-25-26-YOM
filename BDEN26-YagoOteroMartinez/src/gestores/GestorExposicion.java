package gestores;

import java.sql.*;
import java.util.ArrayList;

import clases.*;
import persistencia.*;

public class GestorExposicion {
    private Connection conn;
    private ExposicionDAO exposicionDAO;

    public GestorExposicion(TipoSGBD tipo, String baseDatos, String usuario, String contrasena) {
        this.conn = GestorConexion.getConnection(tipo, baseDatos, usuario, contrasena);
        exposicionDAO = new ExposicionDAO(conn);
    }

    public void ejercicio1() {
        boolean existeLaboratorioFotografo = exposicionDAO.comprobarExistenciaTabla("LaboratorioFotografo");
        if (existeLaboratorioFotografo) {
            exposicionDAO.borrarTabla("LaboratorioFotografo");
            System.out.println("Tabla LaboratorioFotografo borrada correctamente");
        }

        boolean existeLaboratorio = exposicionDAO.comprobarExistenciaTabla("LaboratorioFotografico");

        if (existeLaboratorio) {
            exposicionDAO.borrarTabla("LaboratorioFotografico");
            System.out.println("Tabla LaboratorioFotografico borrada correctamente");
        }

        exposicionDAO.crearTablasLaboratorios();

    }

    public Fotografo obtenerfotografoPorCodigo(int codigo) {
        return exposicionDAO.obtenerFotografoPorCodigo(codigo);
    }

    public Exposicion obtenerExposicionPorCodigo(int codigo) {
        return exposicionDAO.obtenerExposicionPorCodigo(codigo);
    }

    public void darDeAltaColeccionFotografias(Fotografo fotografo, Exposicion exposicion,
            ArrayList<Fotografia> fotografias) {
        comoprobarExistenciaFotografoExposicion(fotografo, exposicion);

        GestorConexion.desactivarAutoCommit(conn);

        try {
            for (Fotografia fotografia : fotografias) {
                exposicionDAO.anadirFotografia(fotografo, exposicion, fotografia);
                exposicionDAO.actualizarNumeroFotografias(fotografo);
            }

            GestorConexion.hacerCommit(conn);
        } catch (SQLException e) {
            GestorConexion.deshacerCambios(conn);
            throw new RuntimeException("Error al añadir la coleccion de fotografias: \n" + e.toString());
        } finally {
            GestorConexion.activarAutoCommit(conn);
        }
    }

    private void comoprobarExistenciaFotografoExposicion(Fotografo f, Exposicion e) {
        if (f == null) {
            throw new ArithmeticException("No existe el fotografo");
        }

        if (e == null) {
            throw new ArithmeticException("No existe la exposicion");
        }
    }

    public void trasladarFoografias(String nombreExposicion1, String nombreExposicion2) {
        Exposicion e1 = exposicionDAO.obtenerExposicionPorNombre(nombreExposicion1);
        Exposicion e2 = exposicionDAO.obtenerExposicionPorNombre(nombreExposicion2);

        try {
            GestorConexion.desactivarAutoCommit(conn);
            mostrarInformacion(e1, e2);

            exposicionDAO.trasladarImagenes(e1,e2);

            GestorConexion.hacerCommit(conn);
        } catch (Exception e) {
            GestorConexion.deshacerCambios(conn);
            throw new RuntimeException( "Error a la hora de mover las imagenes de una exposicion a otra");
        } finally {
            GestorConexion.activarAutoCommit(conn);
        }


    }

    private void mostrarInformacion(Exposicion e1, Exposicion e2) {
        e1.fotografias.addAll(obtenerFotografiasDeExposicion(e1));
        e2.fotografias.addAll(obtenerFotografiasDeExposicion(e2));

        StringBuilder str = new StringBuilder();
        str.append("NOMBRE DE EXPOSICIÓN: " + e1.getNome() + "   " + e1.getLocalidad() + "\n");
        str.append("FOTOGRAFIAS: \n");

        for (Fotografia fotografia : e1.fotografias) {
            if (fotografia instanceof FotografiaArtistica) {
                str.append("ARTISTICA ");
            } else {
                str.append("DOCUMENTAL ");
            }

            str.append(fotografia.getNome() + " -" + fotografia.fotografo.getNombre() +"\n");
        }

        str.append("SE TRASLADAN " + e1.fotografias.size() + " FOTOGRAFIAS DE LA EXPOSICION " + e1.getNome());
        str.append(" A LA EXPOSICIÓN " + e2.getNome() + "\n");

        System.out.println(str.toString());

    }

    private ArrayList<Fotografia> obtenerFotografiasDeExposicion(Exposicion expo) {
        return exposicionDAO.obtenerFotografiasDeExposcion(expo);
    }

}
