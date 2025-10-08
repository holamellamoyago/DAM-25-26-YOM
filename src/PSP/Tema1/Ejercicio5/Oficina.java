package PSP.Tema1.Ejercicio5;

public class Oficina {
    static boolean estaJefe = false;

    public static synchronized void ficharEmpleados(Empleado empleado) throws InterruptedException{
        if (estaJefe) {
           System.out.println(empleado.getNombre() + "hola jefe!, me pongo a trabajar ... "); 
        } else { 
            System.out.println(empleado.getNombre() + "ha llegado. ZZZZ");
            empleado.wait();
        }
    }

    public static synchronized boolean ficharJefe(Jefe jefe) {
        if (estaJefe) {
            return false;
        }

        jefe.notifyAll();
        


        return true;
    }
}
