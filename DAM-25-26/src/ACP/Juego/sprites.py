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
    def __init__(self):
        super().__init__()
        # Intentar cargar el logo de Hacienda; si falla, usar un cuadrado azul
        tamaño = (50, 50)
        imagen = _cargar_imagen(RUTA_IMAGEN_JUGADOR, tamaño)
        if imagen is None:
            imagen = pygame.Surface(tamaño, pygame.SRCALPHA)
            imagen.fill(COLOR_NAVE)
        self.image = imagen
        self.rect = self.image.get_rect()
        # Posición inicial: centro abajo
        self.rect.centerx = ANCHO // 2
        self.rect.bottom = ALTO - 10
        self.velocidad_x = 0

    def update(self):
        """Lógica de movimiento del jugador"""
        self.velocidad_x = 0
        teclas = pygame.key.get_pressed()

        if teclas[pygame.K_LEFT]:
            self.velocidad_x = -VELOCIDAD_JUGADOR
        if teclas[pygame.K_RIGHT]:
            self.velocidad_x = VELOCIDAD_JUGADOR

        self.rect.x += self.velocidad_x

        # Mantener dentro de la pantalla
        if self.rect.right > ANCHO:
            self.rect.right = ANCHO
        if self.rect.left < 0:
            self.rect.left = 0


class Meteorito(pygame.sprite.Sprite):
    def __init__(self, velocidad_base: int = VELOCIDAD_INICIAL_ENEMIGO):
        super().__init__()
        # Intentar cargar la cara de Pedro Sánchez; si falla, usar un cuadrado rojo
        tamaño = (40, 40)
        imagen = _cargar_imagen(RUTA_IMAGEN_METEORITO, tamaño)
        if imagen is None:
            imagen = pygame.Surface(tamaño, pygame.SRCALPHA)
            imagen.fill(COLOR_ENEMIGO)
        self.image = imagen
        self.rect = self.image.get_rect()

        # Aparece en una posición X aleatoria
        self.rect.x = random.randrange(0, ANCHO - self.rect.width)
        # Aparece justo arriba de la pantalla
        self.rect.y = random.randrange(-100, -40)
        # La velocidad base puede variar con la dificultad
        self.velocidad_y = random.randrange(velocidad_base, velocidad_base + 5)

    def update(self):
        self.rect.y += self.velocidad_y
        # Si sale por abajo, lo eliminamos para liberar memoria (Clean Code)
        if self.rect.top > ALTO + 10:
            self.kill()