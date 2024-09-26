package fcm;

import java.io.*;
import java.util.Scanner;

public class Ejercicio05 {
    //5. Crear un programa que solicite al usuario los nombres de dos archivos de texto. El programa debe copiar el
    //contenido del primer archivo al segundo. Verificar que el primer archivo exista antes de proceder con la copia.

    public static void main(String[] args) {
        System.out.println("Introduzca el fichero a copiar");
        File origen = new File(new Scanner(System.in).nextLine());
        System.out.println("Introduzca el fichero destino");
        File destino = new File(new Scanner(System.in).nextLine());
        String linea;

        //Se comprueba si el origen es válido (Existe y no es igual al destino)
       if (!origen.exists() || origen.equals(destino)) {
           System.err.println("El fichero a copiar no existe o es igual al destino");
       } else {
           //Si el destino no existe, se crea
           if (!destino.exists()) {
               try {
                   destino.createNewFile();
               } catch (IOException e) {
                   System.err.println(e.getMessage());
               }
           }

           //Se crea el try con el lector y el escritor
           try (BufferedWriter escritor = new BufferedWriter(new FileWriter(destino)); BufferedReader lector = new BufferedReader(new FileReader(origen))) {
               //Se recorre el fichero de origen y se guarda en "linea"
               while ((linea = lector.readLine())!= null) {
                   //Se escribe la línea guardada en el destino y se añade un retorno de línea
                   escritor.write(linea + "\n");
               }
               System.out.println("Fichero copiado correctamente");
           } catch (IOException e) {
               System.err.println(e.getMessage());
           }
       }
    }
}
