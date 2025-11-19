asignaturas = ["matematicas", "lengua castellana"]
asignaturaNota = []

for i in range(len(asignaturas)):
    nota = input("Dime la nota de la asigntura "+ asignaturas[i] + ": ")
    asignaturaNota.append([asignaturas[i], nota])
    pass

for i in range(len(asignaturaNota)):
    print(asignaturaNota[i])
    pass


asigntura2 = [["matematica" ,2]]

for j in range(len(asigntura2)):
    print(asigntura2[j][0])
    pass
