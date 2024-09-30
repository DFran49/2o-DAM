<html>

<head>
  <title>Ejemplo PHP</title>
</head>

<body>
  Las tablas (o array en inglés), son muy importantes en PHP, ya que generalmente,
  las funciones que devuelven varios valores, como las funciones ligadas a las bases de
  datos, lo hacen en forma de tabla.
  En PHP disponemos de dos tipos de tablas. El primero sería el clásico, utilizando
  índices:

  <?php
  $ciudad[] = "París";
  $ciudad[] = "Jaén";
  $ciudad[] = "Roma";
  $ciudad[] = "Sevilla";
  $ciudad[] = "Londres";
  print("yo vivo en <b>" . $ciudad[1] . "</b><BR>\n");
  ?>
  Esta es una forma de asignar elementos a una tabla, pero una forma más formal es
  utilizando la función array<br>
  <?php
  $ciudad = array("París", "Roma", "Sevilla", "Londres");
  //contamos el número de elementos de la tabla
  $numelentos = count($ciudad);
  //imprimimos todos los elementos de la tabla
  for ($i = 0; $i < $numelentos; $i++) {
    print("La ciudad $i es $ciudad[$i] <BR>\n");
  }
  ?>
  <hr>
  <h1> PRUEBA DE FUNCIONES DE ARRAY </h1>
  <?php
  $semana = array(
    "lunes",
    "martes",
    "miércoles",
    "jueves",
    "viernes",
    "sábado",
    "domingo"
  );
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

  <hr>
  <h1> PRUEBA DE RECORRIDO CON FOREACH Y ARRAY ASOCIATIVO</h1>

  <?php
  $visitas = array("lunes" => 10, "martes" => 8, "miércoles" => 9, "jueves" => 11);
  echo $visitas[0] . "<br>"; //NO HACE NADA
  echo $visitas["lunes"] . "<br>";
  print_r($visitas);
  echo "<br>";
  foreach ($visitas as $clave => $valor) {
    echo "el día $clave he trabajado $valor horas<br>";
  }

  ?>

</body>

</html>