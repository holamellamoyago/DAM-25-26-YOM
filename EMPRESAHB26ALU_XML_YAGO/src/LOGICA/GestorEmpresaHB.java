
package LOGICA;

import org.hibernate.query.Query;

import PERSITENCIA.EmpresaHBDAO;
import POJOS.Departamento;
import POJOS.Empregado;
import POJOS.Proxecto;

public class GestorEmpresaHB {
    public static void comprobarConexion() {
        int resultado = EmpresaHBDAO.conectarHibernate();

        if (resultado == 0) {
            System.out.println("Conexión correcta");

        } else {
            System.out.println("Error de conexión ");

        }
    }

    public static void visualizarProxecto(int proxecto) {
        try {
            Proxecto p = EmpresaHBDAO.buscarProxecto(proxecto);

            if (p == null) {
                System.out.println("No existe el proyecto con código " + proxecto);
            } else {
                System.out.println("Proyecto encontrado:");
                System.out.println("Número: " + p.getNumProxecto());
                System.out.println("Nombre: " + p.getNomeProxecto());
            }

        } catch (RuntimeException e) {
            System.out.println("Error de acceso a la base de datos: " + e.getMessage());
        }
    }

    public static void visualizarEmpregado(String nss) {
        Empregado empregado = EmpresaHBDAO.buscarEmpregado(nss);

        if (empregado != null) {
            System.out.println(empregado);
        } else {
            System.out.println("No existe el empregado " + nss);
        }
    }

    public static void anadirFuncionesPorDepartamento(String funcion, Departamento departamento) {
        if (EmpresaHBDAO.listarTodasTablas().contains("FAMILIAR")) {
            System.out.println("Existe");
        } else{
            System.out.println("No existe");
        }
        
    }

}
