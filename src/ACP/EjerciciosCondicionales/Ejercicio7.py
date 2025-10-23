renta=60001

if renta < 10000:
    print("Tipo impositivo: 5%")
elif renta<20001:
    print("Tipo impositivo: 15%")
elif renta<35001:
    print("Tipo impositivo: 20%")
elif renta < 60001:
    print("Tipo impositivo: 30%")
else:
    print("Tipo impositivo: 45%")
