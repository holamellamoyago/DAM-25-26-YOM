package PERSITENCIA;

import POJOS.Departamento;
import POJOS.Empregado;
import POJOS.Proxecto;
import Utilidades.HibernateUtil;

import java.util.*;
import org.hibernate.*;
import org.hibernate.query.*;

public class EmpresaHBDAO {

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
            // Lanzamos un RuntimeException
            throw new RuntimeException("No se pudo abrir la sesión de Hibernate", e);
        }
    }

    public static Empregado buscarEmpregado(String nssEmpregado) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Empregado.class, nssEmpregado);
        } catch (Exception e) {
            throw new RuntimeException("No se pudo abrir la sesión de Hibernate", e);
        }
    }

    public static List<String> listarTodasTablas() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            NativeQuery query = session.createNativeQuery("SELECT NAME FROM SYS.TABLES");
            List<String> tablas = query.getResultList();
            for (String tabla : tablas) {
                System.out.println("  -> '" + tabla + "'");
            }

            return tablas;

        } catch (Exception e) {
            throw new RuntimeException("No se pudo abrir la sesión de Hibernate", e);
        }
    }

    public static void comprobarExistenciaTabla(String nombreTabla) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            NativeQuery query = session.createNativeQuery("SELECT DB_NAME()");
            System.out.println("BD activa: " + query.getSingleResult());
            System.out.println(nombreTabla + ":___________________");
            NativeQuery query2 = session.createNativeQuery("SELECT * FROM SYS.TABLES WHERE name = :nombreTabla");
            query2.setParameter("nombreTabla", "PERSOAL");
            System.out.println(query2.getResultList());

        } catch (Exception e) {
            throw new RuntimeException("No se pudo abrir la sesión de Hibernate", e);
        }
    }

    public static void crearTablaFunciones() {
        Transaction transacion = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transacion = session.beginTransaction();

            NativeQuery crearTabla = session.createNativeQuery("""
                    CREATE TABLE DEPARTAMENTOFUNCIONES (
                        NumDepartamento INT,
                        Funcion VARCHAR(50)
                    )
                    """);

            crearTabla.executeUpdate();

            session.getTransaction().commit();

        } catch (Exception e) {
            if (transacion != null)
                transacion.rollback();
            throw new RuntimeException("No se pudo abrir la sesión de Hibernate", e);
        }
    }

    public static void anadirFuncionDerpartamento(String funcion, Departamento departamento) {
        Transaction transacion = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transacion = session.beginTransaction();

            Departamento depa = session.get(Departamento.class, departamento.getNumDepartamento());
            depa.getFunciones().add(funcion);

            session.saveOrUpdate(depa);
            session.getTransaction().commit();

        } catch (Exception e) {
            if (transacion != null)
                transacion.rollback();
            throw new RuntimeException("No se pudo abrir la sesión de Hibernate", e);
        }
    }

    public static Departamento obtenerDepartamento(int numDepartamento) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            return session.get(Departamento.class, numDepartamento);

        } catch (Exception e) {
            throw new RuntimeException("No se pudo abrir la sesión de Hibernate", e);
        }
    }

    public static boolean comprobarFuncionExistenteDepartamento(String funcion, Departamento departamento) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            departamento = session.get(Departamento.class, departamento.getNumDepartamento());

            if (departamento.getFunciones().contains(funcion)) {
                System.out.println("El departamento: " + departamento + " ya contiene la funcion: " + funcion);
                return true;
            } 
            
        } catch (Exception e) {
            throw new RuntimeException("No se pudo abrir la sesión de Hibernate", e);
        }


        return false;
    }

    // Transaction transacion = null;
    // try (Session session = HibernateUtil.getSessionFactory().openSession()) {
    // transacion = session.beginTransaction();

    // session.getTransaction().commit();

    // } catch (Exception e) {
    // if (transacion != null) transacion.rollback();
    // throw new RuntimeException("No se pudo abrir la sesión de Hibernate", e);
    // }

}
