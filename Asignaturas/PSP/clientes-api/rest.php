<?php
require 'conexionBD.php';

$verbo=$_SERVER['REQUEST_METHOD'];
$pathInfo=isset($_SERVER['PATH_INFO'])?trim($_SERVER['PATH_INFO'],'/'):'';
$rutas=$pathInfo==''?[]:explode('/',$pathInfo);


if($verbo=='GET')
    if($rutas[0]=='clientes') {
        $sql="select * from clientes";
        $stmt=$con->query($sql);
        $datos=$stmt->fetchAll(PDO::FETCH_OBJ);
        echo json_encode($datos);
        

    }
        

