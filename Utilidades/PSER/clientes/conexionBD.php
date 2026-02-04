<?php
class Conexion extends PDO
{
    private const SERVIDOR_BD = 'localhost';
    private const USUARIO_BD = 'root';
    private const PASSWORD_BD = 'abc123.';
    private const BD = 'pmul';
    private const DSN = "mysql:host=" . self::SERVIDOR_BD . ";dbname=" . self::BD;
    public function __construct()
    {
        parent::__construct(self::DSN, self::USUARIO_BD);
    }

    function getClientes()
    {
        $sql = 'select * from clientes';
        $stmt = $this->query($sql);
        return $stmt->fetchAll();
    }

    function getCliente($codCliente)
    {
        $sql = 'select * from clientes where codCliente= ?';
        $stmt = $this->prepare($sql);
        $stmt->execute([$codCliente]);
        return $stmt->fetchObject();
    }

    function insertarCliente($nombre, $codProvincia, $vip){
        $sql = "INSERT INTO clientes (nombre, codProvincia, vip) VALUES (?,?,?)";
        $stmt = $this-> prepare($sql);
        $stmt -> execute([$nombre, $codProvincia, $vip]);
        
    }
}
try {
    $con = new Conexion();
} catch (PDOException $ex) {
    exit("Problemas: {$ex->getMessage()}");
}
