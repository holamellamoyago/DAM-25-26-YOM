package clases;

public class Habitacion {
    private int idHabitacion;
    private String nombre;

    public Habitacion(int idHabitacion, String nombre) {
        this.idHabitacion = idHabitacion;
        this.nombre = nombre;
    }

    public int getIdHabitacion() {
        return idHabitacion;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return "Habitacion [idHabitacion=" + idHabitacion + ", nombre=" + nombre + "]";
    }


    

    

    
}
