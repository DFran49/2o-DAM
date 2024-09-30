<html>

<head>
    <title>Ejemplo PHP</title>
</head>

<body>
    <?php

    //Por ejemplo intentamos abrir un fichero
    //Este código producirá un error ya que no existe el fichero
    
    $f = file_get_contents("noestoy.php");
    echo "<br>";
    $f = file_get_contents("noestoy.php") or die("No se pudo");


    ?>
</body>

</html>