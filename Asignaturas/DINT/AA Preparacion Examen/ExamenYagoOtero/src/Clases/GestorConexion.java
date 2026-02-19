/*
					* Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
					* Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
					*/
package Clases;

import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author usuario
 */
public class GestorConexion {

	public static Connection getConnection(String baseDatos, String usuario, String contrasena) {
		System.out.println(usuario);
		String url = "jdbc:mysql://localhost:3306/" + baseDatos + "?serverTimezone=UTC";

		try {

			return DriverManager.getConnection(url, usuario, contrasena);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public static String obtenerMetaDatos(Connection con) {
		try {
			var meta = con.getMetaData();

			StringBuilder sb = new StringBuilder();
			sb.append("Driver name: " + meta.getDriverName()).append("\n");
			sb.append("Driver version: " + meta.getDriverVersion()).append("\n");
			sb.append("Producto bd: " + meta.getDatabaseProductName()).append("\n");
			sb.append("Version BD: " + meta.getDatabaseProductVersion()).append("\n");
			sb.append("URL: " + meta.getURL()).append("\n");
			sb.append("Usuario: " + meta.getUserName()).append("\n");
			return sb.toString();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public static ResultSet ejecutarConsulta(Connection conn, String sqlConsulta, ArrayList<Object> parametros)
			throws SQLException, SQLException {
		PreparedStatement stmt = conn.prepareStatement(sqlConsulta);

		for (int i = 1; i < parametros.size(); i++) {
			stmt.setObject(i, parametros.get(i));
		}

		return stmt.executeQuery();
	}

	public static ResultSet ejecutarConsulta(Connection conn, String sqlConsulta, Object... parametros)
			throws SQLException, SQLException {
		PreparedStatement stmt = conn.prepareStatement(sqlConsulta);

		for (int i = 0; i < parametros.length; i++) {
			stmt.setObject(i + 1, parametros[i]);
		}

		return stmt.executeQuery();
	}

	public static void insertarDatos(Connection conn, String sqlConsulta, Object... parametros)
			throws SQLException, SQLException {

		try (PreparedStatement stmt = conn.prepareStatement(sqlConsulta)) {
			conn.setAutoCommit(false);
			for (int i = 0; i < parametros.length; i++) {
				stmt.setObject(i + 1, parametros[i]);
			}

			stmt.executeUpdate();
			conn.commit();

		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();
		}

	}

	public static void setParametros(PreparedStatement ps, Object... params) throws SQLException {
		for (int i = 0; i < params.length; i++) {
			ps.setObject(i + 1, params[i]);
		}
	}

	public static void cerrarConexion(Connection conn) throws SQLException {
		if (conn != null) {
			conn.close();
		}
	}

	public static void borrarTablas(Connection conn, String... tablas) {
		try {
			conn.setAutoCommit(false);

			try (Statement stmt = conn.createStatement()) {
				for (String tabla : tablas) {
					if (tablaExiste(conn, tabla)) {
						stmt.addBatch("DROP TABLE " + tabla);
					}
				}

				stmt.executeBatch();
				conn.commit();
			} catch (SQLException ex) {
				conn.rollback();
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	public static boolean tablaExiste(Connection conn, String tabla) {
		try (ResultSet rs = conn.getMetaData().getTables(null, null, tabla, null)) {
			return rs.next();
		} catch (SQLException e) {
			System.out.println("Error al consultar tablas existentes");
			throw new RuntimeException(e);
		}
	}

	public static void ejecutarLoteTransacioneal(Connection conn, String... sentenciasSQL) throws SQLException {
		try {
			conn.setAutoCommit(false);
			Statement stmt = conn.createStatement();

			for (String sql : sentenciasSQL) {
				stmt.addBatch(sql);
			}

			stmt.executeBatch();
			conn.commit();

		} catch (SQLException e) {
			conn.rollback();
			throw new RuntimeException(e);
		} finally {
			conn.setAutoCommit(true);
		}
	}

	// Día 13-01

	public static int ejecutarSentencia(Connection conn, String sql, Object... params) {
		try {
			PreparedStatement stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);

			setParametros(stmt, params);
			return (stmt.executeUpdate());

		} catch (SQLException e) {
			e.printStackTrace();
			return -99;
		}
	}

	public static int insertarYretornarClaveGenerada(Connection conn, String sql, Object params) {
		try {
			PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			setParametros(ps, params);

			ps.executeUpdate();

			ResultSet rs = ps.getGeneratedKeys();

			if (rs.next()) {
				return rs.getInt(1);
			} else {
				throw new SQLException("No se generó clave primaria");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return -99;
		}
	}

	// Día 15
	public static ResultSet crearResultSetActualizable(Connection conn, String sql) {
		try (Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
			return st.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static ResultSet crearResultSetActualizable2(Connection conn, String sql, Object... params) {
		try (Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
			return st.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static void deshacerCambios(Connection conn) {
		try {
			conn.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void desactivarAutoCommit(Connection conn) {
		try {
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void activarAutoCommit(Connection conn) {
		try {
			conn.setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
