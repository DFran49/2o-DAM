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


?>


