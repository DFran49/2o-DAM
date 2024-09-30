<?php
echo "<br>EJEMPLO IF-ELSEIF-ELSE<br>";
$nombre = "";
if ($nombre == "") {
	echo "Tú no tienes nombre";
} elseif (($nombre == "paco") or ($nombre == "PACO")) {
	echo "Tu nombre es PACO";
} else {
	echo "Tu nombre es " . $nombre;
}
?>


<?php
echo "<br><br>EJEMPLO SWITCH<br>";
$dia = "Jueves";
switch ($dia) {
	case "Lunes":
		echo "Hoy es Lunes";
		break;
	case "Martes":
		echo "Hoy es Martes";
		break;
	case "Miercoles":
		echo "Hoy es Miercoles";
		break;
	case "Jueves":
		echo "Hoy es Jueves";
		break;
	case "Viernes":
		echo "Hoy es Viernes";
		break;
	case "Sábado":
		echo "Hoy es Sábado";
		break;
	case "Domingo":
		echo "Hoy es Domingo";
		break;
	default:
		echo "Esa cadena no corresponde a ningún día de la semana";
}
?>


<?php
echo "<br><br>EJEMPLO WHILE<br>";
$num = 1;
while ($num < 5) {
	echo $num;
	$num++;
}
?>

<?php
echo "<br><br>EJEMPLO WHILE CON BREAK<br>";
$num = 1;
while ($num < 5) {
	echo $num;
	if ($num == 3) {
		echo "<br>Aquí nos salimos \n";
		break;
	}
	$num++;
}
?>


<?php
echo "<br><br>EJEMPLO DO-WHILE CON BREAK<br>";
$num = 1;
do {
	echo $num;
	if ($num == 3) {
		echo "Aquí nos salimos \n";
		break;
	}
	$num++;
} while ($num < 5);
?>

<?php
echo "<br><br>EJEMPLO FOR <br>";
for ($num = 1; $num <= 5; $num++) {

	if ($num == 2) {
		echo "Saltamos esta iteración \n";
		continue;
	}

	if ($num == 3) {
		echo "Aquí nos salimos \n";
		break;
	}
	echo $num;
}
?>