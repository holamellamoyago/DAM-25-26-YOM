package clases;

public class Cliente {
    private int codCliente;
    private String nombre;
    private int provincia;
    private String nif;

    public Cliente(int codCliente, String nombre, int provincia, String nif) {
        this.codCliente = codCliente;
        this.nombre = nombre;
        this.provincia = provincia;
        this.nif = nif;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCodCliente() {
        return codCliente;
    }

    public String getNif() {
        return nif;
    }

    public int getProvincia() {
        return provincia;
    }

    @Override
    public String toString() {
        return "Cliente [codCliente=" + codCliente + ", nombre=" + nombre + ", provincia=" + provincia + ", nif=" + nif
                + "]";
    }

    public String toStringSimple() {
        return "Cliente: " + codCliente + " | " + nombre;
    }

}
