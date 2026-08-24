package persistencia;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.management.RuntimeErrorException;

import com.mysql.cj.x.protobuf.MysqlxPrepare.Prepare;

import clases.Exposicion;
import clases.Fotografia;
import clases.FotografiaArtistica;
import clases.FotografiaDocumental;
import clases.FotografiaFotografoDTO;
import clases.Fotografo;
import gestores.GestorConexion;

public class ExposicionDAO {
    private Connection conn;

    public ExposicionDAO(Connection conn) {
        this.conn = conn;
    }

    public boolean comprobarExistenciaTabla(String nombreTabla) {
        return GestorConexion.tablaExiste(conn, nombreTabla);
    }

    public void borrarTabla(String nombreTabla) {
        GestorConexion.borrarTablas(conn, nombreTabla);
    }

    public void crearTablasLaboratorios() {
        String sql = """
                CREATE TABLE LaboratorioFotografico (
                    Codigo INT IDENTITY(1,1),
                    Nombre VARCHAR(30),
                    AñoInauguracion DATE,

                    CONSTRAINT PK_LaboratorioFotografico PRIMARY KEY (Codigo),
                    CONSTRAINT UQ_Nombre UNIQUE (Nombre)
                )
                """;

        String sql2 = """
                CREATE TABLE LaboratorioFotografo(
                    CodigoLaboratorio INT,
                    CodigoFotografo INT,
                    FechaInicio DATE,
                    FechaFin DATE,

                    CONSTRAINT PK_LaboratorioFotografo PRIMARY KEY (CodigoLaboratorio, CodigoFotografo),
                    CONSTRAINT FK_LABORATORIO FOREIGN KEY (CodigoLaboratorio) REFERENCES LaboratorioFotografico,
                    CONSTRAINT FK_FOTOGRAFO FOREIGN KEY (CodigoFotografo) REFERENCES FOTOGRAFO
                )
                """;

        try {
            GestorConexion.ejecutarLoteTransacioneal(conn, sql, sql2);
            System.out.println("Tablas añadidas correctamente");
        } catch (SQLException e) {
            throw new RuntimeException("Hubo un problema al añadir las tablas: \n" + e.toString());
        }
    }

    public Fotografo obtenerFotografoPorCodigo(int codigo) {
        Fotografo fotografo = new Fotografo();

        String sql = """
                SELECT CODIGO, NOME, LOCALIDADE, PAIS, DATA_NACEMENTO, DATA_FALECEMENTO, COD_INFLUENCER
                        COD_ESTUDIO, NUMFOTOGRAFIAS
                FROM FOTOGRAFO
                WHERE CODIGO = ?
                """;

        try (ResultSet rs = GestorConexion.ejecutarConsulta(conn, sql, codigo)) {

            if (rs.next()) {
                fotografo.setCodigo(rs.getInt("CODIGO"));

                // Integer n = rs.getInt("COD_INFLUENCER");

                // if (n != null) {
                // fotografo.setCodInfluencer(n);
                // }

                fotografo.setCodEstudio(rs.getInt("COD_ESTUDIO"));
                fotografo.setNumfotografias(rs.getInt("NUMFOTOGRAFIAS"));
                fotografo.setNombre(rs.getString("NOME"));
                fotografo.setLocalidade(rs.getString("LOCALIDADE"));
                fotografo.setPais(rs.getString("PAIS"));

                return fotografo;
            }

            return null;

        } catch (SQLException e) {
            throw new RuntimeException("Ocurrió un mensaje al buscar al fotografo\n" + e.toString());
        }
    }

