dinero = int(input("Dinero mensual: "))
edad = int(input("Edad: "))

if edad >= 16 and dinero >= 1000:
    print("Debe tributar")
else:
    print("No debe tributar")