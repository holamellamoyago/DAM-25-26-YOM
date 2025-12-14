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

    public boolean estaOnline() {
        return online;
    }

    public void login() {
        online = true;
        conexiones++;
    }

    public void logOut() {
        online = false;
    }

    @Override
    public String toString() {
        return nombre + (online ? "conextado" : "No conectado") + online +  ", se conexto: " + conexiones + " veces";
    }

}
