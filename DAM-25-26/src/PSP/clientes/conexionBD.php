<?php
class Conexion extends PDO {
    private const SERVIDOR_BD = 'localhost';
    private const USUARIO_BD = 'root';
    private const PASSWORD_BD = '';
    private const BD='pmul';
    private const DSN="mysql:host=".self::SERVIDOR_BD.";dbname=".self::BD;

    public function __construct() {
       parent::__construct(self::DSN,self::USUARIO_BD);
    }
 //aquí los métodos para el tratamiento de los datos
}

try {
    $con=new Conexion();
}
catch (PDOException $ex) {
    exit ("Problemas: {$ex->getMessage()}");
}
