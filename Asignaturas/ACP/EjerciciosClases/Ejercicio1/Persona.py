from operator import concat


class Persona:
    def __init__(self, nombre, edad):
        self.nombre = nombre
        self.edad = edad

    def mostrar(self):
        mensaje = "La persona", self.nombre, "tiene", self.edad, "años"
        if self.edad < 18:
            mensaje = mensaje, " es menor de edad"
        else:
            mensaje = mensaje, "Es mayor de edad"

        print(mensaje)



persona1 = Persona("Yago", 13)
persona1.mostrar()
