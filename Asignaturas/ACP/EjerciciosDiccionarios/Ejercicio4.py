fecha="11/02/25"
fechas = fecha.split("/")

meses={1:"enero", 2: "febrero", 3: "marzo", 4: "abril", 5: "mayo"}

mes = meses[int(fechas[1])]
print(fechas[0],"de", mes)