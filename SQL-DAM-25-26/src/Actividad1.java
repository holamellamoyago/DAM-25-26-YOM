import clases.Departamento;
import clases.TipoSGBD;
import gestores.GestorEmpresa;

public class Actividad1 {

    // Exercicio 1: Creación da estrutura dunha base de datos en diferentes SXBD
    // Exercicio 2. Establecer conexións co diferentes SXBD utilizando o IDE
    // Exercicio 4. Creación de la clase del modelo
    // Exercicio 5. Establecemento de conexións desde Java
    public static void main(String[] args) {
        String baseDatos = "BDEMPRESA25";
        String usuario;
        String contrasena = "abc123.";
        TipoSGBD tipo = TipoSGBD.SQLSERVER;

        usuario = switch (tipo) {
            case MYSQL -> "root";
            case SQLSERVER -> "sa";
            case SQLITE -> "";
            default -> throw new AssertionError();
        };

        GestorEmpresa gestorEmpresa = new GestorEmpresa(tipo, baseDatos, usuario, contrasena);

        /*
         * Exercicio 6. Realización de operacións sinxelas
            * - Mostra por pantalla os datos de cada departamento. 
            * - Insere un novo proxecto.
         * Comproba que o nome é único
         */

         gestorEmpresa.obtenerDepartamentos();

         Departamento depa = new Departamento("Programación", "0010010");
         gestorEmpresa.anadirNuevoDepartamento(depa);

         /* 
          * Exercicio 7. Execución de sentenzas de descrición de datos (DDL) dende a linguaxe Java
          */

          gestorEmpresa.añadirTablaFamiliares();
          gestorEmpresa.añadirTablaVehiculos(tipo);


    }
}
