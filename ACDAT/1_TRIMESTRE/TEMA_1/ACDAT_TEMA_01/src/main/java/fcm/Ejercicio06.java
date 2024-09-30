package fcm;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Ejercicio06 {
    //6. (Opcional) Crear un programa que lea un archivo de texto y permita al usuario ingresar una palabra para buscar
    //y otra palabra para reemplazarla. El contenido modificado debe escribirse en un nuevo archivo.

    public static void main(String[] args) {
        //Se recopilan los datos necesarios
        System.out.println("Introduzca el fichero donde se va a buscar la palabra");
        File origen = new File(new Scanner(System.in).nextLine());
        System.out.println("Introduzca el fichero destino");
        File destino = new File(new Scanner(System.in).nextLine());
        System.out.println("Introduzca la palabra a buscar");
        String palabra = new Scanner(System.in).nextLine();
        System.out.println("Introduzca la palabra por la cual la quiere sustituir");
        String nuevaPalabra = new Scanner(System.in).nextLine();

        String linea;

        //Se comprueba si el origen existe
        if (!origen.exists()) {
            System.out.println("El fichero indicado no existe");
        } else {
            //Si el destino no existe se crea
            if (!destino.exists()) {
                try {
                    destino.createNewFile();
                } catch (IOException e) {
                    System.err.println(e.getMessage());
                }
            }

            //Se crea el try con el lector y el escritor
            try (BufferedWriter escritor = new BufferedWriter(new FileWriter(destino,true));
                 BufferedReader lector = new BufferedReader(new FileReader(origen))) {
                //Se lee una línea en cada ciclo
                while ((linea = lector.readLine()) != null) {
                    //Si la línea contiene la palabra, la sustituye y la escribe en el destino, si no, la escribe sin alterar
                    if (linea.contains(palabra)) {
                        escritor.write(linea.replaceAll(palabra,nuevaPalabra)+"\n");
                    } else {
                        escritor.write(linea+"\n");
                    }
                }
                System.out.println("Proceso completado");
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
