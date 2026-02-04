import Agenda
import Contacto

agenda = Agenda.Agenda()
contacto = Contacto.Contacto("Yago", "123456789", "y@y.com")
agenda.anhadirContacto(contacto)
print(agenda.mostrarContactos())