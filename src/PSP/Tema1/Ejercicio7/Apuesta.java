package PSP.Tema1.Ejercicio7;

public class Apuesta {
    final int MAX_GGOLES = 4;
    int golesDM2;
    int golesPRF;

    int cantidad;
    int precio;

    public Apuesta(int golesDM2, int golesPRF, int cantidad, int precio) {
        this.golesDM2 = golesDM2;
        this.golesPRF = golesPRF;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public int getGolesDM2() {
        return golesDM2;
    }

    public void setGolesDM2(int golesDM2) {
        this.golesDM2 = golesDM2;
    }

    public int getGolesPRF() {
        return golesPRF;
    }

    public void setGolesPRF(int golesPRF) {
        this.golesPRF = golesPRF;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

}
