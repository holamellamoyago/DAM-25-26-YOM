import pygame
import sys
from settings import *
from sprites import Jugador, Meteorito
from database import DBManager


class Juego:
    def __init__(self):
        pygame.init()
        self.pantalla = pygame.display.set_mode((ANCHO, ALTO))
        pygame.display.set_caption(TITULO)
        self.reloj = pygame.time.Clock()
        self.font = pygame.font.SysFont("Arial", 24)
        self.font_big = pygame.font.SysFont("Arial", 48)
        self.font_medium = pygame.font.SysFont("Arial", 32)

        # Gestor de Base de Datos
        self.db_manager = DBManager()

        self.corriendo = True
        # Nuevo estado: REGISTER
        self.estado = "LOGIN"  # Estados: LOGIN, REGISTER, LEADERBOARD, JUGANDO, INPUT_NAME

        # Dificultad (selector)
        # Cada dificultad ajusta la tasa de aparición (frames entre meteoritos) y velocidad base
        self.dificultades = {
            "Fácil": {"tasa": max(10, TASA_APARICION_ENEMIGOS + 15), "vel": max(1, VELOCIDAD_INICIAL_ENEMIGO - 2)},
            "Normal": {"tasa": TASA_APARICION_ENEMIGOS, "vel": VELOCIDAD_INICIAL_ENEMIGO},
            "Difícil": {"tasa": max(1, TASA_APARICION_ENEMIGOS - 10), "vel": VELOCIDAD_INICIAL_ENEMIGO + 2},
        }
        self.dificultad_actual = "Normal"

        # Variables de Login/Registro
        self.usuario_logueado = ""
        self.input_nombre = ""
        self.input_password = ""
        self.input_activo = "nombre"  # Para saber qué campo está editando
        self.error_mensaje = ""  # Usado para login y registro

        self.nuevo_juego()

    # ... (método nuevo_juego se mantiene igual)

    def nuevo_juego(self):
        """Reinicia todas las variables para una nueva partida"""
        self.sprites_all = pygame.sprite.Group()
        self.grupo_enemigos = pygame.sprite.Group()

        self.jugador = Jugador()
        self.sprites_all.add(self.jugador)

        self.puntuacion = 0
        self.contador_frames = 0
        # NOTA: El estado se maneja por separado (LOGIN, REGISTER, LEADERBOARD, JUGANDO)

    def manejar_eventos(self):
        for evento in pygame.event.get():
            if evento.type == pygame.QUIT:
                self.corriendo = False
                self.db_manager.cerrar()
                sys.exit()

            # --- Manejo de Eventos por Estado ---

            if self.estado == "LOGIN":
                self._handle_login_events(evento)
            elif self.estado == "REGISTER":
                self._handle_register_events(evento)  # Nuevo manejador
            elif self.estado == "LEADERBOARD":
                self._handle_leaderboard_events(evento)
            elif self.estado == "JUGANDO" and evento.type == pygame.KEYDOWN and evento.key == pygame.K_SPACE:
                pass  # Acción opcional
            elif self.estado == "INPUT_NAME":
                self._handle_input_name_events(evento)

    # Métodos privados para el manejo de eventos (Clean Code)
    def _handle_login_events(self, evento):
        if evento.type == pygame.KEYDOWN:
            self.error_mensaje = ""  # Limpiar error al escribir

            if evento.key == pygame.K_TAB:
                self.input_activo = "password" if self.input_activo == "nombre" else "nombre"

            elif evento.key == pygame.K_RETURN:
                if self.db_manager.verificar_login(self.input_nombre.strip(), self.input_password.strip()):
                    self.usuario_logueado = self.input_nombre
                    self.estado = "LEADERBOARD"
                    self.nuevo_juego()
                else:
                    self.error_mensaje = "Usuario o contraseña incorrectos."

            elif evento.key == pygame.K_r:  # <--- NUEVO: Presiona R para ir a Registro
                self.estado = "REGISTER"
                self.input_nombre = ""
                self.input_password = ""
                self.error_mensaje = ""
                self.input_activo = "nombre"

            elif evento.key == pygame.K_BACKSPACE:
                if self.input_activo == "nombre":
                    self.input_nombre = self.input_nombre[:-1]
                else:
                    self.input_password = self.input_password[:-1]
            else:
                if evento.unicode.isprintable():
                    if self.input_activo == "nombre":
                        if len(self.input_nombre) < 15:
                            self.input_nombre += evento.unicode
                    else:
                        if len(self.input_password) < 15:
                            self.input_password += evento.unicode

    def _handle_register_events(self, evento):  # <--- NUEVO: Manejador de Registro
        if evento.type == pygame.KEYDOWN:
            self.error_mensaje = ""  # Limpiar error al escribir

            if evento.key == pygame.K_TAB:
                self.input_activo = "password" if self.input_activo == "nombre" else "nombre"

            elif evento.key == pygame.K_RETURN:
                nombre = self.input_nombre.strip()
                password = self.input_password.strip()

                if not nombre or not password:
                    self.error_mensaje = "Nombre y contraseña no pueden estar vacíos."
                elif len(password) < 4:
                    self.error_mensaje = "La contraseña debe tener al menos 4 caracteres."
                elif self.db_manager.registrar_usuario(nombre, password):
                    self.usuario_logueado = nombre
                    self.estado = "LEADERBOARD"
                    self.nuevo_juego()
                else:
                    self.error_mensaje = "Ese nombre de usuario ya existe. Intenta otro."

            elif evento.key == pygame.K_l:  # <--- NUEVO: Presiona L para ir a Login
                self.estado = "LOGIN"
                self.input_nombre = ""
                self.input_password = ""
                self.error_mensaje = ""
                self.input_activo = "nombre"

            elif evento.key == pygame.K_BACKSPACE:
                if self.input_activo == "nombre":
                    self.input_nombre = self.input_nombre[:-1]
                else:
                    self.input_password = self.input_password[:-1]
            else:
                if evento.unicode.isprintable():
                    if self.input_activo == "nombre":
                        if len(self.input_nombre) < 15:
                            self.input_nombre += evento.unicode
                    else:
                        if len(self.input_password) < 15:
                            self.input_password += evento.unicode

    def _handle_leaderboard_events(self, evento):
        if evento.type == pygame.KEYDOWN:
            if evento.key == pygame.K_SPACE:
                self.estado = "JUGANDO"
                # Reiniciamos contadores al empezar
                self.contador_frames = 0
            elif evento.key == pygame.K_l:  # <--- NUEVO: Presiona L para cerrar sesión
                self.usuario_logueado = ""
                self.input_nombre = ""
                self.input_password = ""
                self.estado = "LOGIN"
            elif evento.key == pygame.K_q:
                self.corriendo = False
                self.db_manager.cerrar()
                sys.exit()
            # Selector de dificultad: 1, 2, 3
            elif evento.key == pygame.K_1:
                self.dificultad_actual = "Fácil"
            elif evento.key == pygame.K_2:
                self.dificultad_actual = "Normal"
            elif evento.key == pygame.K_3:
                self.dificultad_actual = "Difícil"

    def _handle_input_name_events(self, evento):
        # Este método se mantiene por si lo quieres reutilizar, aunque ahora va directo a LEADERBOARD
        if evento.type == pygame.KEYDOWN:
            if evento.key == pygame.K_SPACE:
                self.nuevo_juego()
                self.estado = "LEADERBOARD"

    # ... (método actualizar se mantiene igual, guarda automáticamente)

    def actualizar(self):
        if self.estado == "JUGANDO":
            self.sprites_all.update()

            # Generar enemigos
            self.contador_frames += 1
            tasa = self.dificultades[self.dificultad_actual]["tasa"]
            if self.contador_frames >= tasa:
                vel_base = self.dificultades[self.dificultad_actual]["vel"]
                m = Meteorito(velocidad_base=vel_base)
                self.sprites_all.add(m)
                self.grupo_enemigos.add(m)
                self.contador_frames = 0
                self.puntuacion += 1

            # Colisiones
            hits = pygame.sprite.spritecollide(self.jugador, self.grupo_enemigos, False)
            if hits:
                # Guardamos la puntuación automáticamente
                self.db_manager.guardar_record(self.usuario_logueado, self.puntuacion)
                self.estado = "LEADERBOARD"  # Volvemos a la tabla de récords

    def dibujar(self):
        self.pantalla.fill(COLOR_FONDO)

        if self.estado == "LOGIN":
            self.dibujar_login_screen()
        elif self.estado == "REGISTER":
            self.dibujar_register_screen()  # Nuevo dibujado
        elif self.estado == "LEADERBOARD":
            self.dibujar_leaderboard_screen()
        elif self.estado == "JUGANDO":
            self.sprites_all.draw(self.pantalla)
            self.dibujar_texto(f"Score: {self.puntuacion}", 10, 10)
            self.dibujar_texto(f"Jugador: {self.usuario_logueado}", 10, 40)
        elif self.estado == "INPUT_NAME":
            self.dibujar_gameover_screen()

        pygame.display.flip()

    # --- Métodos de Dibujado (Clean Code: Separación de UI) ---

    def dibujar_login_screen(self):
        self.dibujar_texto_centrado("INICIAR SESIÓN", -150, size=48)
        self.dibujar_texto_centrado("Usuario:", -50)
        self.dibujar_input_box("nombre", self.input_nombre, -20)

        self.dibujar_texto_centrado("Contraseña:", 50)
        password_oculta = "*" * len(self.input_password)
        self.dibujar_input_box("password", password_oculta, 80)

        if self.error_mensaje:
            self.dibujar_texto_centrado(self.error_mensaje, 140, color=(255, 0, 0))

        self.dibujar_texto_centrado("Presiona ENTER para ingresar.", 200, color=(0, 255, 0))
        self.dibujar_texto_centrado("Presiona R para registrarte.", 240, color=(255, 255, 0))  # <--- NUEVA OPCIÓN

    def dibujar_register_screen(self):  # <--- NUEVA PANTALLA
        self.dibujar_texto_centrado("REGISTRAR NUEVO USUARIO", -150, size=48)
        self.dibujar_texto_centrado("Elige Nombre:", -50)
        self.dibujar_input_box("nombre", self.input_nombre, -20)

        self.dibujar_texto_centrado("Elige Contraseña:", 50)
        password_oculta = "*" * len(self.input_password)
        self.dibujar_input_box("password", password_oculta, 80)

        if self.error_mensaje:
            self.dibujar_texto_centrado(self.error_mensaje, 140, color=(255, 0, 0))

        self.dibujar_texto_centrado("Presiona ENTER para registrarte.", 200, color=(0, 255, 0))
        self.dibujar_texto_centrado("Presiona L para ir a Login.", 240, color=(255, 255, 0))  # <--- NUEVA OPCIÓN

    def dibujar_input_box(self, campo, texto, offset_y):
        rect_color = (255, 255, 255) if self.input_activo == campo else COLOR_UI_FONDO
        rect = pygame.Rect(ANCHO // 2 - 150, ALTO // 2 + offset_y, 300, 40)

        pygame.draw.rect(self.pantalla, rect_color, rect, 2)
        pygame.draw.rect(self.pantalla, COLOR_UI_FONDO, rect.inflate(-4, -4))  # Fondo interior

        texto_superficie = self.font.render(texto, True, COLOR_TEXTO)
        self.pantalla.blit(texto_superficie, (rect.x + 10, rect.y + 10))

    def dibujar_leaderboard_screen(self):
        self.dibujar_texto_centrado(f"Bienvenido, {self.usuario_logueado}", -200, color=(0, 255, 0))
        self.dibujar_texto_centrado("--- TOP SCORES ---", -140, size=48, color=(255, 215, 0))

        records = self.db_manager.obtener_mejores_scores()
        start_y = ALTO // 2 - 80

        if not records:
            self.dibujar_texto_centrado("No hay récords guardados.", 0)

        for i, (nombre, score) in enumerate(records):
            texto = f"{i + 1}. {nombre} - {score}"
            self.dibujar_texto_centrado(texto, start_y + i * 40, size=32)

        # Mostrar dificultad actual y controles para cambiarla
        self.dibujar_texto_centrado(f"Dificultad actual: {self.dificultad_actual} (1: Fácil, 2: Normal, 3: Difícil)", 160, color=(173, 216, 230))
        self.dibujar_texto_centrado("Presiona ESPACIO para empezar la partida.", 200, color=(0, 255, 0))
        self.dibujar_texto_centrado("Presiona L para cerrar sesión.", 240, color=(255, 255, 0))  # <--- NUEVA OPCIÓN

    # ... (métodos dibujar_gameover_screen, dibujar_texto, dibujar_texto_centrado se mantienen igual)

    def dibujar_gameover_screen(self):
        s = pygame.Surface((ANCHO, ALTO))
        s.set_alpha(150)
        s.fill(0)
        self.pantalla.blit(s, (0, 0))

        self.dibujar_texto_centrado("¡NAVE DESTRUIDA!", -50, size=48, color=(255, 0, 0))
        self.dibujar_texto_centrado(f"Tu puntuación: {self.puntuacion}", 20, size=32)
        self.dibujar_texto_centrado("Presiona ESPACIO para ir a la tabla de récords.", 100, color=(255, 255, 0))

    def dibujar_texto(self, texto, x, y, size=24, color=COLOR_TEXTO):
        fuente = self.font
        if size == 48:
            fuente = self.font_big
        elif size == 32:
            fuente = self.font_medium

        superficie = fuente.render(texto, True, color)
        self.pantalla.blit(superficie, (x, y))

    def dibujar_texto_centrado(self, texto, offset_y, size=24, color=COLOR_TEXTO):
        fuente = self.font
        if size == 48:
            fuente = self.font_big
        elif size == 32:
            fuente = self.font_medium

        superficie = fuente.render(texto, True, color)
        rect = superficie.get_rect(center=(ANCHO // 2, ALTO // 2 + offset_y))
        self.pantalla.blit(superficie, rect)

    def run(self):
        while self.corriendo:
            self.reloj.tick(FPS)
            self.manejar_eventos()
            self.actualizar()
            self.dibujar()


if __name__ == "__main__":
    juego = Juego()
    juego.run()