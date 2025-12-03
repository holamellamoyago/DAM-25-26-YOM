import pygame
import random
import os
from settings import *


def _cargar_imagen(ruta_relativa: str, tamaño: tuple[int, int] | None = None) -> pygame.Surface | None:
    """Intenta cargar una imagen desde la ruta relativa al directorio de este archivo.
    Devuelve una Surface (escalada si se indica tamaño) o None si falla.
    """
    try:
        base_dir = os.path.dirname(__file__)
        ruta = os.path.join(base_dir, ruta_relativa)
        imagen = pygame.image.load(ruta).convert_alpha()
        if tamaño:
            imagen = pygame.transform.smoothscale(imagen, tamaño)
        return imagen
    except Exception:
        return None


class Jugador(pygame.sprite.Sprite):
    def __init__(self, controles=None, pos_x=None, skin_nave=None):
        super().__init__()
        self.tamaño_base = (50, 50)
        self.tamaño_actual = self.tamaño_base

        # Skin de nave (si no se proporciona, usar la clásica)
        self.skin_nave = skin_nave or {"imagen_ruta": RUTA_IMAGEN_JUGADOR, "color_fallback": COLOR_NAVE}

        # Intentar cargar la imagen de la skin; si falla, usar el color fallback
        imagen = _cargar_imagen(self.skin_nave.get("imagen_ruta"), self.tamaño_actual)
        if imagen is None:
            imagen = pygame.Surface(self.tamaño_actual, pygame.SRCALPHA)
            # Parsear color_fallback (formato "R,G,B")
            try:
                r, g, b = map(int, self.skin_nave.get("color_fallback", "0,128,255").split(","))
                imagen.fill((r, g, b))
            except:
                imagen.fill(COLOR_NAVE)
        self.image = imagen
        self.imagen_original = imagen.copy()
        self.rect = self.image.get_rect()

        # Posición inicial: centro abajo o personalizada
        if pos_x is None:
            self.rect.centerx = ANCHO // 2
        else:
            self.rect.centerx = pos_x
        self.rect.bottom = ALTO - 10
        self.velocidad_x = 0

        # Controles (para multijugador)
        if controles is None:
            self.controles = CONTROLES_JUGADOR_1
        else:
            self.controles = controles

        # Power-ups activos
        self.tiene_escudo = False
        self.vidas_extra = 0
        self.tamaño_reducido = False
        self.puntos_dobles = False

    def update(self):
        """Lógica de movimiento del jugador"""
        self.velocidad_x = 0
        teclas = pygame.key.get_pressed()

        if teclas[self.controles["izq"]]:
            self.velocidad_x = -VELOCIDAD_JUGADOR
        if teclas[self.controles["der"]]:
            self.velocidad_x = VELOCIDAD_JUGADOR

        self.rect.x += self.velocidad_x

        # Mantener dentro de la pantalla
        if self.rect.right > ANCHO:
            self.rect.right = ANCHO
        if self.rect.left < 0:
            self.rect.left = 0

    def aplicar_tamaño_reducido(self):
        """Reduce el tamaño del jugador temporalmente"""
        self.tamaño_actual = (30, 30)
        self.tamaño_reducido = True
        self._actualizar_imagen()

    def restaurar_tamaño(self):
        """Restaura el tamaño normal del jugador"""
        self.tamaño_actual = self.tamaño_base
        self.tamaño_reducido = False
        self._actualizar_imagen()

    def _actualizar_imagen(self):
        """Reescala la imagen al tamaño actual usando la skin actual"""
        centro_anterior = self.rect.center
        imagen = _cargar_imagen(self.skin_nave.get("imagen_ruta"), self.tamaño_actual)
        if imagen is None:
            imagen = pygame.Surface(self.tamaño_actual, pygame.SRCALPHA)
            # Parsear color_fallback
            try:
                r, g, b = map(int, self.skin_nave.get("color_fallback", "0,128,255").split(","))
                imagen.fill((r, g, b))
            except Exception:
                imagen.fill(COLOR_NAVE)
        self.image = imagen
        self.rect = self.image.get_rect()
        self.rect.center = centro_anterior


