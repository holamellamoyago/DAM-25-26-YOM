package AD.Tema1.Actividad3.Actividad3Intento2.Logica;

import AD.Tema1.Actividad3.Actividad3Intento2.Clases.*;
import AD.Tema1.Actividad3.Actividad3Intento2.Persistencia.CorredorWritter;
import AD.Tema1.Actividad3.Actividad3Intento2.*;

public class GestorCorredores {
    String ruta ;

    public GestorCorredores(String ruta) {
        this.ruta = ruta;
    } 

    public void anhadirCorredor(Corredor c) { 
        CorredorWritter writter = new CorredorWritter(ruta);
        writter.abrirarchivo();

        writter.escribir(c);

        writter.cerararchivo();
    }
}
