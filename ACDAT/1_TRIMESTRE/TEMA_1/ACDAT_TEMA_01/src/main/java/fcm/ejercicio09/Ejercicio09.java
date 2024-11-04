package fcm.ejercicio09;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Ejercicio09 {
    //9. Crear una clase Estudiante con los atributos: nombre (String), edad (int) y calificaciones (un array de
    //double). Escribir un programa que cree objetos de tipo Estudiante y almacene sus datos en un archivo binario
    //utilizando la clase DataOutputStream. El programa también debe leer los objetos Estudiante desde el archivo
    //y mostrar sus datos en la pantalla. Nota: no se deben utilizar ObjectOutputStream ni ObjectInputStream.

    public static void main(String[] args) {
        String archivo = "datosEstudiantes.bin";
        ArrayList<Estudiante> estudiantes = new ArrayList<>();

        System.out.println("Elija la opción a usar:" +
                "\n 1. Leer de fichero" +
                "\n 2. Escribir a fichero");
        int opcion = new Scanner(System.in).nextInt();

        switch (opcion) {
            case 1 -> {
                try (DataInputStream di = new DataInputStream(new FileInputStream(archivo))) {
                    while (true) {
                        String cadenaTexto = di.readUTF();
                        System.out.println("Cadena de texto: " + cadenaTexto);
                    }
                } catch (IOException e) {
                    System.err.println("Error al leer el archivo: " + e.getMessage());
                }
            }
            case 2 -> {
                for (int i = 0; i < new Random().nextInt(2,10); i++) {
                    double[] calificaciones = new double[10];
                    for (int j = 0; j < calificaciones.length; j++) {
                        calificaciones[j] = (new Random().nextInt(0,100)/2);
                    }
                    estudiantes.add(new Estudiante("estudiante"+(i+1),new Random().nextInt(16,35),calificaciones));
                }
                try (DataOutputStream escribir = new DataOutputStream(new FileOutputStream(archivo))) {
                    for (Estudiante e : estudiantes) {
                        escribir.writeUTF(e.toString()+"\n");
                    }
                    escribir.flush(); // Limpia el flujo de salida para asegurarse de que todos los datos se escriban
                    System.out.println("Datos escritos en el archivo correctamente.");
                } catch (IOException e) {
                    System.err.println("Error al escribir en el archivo: " + e.getMessage());
                }
            }
            default -> System.err.println("No es una opción válida, intentelo de nuevo");
        }



    }
}
