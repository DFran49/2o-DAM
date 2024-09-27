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
        String texto = "";
        int coincidencias = 0;
        int letraINI = 0;
        int lineas = 0;
        int contador = 0;
        int chars = 0;
        boolean romper = false;

        if (!origen.exists()) {
            System.out.println("El fichero indicado no existe");
        } else {
            try (BufferedWriter escritor = new BufferedWriter(new FileWriter(origen,true));
                 BufferedReader lector = new BufferedReader(new FileReader(origen))) {
                while ((linea = lector.readLine()) != null || !romper) {
                    for (int i = 0; i < linea.length() && !romper; i++) {
                        if ((linea.charAt(i) == palabra.charAt(coincidencias)) && coincidencias < palabra.length()) {
                            coincidencias++;
                            if (letraINI == 0) {
                                letraINI = i;
                            }
                        } else if (coincidencias != palabra.length()) {
                            coincidencias = 0;
                            letraINI = 0;
                        }
                        if (coincidencias != palabra.length()) {
                            chars++;
                        }
                        if (coincidencias == palabra.length()) {
                            romper = true;
                            texto = linea;
                            lineas = contador;
                        }

                    }
                    contador++;
                }

                System.out.println(lineas);
                System.out.println(letraINI);
                if (coincidencias != palabra.length()) {
                    System.err.println("No se ha encontrado la palabra");
                } else {
                    System.out.println("Introduzca la palabra por la cual la quiere sustituir");
                    String nuevaPalabra = new Scanner(System.in).nextLine();

                    //escritor.write(nuevaPalabra,chars,nuevaPalabra.length()-1);
                    if (letraINI == 1) {
                        System.out.println(texto.substring(0,letraINI-1) + nuevaPalabra +
                                texto.substring(letraINI+palabra.length()));
                    }



                }

            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
