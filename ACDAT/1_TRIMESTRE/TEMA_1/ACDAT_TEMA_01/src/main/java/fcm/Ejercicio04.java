package fcm;

import java.io.*;
import java.util.Scanner;

public class Ejercicio04 {
    //4. Crear un programa que lea un archivo de texto y cuente el número total de palabras que contiene.
    //Posteriormente, crear un nuevo archivo de texto para escribir el conteo total de palabras.

    public static void main(String[] args) {
        System.out.println("Introduzca un archivo");
        File archivo = new File(new Scanner(System.in).nextLine());

        if (!archivo.isFile()) {
            System.err.println("El archivo no es un fichero o no existe");
        } else {
            String linea;


                 try (BufferedReader lector = new BufferedReader(new FileReader(archivo))) {
                     while ((linea = lector.readLine())!= null) {
                         System.out.println(linea);
                     }
                 } catch (FileNotFoundException e) {
                     System.err.println(e.getMessage());
                 } catch (IOException e) {
                     System.err.println(e.getMessage());
                 }


        }
    }
}
