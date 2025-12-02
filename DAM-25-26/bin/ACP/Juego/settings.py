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
TITULO = "Space Dodge: OOP Edition"
DATABASE_NAME = "records.db"

# Rutas de imágenes (si no existen, el juego usará formas de color por defecto)
# Puedes colocar los archivos en src/ACP/Juego/assets/ con estos nombres o cambiar las rutas aquí.
RUTA_IMAGEN_JUGADOR = "assets\\logo_hacienda.png"
RUTA_IMAGEN_METEORITO = "assets\\cara_pedro.png"

# Físicas
VELOCIDAD_JUGADOR = 8
VELOCIDAD_INICIAL_ENEMIGO = 5
TASA_APARICION_ENEMIGOS = 25 # Menor es más rápido