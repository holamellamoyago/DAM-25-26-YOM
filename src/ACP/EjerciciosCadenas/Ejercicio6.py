frase = input("Introduce una frase: ")
vocal = input("Que vocal quieres convertir? ")

for letra in frase:
    if letra == vocal:
        print("".join(str(letra.upper)))
    else:
        print(letra)


