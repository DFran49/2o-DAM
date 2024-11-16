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

//DEBUG: saldrá antes de la respuesta JSON, pero habrá que quitarlo para evitar errores!!
/*echo "Datos de depuración:";
echo $datos->nombre;
echo $datos->edad;
echo $datos->activo;*/

//C) Se comprueba que le pasamos las variables correctamente
if (isset($datos->N_Pokedex) && isset($datos->Especie) && isset($datos->Denominacion) && isset($datos->Descripcion) && isset($datos->Sprite) && isset($datos->Tipo_1) && 
    isset($datos->Tipo_2) && isset($datos->Tamaño) && isset($datos->Peso) && isset($datos->Habilidades) && isset($datos->Estadisticas)) {

    //D) Se rellena el objeto actor con datos salvo el id
    $pkm->N_Pokedex = $datos->N_Pokedex;
    $pkm->Especie = $datos->Especie;
    $pkm->Denominacion = $datos->Denominacion;
    $pkm->Descripcion = $datos->Descripcion;
    $pkm->Sprite = $datos->Sprite;
    $pkm->Tipo_1 = $datos->Tipo_1;
    $pkm->Tipo_2 = $datos->Tipo_2;
    $pkm->Tamaño = $datos->Tamaño;
    $pkm->Peso = $datos->Peso;
    $pkm->Habilidades = $datos->Habilidades;
    $pkm->Estadisticas = $datos->Estadisticas;
    
   //DEBUG: echo "Nombres es.".$pkm->Especie;

    //E) Llamamos a la base de datos
    if ($pkm->insertar()) {
        //F) Se envía respuesta y se envían los datos codificados
        http_response_code(201);
        echo json_encode(array("info" => "Pokemon Creado!"));
    } else {
        http_response_code(503);
        echo json_encode(array("info" => "No se puede crear"));
    }
} else { //D) En caso de no recibir datos, informa
    http_response_code(400);
    echo json_encode(array("info" => "No se puede crear, falta algo!"));
    echo $pkm->Especie;
}

?>