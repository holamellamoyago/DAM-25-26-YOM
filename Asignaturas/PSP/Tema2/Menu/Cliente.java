package PSP.Tema2.Menu;

public class Cliente {
    private int codCliente, codProvincia;
    private String nombre;
    private boolean vip;

    public Cliente(int codCliente, int codProvincia, String nombre, boolean vip) {
        this.codCliente = codCliente;
        this.codProvincia = codProvincia;
        this.nombre = nombre;
        this.vip = vip;
    }

    public int getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(int codCliente) {
        this.codCliente = codCliente;
    }

    public int getCodProvincia() {
        return codProvincia;
    }

    public void setCodProvincia(int codProvincia) {
        this.codProvincia = codProvincia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isVip() {
        return vip;
    }

    public void setVip(boolean vip) {
        this.vip = vip;
    }
   
}
