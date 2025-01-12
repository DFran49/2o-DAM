<!DOCTYPE html>
<html>

<head>
    <title>Formulario JSON - Prueba API - EDITAR</title>
</head>

<body>
    <form id="miFormulario">

        <label for="id">id:</label>
        <input type="text" name="id" id="id" disabled value="<?php echo $_GET["id"] ?>"><br>


        <label for="nombre">Nombre:</label>
        <input type="text" name="nombre" id="nombre" value="<?php echo $_GET["nombre"] ?>"><br>

        <label for="edad">Edad:</label>
        <input type="text" name="edad" id="edad" value="<?php echo $_GET["edad"] ?>"><br>

        <label for="activo">Activo:</label>
        <input type="checkbox" name="activo" id="activo" <?php
        // Verifica si el valor 'valor' se encuentra en $_GET
        if (isset($_GET["activo"]) && $_GET["activo"] == 1) {
            echo 'checked';
        } ?>><br>

        <input type="submit" value="Enviar">
    </form>

    <div id="respuesta"></div>

    <script>
        //Agrega un controlador de eventos para el formulario
        document.getElementById("miFormulario").addEventListener("submit", function (event) {
            event.preventDefault(); // Evitar el envío predeterminado del formulario

            //Cogemos los valores de los campos
            var id = document.getElementById("id").value;
            var nombre = document.getElementById("nombre").value;
            var edad = document.getElementById("edad").value;
            var activo;
            if (document.getElementById("activo").checked) {
                activo = 1;
            } else {
                activo = 0;
            }

            //Creamos un objeto JavaScript con los datos
            var datos = {
                id: id,
                nombre: nombre,
                edad: edad,
                activo: activo
            };

            console.log(datos);
            console.log(JSON.stringify(datos));

            // Enviamos los datos al servidor en formato JSON
            var xhr = new XMLHttpRequest();
            xhr.open("POST", "../crud/actualizar.php", true);
            xhr.setRequestHeader("Content-Type", "application/json; charset=UTF-8");
            xhr.send(JSON.stringify(datos));

            //Manejamos la respuesta del servidor
            xhr.onload = function () {
                if (xhr.status == 200) {
                    document.getElementById("respuesta").innerHTML = "Datos enviados OK";
                } else {
                    document.getElementById("respuesta").innerHTML = "Error al enviar datos";
                }
            };


        }

        );
    </script>
</body>

</html>