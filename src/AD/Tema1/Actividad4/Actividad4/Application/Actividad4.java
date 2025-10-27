package AD.Tema1.Actividad4.Actividad4.Application;

import java.time.LocalDate;
import java.util.ArrayList;

import AD.Tema1.Actividad4.Actividad4.logica.GestorCorredores;
import AD.Tema1.Actividad4.Actividad4.model.Corredor;
import AD.Tema1.Actividad4.Actividad4.model.Fondista;
import AD.Tema1.Actividad4.Actividad4.persistencia.TipoValidacion;

public class Actividad4 {
    public static void main(String[] args) {
        GestorCorredores gestor = new GestorCorredores();

        gestor.cargarDocumento("Archivos/Corredores.xml", TipoValidacion.DTD);
        gestor.leerCorredores();

        System.out.println(gestor.leerCorredorCodigo("C02"));
        System.out.println(gestor.leerCorredorDorsal(3));

        System.out.println();
        gestor.eliminarCorredor("19");

        Fondista corredor = new Fondista("C19", 19, "PSOE", "Pedro Sanchez", LocalDate.parse("2003-02-11"), 11);
        gestor.anhadirCorredor(corredor);
    }
}
