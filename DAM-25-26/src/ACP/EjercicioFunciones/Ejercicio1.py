def datos_personales(nombre, edad, ciudad = "Pontevedra"):
    print("Me llamo:", nombre)
    print("Tengo:", edad, "años")
    print("Y vivo en:", ciudad)

def calculadora(n1, n2):
    suma=n1+n2
    resta=n1-n2
    multiplicacion=n1*n2
    division=n1/n2
    return ([suma,resta,multiplicacion,division])


def saludar():
    print("¡Hola amiga!")
    pass

saludar()

datos_personales("Yago", "22")

## Calculadora ##
resultado = calculadora(2,2)[2]
print(resultado)

