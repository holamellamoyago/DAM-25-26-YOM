
package LOGICA;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Session;

import DTO.ProxectoDTO;
import PERSITENCIA.EmpresaHBDAO;
import PERSITENCIA.EmpresaHBDAO_Consultas;
import POJOS.Departamento;
import POJOS.Empregado;
import POJOS.Funcion;
import POJOS.Habilidad;
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
        Map<String, String> telefonos = EmpresaHBDAO.obtenerTelefonosEmpregado(nss);

        if (telefonos.containsKey(tlfn)) {
            System.out.println("Ya existe ese número guardado");
            return;
        }

        EmpresaHBDAO.guardarNumeroTlfn(EmpresaHBDAO.obtenerEmpregado(nss), tlfn, informacion);
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

    public static void eliminarVehiculoEmpregado(String nss) {
        Empregado e = obtenerEmpregado(nss);

        if (e == null) {
            System.out.println("Ese empregado no tiene vehiculo");
            return;
        }

        EmpresaHBDAO.eliminarVehiculoEmpregado(nss);
    }

    public static void anadirEmpregado(Empregado empregado) {
        if (obtenerEmpregado(empregado.getNss()) != null) {
            System.out.println("Ya existe el empregado , no se inserta nada nuevo");
            return;
        }

        EmpresaHBDAO.anadirEmpregado(empregado);

    }

    public static void listarProxectosVigoSantiago() {
        List<Proxecto> proxectos = EmpresaHBDAO_Consultas.listarProxectosVigoSantiago();

        System.out.println("\nNumero" + "\t" + "Nombre" + "\t" + "Lugar");
        for (Proxecto p : proxectos) {
            System.out.println(p.getNumProxecto() + "\t" + p.getNomeProxecto() + "\t" + p.getLugar());
        }
    }

    public static void listarProxectosVigoSantiagoDTO() {
        List<ProxectoDTO> proxectos = EmpresaHBDAO_Consultas.listarProxectosVigoSantiagoDTO();

        System.out.println("\nNumero" + "\t" + "Nombre" + "\t\t" + "Lugar");
        for (ProxectoDTO p : proxectos) {
            System.out.println(p.getNumProxecto() + "\t" + p.getNomeProxecto() + "\t\t" + p.getLugar());
        }
    }

    public static Habilidad anadirHabilidad(String strHabilidad) {
        List<Habilidad> habilidades = obtenerHabilidades();

        Habilidad habilidad = null;
        for (Habilidad h : habilidades) {
            if (h.getHabilidad().equals(strHabilidad)) {
                System.out.println("La habilidad ya existe");
                habilidad = h;
            }
        }

        if (habilidad == null) {
            System.out.println("Creada la habilidad " + strHabilidad);
            return EmpresaHBDAO.añadirHabilidad(strHabilidad);
        }

        return habilidad;

    }

    private static List<Habilidad> obtenerHabilidades() {
        return EmpresaHBDAO.obtenerHabilidades();
    }

    public static void asignarHabilidad(String nss, Habilidad h) {
        Empregado e = obtenerEmpregado(nss);

        if (e.getHabilidades().contains(h)) {
            System.out.println("El empregado ya tiene esa habilidad");
            return;
        }

        EmpresaHBDAO.asignarHabilidad(e,h);
        System.out.println("Habilidad asignada");
    }

}
