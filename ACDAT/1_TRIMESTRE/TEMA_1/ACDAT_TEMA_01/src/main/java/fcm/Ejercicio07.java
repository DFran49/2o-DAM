package fcm;

import java.util.Scanner;

public class Ejercicio07 {
    //7. Crear un programa que lea un archivo de texto y aplique un cifrado César con un desplazamiento de 5
    //posiciones a cada carácter del archivo. El resultado cifrado debe guardarse en un nuevo archivo.

    public static void main(String[] args) {
        System.out.println("Introduzca la palabra por la cual la quiere sustituir");
        String nuevaPalabra = new Scanner(System.in).nextLine();
        String lineaModificable = "Hola soy paco y feliz";

        String nuevo = lineaModificable.substring(0,8+1) + nuevaPalabra + lineaModificable.substring(9+nuevaPalabra.length());
        System.out.println(nuevo);
    }
}
