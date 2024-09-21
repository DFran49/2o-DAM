package fcm;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Ejercicio02 {
    //2. Crear un programa que copie un fichero de una ubicación a otra. El programa debe solicitar al usuario que
    //introduzca la ruta y nombre del fichero de origen, y la ruta y nombre del fichero de destino. Verificar si el fichero
    //de origen existe antes de proceder con la copia.

    public static void main(String[] args) {
        System.out.println("Introduzca la ruta y el nombre del fichero de origen");
        Path origen = Paths.get(new Scanner(System.in).nextLine());
        //Origen usado: G:\prueba.txt

        //Compruebo si el archivo existe
        if (Files.exists(origen)) {
            System.out.println("Introduzca la ruta y el nombre del fichero de destino");
            Path destino = Paths.get(new Scanner(System.in).nextLine());
            //Destino usado: G:\copia.txt

            try {
                Files.copy(origen,destino);
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        } else {
            System.out.println("El fichero de origen no existe, inténtelo de nuevo");
        }


    }
}