class Meteorito(pygame.sprite.Sprite):
    def __init__(self, velocidad_base: int = VELOCIDAD_INICIAL_ENEMIGO, skin_meteorito=None):
        super().__init__()
        tamaño = (40, 40)

        # Skin de meteorito (si no se proporciona, usar la clásica)
        self.skin_meteorito = skin_meteorito or {"imagen_ruta": RUTA_IMAGEN_METEORITO, "color_fallback": COLOR_ENEMIGO}

        # Intentar cargar la imagen de la skin; si falla, usar el color fallback
        imagen = _cargar_imagen(self.skin_meteorito.get("imagen_ruta"), tamaño)
        if imagen is None:
            imagen = pygame.Surface(tamaño, pygame.SRCALPHA)
            # Parsear color_fallback
            try:
                r, g, b = map(int, self.skin_meteorito.get("color_fallback", "255,50,50").split(","))
                imagen.fill((r, g, b))
            except Exception:
                imagen.fill(COLOR_ENEMIGO)
        self.image = imagen
        self.rect = self.image.get_rect()

        # Aparece en una posición X aleatoria
        self.rect.x = random.randrange(0, ANCHO - self.rect.width)
        # Aparece justo arriba de la pantalla
        self.rect.y = random.randrange(-100, -40)
        # La velocidad base puede variar con la dificultad
        self.velocidad_y = random.randrange(velocidad_base, velocidad_base + 5)
        self.velocidad_base = self.velocidad_y

    def update(self):
        self.rect.y += self.velocidad_y
        # Si sale por abajo, lo eliminamos para liberar memoria (Clean Code)
        if self.rect.top > ALTO + 10:
            self.kill()

    def aplicar_slowmotion(self):
        """Reduce la velocidad a la mitad"""
        self.velocidad_y = max(1, self.velocidad_base // 2)

    def restaurar_velocidad(self):
        """Restaura la velocidad normal"""
        self.velocidad_y = self.velocidad_base


class PowerUp(pygame.sprite.Sprite):
    def __init__(self, tipo=None):
        super().__init__()
        # Tipo aleatorio si no se especifica
        if tipo is None:
            self.tipo = random.choice(TIPOS_POWERUP)
        else:
            self.tipo = tipo

        # Crear imagen del power-up (estrella)
        tamaño = (30, 30)
        self.image = pygame.Surface(tamaño, pygame.SRCALPHA)
        color = COLORES_POWERUP[self.tipo]

        # Dibujar una estrella simple
        centro = (tamaño[0] // 2, tamaño[1] // 2)
        puntos = []
        for i in range(10):
            angulo = i * 36
            radio = 15 if i % 2 == 0 else 7
            x = centro[0] + radio * pygame.math.Vector2(1, 0).rotate(angulo).x
            y = centro[1] + radio * pygame.math.Vector2(1, 0).rotate(angulo).y
            puntos.append((x, y))
        pygame.draw.polygon(self.image, color, puntos)

        self.rect = self.image.get_rect()

        # Posición aleatoria
        self.rect.x = random.randrange(0, ANCHO - self.rect.width)
        self.rect.y = random.randrange(-100, -40)

        # Velocidad de caída (más lenta que meteoritos)
        self.velocidad_y = 3

    def update(self):
        self.rect.y += self.velocidad_y
        # Si sale por abajo, lo eliminamos
        if self.rect.top > ALTO + 10:
            self.kill()


class Particula(pygame.sprite.Sprite):
    """Efecto de partículas para explosiones y estela"""
    def __init__(self, x, y, color, velocidad_x=0, velocidad_y=0):
        super().__init__()
        tamaño = random.randint(2, 5)
        self.image = pygame.Surface((tamaño, tamaño), pygame.SRCALPHA)
        self.image.fill(color)
        self.rect = self.image.get_rect()
        self.rect.center = (x, y)

        self.velocidad_x = velocidad_x + random.uniform(-2, 2)
        self.velocidad_y = velocidad_y + random.uniform(-2, 2)
        self.vida = random.randint(10, 30)
        self.alpha = 255

    def update(self):
        self.rect.x += self.velocidad_x
        self.rect.y += self.velocidad_y
        self.vida -= 1
        self.alpha = max(0, int(255 * (self.vida / 30)))
        self.image.set_alpha(self.alpha)

        if self.vida <= 0:
            self.kill()
