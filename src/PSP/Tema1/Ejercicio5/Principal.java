package PSP.Tema1.Ejercicio5;

public abstract class Principal extends ThreadGroup {
    public Principal(String name) {
        super(name);
        //TODO Auto-generated constructor stub
    }

    public void customJoin(Thread c){
        try {
            c.join();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
