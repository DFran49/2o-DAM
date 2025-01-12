<?php
header("Access-Control-Allow-Origin: *");
header("Content-Type: application/json; charset=UTF-8");
header("Access-Control-Allow-Methods: POST");
header("Access-Control-Max-Age: 3600");
header("Access-Control-Allow-Headers: Content-Type");

include_once '../basedatos/Cine.php';
include_once '../tablas/Actores.php';

//A) Se crea conexión y objeto act
$database = new Cine();
$conex = $database->dameConexion();
$act = new Actores($conex);

//B) Se decodifican los datos de entrada vía JSON
$datos = json_decode(file_get_contents("php://input"));

//DEBUG: saldrá antes de la respuesta JSON, pero habrá que quitarlo para evitar errores!!
/*echo "Datos de depuración:";
echo $datos->nombre;
echo $datos->edad;
echo $datos->activo;
echo $datos->fotocodif;*/

//C) Se comprueba que le pasamos las variables correctamente
if (isset($datos->nombre) && isset($datos->edad) && isset($datos->activo) && isset($datos->fotourl) && isset($datos->fotocodif)) {

    //D) Se rellena el objeto actor con datos salvo el id
    $act->nombre = $datos->nombre;
    $act->edad = $datos->edad;
    $act->activo = $datos->activo;
    $act->fotourl = $datos->fotourl;
    $act->fotocodif = $datos->fotocodif;
    
   //DEBUG: echo "Nombres es.".$act->nombre;

    //E) Llamamos a la base de datos
    if ($act->insertar()) {
        //F) Se envía respuesta y se envían los datos codificados
        http_response_code(201);
        echo json_encode(array("info" => "Actor/Actriz Creado!"));
    } else {
        http_response_code(503);
        echo json_encode(array("info" => "No se puede crear"));
    }
} else { //D) En caso de no recibir datos, informa
    http_response_code(400);
    echo json_encode(array("info" => "No se puede crear, falta algo!"));
}

?>