package PSP.Tema2.ComprobarEstadoUsuarios;

public class Usuario {
    private String nombre;
    private int conexiones;
    private boolean online;

    public Usuario(String nombre) {
        this.nombre = nombre;
        this.conexiones = 0;
        this.online = false;
    }

    
}
