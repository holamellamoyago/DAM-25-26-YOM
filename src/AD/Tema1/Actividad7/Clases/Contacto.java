package AD.Tema1.Actividad7.Clases;

import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlTransient;

@XmlSeeAlso({
    Telefonos.class,
    Email.class
})
@XmlTransient
public abstract class Contacto {
    
}
