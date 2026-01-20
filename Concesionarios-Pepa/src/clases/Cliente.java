package clases;

import java.sql.Date;

public class Cliente {
    private int id;
    private String nombre, ciudad;
    private Date fecha;

    public Cliente(int id, String nombre, String ciudad, Date fecha) {
        this.id = id;
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.fecha = fecha;
    }

    public Cliente() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Cliente " + nombre;
    }

    
}
