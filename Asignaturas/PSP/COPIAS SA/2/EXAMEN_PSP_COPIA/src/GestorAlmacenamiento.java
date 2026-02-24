
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import Clases.Pregunta;

public class GestorAlmacenamiento {
    private static GestorAlmacenamiento gestor;
    private ArrayList<Pregunta> preguntas = new ArrayList<>();
    private HashMap<String, Integer> records = new HashMap<>();

    private GestorAlmacenamiento() {
    }

    public ArrayList<Pregunta> getPreguntas() {
        return preguntas;
    }

    

    public HashMap<String, Integer> getRecords() {
        return records;
    }

    public static synchronized GestorAlmacenamiento getInstance() {
        if (gestor == null)
            gestor = new GestorAlmacenamiento();

        return gestor;
    }

    public void registrarRecord(String usuario, int numAciertos) {
        if (records.containsKey(usuario)) {
            if (numAciertos > records.get(usuario)) {
                records.put(usuario, numAciertos);
            }
        } else {
            records.put(usuario, numAciertos);
        }

    }

}
