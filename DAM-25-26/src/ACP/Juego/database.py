import sqlite3
import hashlib
import re
import sqlite3
import hashlib
import re
from datetime import datetime, timedelta
from settings import DATABASE_NAME, MIN_LONGITUD_PASSWORD, INTENTOS_LOGIN_MAX, TIEMPO_BLOQUEO_SEGUNDOS


class DBManager:
    """Clase encargada de la persistencia de datos (Patrón Manager/DAO)"""

    def __init__(self):
        self.connection = sqlite3.connect(DATABASE_NAME)
        self.cursor = self.connection.cursor()
        self.crear_tablas()
        self.inicializar_datos()

        # Control de intentos de login
        self.intentos_fallidos = {}  # {usuario: (contador, timestamp_bloqueo)}

    def _hash_password(self, password: str) -> str:
        """Genera un hash SHA-256 de la contraseña"""
        return hashlib.sha256(password.encode('utf-8')).hexdigest()

    def _validar_password_segura(self, password: str) -> tuple[bool, str]:
        """
        Valida que la contraseña cumpla los requisitos de seguridad.
        Retorna (es_valida, mensaje_error)
        """
        if len(password) < MIN_LONGITUD_PASSWORD:
            return False, f"Mínimo {MIN_LONGITUD_PASSWORD} caracteres"

        if not re.search(r'[A-Z]', password):
            return False, "Necesita al menos una mayúscula"

        if not re.search(r'[0-9]', password):
            return False, "Necesita al menos un número"

        if not re.search(r'[!@#$%^&*(),.?":{}|<>]', password):
            return False, "Necesita al menos un carácter especial"

        return True, ""

    def crear_tablas(self):
        # Tabla de Usuarios (mejorada con fecha de registro)
        query_users = """
                      CREATE TABLE IF NOT EXISTS usuarios
                      (
                          id INTEGER PRIMARY KEY AUTOINCREMENT,
                          nombre TEXT UNIQUE NOT NULL,
                          password TEXT NOT NULL,
                          fecha_registro TEXT DEFAULT CURRENT_TIMESTAMP,
                          ultima_sesion TEXT
                      )
                      """
        self.cursor.execute(query_users)

        # Tabla de Puntuaciones
        query_scores = """
                       CREATE TABLE IF NOT EXISTS puntuaciones
                       (
                           id INTEGER PRIMARY KEY AUTOINCREMENT,
                           nombre TEXT NOT NULL,
                           puntuacion INTEGER NOT NULL,
                           fecha TEXT DEFAULT CURRENT_TIMESTAMP
                       )
                       """
        self.cursor.execute(query_scores)

        # Tabla de Perfiles de Usuario
        query_perfiles = """
                        CREATE TABLE IF NOT EXISTS perfiles_usuario
                        (
                            usuario_id INTEGER PRIMARY KEY,
                            total_partidas INTEGER DEFAULT 0,
                            tiempo_total_juego REAL DEFAULT 0.0,
                            puntuacion_promedio REAL DEFAULT 0.0,
                            racha_actual INTEGER DEFAULT 0,
                            mejor_racha INTEGER DEFAULT 0,
                            FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
                        )
                        """
        self.cursor.execute(query_perfiles)

        # Tabla de Historial de Partidas
        query_historial = """
                         CREATE TABLE IF NOT EXISTS historial_partidas
                         (
                             id INTEGER PRIMARY KEY AUTOINCREMENT,
                             usuario_id INTEGER,
                             puntuacion INTEGER NOT NULL,
                             dificultad TEXT,
                             duracion REAL,
                             fecha TEXT DEFAULT CURRENT_TIMESTAMP,
                             FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
                         )
                         """
        self.cursor.execute(query_historial)

        # Tabla de Logros
        query_logros = """
                      CREATE TABLE IF NOT EXISTS logros
                      (
                          id INTEGER PRIMARY KEY AUTOINCREMENT,
                          nombre TEXT UNIQUE NOT NULL,
                          descripcion TEXT,
                          condicion_puntos INTEGER
                      )
                      """
        self.cursor.execute(query_logros)

        # Tabla de Logros del Usuario
        query_usuario_logros = """
                              CREATE TABLE IF NOT EXISTS usuario_logros
                              (
                                  usuario_id INTEGER,
                                  logro_id INTEGER,
                                  fecha_obtencion TEXT DEFAULT CURRENT_TIMESTAMP,
                                  PRIMARY KEY (usuario_id, logro_id),
                                  FOREIGN KEY (usuario_id) REFERENCES usuarios(id),
                                  FOREIGN KEY (logro_id) REFERENCES logros(id)
                              )
                              """
        self.cursor.execute(query_usuario_logros)

        # Tabla de Configuración de Usuario
        query_config = """
                      CREATE TABLE IF NOT EXISTS configuracion_usuario
                      (
                          usuario_id INTEGER PRIMARY KEY,
                          volumen_musica REAL DEFAULT 0.5,
                          volumen_efectos REAL DEFAULT 0.7,
                          tema_visual TEXT DEFAULT 'espacial',
                          FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
                      )
                      """
        self.cursor.execute(query_config)

        # Tabla de Skins
        query_skins = """
                      CREATE TABLE IF NOT EXISTS skins
                      (
                          id INTEGER PRIMARY KEY AUTOINCREMENT,
                          nombre TEXT UNIQUE NOT NULL,
                          tipo TEXT NOT NULL,  -- 'nave', 'meteorito', 'fondo'
                          imagen_ruta TEXT,
                          color_fallback TEXT,  -- Color en formato 'R,G,B'
                          precio_puntos INTEGER DEFAULT 0,
                          descripcion TEXT
                      )
                      """
        self.cursor.execute(query_skins)

        # Tabla de Skins del Usuario
        query_usuario_skins = """
                             CREATE TABLE IF NOT EXISTS usuario_skins
                             (
                                 usuario_id INTEGER,
                                 skin_id INTEGER,
                                 fecha_obtencion TEXT DEFAULT CURRENT_TIMESTAMP,
                                 activa INTEGER DEFAULT 0,  -- 1 si está activa, 0 si no
                                 PRIMARY KEY (usuario_id, skin_id),
                                 FOREIGN KEY (usuario_id) REFERENCES usuarios(id),
                                 FOREIGN KEY (skin_id) REFERENCES skins(id)
                             )
                             """
        self.cursor.execute(query_usuario_skins)

        self.connection.commit()

    def inicializar_datos(self):
        # 1. Añadir usuario inicial si no existe (con contraseña hasheada)
        self.cursor.execute("SELECT COUNT(*) FROM usuarios WHERE nombre=?", ("invitado",))
        if self.cursor.fetchone()[0] == 0:
            password_hash = self._hash_password("Invitado123!")
            self.cursor.execute("INSERT INTO usuarios (nombre, password) VALUES (?, ?)", ("invitado", password_hash))
            usuario_id = self.cursor.lastrowid
            # Crear perfil para invitado
            self.cursor.execute("INSERT INTO perfiles_usuario (usuario_id) VALUES (?)", (usuario_id,))
            self.connection.commit()
            print("INFO: Usuario 'invitado' (pass: Invitado123!) creado.")

        # 2. Añadir puntuación inicial (33 puntos) si no existe
        self.cursor.execute("SELECT COUNT(*) FROM puntuaciones WHERE nombre=?", ("Récord Inicial",))
        if self.cursor.fetchone()[0] == 0:
            self.cursor.execute("INSERT INTO puntuaciones (nombre, puntuacion) VALUES (?, ?)", ("Récord Inicial", 33))
            self.connection.commit()
            print("INFO: Puntuación inicial (33) creada.")

        # 4. Inicializar skins si no existen
        skins_base = [
            ("nave_clasica", "nave", "assets/nave_clasica.png", "0,128,255", 0, "Nave clásica azul"),
            ("nave_cyber", "nave", "assets/nave_cyber.png", "255,0,255", 100, "Nave cyberpunk"),
            ("nave_retro", "nave", "assets/nave_retro.png", "255,255,0", 200, "Nave retro pixel"),
            ("meteorito_clasico", "meteorito", "assets/meteorito_clasico.png", "255,50,50", 0, "Meteorito clásico rojo"),
            ("meteorito_fuego", "meteorito", "assets/meteorito_fuego.png", "255,165,0", 150, "Meteorito de fuego"),
            ("fondo_espacial", "fondo", "assets/fondo_espacial.jpg", "20,20,40", 0, "Fondo espacial clásico"),
            ("fondo_neon", "fondo", "assets/fondo_neon.jpg", "0,255,255", 250, "Fondo neon cyberpunk"),
        ]

        for nombre, tipo, ruta, color, precio, desc in skins_base:
            self.cursor.execute("SELECT COUNT(*) FROM skins WHERE nombre=?", (nombre,))
            if self.cursor.fetchone()[0] == 0:
                self.cursor.execute("INSERT INTO skins (nombre, tipo, imagen_ruta, color_fallback, precio_puntos, descripcion) VALUES (?, ?, ?, ?, ?, ?)",
                                  (nombre, tipo, ruta, color, precio, desc))

        # Asignar skins clásicas a usuarios existentes si no las tienen
        self.cursor.execute("SELECT id FROM usuarios")
        usuarios = self.cursor.fetchall()

        for (usuario_id,) in usuarios:
            # Asignar nave clásica si no tiene ninguna nave
            self.cursor.execute("""
                SELECT COUNT(*) FROM usuario_skins us
                JOIN skins s ON us.skin_id = s.id
                WHERE us.usuario_id = ? AND s.tipo = 'nave'
            """, (usuario_id,))
            if self.cursor.fetchone()[0] == 0:
                self.cursor.execute("SELECT id FROM skins WHERE nombre='nave_clasica'")
                skin_id = self.cursor.fetchone()[0]
                self.cursor.execute("INSERT INTO usuario_skins (usuario_id, skin_id, activa) VALUES (?, ?, 1)",
                                  (usuario_id, skin_id))

            # Asignar meteorito clásico si no tiene ninguno
            self.cursor.execute("""
                SELECT COUNT(*) FROM usuario_skins us
                JOIN skins s ON us.skin_id = s.id
                WHERE us.usuario_id = ? AND s.tipo = 'meteorito'
            """, (usuario_id,))
            if self.cursor.fetchone()[0] == 0:
                self.cursor.execute("SELECT id FROM skins WHERE nombre='meteorito_clasico'")
                skin_id = self.cursor.fetchone()[0]
                self.cursor.execute("INSERT INTO usuario_skins (usuario_id, skin_id, activa) VALUES (?, ?, 1)",
                                  (usuario_id, skin_id))

            # Asignar fondo clásico si no tiene ninguno
            self.cursor.execute("""
                SELECT COUNT(*) FROM usuario_skins us
                JOIN skins s ON us.skin_id = s.id
                WHERE us.usuario_id = ? AND s.tipo = 'fondo'
            """, (usuario_id,))
            if self.cursor.fetchone()[0] == 0:
                self.cursor.execute("SELECT id FROM skins WHERE nombre='fondo_espacial'")
                skin_id = self.cursor.fetchone()[0]
                self.cursor.execute("INSERT INTO usuario_skins (usuario_id, skin_id, activa) VALUES (?, ?, 1)",
                                  (usuario_id, skin_id))

        self.connection.commit()

    # --- Métodos de Autenticación (mejorados) ---

    def verificar_login(self, nombre: str, password: str) -> bool:
        """Verifica las credenciales del usuario con protección contra fuerza bruta."""
        # Verificar si el usuario está bloqueado
        if nombre in self.intentos_fallidos:
            intentos, timestamp_bloqueo = self.intentos_fallidos[nombre]
            if intentos >= INTENTOS_LOGIN_MAX:
                if datetime.now() < timestamp_bloqueo:
                    return False  # Aún bloqueado
                else:
                    # Desbloquear
                    del self.intentos_fallidos[nombre]

        query = "SELECT id, password FROM usuarios WHERE nombre=?"
        self.cursor.execute(query, (nombre,))
        resultado = self.cursor.fetchone()

        if resultado:
            usuario_id, password_hash = resultado
            password_ingresada_hash = self._hash_password(password)

            if password_hash == password_ingresada_hash:
                # Login exitoso
                if nombre in self.intentos_fallidos:
                    del self.intentos_fallidos[nombre]

                # Actualizar última sesión
                self.cursor.execute("UPDATE usuarios SET ultima_sesion=? WHERE id=?",
                                  (datetime.now().isoformat(), usuario_id))
                self.connection.commit()
                return True

        # Login fallido
        if nombre not in self.intentos_fallidos:
            self.intentos_fallidos[nombre] = [1, None]
        else:
            self.intentos_fallidos[nombre][0] += 1

        if self.intentos_fallidos[nombre][0] >= INTENTOS_LOGIN_MAX:
            self.intentos_fallidos[nombre][1] = datetime.now() + timedelta(seconds=TIEMPO_BLOQUEO_SEGUNDOS)

        return False

    def obtener_intentos_restantes(self, nombre: str) -> int:
        """Devuelve cuántos intentos le quedan al usuario"""
        if nombre not in self.intentos_fallidos:
            return INTENTOS_LOGIN_MAX

        intentos = self.intentos_fallidos[nombre][0]
        return max(0, INTENTOS_LOGIN_MAX - intentos)

    def esta_bloqueado(self, nombre: str) -> tuple[bool, int]:
        """Retorna (esta_bloqueado, segundos_restantes)"""
        if nombre not in self.intentos_fallidos:
            return False, 0

        intentos, timestamp_bloqueo = self.intentos_fallidos[nombre]
        if intentos >= INTENTOS_LOGIN_MAX and timestamp_bloqueo:
            if datetime.now() < timestamp_bloqueo:
                segundos = (timestamp_bloqueo - datetime.now()).seconds
                return True, segundos

        return False, 0

    def registrar_usuario(self, nombre: str, password: str, password_confirmar: str = None) -> tuple[bool, str]:
        """
        Intenta registrar un nuevo usuario con validaciones de seguridad.
        Devuelve (exito, mensaje_error)
        """
        # Validar que las contraseñas coincidan si se proporciona confirmación
        if password_confirmar is not None and password != password_confirmar:
            return False, "Las contraseñas no coinciden"

        # Validar seguridad de la contraseña
        es_valida, mensaje = self._validar_password_segura(password)
        if not es_valida:
            return False, mensaje

        try:
            password_hash = self._hash_password(password)
            query = "INSERT INTO usuarios (nombre, password) VALUES (?, ?)"
            self.cursor.execute(query, (nombre, password_hash))
            usuario_id = self.cursor.lastrowid

            # Crear perfil inicial
            self.cursor.execute("INSERT INTO perfiles_usuario (usuario_id) VALUES (?)", (usuario_id,))

            # Crear configuración inicial
            self.cursor.execute("INSERT INTO configuracion_usuario (usuario_id) VALUES (?)", (usuario_id,))

            self.connection.commit()
            return True, ""
        except sqlite3.IntegrityError:
            return False, "Ese nombre ya existe"

    # --- Métodos de Puntuación y Estadísticas ---

    def guardar_record(self, nombre: str, puntuacion: int, dificultad: str = "Normal", duracion: float = 0.0):
        """Guarda la puntuación y actualiza estadísticas del usuario"""
        # Obtener usuario_id
        self.cursor.execute("SELECT id FROM usuarios WHERE nombre=?", (nombre,))
        resultado = self.cursor.fetchone()
        if not resultado:
            return

        usuario_id = resultado[0]

        # Guardar en puntuaciones
        query = "INSERT INTO puntuaciones (nombre, puntuacion) VALUES (?, ?)"
        self.cursor.execute(query, (nombre, puntuacion))

        # Guardar en historial
        query_historial = "INSERT INTO historial_partidas (usuario_id, puntuacion, dificultad, duracion) VALUES (?, ?, ?, ?)"
        self.cursor.execute(query_historial, (usuario_id, puntuacion, dificultad, duracion))

        # Actualizar perfil
        self.cursor.execute("""
            UPDATE perfiles_usuario 
            SET total_partidas = total_partidas + 1,
                tiempo_total_juego = tiempo_total_juego + ?
            WHERE usuario_id = ?
        """, (duracion, usuario_id))

        # Recalcular puntuación promedio
        self.cursor.execute("""
            SELECT AVG(puntuacion) FROM historial_partidas WHERE usuario_id = ?
        """, (usuario_id,))
        promedio = self.cursor.fetchone()[0] or 0.0

        self.cursor.execute("""
            UPDATE perfiles_usuario 
            SET puntuacion_promedio = ?
            WHERE usuario_id = ?
        """, (promedio, usuario_id))

        # Verificar y otorgar logros
        self._verificar_logros(usuario_id, puntuacion)

        self.connection.commit()

    def _verificar_logros(self, usuario_id: int, puntuacion: int):
        """Verifica y otorga logros basados en la puntuación"""
        self.cursor.execute("""
            SELECT id FROM logros 
            WHERE condicion_puntos <= ?
            AND id NOT IN (SELECT logro_id FROM usuario_logros WHERE usuario_id = ?)
        """, (puntuacion, usuario_id))

        logros_nuevos = self.cursor.fetchall()
        for (logro_id,) in logros_nuevos:
            self.cursor.execute("""
                INSERT INTO usuario_logros (usuario_id, logro_id) VALUES (?, ?)
            """, (usuario_id, logro_id))

    def obtener_logros_usuario(self, nombre: str) -> list:
        """Devuelve los logros desbloqueados por el usuario"""
        self.cursor.execute("SELECT id FROM usuarios WHERE nombre=?", (nombre,))
        resultado = self.cursor.fetchone()
        if not resultado:
            return []

        usuario_id = resultado[0]

        self.cursor.execute("""
            SELECT l.nombre, l.descripcion, ul.fecha_obtencion
            FROM logros l
            JOIN usuario_logros ul ON l.id = ul.logro_id
            WHERE ul.usuario_id = ?
            ORDER BY ul.fecha_obtencion DESC
        """, (usuario_id,))

        return self.cursor.fetchall()

    def obtener_mejores_scores(self, limite=5):
        """Devuelve el top de jugadores"""
        query = "SELECT nombre, puntuacion FROM puntuaciones ORDER BY puntuacion DESC LIMIT ?"
        self.cursor.execute(query, (limite,))
        return self.cursor.fetchall()

    def obtener_estadisticas_usuario(self, nombre: str) -> dict:
        """Devuelve estadísticas completas del usuario"""
        self.cursor.execute("SELECT id FROM usuarios WHERE nombre=?", (nombre,))
        resultado = self.cursor.fetchone()
        if not resultado:
            return {}

        usuario_id = resultado[0]

        self.cursor.execute("""
            SELECT total_partidas, tiempo_total_juego, puntuacion_promedio, 
                   racha_actual, mejor_racha
            FROM perfiles_usuario
            WHERE usuario_id = ?
        """, (usuario_id,))

        resultado = self.cursor.fetchone()
        if not resultado:
            return {}

        return {
            'total_partidas': resultado[0],
            'tiempo_total': resultado[1],
            'promedio': resultado[2],
            'racha_actual': resultado[3],
            'mejor_racha': resultado[4]
        }

    def cerrar(self):
        self.connection.close()

    # --- Métodos de Skins ---

    def obtener_skins_disponibles(self):
        """Obtiene todas las skins disponibles en el juego"""
        self.cursor.execute("SELECT * FROM skins ORDER BY precio_puntos")
        return self.cursor.fetchall()

    def obtener_skins_usuario(self, usuario_id):
        """Obtiene las skins desbloqueadas por un usuario"""
        self.cursor.execute("""
            SELECT s.* FROM skins s
            JOIN usuario_skins us ON s.id = us.skin_id
            WHERE us.usuario_id = ?
        """, (usuario_id,))
        return self.cursor.fetchall()

    def desbloquear_skin(self, usuario_id, skin_id):
        """Desbloquea una skin para un usuario"""
        try:
            self.cursor.execute("INSERT INTO usuario_skins (usuario_id, skin_id) VALUES (?, ?)",
                              (usuario_id, skin_id))
            self.connection.commit()
            return True
        except sqlite3.IntegrityError:
            return False  # Ya desbloqueada

    def tiene_skin_desbloqueada(self, usuario_id, skin_id):
        """Verifica si un usuario tiene una skin desbloqueada"""
        self.cursor.execute("SELECT COUNT(*) FROM usuario_skins WHERE usuario_id=? AND skin_id=?",
                          (usuario_id, skin_id))
        return self.cursor.fetchone()[0] > 0

    def obtener_skin_activa(self, usuario_id, tipo):
        """Obtiene la skin activa de un tipo para un usuario"""
        self.cursor.execute("""
            SELECT s.* FROM skins s
            JOIN usuario_skins us ON s.id = us.skin_id
            WHERE us.usuario_id = ? AND s.tipo = ? AND us.activa = 1
        """, (usuario_id, tipo))
        return self.cursor.fetchone()

    def cambiar_skin_activa(self, usuario_id, skin_id):
        """Cambia la skin activa para un usuario (desactiva otras del mismo tipo)"""
        # Obtener tipo de la skin
        self.cursor.execute("SELECT tipo FROM skins WHERE id=?", (skin_id,))
        tipo = self.cursor.fetchone()[0]

        # Desactivar todas las skins del mismo tipo
        self.cursor.execute("""
            UPDATE usuario_skins SET activa = 0
            WHERE usuario_id = ? AND skin_id IN (
                SELECT id FROM skins WHERE tipo = ?
            )
        """, (usuario_id, tipo))

        # Activar la nueva skin
        self.cursor.execute("""
            UPDATE usuario_skins SET activa = 1
            WHERE usuario_id = ? AND skin_id = ?
        """, (usuario_id, skin_id))

        self.connection.commit()