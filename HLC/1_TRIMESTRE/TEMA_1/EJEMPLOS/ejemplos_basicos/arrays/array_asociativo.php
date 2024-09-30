<?php
//Ejemplo interesante, ¿que salida pensais que dará este código?

$ciudad = array(1 => "París", 5 => "Roma", 7 => "Sevilla", 3 => "Londres");

$numelentos = count($ciudad);
//imprimimos todos los elementos de la tabla
for ($i = 0; $i < $numelentos; $i++) {
    print("La ciudad $i es $ciudad[$i] <BR>\n");
}
?>