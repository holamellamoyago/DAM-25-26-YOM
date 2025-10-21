package AD.Tema1.Actividad4.Actividad4.persistencia;

public class ExcepcionXML extends RuntimeException{

    public ExcepcionXML(String mensaje, Throwable causa ) {
        super(mensaje, causa);
    }

    public ExcepcionXML(String mensaje) {
        super(mensaje);
    }

    
}
