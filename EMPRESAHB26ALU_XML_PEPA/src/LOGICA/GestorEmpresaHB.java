package LOGICA;

import PERSITENCIA.EmpresaHBDAO;
import POJOS.Empregado;
import POJOS.Familiar;
import POJOS.Proxecto;
import POJOS.ProxectoFase;
import java.time.LocalDate;
import java.util.List;

public class GestorEmpresaHB {

    public static void comprobarConexion() {
        int resultado = EmpresaHBDAO.conectarHibernateDAO();

        if (resultado == 0) {
            System.out.println("Conexión correcta");

        } else {
            System.out.println("Error de conexión ");

        }
    }

    public static void visualizarProxecto(int proxecto) {
        try {
            Proxecto p = EmpresaHBDAO.buscarProxectoDAO(proxecto);

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

    /*
  NOTA   :  CAMBIÉ FECHADENACIMEINTO EN LOS POJOS Y EN EL FICHERO HBM.XML POR localdate
     */
    public static void altaEmpregado(Empregado empregado) {

        // Validacións básicas
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

        // Chamada ao DAO
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
            System.out.println("Función engadida correctamente.");
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

    public static void engadirFaseProxecto(int numProxecto, ProxectoFase fase) {
        try {
            boolean actualizado = EmpresaHBDAO.engadirFaseProxectoDAO(numProxecto, fase);
            if (actualizado) {
                System.out.println("Fase actualizada correctamente no proxecto " + numProxecto + ".");
            } else {
                System.out.println("Fase engadida correctamente ao proxecto " + numProxecto + ".");
            }
        } catch (RuntimeException e) {
            System.out.println("ERRO: " + e.getMessage());
        }
    }

    public static void engadirOuActualizarTelefono(String nss, String numero, String tipo) {

        try {
            Boolean actualizado = EmpresaHBDAO.engadirTelefonoDAO(nss, numero, tipo);
            if (actualizado == null) {
                System.out.println("O empregado co NSS " + nss + " non existe.");
                return;
            }

            if (actualizado) {
                System.out.println("Teléfono " + numero + " do empregado " + nss + " actualizado correctamente.");
            } else {
                System.out.println("Teléfono " + numero + " do empregado " + nss + "engadido correctamente.");
            }

        } catch (RuntimeException e) {
            System.out.println("ERRO: " + e.getMessage());
        }
    }

    public static void borrarTelefono(String nss, String numero) {
        try {
            Boolean borrado = EmpresaHBDAO.borrarTelefonoDAO(nss, numero);
            if (borrado == null) {
                System.out.println("O empregado co NSS " + nss + " non existe.");
                return;
            }
            if (!borrado) {
                System.out.println("O teléfono " + numero + " non existe para o empregado " + nss + ".");
                return;
            }
            System.out.println("Teléfono " + numero + " borrado correctamente do empregado " + nss + ".");

        } catch (RuntimeException e) {
            System.out.println("ERRO: " + e.getMessage());
        }
    }

    public static void crearFamiliar(String nssEmpregado, Familiar familiar) {
        Boolean resultado = EmpresaHBDAO.engadirFamiliarDAO(nssEmpregado, familiar);

        if (resultado == null) {
            System.out.println("ERROR: O empregado co NSS " + nssEmpregado + " non existe.");
        } else if (!resultado) {
            System.out.println("ERROR: Xa existe un familiar co NSS " + familiar.getNss() + " para el  empregado " + nssEmpregado);
        } else {
            System.out.println("Familiar " + familiar.getNss() + " engadido correctamente ao empregado " + nssEmpregado + ".");
        }
    }
    // Método que obtiene todos los empleados de una localidad y los imprime

    public static void mostrarEmpregadosPorLocalidade(String localidade) {
        if (localidade == null || localidade.isBlank()) {
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

    public static void crearEmpregadoConDepartamento(Empregado empregado, String nomeDepartamento) {

        int resultado = EmpresaHBDAO.crearEmpregadoConDepartamentoDAO(empregado, nomeDepartamento);

        switch (resultado) {
            case 0 -> System.out.println("Empleado creado correctamente en el departamento " + nomeDepartamento);
            case -1 -> System.out.println("ERROR: El empleado con NSS " + empregado.getNss() + " ya existe.");
            case -2 -> System.out.println("ERROR: El departamento " + nomeDepartamento + " no existe.");
            default -> System.out.println("ERROR inesperado al crear el empleado.");
        }
    }
    public static void cambiarDepartamentoEmpregado(
        String nssEmpregado, int numDepartamento) {

    int resultado = EmpresaHBDAO
            .cambiarDepartamentoEmpregadoHQL(nssEmpregado, numDepartamento);

    switch (resultado) {
        case 0 ->
            System.out.println("Departamento do empregado actualizado correctamente.");
        case -1 ->
            System.out.println("ERROR: O empregado non existe.");
        case -2 ->
            System.out.println("ERROR: O departamento non existe.");
        case -3 ->
            System.out.println("O empregado xa pertence a ese departamento.");
        default ->
            System.out.println("Erro inesperado ao cambiar o departamento.");
    }
}

}

