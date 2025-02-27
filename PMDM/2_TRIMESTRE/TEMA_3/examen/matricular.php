<?php 

header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Headers: Content-Type");
header("Content-Type: application/json; charset=UTF-8");

include_once 'comun.php';

if($_SERVER["REQUEST_METHOD"]=="POST"){
	$datos = json_decode(file_get_contents("php://input"));
	if(
		isset($datos->nombre) &&
		isset($datos->edad) &&
		isset($datos->cod_curso)
	){
		mandarBien(0,"Se han enviado correctamente los datos para matricular un alumno, pero el api aún no implementa esta funcionalidad. Considera el ejercicio correctamente superado");

	}else{
		mandarError(4,"No se han enviado todos los parámetros requeridos: nombre, edad, código del curso");
	}

}else{
	mandarError(3,"Este endpoint necesita datos enviados con el método POST");
}



?>


