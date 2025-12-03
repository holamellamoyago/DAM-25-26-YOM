import pygame

# Dimensiones
ANCHO = 800
ALTO = 600
FPS = 60

# Colores (R, G, B)
COLOR_FONDO = (20, 20, 40) # Azul oscuro espacial
COLOR_TEXTO = (255, 255, 255)
COLOR_NAVE = (0, 128, 255)
COLOR_ENEMIGO = (255, 50, 50)
COLOR_UI_FONDO = (50, 50, 50)

# Configuración del Juego
TITULO = "Space Dodge: Ultimate Edition"
DATABASE_NAME = "records.db"

# Rutas de imágenes (si no existen, el juego usará formas de color por defecto)
# Puedes colocar los archivos en src/ACP/Juego/assets/ con estos nombres o cambiar las rutas aquí.
RUTA_IMAGEN_JUGADOR = "assets\\logo_hacienda.png"
RUTA_IMAGEN_METEORITO = "assets\\cara_pedro.png"

# Físicas
VELOCIDAD_JUGADOR = 8
VELOCIDAD_INICIAL_ENEMIGO = 5
TASA_APARICION_ENEMIGOS = 25 # Menor es más rápido

# Power-Ups
TIPOS_POWERUP = ["escudo", "slowmotion", "puntos_dobles", "multivida", "reducir_tamaño"]
COLORES_POWERUP = {
    "escudo": (0, 255, 255),  # Cyan
    "slowmotion": (255, 255, 0),  # Amarillo
    "puntos_dobles": (255, 165, 0),  # Naranja
    "multivida": (0, 255, 0),  # Verde
    "reducir_tamaño": (255, 0, 255)  # Magenta
}
DURACION_POWERUP = {
    "escudo": 8,
    "slowmotion": 10,
    "puntos_dobles": 15,
    "multivida": 999,  # Permanente hasta usarse
    "reducir_tamaño": 12
}
TASA_APARICION_POWERUP = 400  # Frames entre power-ups

# Sistema de niveles
PUNTOS_POR_NIVEL = 50  # Cada 50 puntos sube de nivel

# Seguridad - Requisitos de contraseña
MIN_LONGITUD_PASSWORD = 8
INTENTOS_LOGIN_MAX = 5
TIEMPO_BLOQUEO_SEGUNDOS = 30

# Audio
VOLUMEN_MUSICA_DEFAULT = 0.5
VOLUMEN_EFECTOS_DEFAULT = 0.7

# Modo Multijugador
CONTROLES_JUGADOR_1 = {
    "izq": pygame.K_LEFT,
    "der": pygame.K_RIGHT
}
CONTROLES_JUGADOR_2 = {
    "izq": pygame.K_a,
    "der": pygame.K_d
}
