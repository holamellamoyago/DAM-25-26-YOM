package AD.Tema1.Actividad4.Actividad4.Application;

import AD.Tema1.Actividad4.Actividad4.logica.GestorCorredores;
import AD.Tema1.Actividad4.Actividad4.persistencia.TipoValidacion;

public class Actividad4 {
    public static void main(String[] args) {
        GestorCorredores gestor = new GestorCorredores();

        gestor.cargarDocumento("Archivos/Corredores.xml", TipoValidacion.NO_VALIDAR);

        gestor.leerCorredores();

        System.out.println(gestor.leerCorredor("C02"));
    }
}
