<?php 

header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Headers: Content-Type");
header("Content-Type: application/json; charset=UTF-8");

include_once 'comun.php';


	$lugares=array(
		"PY" => array(
			"nombre" => "Programación en lenguaje Python",
			"descripcion" => "Este curso de programación en Python está diseñado para principiantes que desean aprender a programar de manera sencilla y práctica. A través de lecciones teóricas y ejercicios prácticos, los estudiantes aprenderán los fundamentos de Python, incluyendo variables, estructuras de control, funciones y manejo de errores, con un enfoque en la resolución de problemas reales. Al finalizar, los participantes estarán listos para crear sus propias aplicaciones y continuar explorando áreas más avanzadas como la programación orientada a objetos.",
			"foto" => "img/python.jpg"		
		
		),
		"CS" => array(
		"nombre" => "Introducción a la ciberseguridad",
			"descripcion" => "Este curso de introducción a la ciberseguridad está diseñado para quienes desean comprender los conceptos fundamentales de la seguridad informática. A lo largo del curso, los estudiantes aprenderán sobre amenazas, vulnerabilidades, ataques comunes y cómo proteger sistemas y redes. Además, se cubrirán prácticas de seguridad, criptografía básica, control de acceso y la importancia de la privacidad. Al finalizar, los participantes tendrán una base sólida para entender los riesgos digitales y aplicar medidas preventivas en entornos tanto personales como profesionales.",
			"foto" => "img/ciberseguridad.jpg"	
				
		),
		"RM" => array(
			"nombre" => "Refuerzo de matemáticas",
			"descripcion" => "Este curso de refuerzo en matemáticas está diseñado para estudiantes que desean mejorar sus habilidades matemáticas en áreas clave. A lo largo del curso, se abordarán temas como álgebra, geometría, aritmética, y resolución de problemas. A través de explicaciones claras y ejercicios prácticos, los participantes fortalecerán su comprensión de los conceptos y mejorarán su capacidad para enfrentar exámenes y tareas escolares. Ideal para aquellos que buscan mejorar su rendimiento en matemáticas, este curso ofrece un enfoque accesible y efectivo para fortalecer su confianza.",
			"foto" => "img/matematicas.jpeg"	
		
		
		)
	);

	if(isset($_GET['codigo'])){
		$codigo = $_GET['codigo'];
		if(isset($lugares[$codigo])){
			$lugar = $lugares[$codigo];
			http_response_code(200);
			echo json_encode($lugar);
		}else{
			mandarError(1,"El código de lugar proporcionado no es válido");			
		}
	}else{
		mandarError(2,"No se ha especificado un código de lugar");	
	}


?>


