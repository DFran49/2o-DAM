<html>

<head>
    <title>Ejemplo PHP</title>
</head>

<body>
    <?php

    function cuadradolocal($n)
    {
        return ($n * $n);
    }
    echo "El cuadrado de 3 es " . cuadradolocal(3) . "<br>";

    include("libreria.php");
    echo "El cuadrado de 3 es " . cuadrado(3);
    ?>

</body>

</html>