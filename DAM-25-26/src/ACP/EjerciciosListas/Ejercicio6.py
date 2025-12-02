asignaturas = ["matematicas", "lengua castellana"]

for i in range(len(asignaturas)):
    asignatura = asignaturas[i]
    nota = str(input(("Dime la nota de: "+ str(asignatura) + " ")))
    asignaturas.remove(asignatura)
    asignaturas.append([asignatura, nota])
    pass

for i in range(asignaturas): 
    asigntura = asignaturas[i][0]
    nota = asignaturas[i][1]
    if nota < 5:
        print("Debe repetir la asigntura" , asigntura)
        pass
