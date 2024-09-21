package fcm;

import java.io.File;
import java.io.FilenameFilter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Ejercicio03 {
    //3. (Opcional) Crear una función que busque ficheros con una extensión específica dentro de un directorio y sus
    //subdirectorios, mostrando la ruta completa de los ficheros encontrados. Hacer uso de la recursividad.

    public static void BuscarExtension(Path d, String e) {
        //Creamos un array de Files a partir del Path pasado como parámetro, filtrando mediante una expresión lambda
        //usando listFiles, indicando que queremos directorios y ficheros que terminen por la extensión pasada como parámetro
        File [] contenido = d.toFile().listFiles(fichero ->
                fichero.isDirectory() || (fichero.isFile() && fichero.getName().endsWith("."+e)));

        //Recorremos el array y mostramos cada elemento
        for (File a : contenido) {
            System.out.println(a);

            //Si el File actual es un directorio, se llama a la función para el directorio actual y se le aplica toda la función
            if (a.isDirectory()) {
                BuscarExtension(Paths.get(a.toString()),e);
            }

        }
    }

    public static void main(String[] args) {
        System.out.println("Introduzca un directorio");
        Path directorio = Paths.get(new Scanner(System.in).nextLine());
        System.out.println("Introduzca una extensión a buscar (Sin el .)");
        String extension = new Scanner(System.in).nextLine();

        BuscarExtension(directorio,extension);
    }
}
