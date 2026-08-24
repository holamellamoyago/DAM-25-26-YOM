package PSP.Tema1.concesionario;

public class Coche {
    private String modelo;
    public int numeroVisitas;
    public Cliente cliente;

    public Coche(String modelo) {
        this.modelo = modelo;
        numeroVisitas = 0;
    }

    public Cliente isOcupado() {
        return cliente;
    }

    public void sumarVisita(){
        numeroVisitas++;
    }

    @Override
    public String toString() {
        return modelo + "[" + numeroVisitas + "]";
    }


}
