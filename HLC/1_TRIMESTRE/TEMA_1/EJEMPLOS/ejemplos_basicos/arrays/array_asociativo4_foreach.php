<?php
$visitas = array("lunes" => 200, "martes" => 186, "miércoles" => 190, "jueves" => 175);
reset($visitas);
foreach ($visitas as $clave => $valor)
	echo "el día $clave ha tenido $valor visitas<BR>";

//Each lo que hace es dividir cada PAR del array en 2 variables: clave y valor

$notas = array(6, 3, 2, 9, 5);
reset($notas); foreach ($notas as $clave => $valor)
	echo "Posicion $clave contiene el valor $valor<BR>";

//Each lo que hace es dividir cada PAR del array en 2 variables: clave y valor

?>