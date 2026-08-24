import java.util.ArrayList;

import clases.Jugador;
import clases.TipoValidacion;
import gestorDatSecuencial.JugadoresReader;
import gestorDatSecuencial.JugadoresWritter;
import gestorSTAX.GestorJugadoresCursorSTAX;
import gestorSTAX.GestorJugadoresEventosSTAX;

public class App {
    public static void main(String[] args) throws Exception {
        String rutaArchivo = "archivos/jugadores.xml";
        TipoValidacion tipoValidacion = TipoValidacion.NO_VALIDAR;

        GestorJugadoresCursorSTAX gestorJugadoresSTAX = new GestorJugadoresCursorSTAX(rutaArchivo, tipoValidacion);
        // gestorJugadoresSTAX.leerJugadores();

        GestorJugadoresEventosSTAX gestorJugadoresEventosSTAX = new GestorJugadoresEventosSTAX(rutaArchivo,
                tipoValidacion);
        ArrayList<Jugador> jugadores = gestorJugadoresEventosSTAX.leerJugadores();

        GestorJugadoresEventosSTAX gestorJugadoresEventosSTAX2 = new GestorJugadoresEventosSTAX("jugadores.xml",
                TipoValidacion.NO_VALIDAR);
        gestorJugadoresEventosSTAX2.escribirJugadores(jugadores);

        System.out.println("Secuencia: ");
        JugadoresWritter jugadoresWritter = new JugadoresWritter("jugadoresSecuencial.dat");
        jugadoresWritter.escribirJugadores(jugadores);

        JugadoresReader reader = new JugadoresReader("jugadoresSecuencial.dat");
        reader.leerJugadores();
    }
}
