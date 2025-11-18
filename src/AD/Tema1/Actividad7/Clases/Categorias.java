package AD.Tema1.Actividad7.Clases;

import java.util.ArrayList;

import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
public class Categorias {

    @XmlList
    ArrayList<String> categorias;

    public Categorias() {
        categorias = new ArrayList<>();
    }

    public ArrayList<String> getCategorias() {
        return categorias;
    }

    public void setCategorias(ArrayList<String> categorias) {
        this.categorias = categorias;
    }

    

    
}
