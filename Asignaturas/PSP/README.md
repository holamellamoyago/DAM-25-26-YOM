# Programación de Servicios y Procesos (PSP)

Trabajos de **Programación de Servicios y Procesos**: programación concurrente (hilos), comunicación en red (sockets) y servicios REST.

## Contenido

### `Tema1/` — Programación concurrente (hilos)
Simulaciones de concurrencia con `Thread`/`Runnable` y sincronización:

| Ejercicio | Descripción |
|---|---|
| `Aparcamiento` | Plazas de parking compartidas |
| `concesionario` | Concesionario con clientes y coches |
| `futbol` | Estadio con aficionados y puertas |
| `oficina` | Oficina con empleados y jefe |
| `Instituto` | Simulación de instituto |

### `Tema2/` — Sockets y comunicación en red
- `ComprobarEstadoUsuarios` — servidor que comprueba el estado de usuarios
- `TiendaRopa` / `TiendaRopaRegular` — servidor de tienda de ropa (con y sin hilos)
- `Menu` — cliente con menú
- `Ejercicio1` — cliente básico

### `Tema3/` + `STOCK_PSP/` — Servicio de stock
Servidor de gestión de stock (`PrendaRopa`, `Tallas`, `Usuario`) con cliente y almacenamiento. `STOCK_PSP` es la versión como proyecto NetBeans.

### Clientes y servicios REST
| Carpeta | Descripción |
|---|---|
| `clientes-api` | API REST en PHP (`rest.php` + `conexionBD.php`) |
| `cliente-rest` | Cliente REST (VS Code) con `Get`/`Post`/`Put`/`Patch`/`Delete` |
| `rest-clientes` | Proyectos NetBeans `RestGET`, `RestPOST`, `RestDELETE` |
| `examen-rest` | Examen REST (modelo `Habitacion`/`Reserva`) |

### `ExamenPSP/` — Examen
Examen de hilos (`Cine`/`Cinefilo`/`Pelicula`/`Sala` y `Pintor`/`Fotografo`/`Casa`/`Tabique`).

## Requisitos

- JDK 8+ (NetBeans/Eclipse/VS Code).
- La API `clientes-api` requiere PHP + MySQL (base de datos `clientes`, ver [`../../bd/clientes.sql`](../../bd/clientes.sql)).
