
package LOGICA;

import java.util.Map;
import java.util.Set;

import PERSITENCIA.EmpresaHBDAO;
import POJOS.Departamento;
import POJOS.Empregado;
import POJOS.Funcion;
import POJOS.Proxecto;
import POJOS.Vehiculo;

public class GestorEmpresaHB {
    public static void comprobarConexion() {
        int resultado = EmpresaHBDAO.conectarHibernate();

        if (resultado == 0) {
            System.out.println("Conexión correcta");

        } else {
            System.out.println("Error de conexión ");

        }
    }

    public static Proxecto visualizarProxecto(int proxecto) {
        try {
            Proxecto p = EmpresaHBDAO.buscarProxecto(proxecto);

            if (p == null) {
                throw new RuntimeException("No se encontro el proxecto");
            } else {
                System.out.println("Proyecto encontrado:");
                System.out.println("Número: " + p.getNumProxecto());
                System.out.println("Nombre: " + p.getNomeProxecto());
                return p;
            }

        } catch (RuntimeException e) {
            throw new RuntimeException("No se encontro el proxecto");
        }
    }

    public static void cambiarLugarProxecto(Proxecto pro, String nuevoLugar) {
        if (EmpresaHBDAO.cambiarLugarProxecto(pro, nuevoLugar)) {
            System.out.println("Lugar cambiado correctamente");
        }
    }

    public static Empregado obtenerEmpregado(String nss) {
        Empregado e = EmpresaHBDAO.obtenerEmpregado(nss);
        // System.out.println("Empregado: " + e.getNome() + ", " + e.getDireccion());
        System.out.println(e.getDepartamento());

        return e;
    }

    public static Departamento obtenerDepartamento(int numDepartamento) {
        Departamento d = EmpresaHBDAO.buscarDepartamento(numDepartamento);

        if (d != null) {
            System.out.println("\nDepartamento encontrado");
            return d;
        } else {
            throw new RuntimeException("No se encontro el departament");
        }
    }

    public static void anadirFuncionesDepartamento(int departamento, Set<String> funciones) {
        Departamento d = obtenerDepartamento(departamento);
        System.out.println(d);
        funciones.addAll(d.getFunciones());

        EmpresaHBDAO.anadirFuncionesDepartamenbto(d, funciones);
        System.out.println("Funciones anadidas");

    }

    public static void anadirTelefonoEmpregado(String nss, String tlfn, String informacion) {
        Map<String, String> telefonos =  EmpresaHBDAO.obtenerTelefonosEmpregado(nss);
        
        if (telefonos.containsKey(tlfn)) {
            System.out.println("Ya existe ese número guardado");
            return;
        }

    
        EmpresaHBDAO.guardarNumeroTlfn(EmpresaHBDAO.obtenerEmpregado(nss) , tlfn, informacion);
    }

    public static void cambiarMatriculaVehiculo(String nss, String nuevaMatricula) {
        Empregado e = obtenerEmpregado(nss);
        Vehiculo v = e.getVehiculo();

        if (v.getMatricula().equals(nuevaMatricula)) {
            System.out.println("Ya tiene esa matrícula");
            return;
        }


        EmpresaHBDAO.cambiarMatriculaVehiculo(nss, nuevaMatricula);
    }

}
