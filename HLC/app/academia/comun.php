<?php 

function mandarError($cod,$desc){
	http_response_code(500);
	echo json_encode(
		array(
			"cod_error" => $cod,
			"desc_error" => $desc
		)
	);
}

function mandarBien($cod,$desc){
	http_response_code(200);
	echo json_encode(
		array(
			"cod_error" => $cod,
			"desc_error" => $desc
		)
	);
}



?>


