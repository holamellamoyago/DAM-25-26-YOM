package LOGICA;

import PERSITENCIA.EmpresaHBDAO;
import POJOS.Empregado;
import POJOS.Proxecto;
import java.time.LocalDate;
import java.util.List;

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

    public static void altaEmpregado(Empregado empregado) {

       
        if (empregado.getNss() == null || empregado.getNss().isEmpty()) {
            System.out.println("Erro: o NSS non pode estar baleiro");
            return;
        }
        if (empregado.getNome() == null || empregado.getNome().isEmpty()) {
            System.out.println("Erro: o nome non pode estar baleiro");
            return;
        }
        if (empregado.getApelido1() == null || empregado.getApelido1().isEmpty()) {
            System.out.println("Erro: o primeiro apelido non pode estar baleiro");
            return;
        }
        if (empregado.getDataNacemento() == null || empregado.getDataNacemento().isAfter(LocalDate.now())) {
            System.out.println("Erro: a data de nacemento é inválida");
            return;
        }
        if (empregado.getSexo() == null || (empregado.getSexo() != 'H' && empregado.getSexo() != 'M')) {
            System.out.println("Erro: o sexo debe ser 'H' ou 'M'");
            return;
        }

              try {
            EmpresaHBDAO.guardarEmpregadoDAO(empregado);
            System.out.println("Empregado creado correctamente.");
        } catch (RuntimeException e) {
            System.out.println("ERRO: " + e.getMessage());
        }
    }

    public static void buscarEmpregado(String nss) {
        try {
            Empregado e = EmpresaHBDAO.buscarEmpregadoDAO(nss);
            if (e != null) {
                System.out.println("Empleado encontrado: " + e.getNome() + " " + e.getApelido1());
            } else {
                System.out.println("Empleado no encontrado");
            }
        } catch (RuntimeException e) {
            System.out.println("Error al buscar empleado: " + e.getMessage());
        }
    }

    public static void altaFuncionDept(int numDepartamento, String funcion) {
        try {
            EmpresaHBDAO.guardarFuncionDeptDAO(numDepartamento, funcion);
            System.out.println("Función "+ funcion+ " engadida correctamente ao departamento "+numDepartamento);
        } catch (RuntimeException e) {
            System.out.println("ERRO: " + e.getMessage());
        }
    }

    
    public static void eliminarFuncionDepartamento(int numDepartamento, String funcion) {

        if (funcion == null || funcion.isBlank()) {
            System.out.println("Erro: a función non pode estar baleira.");
            return;
        }

        try {
           EmpresaHBDAO.eliminarFuncionDeptDAO(numDepartamento, funcion);
            System.out.println("Función eliminada correctamente do departamento.");

        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
   
// Método que obtiene todos los empleados de una localidad y los imprime
    public static void mostrarEmpregadosPorLocalidade(String localidade) {
        if (localidade == null || localidade.isBlank()) {  //Si se introduce " "
            System.out.println("Localidade non válida.");
            return;
        }

        try {
            List<Empregado> empregados = EmpresaHBDAO.obterEmpregadosPorLocalidadeDAO(localidade);

            if (empregados.isEmpty()) {
                System.out.println("Non hai empregados na localidade: " + localidade);
            } else {
                System.out.println("Empregados en " + localidade + ":");
                for (Empregado e : empregados) {
                    String apelido2 = e.getApelido2() != null ? e.getApelido2() : "";
                    System.out.println(e.getNss() + " - " + e.getNome() + " " + e.getApelido1() + " " + apelido2);
                }
            }

        } catch (RuntimeException ex) {
            System.out.println("Erro ao consultar empregados: " + ex.getMessage());
        }
    }
    
    
}



