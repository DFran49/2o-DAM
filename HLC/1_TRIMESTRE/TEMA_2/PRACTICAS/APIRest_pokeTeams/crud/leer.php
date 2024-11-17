<?php
//Se han activado los ERRORES (displayError) en el servidor!!!
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Headers: Content-Type");
header("Content-Type: application/json; charset=UTF-8");

include_once '../basedatos/PokeTeams.php';
include_once '../tablas/Pokemon.php';


//A) Se crea conexión y objeto act
$database = new PokeTeams();
$conex = $database->dameConexion();
$pkm = new Pokemon($conex);

//B) Se establecen criterios de búsqueda
if (isset($_GET['N_Pokedex']))
    $pkm->N_Pokedex = $_GET['N_Pokedex'];
else
    $pkm->N_Pokedex = -1; //Mostrará todo

$result = $pkm->leer();

if (isset($_GET['Especie'])) {
    $pkm->Especie = $_GET['Especie'];
    $result = $pkm->leerSegunEspecie();
}

if (isset($_GET['Denominacion'])) {
    $pkm->Denominacion = $_GET['Denominacion'];
    $result = $pkm->leerSegunDenominacion();
}

//OTRA POSIBLE FUNCIONALIDAD: LEER VIVOS
if (isset($_GET['Tipo_1'])) {
    $pkm->Tipo_1 = $_GET['Tipo_1'];
    $result = $pkm->leerSegunTipo1();
}

if (isset($_GET['Tipo_2'])) {
    $pkm->Tipo_2 = $_GET['Tipo_2'];
    $result = $pkm->leerSegunTipo2();
}
//Tal cual está montado no contempla el uso de ambas a la vez

//C) Se leen los datos devueltos y se guardan en un array
if ($result->num_rows > 0) {
    if ($result->num_rows == 1)
        $unsolopokemon = true; //Esta variable la usa la APP de JAVAFX

    $listaPokemon = array();  
    while ($pokemon = $result->fetch_assoc()) { //Crea un array asociativo con cada pokemon	
        extract($pokemon); //Exporta las variables de un array
        $datosExtraidos = array(
            "N_Pokedex" => $N_Pokedex,
            "Especie" => $Especie,
            "Denominacion" => $Denominacion,
            "Tipo_1" => $Tipo_1,
            "Tipo_2" => $Tipo_2,
            "Tamaño" => $Tamaño,
            "Peso" => $Peso,
            "Habilidades" => $Habilidades,
            "Estadisticas" => $Estadisticas,
        );
        array_push($listaPokemon, $datosExtraidos); //Hace un append al final de la lista 
    }
    //D) Se envía respuesta y se envían los datos codificados
    http_response_code(200);
    if ($unsolopokemon)//Con este cambio funcionará también para la API con JAVAFX 
    //donde solo queremos sacar los actores de uno en uno, no todos
        echo json_encode($datosExtraidos);
    else
        echo json_encode($listaPokemon);
} else { //E) En caso de no recibir datos, informa
    http_response_code(404);
    echo json_encode(
        array("info" => "No se encontraron datos")
    );
}