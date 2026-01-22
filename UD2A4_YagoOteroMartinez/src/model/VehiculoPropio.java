package model;

import java.sql.Date;

public class VehiculoPropio extends Vehiculo {

    //private int idVehiculo;
    //NO HAY QUE DUPLICAR LA CLAVE!! se extiende normal
    private Date dataCompra;
    private double prezoPagado;

    public VehiculoPropio(int id, String matricula, String marca, String modelo, String combustible) {
        super(id, matricula, marca, modelo, combustible);
    }

    // CORRECCION: constructor completo con super
    public VehiculoPropio(int id, String matricula, String marca,
                          String modelo, String combustible,
                          Date dataCompra, double prezoPagado) {

        super(id, matricula, marca, modelo, combustible);
        this.dataCompra = dataCompra;
        this.prezoPagado = prezoPagado;
    }

    // CORRECCION: constructor sin id - antes de insertar en BD
    public VehiculoPropio(String matricula, String marca,
                          String modelo, String combustible,
                          Date dataCompra, double prezoPagado) {

        super(matricula, marca, modelo, combustible);
        this.dataCompra = dataCompra;
        this.prezoPagado = prezoPagado;
    }

    // constructor vacío
    public VehiculoPropio() {}

    public Date getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(Date dataCompra) {
        this.dataCompra = dataCompra;
    }

    public double getPrezoPagado() {
        return prezoPagado;
    }

    public void setPrezoPagado(double prezoPagado) {
        this.prezoPagado = prezoPagado;
    }
}
