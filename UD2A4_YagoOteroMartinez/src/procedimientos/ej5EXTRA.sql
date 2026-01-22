CREATE OR ALTER PROCEDURE sp_empleados_por_proyecto
    @numProxecto INT
    AS
BEGIN
SELECT
    e.NSS,
    e.Nome + ' ' + e.Apelido1 AS NomeCompleto,
    p.Lugar,
    p.NumDepartControla
FROM EMPREGADO e
         JOIN EMPREGADO_PROXECTO ep ON e.NSS = ep.NSSEmpregado
         JOIN PROXECTO p ON ep.NumProxecto = p.NumProxecto
WHERE p.NumProxecto = @numProxecto;
END;
GO
