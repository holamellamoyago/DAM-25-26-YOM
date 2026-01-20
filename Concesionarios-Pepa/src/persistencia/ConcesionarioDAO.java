package persistencia;

import java.sql.*;
import java.util.*;

import com.mysql.cj.x.protobuf.MysqlxPrepare.Prepare;

import clases.*;
import gestores.*;

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

    public int añadirCliente(Cliente cliente) {
        String sql = """
                INSERT INTO Clientes (Nombre, Ciudad, FechaRegistro) VALUES (?,?,?)
                """;

        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getCiudad());
            ps.setDate(3, cliente.getFecha());

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas == 0) {
                throw new SQLException("Inserción fallida, no se afectaron filas.");
            }
            
            ResultSet rs =  ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            } 
            
            throw new RuntimeException("Error al coger el id");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
    }

    
    public int añadirVendedor(Vendedor vendedor) {
        String sql = """
                INSERT INTO Clientes (Nombre, Zona, Comision) VALUES (?,?,?)
                """;

        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, vendedor.getNombre());
            ps.setString(2, vendedor.getZona());
            ps.setInt(3, vendedor.getComision());

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas == 0) {
                throw new SQLException("Inserción fallida, no se afectaron filas vendedor.");
            }
            
            ResultSet rs =  ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            } 
            
            throw new RuntimeException("Error al coger el id");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
    }

    public Integer comprobarExistenciaCliente(Cliente cliente) {
        String sql = """
                SELECT idCliente, Nombre, Ciudad, FechaRegistro 
                FROM Clientes 
                WHERE Nombre = ? AND FechaRegistro = ? AND Ciudad = ?
                """;
        
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, cliente.getNombre());
                ps.setDate(2, cliente.getFecha());
                ps.setString(3, cliente.getCiudad());

                ResultSet rs =  ps.executeQuery();

                if (rs.next()) {
                    return rs.getInt(1);
                }

                return null;
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
    }

    public Integer comprobarExistenciaVendedor(Vendedor vendedor) {
        String sql = """
                SELECT idVendedor, Nombre, Zona, Comision 
                FROM Clientes 
                WHERE Nombre = ? AND Zona = ? AND Comision = ?
                """;
        
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, vendedor.getNombre());
                ps.setString(2, vendedor.getZona());
                ps.setInt(3, vendedor.getComision());

                ResultSet rs =  ps.executeQuery();

                if (rs.next()) {
                    return rs.getInt(1);
                }

                return null;
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
    }

}
