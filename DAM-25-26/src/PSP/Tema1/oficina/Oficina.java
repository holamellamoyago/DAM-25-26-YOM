package PSP.Tema1.oficina;

public class Oficina {

    private boolean estaJefe;

    public Oficina() {
        estaJefe = false;
    }

    public synchronized void llegarOficina(Empleado empleado) throws InterruptedException {

        if (estaJefe) {
            System.out.println(empleado.nombre + " ha llegado. Hola jefe!, me pongo a trabajar...");
            return;
        }

        while (!estaJefe) {
            System.out.println(empleado.nombre + " ha llegado. ZZZZ");
            wait();
        }

        System.out.println(empleado.nombre + "  desperazándose), Ehhh, A TRABAJAR!");

    }

    public synchronized void llegaJefe() {
        System.out.println("EL JEFE HA LLEGADO! (dice el jefe gritando al llegar, por lo que despierta a todos)");
        notifyAll();
        estaJefe = true;
    }
}