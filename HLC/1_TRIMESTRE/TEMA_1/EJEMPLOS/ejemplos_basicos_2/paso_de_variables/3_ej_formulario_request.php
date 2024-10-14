<?php
//Captura de variables
$nom=$_REQUEST["nombre"];
$est=$_REQUEST["estudiante"];
$eda=$_REQUEST["edad"];
$ciu=$_REQUEST["ciudad"];

echo "DATOS CAPTURADOS CON REQUEST: ".$nom." ".$est." ".$eda." ".$ciu."<br>";

echo "<br><br>";

echo "Contenido del array REQUEST: ";
print_r($_REQUEST);
echo "<br>Otra forma de cazarlas:<br>";

foreach ($_REQUEST as  $clave=> $valor) 
{
echo "Posicion $clave contiene: $valor<BR>";
}

echo "<br><br>";
echo "Para saber si he marcado un checkbox o un radioboton uso la función isset (viene a significar 'ESTA PUESTO')<br>";
if (isset($est))
	echo "Checkbox ESTUDIANTE marcado";
else 
    echo "Checkbox ESTUDIANTE <b> NO </b> marcado";

echo "<br><br>";

if (isset($eda))
	echo "Radioboton EDAD marcado";
else 
    echo "Radioboton EDAD <b> NO </b> marcado";

//Para evitar posibles errores en recogida de variables, siempre haremos el isset

?>