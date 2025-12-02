package PSP.Tema1.Carreras;

public class Atleta extends Thread{
    String nombre;

    public Atleta(int i) {
        this.nombre = "Atleta" + String.valueOf(i);
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        super.run();
    }

    
}
