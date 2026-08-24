package PERSISTENCIA;

import UTILIDADES.HibernateUtil;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import POJOS.Pasteleria;
import POJOS.Pastelero;
import POJOS.Producto;

public class HBPasteleriaDAO {

    // Plantilla

    // try (Session session = HibernateUtil.getSessionFactory().openSession()) {
    // String hql = """
    // """;

    // Query<Pastelero> query = session.createQuery(hql, Pastelero.class);
    // return query.getResultList();
    // } catch (Exception e) {
    // throw new RuntimeException("Error al conectar con la conexión");
    // }

    // Plantilla para transacciones

    // Transaction tx = null;
    // try (Session sesion = HibernateUtil.getSessionFactory().openSession()) {
    // tx = sesion.beginTransaction();

    // tx.commit();
    // } catch (HibernateException e) {
    // if (tx != null) tx.rollback();
    // throw new RuntimeException("No se pudo abrir la sesión de Hibernate", e);
    // }

    public static int conectarHibernateDAO() {
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        if (sesion != null) {

            sesion.close();
            return 0;
        } else {
            return -1;
        }
    }

    public static List<Pastelero> obtenerPasteleros() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = """
                    FROM Pastelero
                    """;

            Query<Pastelero> query = session.createQuery(hql, Pastelero.class);
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error al conectar con la conexión");
        }
    }

    public static Pastelero obtenerPastelero(String codPastelero) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = """
                    FROM Pastelero WHERE Codigo = :cod
                    """;

            Query<Pastelero> query = session.createQuery(hql, Pastelero.class);
            query.setParameter("cod", codPastelero);
            return query.getResultList().get(0);

        } catch (Exception e) {
            throw new RuntimeException("El pastenero no existe");
        }
    }

    public static void actualizarAnadirNivelHabilidad(Pastelero p, String nombreHabilidad, String nivelAsociado) {
        Transaction tx = null;
        try (Session sesion = HibernateUtil.getSessionFactory().openSession()) {
            tx = sesion.beginTransaction();

            p.getTecnicas().put(nombreHabilidad, nivelAsociado);

            sesion.saveOrUpdate(p);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            throw new RuntimeException("No se pudo abrir la sesión de Hibernate", e);
        }
    }

    public static Pasteleria obtenerPasteleria(int codigo) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = """
                    FROM Pasteleria WHERE Codigo = :cod
                            """;

            Query<Pasteleria> query = session.createQuery(hql, Pasteleria.class);
            query.setParameter("cod", codigo);
            List<Pasteleria> pastelerias = query.getResultList();
            return pastelerias.get(0);
        } catch (Exception e) {
            throw new RuntimeException("No se encontro la pasteleria");
        }
    }

    public static Pasteleria obtenerPasteleria(String nombre) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = """
                    FROM Pasteleria WHERE Nome = :nome
                            """;

            Query<Pasteleria> query = session.createQuery(hql, Pasteleria.class);
            query.setParameter("nome", nombre);
            List<Pasteleria> pastelerias = query.getResultList();
            return pastelerias.get(0);
        } catch (Exception e) {
            throw new RuntimeException("No se encontro la pasteleria");
        }
    }

    public static Pastelero obtenerPasteleroPorAlias(String aliasPastelero) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = """
                    FROM Pastelero WHERE Alias = :alias
                        """;

            Query<Pastelero> query = session.createQuery(hql, Pastelero.class);
            query.setParameter("alias", aliasPastelero);
            return query.getResultList().get(0);
        } catch (Exception e) {
            throw new RuntimeException("Error al conectar con la conexión");
        }
    }

    public static void actualizarDuenoPasteleria(Pasteleria pasteleria, Pastelero pasteleroAnterior,
            Pastelero pasteleroNuevo) {

        Transaction tx = null;
        try (Session sesion = HibernateUtil.getSessionFactory().openSession()) {
            tx = sesion.beginTransaction();

            // Primero tenemos que quitar la pasteleria al dueño anterior
            // Al final lo comento porqu eesto ya lo hace automaticamente hibernate
            // pasteleroAnterior.getPastelerias().remove(pasteleria);

            // Segundo añadimos la pasteleria al nuevo dueño
            pasteleroNuevo.getPastelerias().add(pasteleria);

            // A la pasteleria se le actualiza el nuevo pastelero
            pasteleria.setPastelero(pasteleroNuevo);

            // sesion.update(pasteleroAnterior);
            sesion.update(pasteleroNuevo);
            sesion.update(pasteleria);
            tx.commit();

            System.out.println("EXITO: Se ha cambiado el dueño a " + pasteleroNuevo.getNome() + " correctamente");
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            throw new RuntimeException("No se pudo abrir la sesión de Hibernate", e);
        }
    }

    public static List<Producto> obtenerProductos() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = """
                                FROM Producto
                    """;

            Query<Producto> query = session.createQuery(hql, Producto.class);
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error al conectar con la conexión");
        }
    }

    public static Producto obtenerProdcto(int codigo) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = """
                                FROM Producto WHERE Codigo = :cod
                    """;

            Query<Producto> query = session.createQuery(hql, Producto.class);
            query.setParameter("cod", codigo);
            return query.getResultList().get(0);
        } catch (Exception e) {
            throw new RuntimeException("No SE ENCONTRO EL PRODUCTO");
        }
    }

    public static void eliminarProducto(Producto pro) {
        Transaction tx = null;
        try (Session sesion = HibernateUtil.getSessionFactory().openSession()) {
            tx = sesion.beginTransaction();

            sesion.remove(pro);

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            throw new RuntimeException("No se pudo abrir la sesión de Hibernate", e);
        }
    }

    public static List<Pastelero> obtenerPastelerosVarones() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = """
            FROM Pastelero WHERE Sexo = 'H'
                    """;

            Query<Pastelero> query = session.createQuery(hql, Pastelero.class);
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error al conectar con la conexión");
        }
    }
}