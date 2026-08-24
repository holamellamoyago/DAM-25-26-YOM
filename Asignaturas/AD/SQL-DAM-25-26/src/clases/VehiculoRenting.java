package clases;

import java.time.LocalDate;

public class VehiculoRenting extends Vehiculo {
    private LocalDate fechaInicio;
    private float precioMensual;
    private int meses;
    public VehiculoRenting() {
    }
    public LocalDate getFechaInicio() {
        return fechaInicio;
    }
    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
    public float getPrecioMensual() {
        return precioMensual;
    }
    public void setPrecioMensual(float precioMensual) {
        this.precioMensual = precioMensual;
    }
    public int getMeses() {
        return meses;
    }
    public void setMeses(int meses) {
        this.meses = meses;
    }

    
    
}
