package PSP.Tema3.STOCK;

public class Config {
    static final int PUERTO = 5000;

    static final String CMD_REGEXP = "([a-zñ]+)\\s+([a-zñ]+)\\s+(\\d+)";

    static final String CMD_GET = "GET";
    static final String CMD_PUT = "PUT";
    static final String CMD_INFO = "INFO";
    static final String CMD_SALIR = "SALIR";
    static final String CMD_APAGAR = "APAGAR";

    static final String STR_NO_CONEXION = "Imposible realizar conexión";
    static final String STR_CONEXIONES_ACTIVAS = "Imposible apagar. Hay conexiones activas";
    static final String STR_MATERIAL_DESCONOCIDO = "Material desconocido";
    static final String STR_SIN_STOCK = "No hay stock suficiente";
    static final String STR_ERROR_CANTIDAD = "Cantidad no válida";

    static final String STR_FORMATO_COMANDOS = String.format(
            "Sintaxis: [%s|%s|%s|%s] [material cantidad] (* mejorar)",
            CMD_GET, CMD_PUT, CMD_INFO, CMD_SALIR);
}
