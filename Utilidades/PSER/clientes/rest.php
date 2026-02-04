<?php
require 'conexionBD.php';

$verbo = $_SERVER['REQUEST_METHOD'];
$pathInfo = isset($_SERVER['PATH_INFO']) ? trim($_SERVER['PATH_INFO'], '/') : '';
$rutas = $pathInfo == '' ? [] : explode('/', $pathInfo);

$items = count($rutas);

if ($items == 0) {
    http_response_code(404);
    exit;
}

if ($verbo == 'GET') {
    if ($rutas[0] == 'clientes') {
        switch ($items) {
            case 1:
                $clientes = $con->getClientes();
                echo json_encode($clientes);
                break;
            case 2:
                $codCliente = $rutas[1];
                if (!preg_match('/\d+/', $codCliente)) exit;

                $cliente = $con->getCliente($codCliente);

                if ($cliente) {
                    echo json_encode($cliente);
                } else {
                    http_response_code(404);
                }

                break;
            default:
                # code...
                break;
        }
    }
} else if ($verbo == 'POST') {
    if ($rutas[0] == 'clientes') {
        $stringDatosCabecera = file_get_contents('php://input', true);
        parse_str($stringDatosCabecera, $datos);
        $nombre = $datos['nombre'];
        $codProvicncia = $datos['codProvincia'];
        $vip = $datos['vip'];
        //TODO Validar datos

        $con->insertarCliente($nombre, $codProvicncia, $vip);

        if ($con) {
            http_response_code(201);
        } else {
            http_response_code(422);
        }
    }
}
