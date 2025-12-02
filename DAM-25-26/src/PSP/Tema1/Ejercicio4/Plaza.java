package PSP.Tema1.Ejercicio4;

public class Plaza {
    private int numeroPlaza;
    private boolean disponible;

    public Plaza(int numeroPlaza) {
        this.numeroPlaza = numeroPlaza;
        disponible = true;
    }

    @Override
    public String toString() {
        return "Plaza " + numeroPlaza + ", " + disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public int getNumeroPlaza() {
        return numeroPlaza;
    }

    public void setNumeroPlaza(int numeroPlaza) {
        this.numeroPlaza = numeroPlaza;
    }

    public boolean isDisponible() {
        return disponible;
    }

    

}
