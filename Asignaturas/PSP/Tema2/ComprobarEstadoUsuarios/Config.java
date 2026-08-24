package PSP.Tema2.ComprobarEstadoUsuarios;

public class Config {
    static final int PUERTO = 500;
    static final String CMD_SALIR = "salir";
    static final String CMD_APAGAR = "apagar";

    enum AutorizarConexion {OK, CredencialesIncorrectas, UsuarioYaConectado}
}
