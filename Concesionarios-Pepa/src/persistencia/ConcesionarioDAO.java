package persistencia;

import java.sql.*;
import java.util.*;
import clases.*;

public class ConcesionarioDAO {
    private Connection conn;

    public ConcesionarioDAO(Connection conn) {
        this.conn = conn;
    }

    public List<Marca> obtenerMarcasVehiculos() {
        List<Marca> marcas = new ArrayList<>();

        String sql = """
                SELECT * FROM MARCAS
                """;

        try (Statement st = conn.createStatement()) {
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                String nombre = rs.getString("Nombre");
                String pais = rs.getString("Pais");

                Marca marca = new Marca();
                marca.setId(rs.getInt("IdMarca"));
                marca.setNombre(nombre);
                marca.setPais(pais);

                marcas.add(marca);
            }

            return marcas;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean comprobarCocheDisponible(int idCoche) {
        String sql = """
                SELECT Estado FROM coches where idCoche = ?
                """;
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idCoche);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String dispo = rs.getString("Estado");
                if (dispo.equals("Disponible")) {
                    return true;
                }
            }

            return false;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
