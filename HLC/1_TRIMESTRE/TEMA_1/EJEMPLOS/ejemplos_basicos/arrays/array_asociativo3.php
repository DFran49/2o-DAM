<?php
$semana = array("lunes", "martes", "miércoles", "jueves", "viernes", "sábado", "domingo");
echo count($semana); //7
//situamos el puntero en el primer elemento
reset($semana);
echo current($semana); //lunes
next($semana);
echo pos($semana); //martes
end($semana);
echo pos($semana); //domingo
prev($semana);
echo current($semana); //sábado
?>