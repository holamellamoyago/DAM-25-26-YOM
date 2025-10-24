nombre = input("Dime el nombre del usuario: ")
apellido1 = input("Dime el primer apellido: ")
apellido2 = input("Dime el segundo apellido: ")

print(nombre.lower() + " " + apellido1.lower() + " " + apellido2.lower())
print(nombre.upper() + " " + apellido1.upper() + " " + apellido2.upper())

print("Capitalize: ")
print(nombre.capitalize() + " " + apellido1.capitalize() + " " + apellido2.capitalize())

print("Otra forma de capitalizar: ")
print(nombre[0].upper() + nombre[1:len(nombre)].lower())
