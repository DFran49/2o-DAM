<?php
//MATRIZ PARA ALMACENAR TODAS LAS LLUVIAS EN EL MES DE ENERO
$lluvias[0] = array("lunes" => 200, "martes" => 186, "miércoles" => 190, "jueves" => 175); //LLUVIAS PRIMERA SEMANA ENERO
$lluvias[1] = array("lunes" => 230, "martes" => 16, "miércoles" => 34, "jueves" => 54); //LLUVIAS SEGUNDA SEMANA ENERO

reset($lluvias);
echo "primera semana <br>";
foreach ($lluvias[0] as $clave => $valor)
	echo "el día $clave ha tenido $valor lluvias<BR>";

echo "segunda semana <br>"; foreach ($lluvias[1] as $clave => $valor)
	echo "el día $clave ha tenido $valor lluvias<BR>";


//Se podría haber hecho con un doble bucle. Queda propuesto como ejercicio.


?>