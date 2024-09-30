<?php
$ciudad = array("París", "Roma", "Sevilla", "Londres");
//contamos el número de elementos de la tabla
$numelentos = count($ciudad);
//imprimimos todos los elementos de la tabla
for ($i=0; $i < $numelentos; $i++){
    print ("La ciudad $i es $ciudad[$i] <BR>\n");
}
?>