    public Exposicion obtenerExposicionPorCodigo(int codigo) {
        Exposicion exposicion = new Exposicion();

        String sql = """
                SELECT CODIGO, SITIO, RUA, NUMERO, CODIGO_POSTAL, COD_LOCALIDADE, METROS, NOME
                FROM EXPOSICION
                WHERE CODIGO = ?
                """;

        try (ResultSet rs = GestorConexion.ejecutarConsulta(conn, sql, codigo)) {
            if (rs.next()) {
                exposicion.setCodigo(codigo);
                exposicion.setCodLocalidade(rs.getInt("COD_LOCALIDADE"));
                exposicion.setMetros(rs.getInt("METROS"));
                exposicion.setNumero(rs.getInt("NUMERO"));

                exposicion.setCodPostal(rs.getString("CODIGO_POSTAL"));
                exposicion.setNome(rs.getString("NOME"));
                exposicion.setRua(rs.getString("RUA"));
                exposicion.setSitio(rs.getString("SITIO"));

                return exposicion;
            }

            throw new RuntimeException("No se encuentra ninguna exposicion con ese codigo");

        } catch (SQLException e) {
            throw new RuntimeException("Ocurrió un mensaje al buscar la exposicion\n" + e.toString());
        }
    }

    public Exposicion obtenerExposicionPorNombre(String nombre) {
        Exposicion exposicion = new Exposicion();

        String sql = """
                SELECT CODIGO, SITIO, RUA, NUMERO, CODIGO_POSTAL, COD_LOCALIDADE, METROS, NOME
                FROM EXPOSICION
                WHERE NOME = ?
                """;

        try (ResultSet rs = GestorConexion.ejecutarConsulta(conn, sql, nombre)) {
            if (rs.next()) {
                exposicion.setCodigo(rs.getInt("CODIGO"));
                exposicion.setCodLocalidade(rs.getInt("COD_LOCALIDADE"));
                exposicion.setMetros(rs.getInt("METROS"));
                exposicion.setNumero(rs.getInt("NUMERO"));

                exposicion.setCodPostal(rs.getString("CODIGO_POSTAL"));
                exposicion.setNome(rs.getString("NOME"));
                exposicion.setRua(rs.getString("RUA"));
                exposicion.setSitio(rs.getString("SITIO"));

                exposicion.setLocalidad(obtenerLocalidadExposicion(nombre));

                return exposicion;
            }

            throw new RuntimeException("No se encuentra ninguna exposicion con ese nombre");

        } catch (SQLException e) {
            throw new RuntimeException("Ocurrió un mensaje al buscar la exposicion\n" + e.toString());
        }
    }

    private String obtenerLocalidadExposicion(String nombreExposicion) {
        String sql = """
                    {call pt_obtenerLocalidadExposicion(?,?)}
                """;

        try {
            CallableStatement cs = conn.prepareCall(sql);
            cs.setString(1, nombreExposicion);
            cs.registerOutParameter(2, Types.VARCHAR);

            cs.execute();

            return cs.getString(2);
        } catch (SQLException e) {
            throw new RuntimeException("Errror al llamar al parametro de loclaizar localidade");
        }
    }

