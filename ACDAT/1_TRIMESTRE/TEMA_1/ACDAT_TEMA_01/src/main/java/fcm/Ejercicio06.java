package fcm;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Ejercicio06 {
    //6. (Opcional) Crear un programa que lea un archivo de texto y permita al usuario ingresar una palabra para buscar
    //y otra palabra para reemplazarla. El contenido modificado debe escribirse en un nuevo archivo.

    public static void main(String[] args) {
        System.out.println("Introduzca el fichero donde se va a buscar la palabra");
        File origen = new File(new Scanner(System.in).nextLine());
        System.out.println("Introduzca la palabra a buscar");
        String palabra = new Scanner(System.in).nextLine();

        String linea;
        ArrayList<String> texto = new ArrayList<>();
        ArrayList<Character> letras = new ArrayList<>();
        int coincidencias = 0;
        int lineas = 0;
        int letraINI = 0;
        int contador = 0;
        int chars = 0;

        if (!origen.exists()) {
            System.out.println("El fichero indicado no existe");
        } else {
            System.out.println("a");
            try (BufferedWriter escritor = new BufferedWriter(new FileWriter(origen)); BufferedReader lector = new BufferedReader(new FileReader(origen))) {
                System.out.println(lector.readLine());
                while ((linea = lector.readLine()) != null) {
                    for (int i = 0; i < linea.length(); i++) {
                        System.out.println(linea.charAt(i));
                        if ((linea.charAt(i) == palabra.charAt(coincidencias))) {
                            coincidencias++;
                            letraINI = i;
                            lineas = contador;
                            System.out.println(coincidencias);
                            System.out.println(letraINI);
                            System.out.println(lineas);
                        } else if (contador != palabra.length()) {
                            coincidencias = 0;
                            letraINI = 0;
                            lineas = 0;
                        }
                        if (coincidencias != palabra.length()) {
                            chars++;
                        }

                    }
                    contador++;
                }

                System.out.println(coincidencias);
                if (coincidencias != palabra.length()) {
                    System.err.println("No se ha encontrado la palabra");
                } else {
                    System.out.println("Introduzca la palabra por la cual la quiere sustituir");
                    String nuevaPalabra = new Scanner(System.in).nextLine();
                    String lineaModificable = texto.get(lineas);

                    //texto.set(lineas, lineaModificable.substring(0,letraINI-1) + nuevaPalabra + lineaModificable.substring(letraINI+nuevaPalabra.length())):
                    System.out.println(lineaModificable.substring(0,letraINI) + nuevaPalabra + lineaModificable.substring(letraINI+nuevaPalabra.length()));

                }

            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
