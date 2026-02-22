package PERSITENCIA;

import POJOS.Departamento;
import POJOS.Empregado;
import POJOS.Funcion;
import POJOS.Habilidad;
import POJOS.Proxecto;
import POJOS.Vehiculo;
import Utilidades.HibernateUtil;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class EmpresaHBDAO {

    /*
     * ConectarHibernate
     * buscarProxecto
     * 
     * 
     */

    // Transaction tx = null;
    // try (Session sesion = HibernateUtil.getSessionFactory().openSession()) {
    // tx = sesion.beginTransaction();

    // tx.commit();
    // } catch (HibernateException e) {
    // if (tx != null) tx.rollback();
    // throw new RuntimeException("No se pudo abrir la sesión de Hibernate", e);
    // }
    public static int conectarHibernate() {
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        if (sesion != null) {

            sesion.close();
            return 0;
        } else {
            return -1;
        }
    }

    public static Proxecto buscarProxecto(int proxecto) {
        try (Session sesion = HibernateUtil.getSessionFactory().openSession()) {
            return sesion.get(Proxecto.class, proxecto);
        } catch (HibernateException e) {
            throw new RuntimeException("No se pudo abrir la sesión de Hibernate", e);
        }
    }

    public static boolean cambiarLugarProxecto(Proxecto proxecto, String nuevoLugar) {
        Transaction tx = null;
        try (Session sesion = HibernateUtil.getSessionFactory().openSession()) {
            tx = sesion.beginTransaction();

            proxecto.setLugar(nuevoLugar);
            sesion.saveOrUpdate(proxecto);

            tx.commit();
            return true;
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            throw new RuntimeException("No se pudo abrir la sesión de Hibernate", e);
        }
    }

    public static Empregado obtenerEmpregado(String string) {

        Transaction tx = null;
        try (Session sesion = HibernateUtil.getSessionFactory().openSession()) {
            tx = sesion.beginTransaction();

            Empregado e = sesion.get(Empregado.class, string);

            return e;
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            throw new RuntimeException("No se pudo abrir la sesión de Hibernate", e);
        }
    }

    public static Departamento buscarDepartamento(int numDepartamento) {
        try (Session sesion = HibernateUtil.getSessionFactory().openSession()) {
            return sesion.get(Departamento.class, numDepartamento);
        } catch (Exception e) {
            throw new RuntimeException("No se pudo abrir la sesión de Hibernate", e);
        }
    }

    public static void anadirFuncionesDepartamenbto(Departamento d, Set<String> funciones) {
        Transaction tx = null;
        try (Session sesion = HibernateUtil.getSessionFactory().openSession()) {
            tx = sesion.beginTransaction();

            System.out.println(d.getFunciones());
            d.setFunciones(funciones);
            sesion.saveOrUpdate(d);

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            throw new RuntimeException("No se pudo abrir la sesión de Hibernate", e);
        }
    }

    public static Map<String, String> obtenerTelefonosEmpregado(String nss) {
        try (Session sesion = HibernateUtil.getSessionFactory().openSession()) {
            Empregado e = sesion.get(Empregado.class, nss);
            return e.getTelefonos();
        } catch (HibernateException e) {
            throw new RuntimeException("No se pudo abrir la sesión de Hibernate", e);
        }
    }

    public static void guardarNumeroTlfn(Empregado empregado, String tlfn, String informacion) {
        Transaction tx = null;
        try (Session sesion = HibernateUtil.getSessionFactory().openSession()) {
            tx = sesion.beginTransaction();

            empregado.getTelefonos().put(tlfn, informacion);
            sesion.saveOrUpdate(empregado);

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            throw new RuntimeException("No se pudo abrir la sesión de Hibernate", e);
        }
    }

    public static void cambiarMatriculaVehiculo(String nss, String nuevaMatricula) {
        Transaction tx = null;
        try (Session sesion = HibernateUtil.getSessionFactory().openSession()) {
            tx = sesion.beginTransaction();

            Empregado e = sesion.get(Empregado.class, nss);
            e.getVehiculo().setMatricula(nuevaMatricula);

            sesion.update(e);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            throw new RuntimeException("No se pudo abrir la sesión de Hibernate", e);
        }
    }

    public static Vehiculo obtenerVehiculo(String nss) {
        try (Session sesion = HibernateUtil.getSessionFactory().openSession()) {
            return sesion.get(Vehiculo.class, nss);
        } catch (HibernateException e) {
            throw new RuntimeException("No se pudo abrir la sesión de Hibernate", e);
        }
    }

    public static void eliminarVehiculoEmpregado(String nss) {
        Transaction tx = null;
        try (Session sesion = HibernateUtil.getSessionFactory().openSession()) {
            tx = sesion.beginTransaction();

            Empregado e = obtenerEmpregado(nss);
            e.setVehiculo(null);

            sesion.remove(obtenerVehiculo(nss));

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            throw new RuntimeException("No se pudo abrir la sesión de Hibernate", e);
        }
    }

    public static void anadirEmpregado(Empregado empregado) {
        Transaction tx = null;
        try (Session sesion = HibernateUtil.getSessionFactory().openSession()) {
            tx = sesion.beginTransaction();

            sesion.save(empregado);

            tx.commit();
            System.out.println("Empregado creado correctamente");
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            throw new RuntimeException("No se pudo abrir la sesión de Hibernate", e);
        }
    }

    public static List<Habilidad> obtenerHabilidades() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = """
                    FROM Habilidad
                    """;
            Query<Habilidad> query = session.createQuery(hql, Habilidad.class);

            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("No se pudo abrir la sesión de Hibernate", e);
        }
    }

    public static Habilidad añadirHabilidad(String strHabilidad) {
        Transaction tx = null;
        try (Session sesion = HibernateUtil.getSessionFactory().openSession()) {
            tx = sesion.beginTransaction();

            Habilidad h = new Habilidad();
            h.setHabilidad(strHabilidad);

            sesion.saveOrUpdate(h);
            tx.commit();
            return h;
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            throw new RuntimeException("No se pudo abrir la sesión de Hibernate", e);
        }
    }

    public static void asignarHabilidad(Empregado emp, Habilidad h) {
        Transaction tx = null;
        try (Session sesion = HibernateUtil.getSessionFactory().openSession()) {
            tx = sesion.beginTransaction();

            emp.getHabilidades().add(h);

            sesion.saveOrUpdate(emp);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            throw new RuntimeException("No se pudo abrir la sesión de Hibernate", e);
        }
    }

}
