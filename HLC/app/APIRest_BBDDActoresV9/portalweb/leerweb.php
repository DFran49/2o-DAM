<?php

ini_set('display_errors', 1);
ini_set('display_startup_errors', 1);
error_reporting(E_ALL);


include_once '../basedatos/Cine.php';
include_once '../tablas/Actores.php';


//Se crea conexión y objeto act
$database = new Cine();
$conex = $database->dameConexion();
$act = new Actores($conex);

//Verificamos que se le está pasando una variable y no está vacía, en cuyo caso buscará esa id, si no, devuelve -1
//Y mostrará todo

//LEER POR ID
if (isset($_GET['id']) && $_GET['id'])
    $act->id = $_GET['id'];
else
    $act->id = -1; //Mostrará todo

$result = $act->leer();


if ($result->num_rows > 0) {

    echo '<th><a href="insertar.html">INSERTAR</a></th>';
    echo '<table border="1">';
    echo '<tr><th>ID</th><th>Nombre</th><th>Edad</th><th>Activo</th><th>Foto URL</th><th>Foto Base64</th><th colspan="3">Acciones</th>
   
    </tr>';

    while ($actor = $result->fetch_assoc()) { //Crea un array asociativo con cada actor	
        extract($actor); //Exporta las variables de un array
        $dato = array(
            "id" => $id,
            "nombre" => $nombre,
            "edad" => $edad,
            "activo" => $activo,
            "fotourl" => $fotourl,
            "fotocodif" => $fotocodif
        );

        //$fotocodif=base64_decode($fotocodif);

        echo '<tr>';
        echo '<td>' . $id . '</td>';
        echo '<td>' . $nombre . '</td>';
        echo '<td>' . $edad . '</td>';
        echo '<td>' . ($activo ? 'Sí' : 'No') . '</td>';
        echo "<td>    <img src='./fotosactores/$fotourl' width='30' height='30' alt='' ></td>";
        echo "<td> <img src='data:image/jpeg;base64,".$fotocodif."' width='30' height='30' alt='' ></td>";
        echo "<td><a href='editar.php?id=$id&nombre=$nombre&edad=$edad&activo=$activo'>Editar</a></td>";
        echo "<td><a href='subeimg.php?id=$id'>Subir foto</a></td>";
 
        echo "<td><button id='botonBorrar' onclick='miFuncion($id)'>Borrar</button></td>";
        echo '</tr>';
    }
    echo '</table>';

}

?>


<script>

    function miFuncion(miid) {


        var idJSON = {
            id: miid
        };

        // Realiza una solicitud a tu script PHP para eliminar el elemento
        var xhr = new XMLHttpRequest();
        xhr.open("POST", "../crud/borrar.php", true);
        xhr.setRequestHeader("Content-Type", "application/json; charset=UTF-8");
        xhr.send(JSON.stringify(idJSON));

        //Manejamos la respuesta del servidor
        xhr.onload = function () {
            if (xhr.status === 200) {
                // La solicitud se completó correctamente, procesa la respuesta si es necesario
                var respuesta = JSON.parse(xhr.responseText);
                console.log("Respuesta del servidor:", respuesta);
            } else {
                // Manejo de errores aquí
                console.error("Error en la solicitud:", xhr.status, xhr.statusText);
            }
        };

        //Recarga la página pasado 1 segundo
        setTimeout(function () {
            location.reload();
        }, 1000); 

    }

</script>