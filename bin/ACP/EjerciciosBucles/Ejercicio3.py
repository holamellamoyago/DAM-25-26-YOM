from operator import concat


numero = 20
numeros = str("Desde aqui: ")

for i in range(numero):
    if (i%2) != 0:
        numeros += (str(i) + ", ")
        pass
    else: 
        print("El número", i, "es par")
    pass

print(numeros)