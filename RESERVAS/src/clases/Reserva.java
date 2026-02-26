package clases;

public class Reserva {
    private int codReserva, dia, numDias, idHabitacion;
    private String nombre;

    public Reserva(int codReserva, int dia, int numDias, int idHabitacion, String nombre) {
        this.codReserva = codReserva;
        this.dia = dia;
        this.numDias = numDias;
        this.idHabitacion = idHabitacion;
        this.nombre = nombre;
    }

    public int getCodReserva() {
        return codReserva;
    }

    public int getDia() {
        return dia;
    }

    public int getNumDias() {
        return numDias;
    }

    public int getIdHabitacion() {
        return idHabitacion;
    }

    public String getNombre() {
        return nombre;
    }

    

    
}
