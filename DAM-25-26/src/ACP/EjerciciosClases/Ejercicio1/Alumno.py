class Alumno:
    def __init__(self, nombre, edad, nota):
        self.nombre = nombre
        self.edad = edad
        self.nota = nota

    def mostrar(self):
        print("Alumno",self.nombre, "tiene", self.edad, "años", "saco un", self.nota)
        if self.nota < 5:
            print("El alumno no aprobó")
        else:
            print("El alumno aprobo, olee! ")

alumno1 = Alumno("Yago", 22, 7)
alumno1.mostrar()
