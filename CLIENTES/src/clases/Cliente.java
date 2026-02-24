package clases;

public class Cliente {
    private String nombre;
    // private boolean vip;
    private int provincia;
    private String nif;



    public Cliente(String nombre, int provincia, String nif) {
        this.nombre = nombre;
        this.provincia = provincia;
        this.nif = nif;
    }

    public String getNombre() {
        return nombre;
    }

 

    public int getProvincia() {
        return provincia;
    }

    @Override
    public String toString() {
        return "Cliente [nombre=" + nombre + ", nif=" + nif + ", provincia=" + provincia + "]";
    }

}
