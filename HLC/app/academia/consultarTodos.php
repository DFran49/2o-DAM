<?php 


	$lugares=[
		"cursos" => [
			[
				"codigo" => "PY",
				"nombre" => "Programación en lenguaje Python",
				"num_plazas" => 30,
				"plazas_ocupadas" => 16,
				"departamento" => "informática"
			],
			[
				"codigo" => "CS",
				"nombre" => "Introducción a la ciberseguridad",
				"num_plazas" => 12,
				"plazas_ocupadas" => 10,
				"departamento" => "informática"
			],
			[
				"codigo" => "RM",
				"nombre" => "Refuerzo de matemáticas",
				"num_plazas" => 30,
				"plazas_ocupadas" => 30,
				"departamento" => "matemáticas"
			]
		],
		"total" => "3"
	];

	http_response_code(200);
	echo json_encode($lugares);


?>


