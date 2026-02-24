<?php
require 'conexionBD.php';

$verbo=$_SERVER['REQUEST_METHOD'];
$pathInfo=isset($_SERVER['PATH_INFO'])?trim($_SERVER['PATH_INFO'],'/'):'';
$rutas=$pathInfo==''?[]:explode('/',$pathInfo);

$items=count($rutas);
if($items==0) {
    http_response_code(404);
    exit;
}
if($verbo=='GET')
    if($rutas[0]=='clientes') {
        switch($items) {
            case 1:
                $sql="select * from clientes";
                $stmt=$con->query($sql);
                $datos=$stmt->fetchAll(PDO::FETCH_OBJ);
                echo json_encode($datos);
                break;
            case 2:
                $codCliente=$rutas[1];
                if(!preg_match('/^\d+$/',$codCliente)) {

                    exit;
                }
                $sql='select * from clientes where codCliente=?';
                $stmt=$con->prepare($sql);
                $stmt->execute([$codCliente]);
                $cliente=$stmt->fetchObject();
                if($cliente)
                    echo json_encode($cliente);
                else
                    http_response_code(404);
                break;
        }
    }
        

