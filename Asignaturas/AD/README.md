# Acceso a Datos (AD)

Trabajos de la asignatura de **Acceso a Datos**: acceso a bases de datos relacionales (JDBC), procesamiento de ficheros XML y persistencia con Hibernate (mapeo XML y anotaciones).

## Contenido

### `Tema1/` — XML: JAXB, DOM, SAX y STAX
Ejercicios de lectura/escritura de ficheros XML sobre un dominio de **corredores y equipos**:

| Carpeta | Técnica |
|---|---|
| `Actividad1` | Lectura con DOM |
| `Actividad2` / `Actividad2B` | Escritura con DOM |
| `Actividad3` | SAX |
| `Actividad4` | STAX |
| `Actividad5` | JAXB (corredores/equipos) |
| `Actividad6` | JAXB (corredores) |
| `Actividad7` | Persistencia + adaptador |
| `Actividad8` | Persistencia |
| `PersistenciaSTAX` | STAX con cursor y eventos |

### `Tema1Resumen/` — Resumen del tema 1
- `ArchivosRandom/` — acceso aleatorio a ficheros
- `DOM/` — Document Object Model
- `STAX/` — Streaming API for XML
- `Clases/` — clases de dominio

### `archivos-xml/` — Datos de ejemplo
Ficheros XML, DTD y XSD (`Equipos`, `Corredores`, `Registro`) usados como entrada por los ejercicios.

### `RealMadridArchivos/` — Lectura de ficheros
Proyecto que combina `gestorSAX`, `gestorSTAX`, `gestorDatRandom` y `gestorDatSecuencial` con manejo de errores.

### `BDEN26/` — JDBC
Acceso a base de datos con **JDBC** sobre un dominio de exposiciones de fotografía (`Exposicion`, `Fotografo`, `Fotografia`). Usa drivers de SQL Server, MySQL y SQLite (incluidos en `lib/`). Incluye `Procedures y funciones.sql`.

### `SQL-DAM-25-26/` — Trabajo de SQL
Proyecto de consultas SQL con su carpeta `Actividades/`.

### `Tema-Hibernate/` — Persistencia con Hibernate
Último tema: mapeo objeto-relacional con Hibernate sobre la base de datos `EMPRESAHB26`:

| Proyecto | Enfoque |
|---|---|
| `ALUHBEXFEB26` | Pastelería — mapeo XML completo |
| `EMPRESAHB26ALU_XML_YAGO` | Empresa — mapeo XML |
| `EMPRESAHB26ALU_ANOT_YAGO` | Empresa — anotaciones JPA |
| `EMPRESA_XML_YAGO` | Empresa — mapeo XML + DTOs y consultas |
| `solucion-dto` | Solución de referencia con DTOs |

## Requisitos

- JDK 8+ (proyectos NetBeans/Eclipse).
- Drivers JDBC y librerías Hibernate: ya incluidos en `lib/` de cada proyecto.
- Base de datos `EMPRESAHB26` / `BDEMPRESA25`: scripts en [`../../bd/`](../../bd/), levantables con Docker.
