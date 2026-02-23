package PERSITENCIA;

import POJOS.Departamento;
import POJOS.Empregado;
import POJOS.Proxecto;
import POJOS.Vehiculo;
import Utilidades.HibernateUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import DTO.ProxectoDTO;

public class EmpresaHBDAO_Consultas {

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

    // ____

    public static int conectarHibernate() {
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        if (sesion != null) {

            sesion.close();
            return 0;
        } else {
            return -1;
        }
    }

    public static List<Proxecto> listarProxectosVigoSantiago() {
        try (Session sesion = HibernateUtil.getSessionFactory().openSession()) {
            String hql = """
                    FROM Proxecto 
                    WHERE Lugar = 'Santiago' OR Lugar = 'Vigo' OR Lugar = :nomeLugar
                    ORDER BY Lugar
                    """;;

            Query<Proxecto> query = sesion.createQuery(hql, Proxecto.class);
            query.setParameter("nomeLugar", "Pontevedra");

            List<Proxecto> list = query.getResultList().get(0);
            return list;

        } catch (HibernateException e) {
            throw new RuntimeException("No se pudo abrir la sesión de Hibernate", e);
        }
    }

    public static List<ProxectoDTO> listarProxectosVigoSantiagoDTO() {
        List<ProxectoDTO> proxectos = new ArrayList<>();

        for (Proxecto p : listarProxectosVigoSantiago()) {
            proxectos.add(new ProxectoDTO(p.getNomeProxecto(), p.getLugar(), p.getNumProxecto()));
        }

        return proxectos;
    }

}
