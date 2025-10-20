package AD.Tema1.Actividad3.Actividad3Intento2.Aplicacion;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import AD.Tema1.Actividad3.Actividad3Intento2.Clases.*;
import AD.Tema1.Actividad3.Actividad3Intento2.Logica.*;

public class App {
    public static void main(String[] args) {
        Corredor corredor1 = new Velocista("Juan Pérez", LocalDate.of(2000, 5, 12), 1, 10.34f);
        Corredor corredor2 = new Fondista("Ana Gómez", LocalDate.of(1995, 3, 22), 2, 42.195f);
        Corredor corredor3 = new Velocista("Carlos Ruiz", LocalDate.of(2002, 11, 30), 3, 9.75f);
        Corredor corredor4 = new Fondista("María López", LocalDate.of(2000, 7, 15), 1, 21.097f);
        Corredor corredor5 = new Velocista("Pedro García", LocalDate.of(1995, 8, 5), 1, 11.20f);
        Corredor corredor6 = new Fondista("Laura Martínez", LocalDate.of(2002, 9, 10), 4, 35.00f);

        // A partir de aquí equipos
        Set<Patrocinador> lista1 = new HashSet<>();
        lista1.add(new Patrocinador("Coca-Cola", 15000.0f, LocalDate.of(2023, 1, 15)));
        lista1.add(new Patrocinador("Nike", 22000.0f, LocalDate.of(2024, 3, 10)));
        Equipo equipo1 = new Equipo("Atlético Norte", new TreeSet<>());

        Set<Patrocinador> lista2 = new HashSet<>();
        lista2.add(new Patrocinador("Adidas", 18000.0f, LocalDate.of(2022, 5, 1)));
        lista2.add(new Patrocinador("Coca-Cola", 16000.0f, LocalDate.of(2021, 4, 4)));
        lista2.add(new Patrocinador("Vodafone", 16500.0f, LocalDate.of(2021, 5, 5)));
        Equipo equipo2 = new Equipo("Deportivo Sur", lista2);

        Set<Patrocinador> lista3 = new HashSet<>();
        lista3.add(new Patrocinador("Renfe", 12000.0f, LocalDate.of(2023, 6, 25)));
        lista3.add(new Patrocinador("Mapfre", 9000.0f, LocalDate.of(2023, 9, 1)));
        Equipo equipo3 = new Equipo("Coruña Team", lista3);

        Set<Patrocinador> lista4 = new HashSet<>();
        lista4.add(new Patrocinador("Repsol", 25000.0f, LocalDate.of(2022, 4, 10)));
        Equipo equipo4 = new Equipo("Racing Galicia", lista4);

        Set<Patrocinador> lista5 = new HashSet<>();
        lista5.add(new Patrocinador("Mahou", 17000.0f, LocalDate.of(2023, 11, 5)));
        Equipo equipo5 = new Equipo("Union Atlética", lista5);

        // Corredores
        ArrayList<Corredor> corredores = new ArrayList<>(List.of(corredor1, corredor2, corredor3));

        GestorCorredores gestorCorredores = new GestorCorredores("Archivos/corredores2.dat");
        gestorCorredores.anhadirCorredor(corredor1);
        gestorCorredores.anhadirCorredor(corredor2);

        System.out.println();
        gestorCorredores.mostrarCorredores();
        System.out.println();

        // Equipos 
        GestorEquipos gestorEquipos = new GestorEquipos("Archivos/equipos.dat");
        gestorEquipos.guardarEquipo(equipo1);


    }
}
