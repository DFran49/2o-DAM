package fcm;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Ejercicio01 {
    //1. Crear un programa que muestre información detallada sobre un fichero o directorio: nombre, ruta completa,
    //tipo (fichero o directorio), tamaño (caso de ser un fichero) y permisos de lectura/escritura/ejecución.

    public static void main(String[] args) {

        System.out.println("Introduzca la ruta de un archivo");
        Path ruta = Paths.get(new Scanner(System.in).nextLine());
        System.out.println("Nombre: " + ruta);
        System.out.println("Ruta completa: " + ruta.getRoot() + ruta.getFileName());
        System.out.println("Tipo: " + (Files.isDirectory(ruta) ? "Directorio" : "Archivo"));
        System.out.println("Tamaño: " + ruta.toFile().length());
        System.out.println("Permisos: " + (Files.isReadable(ruta) ? "Lectura " : "") +
                (Files.isWritable(ruta) ? "Escritura " : "") + (Files.isExecutable(ruta) ? "Ejecución" : ""));
    }
}
