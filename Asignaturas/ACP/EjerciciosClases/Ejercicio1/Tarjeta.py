class Tarjeta():
    def __init__(self, n,b):
        self.numero = n,
        self.banco = b

    def print(self):
        print("Numero de tarjeta " + str(self.numero))
        print("Banco: " + self.banco)


t1 = Tarjeta(1234, "Santandoder")
t1.print()