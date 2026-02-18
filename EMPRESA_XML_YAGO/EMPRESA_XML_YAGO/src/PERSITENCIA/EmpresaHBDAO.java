package PERSITENCIA;

import POJOS.Departamento;
import POJOS.Empregado;
import POJOS.Funcion;
import POJOS.Proxecto;
import Utilidades.HibernateUtil;

import java.util.Map;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
            System.out.println(e.getTelefonos());

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

}
