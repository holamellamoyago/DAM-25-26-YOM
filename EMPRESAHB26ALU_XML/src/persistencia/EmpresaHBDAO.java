package persistencia;

import pojos.*;
import Utilidades.*;

import org.hibernate.*;

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

    // public static void crearEmpregado(Empregado empregado) {
    //     // Uso de try-with-resources para cerrar la sesión automáticamente
    //     try (Session session = HibernateUtil.getSessionFactory().openSession()) {
    //         Transaction tr = session.beginTransaction();
    //             session.save(empregado);
    //             tr.commit();

    //         //     } catch (Exception e) {
    //         //     if (tr != null) tr.rollback();
    //         //     throw e; 
    //         // }
            
    //     } 
    // }

    // public static Empregado buscarEmpregado(String NSS) {
    //     try (Session sesion = HibernateUtil.getSessionFactory().openSession()) {
    //         return sesion.get(Empregado.class, NSS);
    //     } catch (Exception e) {
    //         throw e;
    //     }
    // }
}

}
