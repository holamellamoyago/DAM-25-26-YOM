package PERSITENCIA;

import POJOS.Departamento;
import POJOS.Empregado;
import POJOS.Familiar;
import POJOS.Proxecto;
import POJOS.ProxectoFase;
import Utilidades.HibernateUtil;
import java.util.List;
import java.util.Map;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
                       throw new RuntimeException("No se pudo abrir la sesiónn de Hibernate", e);
        }
    }

    public static void guardarEmpregadoDAO(Empregado empregado) {

        Transaction tx = null;
        try (Session sesion = HibernateUtil.getSessionFactory().openSession()) {
            tx = sesion.beginTransaction();
            Empregado existente = sesion.get(Empregado.class, empregado.getNss());
            if (existente != null) {
                throw new RuntimeException("Xa existe un empregado co NSS " + empregado.getNss());
            }
            sesion.save(empregado);
            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            throw new RuntimeException("Erro de Hibernate ao crear empregado", e);
        }
    }

    public static Empregado buscarEmpregadoDAO(String nss) {
        try (Session sesion = HibernateUtil.getSessionFactory().openSession()) {
            return sesion.get(Empregado.class, nss);
        } catch (HibernateException e) {
            throw new RuntimeException("Error al buscar empleado en BD", e);
        }
    }

    public static Departamento buscarDepartamentoDAO(int numDepartamento) {
        try (Session sesion = HibernateUtil.getSessionFactory().openSession()) {
            return sesion.get(Departamento.class, numDepartamento);
        } catch (HibernateException e) {
            throw new RuntimeException("Erro ao buscar departamento na BD", e);
        }
    }

    public static void guardarFuncionDeptDAO(int numDepartamento, String funcion) {

        Transaction tx = null;

        try (Session sesion = HibernateUtil.getSessionFactory().openSession()) {
            tx = sesion.beginTransaction();
            Departamento d = sesion.get(Departamento.class, numDepartamento);
            if (d == null) {
                throw new RuntimeException("O departamento " + numDepartamento + " non existe.");
            }
            if (d.getFunciones().contains(funcion)) {
                throw new RuntimeException("A función '" + funcion + "' xa está asignada ao departamento " + numDepartamento + ".");
            }
            d.getFunciones().add(funcion);
            tx.commit();

        } catch (HibernateException e) {

            if (tx != null) {
                tx.rollback();
            }
            throw new RuntimeException("Erro de Hibernate ao gardar a función no departamento", e);

        }

    }

        public static void eliminarFuncionDeptDAO(int numDepartamento, String funcion) {

        Transaction tx = null;

        try (Session sesion = HibernateUtil.getSessionFactory().openSession()) {
            tx = sesion.beginTransaction();
            Departamento d = sesion.get(Departamento.class, numDepartamento);
            if (d == null) {
                throw new RuntimeException(
                        "O departamento " + numDepartamento + " non existe."
                );
            }
             if (!d.getFunciones().contains(funcion)) {
                throw new RuntimeException(
                        "A función '" + funcion + "' non pertence ao departamento " + numDepartamento + "."
                );
            }
            d.getFunciones().remove(funcion);

            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            throw new RuntimeException(
                    "Erro de Hibernate ao eliminar a función do departamento", e);
        }
    }

   
    public static List<Empregado> obterEmpregadosPorLocalidadeDAO(String localidade) {

        try (Session sesion = HibernateUtil.getSessionFactory().openSession()) {
            /*
        La consulta devuelve una lista de objetos de una clase asociada: Empregado
             */
            String hql = """
            FROM Empregado e
            WHERE e.enderezo.localidade = :localidade
        """;

            return sesion.createQuery(hql, Empregado.class)
                    .setParameter("localidade", localidade)
                    .getResultList();   // query.list() en HB5 sigue pero está en desuso (deprecated conceptualmente),  en HB6 lo iliminaron 
            /*
        por parte sería 
        Query<Empregado> query = sesion.createQuery(hql, Empregado.class);
        query.setParameter("localidade", localidade);
         return query.getResultList();
             */

        } catch (HibernateException e) {
            throw new RuntimeException("Erro de Hibernate ao consultar empregados por localidade", e);
        }
    }

}
