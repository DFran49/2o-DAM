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
$db = $database->dameConexion();
$act = new Actores($db);

//B) Se decodifican los datos de entrada vía JSON
$datos = json_decode(file_get_contents("php://input"));

//DEBUG:, saldrá antes de la respuesta JSON, pero habrá que quitarlo para evitar errores!!
/*echo "Datos de depuración:";
echo $datos->id;
echo $datos->nombre;
echo $datos->edad;
echo $datos->activo;*/


//C) Se comprueba que le pasamos las variables correctamente
if( isset($datos->id) && isset($datos->nombre) && isset($datos->edad) && isset($datos->activo) && isset($datos->fotourl) && isset($datos->fotocodif)){    

	 //D) Se rellena el objeto actor con datos 
	$act->id = $datos->id;
	$act->nombre = $datos->nombre;
	$act->edad = $datos->edad;
	$act->activo = $datos->activo;
	$act->fotourl = $datos->fotourl;
    $act->fotocodif = $datos->fotocodif;


	if ($act->actualizar()) {//E)Llamamos a la base de datos
		//F) Se envía respuesta y se envían los datos codificados
		http_response_code(200);
		echo json_encode(array("info" => "Actor/Actriz actualizado"));
	} else {
		http_response_code(503);
		echo json_encode(array("info" => "No se ha podido actualizar"));
	}

} else {//G) En caso de no recibir datos, informa
	http_response_code(400);
	echo json_encode(array("info" => "No se ha podido actualizar. Datos incompletos."));
}
?>