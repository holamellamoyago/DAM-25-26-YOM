import sqlite3
from settings import DATABASE_NAME


class DBManager:
    """Clase encargada de la persistencia de datos (Patrón Manager/DAO)"""

    def __init__(self):
        self.connection = sqlite3.connect(DATABASE_NAME)
        self.cursor = self.connection.cursor()
        self.crear_tablas()
        self.inicializar_datos()

    def crear_tablas(self):
        # Tabla de Usuarios (para el Login/Registro)
        query_users = """
                      CREATE TABLE IF NOT EXISTS usuarios \
                      ( \
                          id \
                          INTEGER \
                          PRIMARY \
                          KEY \
                          AUTOINCREMENT, \
                          nombre \
                          TEXT \
                          UNIQUE \
                          NOT \
                          NULL, \
                          password \
                          TEXT \
                          NOT \
                          NULL
                      ) \
                      """
        self.cursor.execute(query_users)

        # Tabla de Puntuaciones
        query_scores = """
                       CREATE TABLE IF NOT EXISTS puntuaciones \
                       ( \
                           id \
                           INTEGER \
                           PRIMARY \
                           KEY \
                           AUTOINCREMENT, \
                           nombre \
                           TEXT \
                           NOT \
                           NULL, \
                           puntuacion \
                           INTEGER \
                           NOT \
                           NULL
                       ) \
                       """
        self.cursor.execute(query_scores)
        self.connection.commit()

    def inicializar_datos(self):
        # 1. Añadir usuario inicial si no existe
        self.cursor.execute("SELECT COUNT(*) FROM usuarios WHERE nombre=?", ("invitado",))
        if self.cursor.fetchone()[0] == 0:
            self.cursor.execute("INSERT INTO usuarios (nombre, password) VALUES (?, ?)", ("invitado", "1234"))
            self.connection.commit()
            print("INFO: Usuario 'invitado' (pass: 1234) creado.")

        # 2. Añadir puntuación inicial (33 puntos) si no existe
        self.cursor.execute("SELECT COUNT(*) FROM puntuaciones WHERE nombre=?", ("Récord Inicial",))
        if self.cursor.fetchone()[0] == 0:
            self.cursor.execute("INSERT INTO puntuaciones (nombre, puntuacion) VALUES (?, ?)", ("Récord Inicial", 33))
            self.connection.commit()
            print("INFO: Puntuación inicial (33) creada.")

    # --- Métodos de Autenticación ---

    def verificar_login(self, nombre: str, password: str) -> bool:
        """Verifica las credenciales del usuario."""
        query = "SELECT password FROM usuarios WHERE nombre=?"
        self.cursor.execute(query, (nombre,))
        resultado = self.cursor.fetchone()

        if resultado and resultado[0] == password:
            return True
        return False

    def registrar_usuario(self, nombre: str, password: str) -> bool:
        """
        Intenta registrar un nuevo usuario.
        Devuelve True si tiene éxito, False si el nombre ya existe.
        """
        try:
            query = "INSERT INTO usuarios (nombre, password) VALUES (?, ?)"
            self.cursor.execute(query, (nombre, password))
            self.connection.commit()
            return True
        except sqlite3.IntegrityError:
            # El error IntegrityError de SQLite ocurre si el nombre UNIQUE ya existe
            return False

    # --- Métodos de Puntuación (Mantenidos) ---

    def guardar_record(self, nombre: str, puntuacion: int):
        query = "INSERT INTO puntuaciones (nombre, puntuacion) VALUES (?, ?)"
        self.cursor.execute(query, (nombre, puntuacion))
        self.connection.commit()

    def obtener_mejores_scores(self, limite=5):
        """Devuelve el top 5 de jugadores"""
        query = "SELECT nombre, puntuacion FROM puntuaciones ORDER BY puntuacion DESC LIMIT ?"
        self.cursor.execute(query, (limite,))
        return self.cursor.fetchall()

    def cerrar(self):
        self.connection.close()