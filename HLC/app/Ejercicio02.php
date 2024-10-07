<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Document</title>
    </head>
    <body>
        <?php
            $precio = 100;
            $pagado = 90;
            echo("El descuento aplicado ha sido de ".(100-($pagado*100)/$precio))."%";
        ?>
    </body>
</html>