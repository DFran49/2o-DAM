<html>

<head>
    <title>Ejemplo PHP</title>
</head>

<body>
    <?php
    echo "Normalmente al hacer una operación con una variable detecta el tipo:<hr>";

    $a = 4 + 1;
    echo "La variable a vale $a y es ";
    echo gettype($a);
    echo "<br>";

    $a = 4 + 0.01;
    echo "La variable a vale $a y es ";
    echo gettype($a);
    echo "<br>";


    $a = "2";
    echo "La variable a vale $a y es ";
    echo gettype($a);
    echo "<br>";


    echo "Normalmente al hacer una operación con una variable detecta el tipo, pero podemos manejar los tipos con settype ($ variable,tipo) haciendo casting<hr>";
    echo "La variable a es ";
    settype($a, "int");
    echo gettype($a);
    echo "<br>";
    echo "La variable a es ";
    settype($a, "float");
    echo gettype($a);
    echo "<br>";
    echo "La variable a es ";
    settype($a, "string");
    echo gettype($a);
    echo "<br>";

    ?>
</body>

</html>