package AD.Tema1.Actividad6.Clases;

import java.util.ArrayList;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElements;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "corredores")
@XmlAccessorType(XmlAccessType.FIELD) // ????????????
public class Corredores {
    
    @XmlElements({
            @XmlElement(name = "velocista", type = Velocista.class),
            @XmlElement(name = "fondista", type = Fondista.class)
    })

    private ArrayList<Corredor> corredores;

    public Corredores() {
        this.corredores = new ArrayList<>();
    }

    public Corredores(ArrayList<Corredor> corredores) {
        this.corredores = corredores;
    }

}
