package AD.Tema1Resumen.DOM.Clases;

public class ExcepcionXML extends RuntimeException{

    public ExcepcionXML(String mensaje, Throwable causa ) {
        super(mensaje, causa);
    }

    public ExcepcionXML(String mensaje) {
        super(mensaje);
    }

    
}
