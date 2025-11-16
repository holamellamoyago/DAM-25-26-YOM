package PSP.Tema1.Subasta;

public class Obra {
    String nombre;
    int precio;
    int nVisitas;
    boolean adjudicada;
    Pujador comprador;

    public Obra(int nObra, int precio) {
        this.nombre = "Obra ".concat(String.valueOf(nObra));
        this.precio = precio;
        this.nVisitas = 0;
        adjudicada = false;
    }

    @Override
    public String toString() {
        return nombre + " " + precio + "€";
    }

    public void sumarVisita(){
        nVisitas++;
    }

    



    
}
