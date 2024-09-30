<html>

<body>
    <?php
    $SalarioTrabajador = 3500;
    $Impuesto = 20; // Porcentaje
    $SueldoReal = $SalarioTrabajador - (($SalarioTrabajador / 100) * $Impuesto);
    echo "Sueldo del trabajador sin impuesto: $SalarioTrabajador<BR>";
    echo "Con el impuesto :$SueldoReal";
    ?>
</body>

</html>