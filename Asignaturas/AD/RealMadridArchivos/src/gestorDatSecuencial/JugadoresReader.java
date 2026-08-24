package gestorDatSecuencial;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import clases.Archivo;
import clases.Jugador;

public class JugadoresReader extends Archivo {

    public JugadoresReader(String ruta) {
        super(ruta);
    }

    private ObjectInputStream archivo;

    public ArrayList<Jugador> leerJugadores() {
        ArrayList<Jugador> jugadores = new ArrayList<>();
        Jugador j;

        abrirarchivo();

        while ((j = leerJugador()) != null) {
            jugadores.add(j);
        }

        System.out.println(jugadores);
        cerararchivo();
        return jugadores;
    }

    private Jugador leerJugador() {
        try {
            return (Jugador) archivo.readObject();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void abrirarchivo() {

        if (!existe()) {
            throw new ArithmeticException("El archivo no existe");
        }

        try {
            archivo = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)));
        } catch (IOException e) {
            archivo = null;
            e.printStackTrace();
        }
    }

    @Override
    public void cerararchivo() {
        if (archivo != null) {
            try {
                archivo.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
