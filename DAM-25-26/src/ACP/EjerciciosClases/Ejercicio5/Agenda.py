class Agenda:
    def __init__(self) :
        self.contactos = []

    def anhadirContacto(self, contacto):
        self.contactos.append(contacto)

    def mostrarContactos(self):
        for i in self.contactos:
            print(i)


