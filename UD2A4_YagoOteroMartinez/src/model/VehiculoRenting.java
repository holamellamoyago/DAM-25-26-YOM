package model;

import java.sql.Date;

public class VehiculoRenting extends Vehiculo {

    //private int idVehiculo;
    //NO HAY QUE DUPLICAR LA CLAVE!! se extiende normal
    private Date dataInicio;
    private double prezoMensual;
    private int mesesContratados;

    public VehiculoRenting(int id, String matricula, String marca, String modelo, String combustible) {
        super(id, matricula, marca, modelo, combustible);
    }
    // CORRECCION: constructor completo con super
    public VehiculoRenting(int id, String matricula, String marca,
                           String modelo, String combustible,
                           Date dataInicio, double prezoMensual,
                           int mesesContratados) {

        super(id, matricula, marca, modelo, combustible);
        this.dataInicio = dataInicio;
        this.prezoMensual = prezoMensual;
        this.mesesContratados = mesesContratados;
    }

    // CORRECCION: constructor sin id
    public VehiculoRenting(String matricula, String marca,
                           String modelo, String combustible,
                           Date dataInicio, double prezoMensual,
                           int mesesContratados) {

        super(matricula, marca, modelo, combustible);
        this.dataInicio = dataInicio;
        this.prezoMensual = prezoMensual;
        this.mesesContratados = mesesContratados;
    }

    // constructor vacío
    public VehiculoRenting() {}

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public double getPrezoMensual() {
        return prezoMensual;
    }

    public void setPrezoMensual(double prezoMensual) {
        this.prezoMensual = prezoMensual;
    }

    public int getMesesContratados() {
        return mesesContratados;
    }

    public void setMesesContratados(int mesesContratados) {
        this.mesesContratados = mesesContratados;
    }
}
