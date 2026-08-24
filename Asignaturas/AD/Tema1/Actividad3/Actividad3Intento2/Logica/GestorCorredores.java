package AD.Tema1.Actividad3.Actividad3Intento2.Logica;

import AD.Tema1.Actividad3.Actividad3Intento2.Clases.*;
import AD.Tema1.Actividad3.Actividad3Intento2.Persistencia.CorredorWritter;
import AD.Tema1.Actividad3.Actividad3Intento2.Persistencia.CorredoresReader;


public class GestorCorredores {
    String ruta;

    public GestorCorredores(String ruta) {
        this.ruta = ruta;
    }

    public void anhadirCorredor(Corredor c) {
        CorredorWritter writter = new CorredorWritter(ruta);
        CorredoresReader reader = new CorredoresReader(ruta);
        writter.abrirarchivo();
        reader.abrirarchivo();

        if (!reader.listarcorredores().contains(c)) {
            writter.escribir(c);
        } else{
            System.out.println("El jugador ya esta en la lista");
        }


        writter.cerararchivo();
        reader.cerararchivo();
    }

    public void mostrarCorredores() {
        CorredoresReader reader = new CorredoresReader(ruta);
        reader.abrirarchivo();

        System.out.println(reader.listarcorredores());

        reader.cerararchivo();
    }

}
