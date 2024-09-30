package fcm;

import java.io.*;
import java.util.Scanner;

public class Ejercicio08 {
    //8. Crear un programa que tome como entrada dos archivos de texto ordenados alfabéticamente y combine su
    //contenido en un nuevo archivo, manteniendo el orden alfabético. Nota: no se permite el uso de listas para este
    //ejercicio.

    //G:/prueba.txt   G:/copia.txt   G:/potroclo.txt

    public static void main(String[] args) {
        //Se leen todos los ficheros
        System.out.println("Introduzca el primer fichero");
        File origen_1 = new File(new Scanner(System.in).nextLine());
        System.out.println("Introduzca el segundo fichero");
        File origen_2 = new File(new Scanner(System.in).nextLine());
        System.out.println("Introduzca el fichero destino");
        File destino = new File(new Scanner(System.in).nextLine());
        String linea_1;
        String linea_2;

        //Si comprueba si no existe alguno de los ficheros a leer
        if (!origen_1.exists() || !origen_2.exists()) {
            System.out.println("Alguno de los dos ficheros de origen no existe");
        } else {
            //Si el fichero destino no existe se crea
            if (!destino.exists()) {
                try {
                    destino.createNewFile();
                } catch (IOException e) {
                    System.err.println(e.getMessage());
                }
            }

            //Se crea el try con los lectores y el escritor
            try (BufferedReader lector_1 = new BufferedReader(new FileReader(origen_1));
                 BufferedReader lector_2 = new BufferedReader(new FileReader(origen_2));
                 BufferedWriter escritor = new BufferedWriter(new FileWriter(destino))) {
                //Se guardan las primeras lecturas en variables
                linea_1 = lector_1.readLine();
                linea_2 = lector_2.readLine();

                //Mientras alguna variable no sea null, se ejecutará el bucle
                while (linea_1 != null || linea_2 != null) {

                    //Se comprueba si ninguno es null
                    if (linea_1 != null && linea_2 != null) {

                        if (linea_1.compareToIgnoreCase(linea_2) == 0) {
                            //Si las palabras son iguales y se escriben ambas a la vez y se leen ambos archivos
                            escritor.write(linea_1 + "\n");
                            escritor.write(linea_2 + "\n");
                            linea_1 = lector_1.readLine();
                            linea_2 = lector_2.readLine();
                        } else if (linea_1.compareToIgnoreCase(linea_2) < 0) {
                            //Si la primera palabra es menor a la segunda, se escribe la primera y se lee del archivo 1
                            escritor.write(linea_1 + "\n");
                            linea_1 = lector_1.readLine();
                        } else {
                            //Si la primera palabra es mayor a la segunda, se escribe la segunda y se lee del archivo 2
                            escritor.write(linea_2 + "\n");
                            linea_2 = lector_2.readLine();
                        }
                    } else if (linea_1 == null) {
                        //Si ya no hay contenido en el archivo 1 se escribe la línea del archivo 2 y se vuelve a leer
                        escritor.write(linea_2 + "\n");
                        linea_2 = lector_2.readLine();
                    } else {
                        //Si ya no hay contenido en el archivo 2 se escribe la línea del archivo 1 y se vuelve a leer
                        escritor.write(linea_1 + "\n");
                        linea_1 = lector_1.readLine();
                    }
                }
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
