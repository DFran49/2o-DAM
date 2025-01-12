<?php
//Se han activado los ERRORES (displayError) en el servidor!!!
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Headers: Content-Type");
header("Content-Type: application/json; charset=UTF-8");

include_once '../basedatos/Cine.php';
include_once '../tablas/Actores.php';


//A) Se crea conexión y objeto act
$database = new Cine();
$conex = $database->dameConexion();
$act = new Actores($conex);

//B) Se establecen criterios de búsqueda
//EJ: LEER POR ID
//Verificamos que se le está pasando una variable con isset:
//Si es un número buscará ese id, si lo encuentra muestra el actor, si no, informará que NO lo ha encontrado
//Si no le pasamos la variable id, o no tiene valor (?id=) devolverá -1 y lo mostrará todo
if (isset($_GET['id']))
    $act->id = $_GET['id'];
else
    $act->id = -1; //Mostrará todo

$result = $act->leer();//Mostrará o bien el id buscado o bien todo

//OTRA POSIBLE FUNCIONALIDAD: LEER VIVOS
if (isset($_GET['activo'])) {
    $act->activo = $_GET['activo'];
    $result = $act->leervivos();
}
//Tal cual está montado no contempla el uso de ambas a la vez

//C) Se leen los datos devueltos y se guardan en un array
if ($result->num_rows > 0) {
    if ($result->num_rows == 1)
        $unsoloactor = true; //Esta variable la usa la APP de JAVAFX

    $listaActores = array();  
    while ($actor = $result->fetch_assoc()) { //Crea un array asociativo con cada actor	
        extract($actor); //Exporta las variables de un array
        $datosExtraidos = array(
            "id" => $id,
            "nombre" => $nombre,
            "edad" => $edad,
            "activo" => $activo,
            "fotourl" => $fotourl,
            "fotocodif" => $fotocodif,
        );
        array_push($listaActores, $datosExtraidos); //Hace un append al final de la lista 
    }
    //D) Se envía respuesta y se envían los datos codificados
    http_response_code(200);
    if ($unsoloactor)//Con este cambio funcionará también para la API con JAVAFX 
    //donde solo queremos sacar los actores de uno en uno, no todos
        echo json_encode($datosExtraidos);
    else
        echo json_encode($listaActores);
} else { //E) En caso de no recibir datos, informa
    http_response_code(404);
    echo json_encode(
        array("info" => "No se encontraron datos")
    );
}