USE MASTER;

IF EXISTS  (
	SELECT * 
	FROM SYS.databases 
	WHERE NAME = 'Concesionario')
	
	BEGIN 
		DROP DATABASE Concesionario;

		PRINT ('BORRADA LA BASE DE DATOS ')

	END

GO
CREATE DATABASE Concesionario;

GO
USE Concesionario;
GO

-- ================= TABLAS MAESTRAS =================

CREATE TABLE Marcas (
    IdMarca INT PRIMARY KEY IDENTITY,
    Nombre NVARCHAR(50),
    Pais NVARCHAR(50)
);

CREATE TABLE Clientes (
    IdCliente INT PRIMARY KEY IDENTITY,
    Nombre NVARCHAR(100),
    Ciudad NVARCHAR(50),
    FechaRegistro DATE
);

CREATE TABLE Vendedores (
    IdVendedor INT PRIMARY KEY IDENTITY,
    Nombre NVARCHAR(100),
    Zona NVARCHAR(50),
    Comision DECIMAL(4,2) -- Ej: 0.10 es 10%
);

CREATE TABLE Extras (
    IdExtra INT PRIMARY KEY IDENTITY,
    Nombre NVARCHAR(50),
    PrecioBase DECIMAL(10,2)
);

-- ================= TABLAS PRINCIPALES =================

CREATE TABLE Coches (
    IdCoche INT PRIMARY KEY IDENTITY,
    IdMarca INT FOREIGN KEY REFERENCES Marcas(IdMarca),
    Modelo NVARCHAR(50),
    Anio INT,
    PrecioCoste DECIMAL(10,2),
    PrecioVenta DECIMAL(10,2),
    Estado NVARCHAR(20) DEFAULT 'Disponible' -- Disponible, Vendido, Taller
);

-- ================= TABLAS TRANSACCIONALES =================

CREATE TABLE Ventas (
    IdVenta INT PRIMARY KEY IDENTITY,
    IdCoche INT FOREIGN KEY REFERENCES Coches(IdCoche),
    IdCliente INT FOREIGN KEY REFERENCES Clientes(IdCliente),
    IdVendedor INT FOREIGN KEY REFERENCES Vendedores(IdVendedor),
    FechaVenta DATE,
    PrecioFinal DECIMAL(10,2) -- Puede incluir descuentos
);

CREATE TABLE Mantenimientos (
    IdMantenimiento INT PRIMARY KEY IDENTITY,
    IdCoche INT FOREIGN KEY REFERENCES Coches(IdCoche),
    Fecha DATE,
    Descripcion NVARCHAR(200),
    Costo DECIMAL(10,2)
);

-- Tabla intermedia (Muchos a Muchos)
CREATE TABLE Coche_Extras (
    IdCoche INT FOREIGN KEY REFERENCES Coches(IdCoche),
    IdExtra INT FOREIGN KEY REFERENCES Extras(IdExtra),
    PRIMARY KEY (IdCoche, IdExtra)
);
GO

-- ================= DATOS DE PRUEBA (Resumidos) =================

-- 1. Marcas y Extras
INSERT INTO Marcas VALUES ('Toyota', 'Japón'), ('BMW', 'Alemania'), ('Ford', 'USA'), ('Tesla', 'USA');
INSERT INTO Extras VALUES ('Techo Solar', 1200), ('Asientos Piel', 2500), ('GPS Avanzado', 800);

-- 2. Vendedores y Clientes
INSERT INTO Vendedores VALUES ('Carlos Ruiz', 'Norte', 0.12), ('Ana Gomez', 'Sur', 0.10), ('Luis Pi', 'Centro', 0.15);
INSERT INTO Clientes VALUES ('Empresa A', 'Madrid', '2022-01-10'), ('Juan Perez', 'Barcelona', '2023-05-20'), ('Maria Lopez', 'Valencia', '2023-11-01');

-- 3. Coches (Mezcla de vendidos y disponibles)
INSERT INTO Coches (IdMarca, Modelo, Anio, PrecioCoste, PrecioVenta, Estado) VALUES 
(1, 'Corolla', 2022, 18000, 24000, 'Vendido'),
(1, 'Yaris', 2023, 14000, 19000, 'Disponible'),
(2, 'X5', 2021, 45000, 60000, 'Vendido'),
(2, 'Serie 3', 2024, 35000, 48000, 'Disponible'),
(3, 'Mustang', 2020, 30000, 45000, 'Taller'),
(4, 'Model 3', 2023, 38000, 52000, 'Vendido');

-- 4. Asignar Extras a Coches
INSERT INTO Coche_Extras VALUES (1, 1), (1, 3), (3, 1), (3, 2), (3, 3), (6, 2);

-- 5. Ventas Históricas
INSERT INTO Ventas (IdCoche, IdCliente, IdVendedor, FechaVenta, PrecioFinal) VALUES
(1, 1, 1, '2023-02-15', 23500), -- Descuento aplicado
(3, 2, 2, '2023-06-10', 60000),
(6, 1, 3, '2024-01-05', 51000);

-- 6. Mantenimientos
INSERT INTO Mantenimientos VALUES 
(1, '2022-12-01', 'Cambio Aceite', 150),
(3, '2022-05-20', 'Revisión Frenos', 400),
(5, '2024-02-01', 'Reparación Motor', 2500);
GO

-- Ejercicio 1 
SELECT C.idCoche, 
	((V.PrecioFinal - C.PrecioCoste) - (V2.Comision) * V.PrecioFinal / 100) 
		- isnull((SELECT SUM(M.Costo) 
			FROM Mantenimientos M
			WHERE M.Fecha < v.FechaVenta AND M.IdCoche = C.IdCoche),0) AS 'RENTABILIDAD REAL NETA'
FROM COCHES C 
INNER JOIN VENTAS V ON C.IdCoche = V.IdCoche
INNER JOIN VENDEDORES V2 ON V2.IdVendedor = V.IdVendedor


SELECT Estado FROM coches where idCoche = 2






