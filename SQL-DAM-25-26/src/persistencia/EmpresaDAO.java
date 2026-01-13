/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import java.util.*;
import java.sql.*;

import clases.*;
import gestores.*;

/**
 *
 * @author usuario
 */
public class EmpresaDAO {

    private Connection conn;

    public EmpresaDAO(Connection conn) {
        this.conn = conn;
    }

    public ArrayList<Departamento> mostrarDepartamentos() {
        ArrayList<Departamento> lista = new ArrayList<>();
        String sql = "SELECT NumDepartamento, NomeDepartamento, NSSDirector FROM DEPARTAMENTO";

        try {
            ResultSet rs = GestorConexion.ejecutarConsulta(conn, sql, new ArrayList<>());

            while (rs.next()) {
                int numDepartamento = rs.getInt(1);
                String nombreDepartamento = rs.getString(2);
                String nssDirector = rs.getString(3);

                Departamento dep = new Departamento(numDepartamento, nombreDepartamento, nssDirector);
                lista.add(dep);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return lista;
    }

    public int obtenerSiguienteCodigo(String nombreTabla) {
        final String SQL = "SELECT count(*) FROM " + nombreTabla;
        int codigo;

        try {
            ResultSet rs = GestorConexion.ejecutarConsulta(conn, SQL, new ArrayList<>());

            rs.next();
            codigo = rs.getInt(1);
            return codigo + 1;

        } catch (SQLException e) {
            System.out.println("Problemas al ejecutar la consulta de códigos");
            throw new RuntimeException(e);
        }

    }

    public void anadirDepartamento(Departamento dp) {
        final String SQL = "INSERT INTO DEPARTAMENTO (NumDepartamento, NomeDepartamento, NSSDirector) VALUES (?, ?, ?)";

        try {

            dp.setNumDepartamento(obtenerSiguienteCodigo("DEPARTAMENTO"));
            GestorConexion.insertarDatos(conn, SQL, dp.getNumDepartamento(), dp.getNomeDepartamento(),
                    dp.getNssDirector());

        } catch (SQLException e) {
            System.out.println("Error al insertar el departamento");
            throw new RuntimeException(e);
        }
    }

    public void insertarProxecto(Proxecto p) {
        p.setNumProxecto(obtenerSiguienteCodigo("PROXECTO"));
        final String SQL = "INSERT INTO PROXECTO +" +
                " (NumProxecto, NomeProxecto, Lugar, NumDepartControla)" +
                "VALUES (?, ?, ?, ?)";

        try {
            GestorConexion.insertarDatos(conn, SQL, p.getNumProxecto(), p.getNomeProxecto(), p.getLugar(),
                    p.getNumDepartControla());
        } catch (SQLException e) {
            System.out.println("Problemas al insertar en proyecto");
            throw new RuntimeException(e);
        }
    }

    public void crearTablaFamiliar() throws SQLException {
        if (GestorConexion.tablaExiste(conn, "FAMILIAR")) {
            System.out.println("Ya existe la tabla FAMILIAR");
            return;
        }

        String familiar = "CREATE TABLE FAMILIAR (" +
                "Numero SMALLINT NOT NULL AUTO_INCREMENT," +
                "NSS_familiar VARCHAR(15) NOT NULL, " +
                "NSS_empregado VARCHAR(15) NOT NULL," +
                "Nome VARCHAR(15) NOT NULL," +
                "Apelido1 VARCHAR(15) NOT NULL," +
                "Apelido2 VARCHAR(15) NULL," +
                "Parentesco VARCHAR(20) NOT NULL," +
                "Sexo CHAR(1) NOT NULL," +
                "constraint PK_FAMILIAR PRIMARY KEY (Numero))";
        // DataNacemento DATE,

        String pkFamiliar = "ALTER TABLE FAMILIAR " +
                "ADD CONSTRAINT PK_FAMILIAR PRIMARY KEY (Numero)";

        String uqFamiliar = "ALTER TABLE FAMILIAR" +
                " ADD CONSTRAINT UQ_FAMILIAR_NSS UNIQUE (NSS_familiar)";

        String eqSexo = "" +
                "ALTER TABLE FAMILIAR" +
                " ADD CONSTRAINT CK_SEXO_FAMILIAR CHECK (Sexo = 'H' OR Sexo = 'M') ";

        GestorConexion.ejecutarLoteTransacioneal(conn, familiar, uqFamiliar, eqSexo);

    }

    public void crearTablaFamiliar_SQLite() {
        // Hayq ue hace otra por que no tiene ALTER

        /*
         * Es todo lo mismo que la anterior pero en esta ya se lo añadimos todo al final
         * directamenteç:
         * sexo TEXT NOT NULL,
         * CONSTRAINT PK , UK, FK ....
         */
    }

    public boolean comprobarExistenciaTabla(String nombreTabla) {
        final String SQL = "SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = ?";

        try (ResultSet rs = GestorConexion.ejecutarConsulta(conn, SQL, nombreTabla)) {
            if (rs.next()) {
                return true;
            }

        } catch (SQLException e) {
            System.out.println("Error al comprobar la existencia de la tabla");
            e.printStackTrace();
        }

        return false;
    }

    public void añadirTablaVehiculos() {
        final String NOMBRE_TABLA_1 = "VEHICULOS";
        final String NOMBRE_TABLA_2 = "VEHICULOS_PROPIOS";
        final String NOMBRE_TABLA_3 = "VEHICULOS_RENTING";

        if (GestorConexion.tablaExiste(conn, NOMBRE_TABLA_1)) {
            System.out.println("Existe la tabla en la DB la TABLA " + NOMBRE_TABLA_1);
            return;
        }
        if (GestorConexion.tablaExiste(conn, NOMBRE_TABLA_2)) {
            System.out.println("Existe la tabla en la DB la tabla " + NOMBRE_TABLA_2);
            return;
        }
        if (GestorConexion.tablaExiste(conn, NOMBRE_TABLA_3)) {
            System.out.println("Existe la tabla en la DB la tabla " + NOMBRE_TABLA_3);
            return;
        }

        final String MYSQL_VEHICULOS = "CREATE TABLE IF NOT EXISTS VEHICULOS (" +
                "CODIGO_VEHICULO SMALLINT AUTO_INCREMENT," +
                "MATRICULA VARCHAR(15)," +
                "MODELO VARCHAR(15)," +
                "COMBUSTIBLE VARCHAR(15)," +
                "CONSTRAINT PK_VEHICULOS PRIMARY KEY (CODIGO_VEHICULO))";

        final String MYSQL_PROPIOS = "CREATE TABLE IF NOT EXISTS  VEHICULOS_PROPIOS (" +
                "CODIGO_PROPIO SMALLINT AUTO_INCREMENT," +
                "CODIGO_VEHICULO SMALLINT NOT NULL," +
                "FECHA_COMPRA DATE NOT NULL," +
                "PRECIO_PAGADO FLOAT NOT NULL, " +
                "CONSTRAINT PK_VEHICULOS_PROPIOS PRIMARY KEY (CODIGO_PROPIO), " +
                "CONSTRAINT FK_VPROPIO_VEHICULOS FOREIGN KEY (CODIGO_VEHICULO) REFERENCES VEHICULOS(CODIGO_VEHICULO))";

        final String MYSQL_RENTING = "CREATE TABLE IF NOT EXISTS VEHICULOS_RENTING (" +
                "CODIGO_RENTING SMALLINT AUTO_INCREMENT," +
                "CODIGO VEHICULO SMALLINT NOT NULL," +
                "FECHA_INICIO DATE NOT NULL," +
                "PRECIO_MENSUAL FLOAT NOT NULL, " +
                "MESES_CONTRATADOS SMALLINT NOT NULL, " +
                "CONSTRAINT PK_VEHICULOS_PROPIOS PRIMARY KEY (CODIGO_RENTING), " +
                "CONSTRAINT FK_VRENTING_VEHICULOS FOREIGN KEY (CODIGO_VEHICULO) REFERENCES VEHICULOS(CODIGO_VEHICULO))";

        final String SQLSERVER_VEHICULOS = "CREATE TABLE VEHICULOS (" +
                "CODIGO_VEHICULO SMALLINT IDENTITY(1,1)," +
                "MATRICULA VARCHAR(15)," +
                "MODELO VARCHAR(15)," +
                "COMBUSTIBLE VARCHAR(15)," +
                "CONSTRAINT PK_VEHICULOS PRIMARY KEY (CODIGO_VEHICULO))";

        final String SQLSERVER_PROPIOS = "CREATE TABLE VEHICULOS_PROPIOS (" +
                "CODIGO_PROPIO SMALLINT IDENTITY(1,1)," +
                "CODIGO_VEHICULO SMALLINT NOT NULL," +
                "FECHA_COMPRA DATE NOT NULL," +
                "PRECIO_PAGADO FLOAT NOT NULL, " +
                "CONSTRAINT PK_VEHICULOS_PROPIOS PRIMARY KEY (CODIGO_PROPIO), " +
                "CONSTRAINT FK_VPROPIO_VEHICULOS FOREIGN KEY (CODIGO_VEHICULO) REFERENCES VEHICULOS(CODIGO_VEHICULO))";

        final String SQLSERVER_RENTING = "CREATE TABLE VEHICULOS_RENTING (" +
                "CODIGO_RENTING SMALLINT IDENTITY(1,1)," +
                "CODIGO_VEHICULO SMALLINT NOT NULL," +
                "FECHA_INICIO DATE NOT NULL," +
                "PRECIO_MENSUAL FLOAT NOT NULL, " +
                "MESES_CONTRATADOS INT NOT NULL, " +
                "CONSTRAINT PK_VEHICULOS_RENTING PRIMARY KEY (CODIGO_RENTING), " +
                "CONSTRAINT FK_VRENTING_VEHICULOS FOREIGN KEY (CODIGO_VEHICULO) REFERENCES VEHICULOS(CODIGO_VEHICULO))";

        try {
            GestorConexion.ejecutarLoteTransacioneal(conn, SQLSERVER_VEHICULOS, SQLSERVER_PROPIOS, SQLSERVER_RENTING);
        } catch (SQLException e) {
            System.out.println("Problemas al ejecutar el lote");
            e.printStackTrace();
        }
        System.out.println("Tabla añadida VEHICULO");
    }

    public List<Departamento> obtenerDepartamentoConProxectos() {
        List<Departamento> departamentos = new ArrayList<>();

        String sql = "SELECT D.NumDepartamento, d.NomeDepartamento " +
                "FROM DEPARTAMENTO d " +
                "WHERE EXISTS (" +
                "SELECT 1 FROM PROXECTO P)";

        try (ResultSet rs = GestorConexion.ejecutarConsulta(conn, sql, new ArrayList<>())) {
            while (rs.next()) {
                int numero = rs.getInt(1);
                String nombre = rs.getString(2);

                Departamento dep = new Departamento(numero, nombre, null);
                departamentos.add(dep);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return departamentos;
    }

    public int anadirFamiliar(Familiar familiar) {
        try {
            conn.setAutoCommit(false);

            String sqlMax = """
                    SELECT COALESCE (MAX(Numero), 0)
                    FROM FAMILIARES
                    WHERE NSS_EMPREGADO = ?
                    """;

            int numero = 1;

            ResultSet rs = GestorConexion.ejecutarConsulta(conn, sqlMax, familiar.getNssEmpregado());

            if (rs.next()) {
                numero = rs.getInt(1) + 1;
            }

            String sqlInsert = """
                    INSERT INTO FAMILIARES (
                        NUMERO, NSS, NSS_EMPREGADO, NOMBRE, APELIDO1 , APELIDO2
                    ) VALUES (?, ?, ? , ? , ? , ?)
                    """;

            GestorConexion.ejecutarSentencia(conn, sqlInsert, numero, familiar.getNss(), familiar.getNssEmpregado(),
                    familiar.getNombre(), familiar.getApelido1(), familiar.getApelido2());

            conn.commit();
            conn.setAutoCommit(true);

            return 1;

        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
            return -99;
        }
    }

    public void anhadirVehiculo(Vehiculo vehiculo) {

        // TODO Queda anadir los datos generales más despues los específicicos

        final String sql_general = """
                INSERT INTO VEHICULO (MATRICULA, MARCA, MODELO) VALUES (?,?,?)
                """;

        GestorConexion.ejecutarSentencia(conn, sql_general, vehiculo.getMatricula(), vehiculo.getMarca(),
                vehiculo.getModelo());

        if (vehiculo instanceof VehiculoPropio) {
            insertarVehiculoPropio(vehiculo);
        }
    }

    private void insertarVehiculoPropio(Vehiculo vehiculo) {
        final String sql = """
                INSERT INTO VEHICULO_PROPIO VALUES ()
                """;
    }

    // Ejercicio 3
    public int cambiarDepartamentoProxecto(String nomeDepartamento, String nomeProxecto) {
        String sql = """
                UPDATE PROXECTO
                SET NumDepartControla = (
                        SELECT NumDepartamento FROM DEPARTAMENTO WHERE NomeDepartamento = ?
                )  WHERE NomeProxecto = ?
                """;

        int filas = GestorConexion.ejecutarSentencia(conn, sql, nomeDepartamento, nomeProxecto);

        if (filas == 0) {
            return -2; // El proyecto no existe
        }

        return 0; // Todo OK
    }

    public Proxecto comprobarExistenciaProxecto(int codigo) {
        String sql = """
                SELECT NumProxecto , NomeProxecto, Lugar, NumDepartControla FROM PROXECTO WHERE NumProxecto = ?
                """;

        try {
            ResultSet rs = GestorConexion.ejecutarConsulta(conn, sql, codigo);
            if (rs.next()) {
                Proxecto pro = new Proxecto();

                pro.setNumProxecto(rs.getInt(1));
                pro.setNomeProxecto(rs.getString(2));
                pro.setLugar(rs.getString(3));
                pro.setNumDepartControla(rs.getInt(4));

                return pro;
            } else {
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    public List<Empregado> obtenerEmpregadosProxecto(int numProxecto) {
        List<Empregado> empregados = new ArrayList<>();

        String sql = """
                SELECT NSS, NOME, APELIDO1
                FROM EMPREGADO E
                INNER JOIN EMPREGADO_PROXECTO EP ON E.NSS = EP.NSSEmpregado
                WHERE EP.NumProxecto  = ?
                    """;

        try (ResultSet rs = GestorConexion.ejecutarConsulta(conn, sql, numProxecto)) {

            while (rs.next()) {
                Empregado em = new Empregado();

                em.setNss(rs.getString(1));
                em.setNome(rs.getString(2));
                em.setApelido1(rs.getString(3));

                empregados.add(em);
            }

            return empregados;

        } catch (SQLException e) {
            e.printStackTrace();
            return empregados;
        }

    }

    public int eliminarEmpregadosDeProxecto( int numProxecto) {
        String sql = """
                DELETE FROM EMPREGADO_PROXECTO WHERE NumProxecto = ?
                """;

        return GestorConexion.ejecutarSentencia(conn, sql, numProxecto);
    }

    public void eliminarProxecto(Proxecto proxecto) {
        String sql = """
                DELETE FROM PROXECTO WHERE NumProxecto = ?
                """;

        GestorConexion.ejecutarSentencia(conn, sql, proxecto.getNumProxecto());
    }

    // public List<EmpregadoSalarioFixoDTO>
    // mostrarDepartamentosSalarioMayorQue(String valor) {
    // List<EmpregadoSalarioFixoDTO> lista = new ArrayList<>();

    // String sql = """
    // SELECT E.NSS, E.Apelido1, Apelido2, F.SALARIO
    // FROM EMPREGADO E
    // JOIN EMPREGADOFIXO F ON F.NSS = E.NSS
    // WHERE SALARIO > ?
    // ORDER BY F.SALARIO
    // """;

    // try (PreparedStatement ps = conn.prepareStatement(sql,
    // ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
    // ResultSet rs = ps.executeQuery();

    // rs.afterLast();
    // while (rs.previous()) {
    // String nombreDpto = rs.getString("NomeDepartamento");

    // String nombre
    // }

    // } catch (SQLException e1) {
    // e1.printStackTrace();
    // }

    // return lista;
    // }

    // public List<Empregado> obtenerDirectoresConProxectos() {
    // List<Empregado> directores = new ArrayList<>();

    // String sql = """
    // SELECT D.NumDepartamento, d.NombreDepartamento, e.nome, e.apelido1,
    // e.apelido2
    // FROM DEPARTAMENTO d
    // INNER JOIN EMPREGADO e ON d.NSSDirector = e.NSS
    // WHERE EXISTS (
    // SELECT 1
    // FROM EMPREGADO
    // )
    // """;

    // }

}
