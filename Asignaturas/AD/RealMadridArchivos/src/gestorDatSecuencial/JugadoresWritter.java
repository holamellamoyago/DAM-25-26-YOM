package gestorDatSecuencial;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import clases.Archivo;
import clases.Jugador;

public class JugadoresWritter extends Archivo {
    private ObjectOutputStream archivo;

    public JugadoresWritter(String ruta) {
        super(ruta);
    }

    public void escribirJugadores(ArrayList<Jugador> jugadores) throws IOException {
        abrirarchivo();
        for (int i = 0; i < jugadores.size(); i++) {
            Jugador j = jugadores.get(i);

            archivo.writeObject(j);
        }

        cerararchivo();
    }

    @Override
    public void cerararchivo() {
        try {
            archivo.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void abrirarchivo() {
        boolean existe = existe() && file.length() > 0;
        try {
            if (existe) {
                archivo = new AppendObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file, existe)));
            } else {
                archivo = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file, existe)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
