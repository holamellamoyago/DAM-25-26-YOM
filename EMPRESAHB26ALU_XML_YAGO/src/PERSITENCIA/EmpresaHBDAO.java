package PERSITENCIA;

import POJOS.Empregado;
import POJOS.Proxecto;
import Utilidades.HibernateUtil;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

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

}
