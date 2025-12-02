package PSP.Tema1.Ejercicio5;

public class Oficina {
    private boolean estaJefe = false;
    private Persona[] personas;

    public Oficina() {
    }

    public void setPersonas(Persona[] personas) {
        this.personas = personas;
    }

    public void ficharEmpleados() {

        // RUTINA!! START Y JOIN OBLIGATORIOS

        for (Persona persona : personas) {
            persona.start();
        }

        for (Persona persona : personas) {
            try {
                persona.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized boolean estaJefe() {
        return estaJefe;
    }

    public synchronized void ficharEmpleado(Empleado e) {

        try {
            if (estaJefe()) {
                trabajarEmpleado(e);
                return;
            }

            while (!estaJefe()) {

                // Aquí es cuando me refiero que hay que
                // esperar al 'dueño de la clase contenedora'

                // TODO ¿Da igual el orden del wait con mis funciones?
                dormirEmpleado(e);
                wait();

            }

            despertarEmpleado(e);
            return;

        } catch (InterruptedException a) {
            a.printStackTrace();
        }

    }

    public synchronized void ficharJefe(Jefe j) {
        try {
            Thread.sleep(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("EL JEFE HA LLEGADO!  (dice el jefe gritando al llegar, por lo que despierta a todos)");
        estaJefe = true;

        // Aquí otra vez , despertamos a los hijos de la clase
        notifyAll();
    }

    private void dormirEmpleado(Empleado e) {
        System.out.println(e.getNombre() + " ha llegado. ZZZZZZ");
    }

    private void despertarEmpleado(Empleado e) {
        System.out.println(e.getNombre() + " desperazándose), buenos días jefe, aquí estoy trabajando");
    }

    private void trabajarEmpleado(Empleado e) {
        System.out.println(e.getNombre() + " ha llegado. Hola jefe!, me pongo a trabajar...");
    }

}
