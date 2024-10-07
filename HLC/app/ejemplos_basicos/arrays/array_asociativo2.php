<?php
$lluvias = array("lunes" => 50, "martes" => 34, "miercoles" => 20, "jueves" => 50, "viernes" => 50, "sabado" => 150, "domingo" => 50);

$numelentos = count($lluvias);
//imprimimos todos los elementos de la tabla. Esto dará un error. 
//No se puede acceder por posición a un array creado de esta forma
for ($i = 0; $i < $numelentos; $i++) {
    print("Elemento $i es $lluvias[$i] <BR>\n");
}

print $lluvias["lunes"] . "<BR>";
print $lluvias["martes"] . "<BR>";
print $lluvias["miercoles"] . "<BR>";

print "Otra forma de sacar la información es con print_r : saca el contenido de cualquier array<br>";
print_r($lluvias);

?>