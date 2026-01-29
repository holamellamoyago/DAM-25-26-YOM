import Agenda

agenda = Agenda.Agenda
contacto = Agenda.Contacto("Yago", "123456789", "y@y.com")
agenda.anhadirContacto(contacto=contacto)
print(agenda.mostrarContactos())