# DAM 2025-2026 · Yago Otero Martínez

Repositorio de trabajo del **2.º curso de DAM** (Desarrollo de Aplicaciones Multiplataforma) — curso 2025/2026.

Recoge los ejercicios, prácticas, proyectos y exámenes de cada asignatura, organizados por módulo y por tema, junto con las bases de datos utilizadas (listas para levantar con Docker) y los apuntes del curso.

---

## Índice

- [Estructura del repositorio](#estructura-del-repositorio)
- [Asignaturas](#asignaturas)
- [Bases de datos y Docker](#bases-de-datos-y-docker)
- [Cómo ejecutar el código](#cómo-ejecutar-el-código)
- [Esquemas y diagramas](#esquemas-y-diagramas)
- [Apuntes](#apuntes)

---

## Estructura del repositorio

```
DAM-25-26-YOM/
├── Asignaturas/          # Código fuente por asignatura
│   ├── ACP/              #   Programación (Python)
│   ├── AD/               #   Acceso a Datos (JDBC, XML, Hibernate)
│   ├── PSP/              #   Programación de Servicios y Procesos
│   ├── DINT/             #   Desarrollo de Interfaces
│   ├── PMUL/             #   Programación Multimedia (libGDX)
│   ├── SXE/              #   Sistemas de Gestión Empresarial (Odoo)
│   └── DASP/             #   Despliegue de Aplicaciones Web
├── bd/                   # Scripts SQL de las bases de datos
├── docs/
│   ├── esquemas.md       # Diagramas (estructura + modelo entidad-relación)
│   └── apuntes/          # Apuntes y entregas (.odt / .pdf)
├── docker/               # Configuración Docker para las BBDD
├── docker-compose.yml    # Levanta MariaDB + SQL Server
└── .gitignore
```

---

## Asignaturas

| Carpeta | Asignatura | Lenguaje / tecnología |
|---|---|---|
| [`Asignaturas/ACP`](Asignaturas/ACP/) | Programación | Python (pygame, SQLite) |
| [`Asignaturas/AD`](Asignaturas/AD/) | Acceso a Datos | Java (JDBC, XML, Hibernate) |
| [`Asignaturas/PSP`](Asignaturas/PSP/) | Prog. de Servicios y Procesos | Java (hilos, sockets, REST) |
| [`Asignaturas/DINT`](Asignaturas/DINT/) | Desarrollo de Interfaces | Java Swing, JasperReports |
| [`Asignaturas/PMUL`](Asignaturas/PMUL/) | Programación Multimedia | Java (libGDX) |
| [`Asignaturas/SXE`](Asignaturas/SXE/) | Sistemas de Gestión Empresarial | Odoo 17 (Python) |
| [`Asignaturas/DASP`](Asignaturas/DASP/) | Despliegue de Aplicaciones Web | Documentación |

Cada asignatura tiene su propio `README.md` con el detalle de sus ejercicios.

---

## Bases de datos y Docker

El repositorio incluye los scripts SQL de dos bases de datos:

| Script | Base de datos | Motor |
|---|---|---|
| [`bd/empresa-sqlserver.sql`](bd/empresa-sqlserver.sql) | `BDEMPRESA25` | SQL Server (T-SQL) |
| [`bd/empresa-mysql.sql`](bd/empresa-mysql.sql) | `BDEMPRESA25` | MySQL / MariaDB |
| [`bd/empresa-sqlite.sql`](bd/empresa-sqlite.sql) | `BDEMPRESA25` | SQLite |
| [`bd/empresa-insertar-datos.sql`](bd/empresa-insertar-datos.sql) | `BDEMPRESA25` | Datos de ejemplo |
| [`bd/clientes.sql`](bd/clientes.sql) | `clientes` | MariaDB |

Para levantar las bases de datos en local con un solo comando:

```bash
docker compose up -d
```

Esto arranca **MariaDB** (con `clientes` y `BDEMPRESA25` cargadas automáticamente) y **SQL Server 2019**. Las credenciales se configuran en [`docker/.env.example`](docker/.env.example) (cópialo a `docker/.env`).

> Detalle completo en [`bd/README.md`](bd/README.md).

---

## Cómo ejecutar el código

### Java (AD, PSP, DINT, PMUL)

Los proyectos Java son proyectos de **NetBeans** (`nbproject/`) o **Eclipse** (`.classpath`/`.project`). Se abren directamente con el IDE correspondiente. Las dependencias están en la carpeta `lib/` de cada proyecto.

- **AD**: requiere los drivers JDBC (SQL Server, MySQL, SQLite) y las librerías de Hibernate — ya incluidas en cada `lib/`.
- **PSP**: ejercicios de consola; se ejecutan con `java` directamente o desde el IDE.
- **DINT**: proyectos Swing con JasperReports (los `.jrxml` se compilan en el IDE).
- **PMUL**: proyecto libGDX; ábrelo con el plugin de libGDX.

### Python (ACP)

```bash
# Ejercicios sueltos
python Asignaturas/ACP/EjerciciosBucles/Ejercicio1.py

# Juego "Space Dodge" (requiere pygame)
pip install pygame
python Asignaturas/ACP/Juego/main.py
```

### Odoo (SXE)

El módulo `Colegio` se instala en una instancia Odoo 17 copiándolo a la carpeta `addons`.

---

## Esquemas y diagramas

Los diagramas de estructura del repositorio y los **modelos entidad-relación** de las bases de datos están en [`docs/esquemas.md`](docs/esquemas.md) (diagramas Mermaid, se renderizan directamente en GitHub).

---

## Apuntes

Los apuntes y entregas del curso (`.odt` y `.pdf`) están en [`docs/apuntes/`](docs/apuntes/), con su índice en [`docs/apuntes/README.md`](docs/apuntes/README.md).

---

## Autor

**Yago Otero Martínez** — DAM 2025/2026.
