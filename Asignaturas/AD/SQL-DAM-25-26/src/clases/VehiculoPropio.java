package clases;

import java.time.LocalDate;

public class VehiculoPropio extends Vehiculo {
    private LocalDate fechaCompra;
    private float precio;

    
    
    public VehiculoPropio() {
    }
    
    public VehiculoPropio(LocalDate fechaCompra, float precio) {
        this.fechaCompra = fechaCompra;
        this.precio = precio;
    }
    public LocalDate getFechaCompra() {
        return fechaCompra;
    }
    public void setFechaCompra(LocalDate fechaCompra) {
        this.fechaCompra = fechaCompra;
    }
    public float getPrecio() {
        return precio;
    }
    public void setPrecio(float precio) {
        this.precio = precio;
    }

    
}
