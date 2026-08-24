# Esquemas y diagramas

Diagramas del repositorio y de las bases de datos, en formato **Mermaid** (se renderizan automáticamente en GitHub).

---

## 1. Estructura del repositorio

```mermaid
flowchart TD
    ROOT[DAM-25-26-YOM] --> ASIG[Asignaturas]
    ROOT --> BD[bd/ · scripts SQL]
    ROOT --> DOCS[docs/]
    ROOT --> DC[docker-compose.yml]

    ASIG --> ACP[ACP · Python]
    ASIG --> AD[AD · Acceso a Datos]
    ASIG --> PSP[PSP · Servicios y Procesos]
    ASIG --> DINT[DINT · Interfaces]
    ASIG --> PMUL[PMUL · Multimedia]
    ASIG --> SXE[SXE · Odoo]
    ASIG --> DASP[DASP · Despliegue]

    AD --> AD1[Tema1 · XML/JAXB]
    AD --> AD2[Tema1Resumen]
    AD --> AD3[BDEN26 · JDBC]
    AD --> AD4[SQL-DAM-25-26]
    AD --> AD5[Tema-Hibernate]

    PSP --> PSP1[Tema1 · hilos]
    PSP --> PSP2[Tema2 · sockets]
    PSP --> PSP3[Tema3 · stock]
    PSP --> PSP4[clientes y REST]

    DOCS --> DOCS1[esquemas.md]
    DOCS --> DOCS2[apuntes/ · .odt .pdf]
```

---

## 2. Modelo entidad-relación · `BDEMPRESA25` (empresa)

Base de datos de una empresa (dominio en gallego), usada en **Acceso a Datos**. Modelo simplificado:

```mermaid
erDiagram
    DEPARTAMENTO ||--o{ EMPREGADO : "ten"
    FUNCION       ||--o{ EMPREGADO : "desempeña"
    EMPREGADO     ||--o{ TELEFONO  : "posúe"
    EMPREGADO     ||--o{ FAMILIAR  : "ten"
    EMPREGADO     ||--o{ VEHICULO  : "conduce"
    LUGAR         ||--o{ PROXECTO  : "localiza"
    EMPREGADO     ||--o{ EMPREGADO_PROXECTO : "traballa"
    PROXECTO      ||--o{ EMPREGADO_PROXECTO : "asignado"
    EMPREGADO     }o--o{ HABILIDAD : "posúe"

    EMPREGADO {
        string nss PK
        string nome
        string apelidos
        date   dataNacemento
        int    numDepartamento FK
        int    numFuncion FK
    }
    EMPREGADOFIXO {
        string nss PK, FK
        float  salario
    }
    EMPREGADOTEMPORAL {
        string nss PK, FK
        date   dataFin
    }
    DEPARTAMENTO {
        int    numDepartamento PK
        string nome
    }
    FUNCION {
        int    numFuncion PK
        string nome
    }
    PROXECTO {
        int    numProxecto PK
        string nome
        int    numLugar FK
    }
    LUGAR {
        int    numLugar PK
        string nome
    }
    HABILIDAD {
        int    numHabilidad PK
        string descricion
    }
    TELEFONO {
        string numero PK
        string nss FK
    }
    FAMILIAR {
        string nss PK
        string nome
        string parentesco
    }
    VEHICULO {
        string matricula PK
        string nss FK
        string modelo
    }
```

> **Nota**: `EMPREGADOFIXO` y `EMPREGADOTEMPORAL` son subtipos de `EMPREGADO` (herencia). El modelo exacto puede variar según la versión del script (`bd/empresa-*.sql`). Los POJOs de Hibernate (`Asignaturas/AD/Tema-Hibernate/`) reflejan las mismas entidades.

---

## 3. Modelo entidad-relación · `clientes`

Base de datos usada en **PSP** (API REST de clientes):

```mermaid
erDiagram
    PROVINCIAS ||--o{ CLIENTES : "contén"

    CLIENTES {
        int    codCliente PK
        string nombre
        int    codProvincia FK
        bool   vip
    }
    PROVINCIAS {
        int    codProvincia PK
        string nombre
    }
```

Datos de ejemplo incluidos en [`bd/clientes.sql`](../bd/clientes.sql): 2 clientes y 4 provincias (A Coruña, Lugo, Ourense, Pontevedra).
