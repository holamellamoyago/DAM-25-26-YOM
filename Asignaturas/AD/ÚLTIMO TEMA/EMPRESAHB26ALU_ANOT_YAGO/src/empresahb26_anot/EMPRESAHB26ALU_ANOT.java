
package empresahb26_anot;

import LOGICA.GestorEmpresaHB;
import POJOS.Empregado;
import POJOS.Enderezo;
import java.text.ParseException;
import java.time.LocalDate;

public class EMPRESAHB26ALU_ANOT {

    public static void main(String[] args) throws ParseException, ClassNotFoundException {
        GestorEmpresaHB.comprobarConexion();

        System.out.println();
        GestorEmpresaHB.visualizarProxecto(1);

        System.out.println();
        GestorEmpresaHB.buscarEmpregado("1111111");

        // crearEmpleado();

        // AltaFuncionDepartamento();
        // BajaFuncionDepartamento();
        //
        // EmpregadoporLocalidad();
    }

    public static void crearEmpleado() {
        Enderezo enderezo = new Enderezo(
                "Calle Falsa", // rua
                123, // numeroCalle
                "1A", // piso
                "15001", // cp
                "Santiago", // localidade
                "A Coru�a" // provincia
        );

        // Creamos el empleado usando el constructor (asumiendo que tienes un
        // constructor que acepte todos los campos)
        Empregado novoEmpregado = new Empregado(
                "00000067", // nss
                "Luis", // nome
                "Souto", // apelido1
                "Real", // apelido2
                LocalDate.of(2000, 1, 15), // dataNacemento
                'H' // sexo
        );
        novoEmpregado.setEnderezo(enderezo);
        GestorEmpresaHB.altaEmpregado(novoEmpregado);

    }

    private static void AltaFuncionDepartamento() {
        int numDepartamento = 1;
        String funcion = "Control de orzamentos";
        GestorEmpresaHB.altaFuncionDept(numDepartamento, funcion);

    }

    private static void BajaFuncionDepartamento() {
        int numDepartamento = 1;
        String funcion = "Control de orzamentos";
        GestorEmpresaHB.eliminarFuncionDepartamento(numDepartamento, funcion);

    }

    private static void EmpregadoporLocalidad() {
        // Probar empleados de la localidad "Vigo"
        GestorEmpresaHB.mostrarEmpregadosPorLocalidade("Vigo");

        // Probar localidad que no existe
        GestorEmpresaHB.mostrarEmpregadosPorLocalidade("Plasencia");
    }

}
