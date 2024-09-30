package fcm;

import java.io.*;
import java.util.Scanner;

public class Ejercicio07 {
    //7. Crear un programa que lea un archivo de texto y aplique un cifrado César con un desplazamiento de 5
    //posiciones a cada carácter del archivo. El resultado cifrado debe guardarse en un nuevo archivo.

    public static void main(String[] args) {
        //Se lee el archivo y el destino
        System.out.println("Introduzca un archivo");
        File archivo = new File(new Scanner(System.in).nextLine());
        System.out.println("Introduzca el fichero destino");
        File destino = new File(new Scanner(System.in).nextLine());

        int letra = 0;

        if (!archivo.exists()) {
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
            try (BufferedReader lector = new BufferedReader(new FileReader(archivo));
                 BufferedWriter escritor = new BufferedWriter(new FileWriter(destino))) {
                //Se lee mientras la letra no sea -1
                while ((letra = lector.read()) != -1) {
                    //Se escribe la letra sumandole 5
                    escritor.write((char) letra+5);
                }
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
            System.out.println("Archivo cifrado.");
        }
    }
}