package Clases;


public class Usuario {
    private String usuario;
    private StringBuilder strBuilder;

    public Usuario(String usuario) {
        this.usuario = usuario;
        strBuilder = new StringBuilder();
    }

    public String getUsuario() {
        return usuario;
    }

    public StringBuilder getStrBuilder() {
        return strBuilder;
    }

    
}
