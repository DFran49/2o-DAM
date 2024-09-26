package fcm;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Ejercicio04 {
    //4. Crear un programa que lea un archivo de texto y cuente el número total de palabras que contiene.
    //Posteriormente, crear un nuevo archivo de texto para escribir el conteo total de palabras.

    public static void main(String[] args) {
        //Se lee el archivo
        System.out.println("Introduzca un archivo");
        File archivo = new File(new Scanner(System.in).nextLine());

        //Se comprueba si el archivo es realmente un archivo
        if (!archivo.isFile()) {
            System.err.println("El archivo no es un fichero o no existe");
        } else {
            String linea;
            int letra = -1;
            int contador = 0;
            int anterior = 0;
            //Se define una lista con los caracteres separadores de palabras
            ArrayList<Integer> blackList = new ArrayList<>();
            blackList.add(32);
            blackList.add(46);
            blackList.add(44);

            //Se crea el try con recursos creando el lector
                 try (BufferedReader lector = new BufferedReader(new FileReader(archivo))) {
                     //Se ejecuta un while que lee una línea en cada ciclo y para cuando no quedan líneas
                     while ((linea = lector.readLine()) != null) {
                         //Se reinicia la variable "anterior" por si la línea anterior acaba en los caracteres de separación
                         anterior = 0;

                         //Se recorren todas las letras de la línea para contar las palabras
                         for (int i = 0; i < linea.length(); i++) {
                             letra = linea.charAt(i);

                             //Se comprueba si el archivo empieza en un caracter de separación, y si no, hay una palabra al comienzo
                             if (i == 0 && !blackList.contains(letra)) {
                                 contador++;
                                 System.out.println("INI");
                             }

                             //Se comprueba si el caracter anterior es de separación y el actual no lo es, en cuyo caso se cuenta una palabra
                             if ((blackList.contains(anterior) && !blackList.contains(letra))) {
                                 contador++;
                                 System.out.println("MID");
                             }

                             //Se guarda la letra para usarla en el siguiente ciclo de la línea
                             anterior = linea.charAt(i);
                         }
                     }
                 } catch (IOException e) {
                     System.err.println(e.getMessage());
                 }
            System.out.println("La cantidad de palabras es: " + contador);

        }
    }
}
