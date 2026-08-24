import Coche

class Deportivo(coche.Coche):
    def __init__(self, coche, marca, modelo, cavallos, aleron):
        super().__init__(coche, marca, modelo, cavallos)
        self.aleron = aleron

class Sub(coche.Coche):
    def __init__(self, coche, marca, modelo, cavallos, espacioMaletero):
        super().__init__(coche, marca, modelo, cavallos)
        self.espacioMaletero = espacioMaletero

