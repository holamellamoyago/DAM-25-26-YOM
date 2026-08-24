# Bases de datos (bd/)

Scripts SQL de las bases de datos usadas en el curso.

| Archivo | Base de datos | Motor | Descripción |
|---|---|---|---|
| `empresa-sqlserver.sql` | `BDEMPRESA25` | SQL Server (T-SQL) | Creación + datos |
| `empresa-mysql.sql` | `BDEMPRESA25` | MySQL / MariaDB | Creación + datos |
| `empresa-sqlite.sql` | `BDEMPRESA25` | SQLite | Creación + datos |
| `empresa-insertar-datos.sql` | `BDEMPRESA25` | (cualquiera) | Inserts de datos de ejemplo |
| `clientes.sql` | `clientes` | MariaDB | Tablas `clientes` y `provincias` + datos |

## Modelo de datos

Los diagramas entidad-relación están en [`../docs/esquemas.md`](../docs/esquemas.md).

## Levantar con Docker

```bash
docker compose up -d
```

Esto arranca:

- **MariaDB** (puerto `3306`): carga automáticamente `clientes` y `BDEMPRESA25` (versión MySQL).
- **SQL Server 2019** (puerto `1433`): para la versión T-SQL de `BDEMPRESA25`.

### Cargar los scripts manualmente

```bash
# MariaDB / MySQL
mysql -h 127.0.0.1 -u root -p < empresa-mysql.sql
mysql -h 127.0.0.1 -u root -p < clientes.sql

# SQLite
sqlite3 empresa.db < empresa-sqlite.sql

# SQL Server (dentro del contenedor o con sqlcmd)
sqlcmd -S localhost -U sa -P 'YagoDAM2026!' -i empresa-sqlserver.sql
```

> **Nota**: los ficheros `.sql` de `BDEMPRESA25` están en gallego y usan `DROP DATABASE IF EXISTS` / `CREATE DATABASE`, por lo que son idempotentes (se pueden re-ejecutar).
