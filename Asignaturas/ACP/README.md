# Programación (ACP) — Python

Ejercicios de **programación en Python** (asignatura de primer curso), organizados por bloques temáticos.

## Contenido

| Carpeta | Bloque |
|---|---|
| `EjerciciosCondicionales/` | Condicionales (`if`/`elif`) |
| `EjerciciosBucles/` | Bucles (`for`/`while`) |
| `EjerciciosCadenas/` | Manejo de cadenas |
| `EjerciciosListas/` | Listas |
| `EjerciciosDiccionarios/` | Diccionarios |
| `EjerciciosClases/` | Programación orientada a objetos (Concesionario, Agenda, Personas, Tarjeta) |
| `EjercicioFunciones/` | Funciones |
| `hola_mundo.py` | Primer programa |

## `Juego/` — Space Dodge: Ultimate Edition

Videojuego de naves hecho con **pygame** y persistencia en **SQLite**:

- 5 power-ups (escudo, slow motion, puntos dobles, multivida, reducir tamaño).
- Sistema de niveles con dificultad progresiva.
- Autenticación con hash SHA-256, límite de intentos y bloqueo temporal.
- Estadísticas y 7 tablas en base de datos (usuarios, perfiles, historial, logros…).
- Sistema de partículas, HUD y pantalla de perfil.

Detalle completo en [`Juego/MEJORAS_IMPLEMENTADAS.md`](Juego/MEJORAS_IMPLEMENTADAS.md).

### Ejecutar

```bash
pip install pygame
python Juego/main.py
```

Usuario por defecto: `invitado` / `Invitado123!`.

## Requisitos

- Python 3.8+.
- `pygame` (solo para el juego).
