<?php //PARA SUBIR ARCHIVOS AL SERVIDOR

include_once '../basedatos/Cine.php';
include_once '../tablas/Actores.php';

if (isset($_FILES['archivo'])) // Si tiene se ha subido un fichero llama a la funcion
     subir_cartelera($_FILES['archivo']['name'], $_FILES['archivo']['size']);


function subir_cartelera($nombre, $taman)
{

     $database = new Cine();
     $db = $database->dameConexion();

     $extension = explode(".", $nombre);
     $num = count($extension) - 1;

     //Límite de 64KB de BLOB. Medium es de 16MB
     if (($extension[$num] != "jpg") || ($taman >= 16000000)) {
          echo "ERROR: la imagen debe de ser jpg y ocupar menos de 16MB";
     } else {
          //se convierte en binario antes de guardarlo
          $tmp = $_FILES['archivo']['tmp_name'];
          //$fotoblob = $db->real_escape_string(file_get_contents($tmp));
          $fotocodif=base64_encode(file_get_contents($tmp));

          if (strlen($fotocodif)>10000) {
               echo  "Codificación mayor de 10000, no podrá guardarse en la base de datos<br>";
          }
          
          if (!move_uploaded_file($tmp, "fotosactores/" . $nombre))
               echo "Error al copiar el archivo<br>";

          else {
               echo "Archivo subido con éxito<br>";


               //Actualiza el campo de la base de datos con el nombre de la imagen
               if (actualiza_tabla($nombre, $fotocodif, $db)) {
                    echo "Tabla Actualizada OK!";
               } else {
                    echo "Error al actualizar tabla";
               }

          }
     }
}

function actualiza_tabla($nombre, $fotocodif, $db)
{
     //Sube los dos tipos de "imagen" (url y Blob) para mostrar
     //distintas opciones. Foto en este formato se hace BIND con s, no con b
     /*
     $stmt = $db->prepare("
     UPDATE actores SET fotourl= ?,  fotoblob = ? WHERE id = ?");
     $stmt->bind_param("ssi", strip_tags($nombre), $fotoblob, strip_tags($_GET['id']));
     */

     //Se utiliza un MEDIUMTEXT codificado como Base64
     $stmt = $db->prepare("
     UPDATE actores SET fotourl= ?,  fotocodif = ? WHERE id = ?");
     $stmt->bind_param("ssi", strip_tags($nombre), $fotocodif, strip_tags($_GET['id']));


     if ($stmt->execute()) {
          return true;
     }

     return false;
}
?>

<form action="subeimg.php?id=<?php echo $_GET['id']; ?>" method="post" enctype="multipart/form-data">
     SUBIR IMAGEN <br><input name="archivo" type="file">
     <input type="hidden" name="id" value="<?php echo $_GET['id']; ?>">
     <br><input name="boton" type="submit">
</form>