    public void anadirFotografia(Fotografo fotografo, Exposicion exposicion, Fotografia fotografia)
            throws SQLException {
        String sql = """
                INSERT INTO FOTOGRAFIA (NOME, MEDIDAS, DATA, COD_FOTOGRAFO, COD_EXPOSICION, COLOR)
                    VALUES (?,?,?,?,?,?)
                """;

        PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        GestorConexion.setParametros(ps,
                fotografia.getNome(),
                fotografia.getMedidas(),
                fotografia.getData(),
                fotografo.getCodigo(),
                exposicion.getCodigo(),
                String.valueOf(fotografia.getColor()));

        ps.executeUpdate();

        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            int codigo = rs.getInt(1);

            if (fotografia instanceof FotografiaArtistica) {
                FotografiaArtistica foto = (FotografiaArtistica) fotografia;

                String sqlEspecial = """
                        INSERT INTO ARTISTICA (CODIGO, ENCUADRE, COMPOSICION)
                            VALUES (?,?,?)
                        """;

                GestorConexion.ejecutarSentencia(conn, sqlEspecial, codigo, foto.getEncuadre(), foto.getComposicion());
                System.out.println("Fotografia de tipo artistica añadida, codigo: " + codigo);
            } else {
                FotografiaDocumental docu = (FotografiaDocumental) fotografia;

                String sqlEspecial = """
                        INSERT INTO DOCUMENTAL (CODIGO, TIPO) VALUES (?,?)
                        """;

                GestorConexion.ejecutarSentencia(conn, sqlEspecial, codigo, docu.getTipo());
                System.out.println("Fotografia de tipo documental añadida, codigo: " + codigo);

            }
        }

    }

    public void actualizarNumeroFotografias(Fotografo fotografo) {
        String sql = """
                    { ? = call fn__ObtenerNumeroFotografias(?)}
                """;

        try (CallableStatement cs = conn.prepareCall(sql)) {
            cs.registerOutParameter(1, Types.INTEGER);
            cs.setInt(2, fotografo.getCodigo());

            cs.executeUpdate();
            cs.getInt(1);

            int numFotografias = cs.getInt(1);

            String sqlExpecial = """
                    UPDATE FOTOGRAFO SET NUMFOTOGRAFIAS = ? WHERE CODIGO = ?
                    """;

            GestorConexion.ejecutarSentencia(conn, sqlExpecial, (numFotografias + 1), fotografo.getCodigo());

        } catch (SQLException e) {
            throw new RuntimeException("Ocurrió un mensaje al actualizar el numero de fotografias\n" + e.toString());
        }
    }

    public ArrayList<Fotografia> obtenerFotografiasDeExposcion(Exposicion expo) {
        ArrayList<Fotografia> fotografias = new ArrayList<>();
        String sql = """
                SELECT CODIGO, NOME, COD_FOTOGRAFO, MEDIDAS, DATA, COD_EXPOSICION, COLOR
                FROM FOTOGRAFIA
                WHERE COD_EXPOSICION = ?
                """;

        try {
            ResultSet rs = GestorConexion.ejecutarConsulta(conn, sql, expo.getCodigo());
            while (rs.next()) {
                int codigo = rs.getInt("CODIGO");
                String nome = rs.getString("NOME");
                String medidas = rs.getString("MEDIDAS");
                Date data = rs.getDate("DATA");
                int codFotografo = rs.getInt("COD_FOTOGRAFO");
                int codExposicion = rs.getInt("COD_EXPOSICION");
                char color = rs.getString("COLOR").toCharArray()[0];

                Fotografo fotografo = obtenerFotografoPorCodigo(codFotografo);

                boolean isArtistica = comprobarExistenciaArtistica(codigo);

                if (isArtistica) {
                    FotografiaArtistica artistica = obtenerFotografiaArtistica(codigo);
                    

                    artistica.setCodigo(codigo);
                    artistica.setCodExposicion(codExposicion);
                    artistica.setCodFotografo(codFotografo);
                    artistica.setColor(color);
                    artistica.setData(data);
                    artistica.setMedidas(medidas);
                    artistica.setNome(nome);
                    artistica.fotografo = fotografo;

                    fotografias.add(artistica);

                } else {
                    FotografiaDocumental documental = obtenerFotografiaDocumental(codigo);
                    documental.setCodigo(codigo);
                    documental.setCodExposicion(codExposicion);
                    documental.setCodFotografo(codFotografo);
                    documental.setColor(color);
                    documental.setData(data);
                    documental.setMedidas(medidas);
                    documental.setNome(nome);
                    documental.fotografo = fotografo;

                    fotografias.add(documental);
                }

            }

            return fotografias;
        } catch (SQLException e) {
            throw new RuntimeException("Ocurrió un problema al obtener las fotografias\n"
                    + e.toString());
        }
    }

    public Fotografo obtenerfotografoPorCodigo(int codigo) {
        String sql = """
                SELECT NOME
                FROM FOTOGRAFO
                WHERE CODIGO = ?
                """;

        try {
            ResultSet rs = GestorConexion.ejecutarConsulta(conn, sql, codigo);

            if (rs.next()) {
                Fotografo fotografo = new Fotografo();
                fotografo.setNombre(rs.getString("Nome"));

                return fotografo;
            }
            throw new RuntimeException("Error al coger al fotografo");
        } catch (SQLException e) {
            throw new RuntimeException("Error al coger al fotografo");
        }
    }

    public ArrayList<FotografiaFotografoDTO> obtenerNombresfotografiaExposicion(Exposicion expo) {
        ArrayList<FotografiaFotografoDTO> fotografiaFotografoDTOs = new ArrayList<>();
        String sql = """
                    {call pr_obtenerNombreFotografiasNombreFotografo(?,?,?)}
                """;

        try {
            CallableStatement cs = conn.prepareCall(sql);
            cs.setString(1, expo.getNome());

            cs.execute();
            ResultSet rs = cs.getResultSet();

            while (rs.next()) {
                String nomeFotografia = rs.getString(1);
                String nomeFoptografo = rs.getString(2);

                FotografiaFotografoDTO f = new FotografiaFotografoDTO(nomeFoptografo, nomeFotografia);
                fotografiaFotografoDTOs.add(f);

            }

            return fotografiaFotografoDTOs;

        } catch (SQLException e) {
            throw new RuntimeException("Ocurrió un problema al obtener las fotografias\n"
                    + e.toString());
        }
    }

    public FotografiaArtistica obtenerFotografiaArtistica(int codFotografia) {
        FotografiaArtistica foto = new FotografiaArtistica();

        String sql = """
                SELECT CODIGO, ENCUADRE, COMPOSICION
                FROM ARTISTICA
                WHERE CODIGO = ?
                """;

        try {
            ResultSet rs = GestorConexion.ejecutarConsulta(conn, sql, codFotografia);
            if (rs.next()) {
                foto.setEncuadre(rs.getString("ENCUADRE"));
                foto.setComposicion(rs.getString("COMPOSICION"));

                return foto;
            }

            throw new RuntimeException("Ocurrio un problema al intentar buscar una fotografia artistica");

        } catch (SQLException e) {
            throw new RuntimeException("Ocurrio un problema al intentar buscar una fotografia artistica");
        }

    }

    public FotografiaDocumental obtenerFotografiaDocumental(int codFotografia) {
        FotografiaDocumental foto = new FotografiaDocumental();

        String sql = """
                SELECT TIPO
                FROM DOCUMENTAL
                WHERE CODIGO = ?
                """;

        try {
            ResultSet rs = GestorConexion.ejecutarConsulta(conn, sql, codFotografia);
            if (rs.next()) {
                foto.setTipo(rs.getString("TIPO"));

                return foto;
            }

            throw new RuntimeException("Ocurrio un problema al intentar buscar una fotografia DOCUMENTAL");

        } catch (SQLException e) {
            throw new RuntimeException("Ocurrio un problema al intentar buscar una fotografia DOCUMENTAL");
        }

    }

    private boolean comprobarExistenciaArtistica(int codFotografia) {
        String sql = """
                SELECT TOP 1 CODIGO, ENCUADRE, COMPOSICION
                FROM ARTISTICA
                WHERE CODIGO = ?
                """;
        try {
            ResultSet rs = GestorConexion.ejecutarConsulta(conn, sql, codFotografia);
            if (rs.next()) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException e) {
            throw new RuntimeException("Ocurrio un problema al intentar buscar una fotografia artistica");
        }

    }

    public void trasladarImagenes(Exposicion e1, Exposicion e2) {
        String sql = """
                UPDATE FOTOGRAFIA SET COD_EXPOSICION = ? WHERE COD_EXPOSICION = ?
                """;

        GestorConexion.ejecutarSentencia(conn, sql, e2.getCodigo(), e1.getCodigo());
    }

}
