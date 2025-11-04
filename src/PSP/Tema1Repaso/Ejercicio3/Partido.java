package PSP.Tema1Repaso.Ejercicio3;

public class Partido {
    int numVotos = 0;

    public synchronized void sumarVoto() {
        numVotos++;
    }

    @Override
    public String toString() {
        return "Partido [numVotos=" + numVotos + "]";
    }


    
}
