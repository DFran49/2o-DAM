package fcm;

import java.io.*;
import java.util.Scanner;

public class Ejercicio13 {
    //13. Crear un programa que permita al usuario editar una línea específica en un archivo de texto utilizando
    //RandomAccessFile. El programa debe solicitar al usuario el número de línea que desea modificar y el nuevo
    //contenido para esa línea, reemplazando el contenido existente en la línea indicada.

    public static void main(String[] args) {
        File archivo = new File("raccess.txt");

        System.out.println("Introduzca el número de línea que quiere cambiar (Empieza en la 1)");
        int linea = (new Scanner(System.in).nextInt())-1;

        System.out.println("Introduzca el nuevo contenido de la línea");
        String nuevaLinea = new Scanner(System.in).nextLine();

        try (RandomAccessFile acceso = new RandomAccessFile(archivo, "rw")) {
            for (int i = 0; i < linea ; i++) {
                acceso.readLine();
            }
            acceso.writeUTF(nuevaLinea);
        } catch (FileNotFoundException e) {
            System.err.println("El archivo no existe.");
        } catch (IOException e) {
            System.err.println("Se produjo un error con el archivo: " + e.getMessage());
        }
    }
}
