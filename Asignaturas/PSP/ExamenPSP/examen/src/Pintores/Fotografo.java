package Pintores;
public class Fotografo extends Thread {
    Casa casa;
    int colorFavorito;
    int totalFotosSacadas = 0;

    public Fotografo (Casa casa) {
        this.casa = casa;
        colorFavorito = 1;
    }

    @Override
    public void run() {
        casa.sacarFotosCasa();


        
    }
}
