<?php
header("Access-Control-Allow-Origin: *");
header("Content-Type: application/json; charset=UTF-8");
header("Access-Control-Allow-Methods: POST");
header("Access-Control-Max-Age: 3600");
header("Access-Control-Allow-Headers: Content-Type");

include_once '../basedatos/PokeTeams.php';
include_once '../tablas/Pokemon.php';

//A) Se crea conexión y objeto act
$database = new PokeTeams();
$conex = $database->dameConexion();
$pkm = new Pokemon($conex);

//B) Se decodifican los datos de entrada vía JSON
$datos = json_decode(file_get_contents("php://input"));

//C) Se comprueba que le pasamos las variables correctamente
if (isset($datos->N_Pokedex)) {
	$pkm->N_Pokedex = $datos->N_Pokedex;  //D) Se rellena el objeto actor con datos necesarios
	if ($pkm->borrar()) {//E)Llamamos a la base de datos
		//F) Se envía respuesta y se envían los datos codificados
		http_response_code(200);
		echo json_encode(array("info" => "Actor borrado con éxito o no está en el sistema!"));
	} else {
		http_response_code(503);
		echo json_encode(array("info" => "No se puede borrar"));
	}
} else {//G) En caso de no recibir datos, informa
	http_response_code(400);
	echo json_encode(array("info" => "No se puede borrar, datos incompletos"));
}
?>