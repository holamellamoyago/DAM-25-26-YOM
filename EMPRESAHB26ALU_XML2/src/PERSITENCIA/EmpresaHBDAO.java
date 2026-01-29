package PERSITENCIA;

import POJOS.Proxecto;
import Utilidades.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

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

}
