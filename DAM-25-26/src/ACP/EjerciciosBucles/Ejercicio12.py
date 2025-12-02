frase="hola me llamo yago"
letra = "o"
contador = 0

for i in range(len(frase)):
    if frase[i] == letra : 
        contador+=1
    pass

print("La letra",letra,"se repitió",contador,"veces")