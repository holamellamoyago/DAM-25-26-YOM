class Agenda:
    def __init__(self) :
        self.contactos = []

    def anhadirContacto(self, contacto):
        self.contactos += contacto

    def mostrarContactos(self):
        for i in self.contactos:
            print(self.contactos[i])


class Contacto:
    def __init__(self, nombre, telefono, email):
        self.nombre  =nombre
        self.telefono = telefono
        self.email = email

