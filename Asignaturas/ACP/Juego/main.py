import pygame
import sys
import time
from settings import *
from sprites import Jugador, Meteorito, PowerUp, Particula
from database import DBManager


class Juego:
    def __init__(self):
        pygame.init()
        pygame.mixer.init()  # Inicializar mixer para audio
        self.pantalla = pygame.display.set_mode((ANCHO, ALTO))
        pygame.display.set_caption(TITULO)
        self.reloj = pygame.time.Clock()
        self.font = pygame.font.SysFont("Arial", 24)
        self.font_big = pygame.font.SysFont("Arial", 48)
        self.font_medium = pygame.font.SysFont("Arial", 32)

        # Gestor de Base de Datos
        self.db_manager = DBManager()

        # Gestor de Skins
        self.skin_manager = SkinManager(self.db_manager)

        # Inicializar sistema de audio
        self._inicializar_audio()

        self.corriendo = True
        # Nuevo estado: REGISTER, STATS, SKINSHOP
        self.estado = "LOGIN"  # Estados: LOGIN, REGISTER, LEADERBOARD, STATS, SKINSHOP, JUGANDO, INPUT_NAME

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

        # Sistema de power-ups y efectos
        self.grupo_powerups = pygame.sprite.Group()
        self.grupo_particulas = pygame.sprite.Group()
        self.powerups_activos = {}  # {tipo: tiempo_fin}
        self.contador_powerup = 0
        
        # Sistema de niveles
        self.nivel_actual = 1
        self.inicio_partida = 0
        self.multiplicador_puntos = 1
        
        self.nuevo_juego()

    # ... (método nuevo_juego se mantiene igual)

    def nuevo_juego(self):
        """Reinicia todas las variables para una nueva partida"""
        self.sprites_all = pygame.sprite.Group()
        self.grupo_enemigos = pygame.sprite.Group()
        self.grupo_powerups = pygame.sprite.Group()
        self.grupo_particulas = pygame.sprite.Group()

        # Obtener skins activas del usuario
        usuario_id = self.skin_manager.obtener_usuario_id(self.usuario_logueado)
        skin_nave = None
        skin_meteorito = None

        if usuario_id:
            skin_nave_data = self.skin_manager.obtener_skin_activa(usuario_id, "nave")
            if skin_nave_data:
                skin_nave = {
                    "imagen_ruta": skin_nave_data[3],  # imagen_ruta
                    "color_fallback": skin_nave_data[4]  # color_fallback
                }

            skin_meteorito_data = self.skin_manager.obtener_skin_activa(usuario_id, "meteorito")
            if skin_meteorito_data:
                skin_meteorito = {
                    "imagen_ruta": skin_meteorito_data[3],  # imagen_ruta
                    "color_fallback": skin_meteorito_data[4]  # color_fallback
                }

        self.skin_nave_activa = skin_nave
        self.skin_meteorito_activa = skin_meteorito

        self.jugador = Jugador(skin_nave=self.skin_nave_activa)
        self.sprites_all.add(self.jugador)

        self.puntuacion = 0
        self.contador_frames = 0
        self.contador_powerup = 0
        self.powerups_activos = {}
        self.nivel_actual = 1
        self.multiplicador_puntos = 1
        self.inicio_partida = time.time()
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
            elif self.estado == "STATS":
                self._handle_stats_events(evento)  # Nuevo manejador
            elif self.estado == "SKINSHOP":
                self._handle_skinshop_events(evento)  # Nuevo manejador
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
            elif evento.key == pygame.K_s:  # <--- NUEVO: Presiona S para ver estadísticas
                self.estado = "STATS"
            elif evento.key == pygame.K_t:  # <--- NUEVO: Presiona T para tienda de skins
                self.estado = "SKINSHOP"
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

    def _handle_stats_events(self, evento):  # <--- NUEVO: Manejador de estadísticas
        if evento.type == pygame.KEYDOWN:
            if evento.key == pygame.K_ESCAPE or evento.key == pygame.K_BACKSPACE:
                self.estado = "LEADERBOARD"

    def _handle_skinshop_events(self, evento):  # <--- NUEVO: Manejador de tienda de skins
        if evento.type == pygame.KEYDOWN:
            if evento.key == pygame.K_ESCAPE or evento.key == pygame.K_BACKSPACE:
                self.estado = "LEADERBOARD"
            elif evento.key in [pygame.K_1, pygame.K_2, pygame.K_3, pygame.K_4, pygame.K_5]:
                # Comprar/desbloquear skin (1-5 primeras skins)
                skin_index = evento.key - pygame.K_1
                skins = self.skin_manager.obtener_skins_disponibles()
                if skin_index < len(skins):
                    skin = skins[skin_index]
                    usuario_id = self.skin_manager.obtener_usuario_id(self.usuario_logueado)
                    if not self.skin_manager.tiene_skin_desbloqueada(usuario_id, skin[0]):
                        # Intentar comprar (por ahora gratis para demo)
                        if self.skin_manager.desbloquear_skin(usuario_id, skin[0]):
                            print(f"Skin {skin[1]} desbloqueada!")
                        else:
                            print("Error al desbloquear skin")
                    else:
                        # Cambiar skin activa
                        self.skin_manager.cambiar_skin_activa(usuario_id, skin[0])
                        print(f"Skin {skin[1]} activada!")

    def _handle_leaderboard_events(self, evento):
        # Este método se mantiene por si lo quieres reutilizar, aunque ahora va directo a LEADERBOARD
        if evento.type == pygame.KEYDOWN:
            if evento.key == pygame.K_SPACE:
                self.nuevo_juego()
                self.estado = "LEADERBOARD"

    # ... (método actualizar se mantiene igual, guarda automáticamente)

    def actualizar(self):
        if self.estado == "JUGANDO":
            self.sprites_all.update()
            self.grupo_powerups.update()
            self.grupo_particulas.update()

            # Calcular nivel actual
            nivel_nuevo = (self.puntuacion // PUNTOS_POR_NIVEL) + 1
            if nivel_nuevo > self.nivel_actual:
                self.nivel_actual = nivel_nuevo

            # Generar enemigos (velocidad aumenta con nivel)
            self.contador_frames += 1
            tasa = max(5, self.dificultades[self.dificultad_actual]["tasa"] - (self.nivel_actual * 2))
            if self.contador_frames >= tasa:
                vel_base = self.dificultades[self.dificultad_actual]["vel"] + (self.nivel_actual - 1)
                m = Meteorito(velocidad_base=vel_base, skin_meteorito=self.skin_meteorito_activa)
                
                # Aplicar slow motion si está activo
                if "slowmotion" in self.powerups_activos:
                    m.aplicar_slowmotion()
                
                self.sprites_all.add(m)
                self.grupo_enemigos.add(m)
                self.contador_frames = 0
                
                # Puntos con multiplicador
                self.puntuacion += self.multiplicador_puntos
                
                # Generar partículas de estela
                if self.puntuacion % 5 == 0:
                    self._generar_estela()

            # Generar power-ups
            self.contador_powerup += 1
            if self.contador_powerup >= TASA_APARICION_POWERUP:
                p = PowerUp()
                self.grupo_powerups.add(p)
                self.contador_powerup = 0

            # Colisiones con power-ups
            hits_powerup = pygame.sprite.spritecollide(self.jugador, self.grupo_powerups, True)
            for powerup in hits_powerup:
                self._aplicar_powerup(powerup.tipo)
                self._generar_explosion(powerup.rect.centerx, powerup.rect.centery, COLORES_POWERUP[powerup.tipo])
                self._reproducir_sonido("powerup")

            # Actualizar power-ups activos
            self._actualizar_powerups_activos()

            # Colisiones con enemigos
            hits = pygame.sprite.spritecollide(self.jugador, self.grupo_enemigos, False)
            if hits:
                # Verificar escudo
                if self.jugador.tiene_escudo:
                    # Destruir enemigo pero no perder
                    for hit in hits:
                        hit.kill()
                        self._generar_explosion(hit.rect.centerx, hit.rect.centery, COLOR_ENEMIGO)
                    self._reproducir_sonido("colision")
                    self.jugador.tiene_escudo = False
                    if "escudo" in self.powerups_activos:
                        del self.powerups_activos["escudo"]
                elif self.jugador.vidas_extra > 0:
                    # Usar vida extra
                    self.jugador.vidas_extra -= 1
                    for hit in hits:
                        hit.kill()
                        self._generar_explosion(hit.rect.centerx, hit.rect.centery, COLOR_ENEMIGO)
                    self._reproducir_sonido("colision")
                else:
                    # Game Over
                    duracion = time.time() - self.inicio_partida
                    self.db_manager.guardar_record(self.usuario_logueado, self.puntuacion, self.dificultad_actual, duracion)
                    self._generar_explosion(self.jugador.rect.centerx, self.jugador.rect.centery, COLOR_NAVE)
                    self._reproducir_sonido("explosion")
                    self.estado = "LEADERBOARD"

    def dibujar(self):
        self.pantalla.fill(COLOR_FONDO)

        if self.estado == "LOGIN":
            self.dibujar_login_screen()
        elif self.estado == "REGISTER":
            self.dibujar_register_screen()  # Nuevo dibujado
        elif self.estado == "STATS":
            self.dibujar_stats_screen()  # Nuevo dibujado
        elif self.estado == "SKINSHOP":
            self.dibujar_skinshop_screen()  # Nuevo dibujado
        elif self.estado == "LEADERBOARD":
            self.dibujar_leaderboard_screen()
        elif self.estado == "JUGANDO":
            # Dibujar partículas primero (fondo)
            self.grupo_particulas.draw(self.pantalla)
            # Dibujar sprites principales
            self.sprites_all.draw(self.pantalla)
            self.grupo_powerups.draw(self.pantalla)
            
            # HUD
            self.dibujar_texto(f"Score: {self.puntuacion}", 10, 10)
            self.dibujar_texto(f"Nivel: {self.nivel_actual}", 10, 40)
            self.dibujar_texto(f"Jugador: {self.usuario_logueado}", 10, 70)
            
            # Mostrar power-ups activos
            y_offset = 100
            for powerup_tipo, tiempo_fin in self.powerups_activos.items():
                tiempo_restante = int(tiempo_fin - time.time())
                if tiempo_restante > 0:
                    color = COLORES_POWERUP.get(powerup_tipo, (255, 255, 255))
                    self.dibujar_texto(f"{powerup_tipo.upper()}: {tiempo_restante}s", 10, y_offset, color=color)
                    y_offset += 25
            
            # Mostrar vidas extra
            if self.jugador.vidas_extra > 0:
                self.dibujar_texto(f"Vidas: {self.jugador.vidas_extra}", ANCHO - 120, 10, color=(0, 255, 0))
            
            # Mostrar escudo activo
            if self.jugador.tiene_escudo:
                pygame.draw.circle(self.pantalla, (0, 255, 255), self.jugador.rect.center, 35, 2)
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
        self.dibujar_texto_centrado("Presiona S para ver estadísticas.", 230, color=(100, 200, 255))
        self.dibujar_texto_centrado("Presiona T para tienda de skins.", 260, color=(255, 200, 0))  # <--- NUEVA OPCIÓN
        self.dibujar_texto_centrado("Presiona L para cerrar sesión.", 290, color=(255, 255, 0))  # <--- NUEVA OPCIÓN

    def dibujar_stats_screen(self):  # <--- NUEVO: Pantalla de estadísticas
        """Dibuja la pantalla de estadísticas del usuario"""
        self.dibujar_texto_centrado(f"PERFIL DE {self.usuario_logueado.upper()}", -240, size=48, color=(255, 215, 0))
        
        # Obtener estadísticas
        stats = self.db_manager.obtener_estadisticas_usuario(self.usuario_logueado)
        
        if stats:
            y = -180
            self.dibujar_texto_centrado("=== ESTADÍSTICAS GENERALES ===", y, size=32, color=(100, 200, 255))
            y += 40
            self.dibujar_texto_centrado(f"Partidas jugadas: {stats['total_partidas']}", y, color=(255, 255, 255))
            y += 30
            self.dibujar_texto_centrado(f"Tiempo total: {stats['tiempo_total']:.1f} segundos", y, color=(255, 255, 255))
            y += 30
            self.dibujar_texto_centrado(f"Puntuación promedio: {stats['promedio']:.1f}", y, color=(255, 255, 255))
            y += 30
            self.dibujar_texto_centrado(f"Racha actual: {stats['racha_actual']}", y, color=(0, 255, 0))
            y += 30
            self.dibujar_texto_centrado(f"Mejor racha: {stats['mejor_racha']}", y, color=(255, 165, 0))
        
        # Obtener logros
        logros = self.db_manager.obtener_logros_usuario(self.usuario_logueado)
        
        if logros:
            y += 50
            self.dibujar_texto_centrado("=== LOGROS DESBLOQUEADOS ===", y, size=32, color=(255, 215, 0))
            y += 40
            for nombre, descripcion, fecha in logros[:5]:  # Mostrar los primeros 5
                y += 25
                self.dibujar_texto_centrado(f"🏆 {nombre}: {descripcion}", y, size=20, color=(200, 200, 200))
        
        self.dibujar_texto_centrado("Presiona ESC para volver.", 260, color=(255, 100, 100))

    def dibujar_skinshop_screen(self):  # <--- NUEVA: Pantalla de tienda de skins
        """Dibuja la pantalla de tienda de skins"""
        self.dibujar_texto_centrado(f"TIENDA DE SKINS - {self.usuario_logueado.upper()}", -240, size=48, color=(255, 215, 0))

        # Obtener skins disponibles y del usuario
        skins_disponibles = self.skin_manager.obtener_skins_disponibles()
        usuario_id = self.skin_manager.obtener_usuario_id(self.usuario_logueado)

        y = -180
        self.dibujar_texto_centrado("=== SKINS DISPONIBLES ===", y, size=32, color=(100, 200, 255))
        y += 40

        for i, skin in enumerate(skins_disponibles[:5]):  # Mostrar primeras 5
            skin_id, nombre, tipo, imagen, color_fallback, precio, descripcion = skin
            tiene_skin = self.skin_manager.tiene_skin_desbloqueada(usuario_id, skin_id)
            activa = self.skin_manager.obtener_skin_activa(usuario_id, tipo)

            status = ""
            color_texto = (255, 255, 255)
            if activa and activa[0] == skin_id:
                status = " [ACTIVA]"
                color_texto = (0, 255, 0)
            elif tiene_skin:
                status = " [DESBLOQUEADA]"
                color_texto = (200, 200, 0)
            else:
                status = f" [PRECIO: {precio} pts]"
                color_texto = (255, 100, 100)

            texto = f"{i+1}. {tipo.upper()}: {descripcion}{status}"
            self.dibujar_texto_centrado(texto, y, size=20, color=color_texto)
            y += 25

        self.dibujar_texto_centrado("Presiona 1-5 para comprar/cambiar skin.", 200, color=(0, 255, 0))
        self.dibujar_texto_centrado("Presiona ESC para volver.", 230, color=(255, 100, 100))

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

    # --- Métodos auxiliares para Power-Ups y Efectos ---

    def _aplicar_powerup(self, tipo: str):
        """Aplica el efecto de un power-up"""
        if tipo == "escudo":
            self.jugador.tiene_escudo = True
            self.powerups_activos["escudo"] = time.time() + DURACION_POWERUP["escudo"]
        
        elif tipo == "slowmotion":
            # Aplicar slow motion a todos los meteoritos existentes
            for enemigo in self.grupo_enemigos:
                enemigo.aplicar_slowmotion()
            self.powerups_activos["slowmotion"] = time.time() + DURACION_POWERUP["slowmotion"]
        
        elif tipo == "puntos_dobles":
            self.multiplicador_puntos = 2
            self.powerups_activos["puntos_dobles"] = time.time() + DURACION_POWERUP["puntos_dobles"]
        
        elif tipo == "multivida":
            self.jugador.vidas_extra += 1
        
        elif tipo == "reducir_tamaño":
            self.jugador.aplicar_tamaño_reducido()
            self.powerups_activos["reducir_tamaño"] = time.time() + DURACION_POWERUP["reducir_tamaño"]

    def _actualizar_powerups_activos(self):
        """Actualiza y remueve power-ups que hayan expirado"""
        tiempo_actual = time.time()
        powerups_expirados = []
        
        for tipo, tiempo_fin in self.powerups_activos.items():
            if tiempo_actual >= tiempo_fin:
                powerups_expirados.append(tipo)
        
        for tipo in powerups_expirados:
            if tipo == "escudo":
                self.jugador.tiene_escudo = False
            elif tipo == "slowmotion":
                # Restaurar velocidad normal a todos los meteoritos
                for enemigo in self.grupo_enemigos:
                    enemigo.restaurar_velocidad()
            elif tipo == "puntos_dobles":
                self.multiplicador_puntos = 1
            elif tipo == "reducir_tamaño":
                self.jugador.restaurar_tamaño()
            
            del self.powerups_activos[tipo]

    def _generar_explosion(self, x, y, color):
        """Genera partículas de explosión"""
        import random
        for _ in range(20):
            vel_x = random.uniform(-5, 5)
            vel_y = random.uniform(-5, 5)
            particula = Particula(x, y, color, vel_x, vel_y)
            self.grupo_particulas.add(particula)

    def _generar_estela(self):
        """Genera partículas de estela detrás del jugador"""
        import random
        x = self.jugador.rect.centerx + random.randint(-10, 10)
        y = self.jugador.rect.bottom
        particula = Particula(x, y, (100, 150, 255), 0, 2)
        self.grupo_particulas.add(particula)

    def _inicializar_audio(self):
        """Inicializa el sistema de audio del juego"""
        self.sonidos = {}
        self.musica_activa = False
        
        # Intentar cargar sonidos (si no existen, crear sonidos sintéticos)
        try:
            # Aquí podrías cargar archivos de audio reales
            # self.sonidos['colision'] = pygame.mixer.Sound('assets/colision.wav')
            # self.sonidos['powerup'] = pygame.mixer.Sound('assets/powerup.wav')
            # etc.
            pass
        except Exception:
            pass
        
        # Configurar volumen inicial
        pygame.mixer.music.set_volume(VOLUMEN_MUSICA_DEFAULT)
    
    def _reproducir_sonido(self, tipo: str):
        """Reproduce un efecto de sonido"""
        # Sonidos sintéticos usando frecuencias
        if tipo == "powerup":
            # Sonido de power-up (opcional: implementar con pygame.sndarray)
            pass
        elif tipo == "colision":
            # Sonido de colisión
            pass
        elif tipo == "explosion":
            # Sonido de explosión
            pass

            self._reproducir_sonido("explosion")

    def run(self):
        while self.corriendo:
            self.reloj.tick(FPS)
            self.manejar_eventos()
            self.actualizar()
            self.dibujar()


class SkinManager:
    """Gestor de skins del juego"""

    def __init__(self, db_manager):
        self.db_manager = db_manager
        self.skins_cache = {}  # Cache de skins por usuario

    def obtener_skins_usuario(self, usuario_id):
        """Obtiene las skins desbloqueadas por un usuario"""
        if usuario_id not in self.skins_cache:
            self.skins_cache[usuario_id] = self.db_manager.obtener_skins_usuario(usuario_id)
        return self.skins_cache[usuario_id]

    def obtener_skin_activa(self, usuario_id, tipo):
        """Obtiene la skin activa de un tipo para un usuario"""
        return self.db_manager.obtener_skin_activa(usuario_id, tipo)

    def cambiar_skin_activa(self, usuario_id, skin_id):
        """Cambia la skin activa para un usuario"""
        self.db_manager.cambiar_skin_activa(usuario_id, skin_id)
        # Limpiar cache para forzar recarga
        if usuario_id in self.skins_cache:
            del self.skins_cache[usuario_id]

    def desbloquear_skin(self, usuario_id, skin_id):
        """Desbloquea una skin para un usuario"""
        exito = self.db_manager.desbloquear_skin(usuario_id, skin_id)
        if exito and usuario_id in self.skins_cache:
            del self.skins_cache[usuario_id]  # Limpiar cache
        return exito

    def tiene_skin_desbloqueada(self, usuario_id, skin_id):
        """Verifica si un usuario tiene una skin desbloqueada"""
        return self.db_manager.tiene_skin_desbloqueada(usuario_id, skin_id)

    def obtener_skins_disponibles(self):
        """Obtiene todas las skins disponibles"""
        return self.db_manager.obtener_skins_disponibles()

    def obtener_usuario_id(self, nombre_usuario):
        """Obtiene el ID de un usuario por su nombre"""
        self.db_manager.cursor.execute("SELECT id FROM usuarios WHERE nombre=?", (nombre_usuario,))
        resultado = self.db_manager.cursor.fetchone()
        return resultado[0] if resultado else None


if __name__ == "__main__":
    juego = Juego()
    juego.run()