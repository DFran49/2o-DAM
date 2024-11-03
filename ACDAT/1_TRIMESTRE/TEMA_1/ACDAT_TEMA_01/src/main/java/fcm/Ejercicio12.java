package fcm;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

public class Ejercicio12 {
    //12. Crear un programa que lea un archivo de propiedades llamado "config.properties" y muestre en pantalla
    //todos los pares clave-valor que contiene. Además, debe solicitar al usuario que ingrese una clave y un nuevo
    //valor, para actualizar el valor correspondiente a esa clave en el archivo y guardar los cambios. Nota: comprobar
    //si el archivo "config.properties" existe antes de intentar leerlo.

    public static void main(String[] args) {
        Properties properties = new Properties(); // Crear un objeto de la clase Properties

        try (FileInputStream fileIn = new FileInputStream("config.properties")) {
            properties.load(fileIn);
            properties.forEach((key, value) -> System.out.println(" - " + key + " = " + value));
        } catch (IOException e) {
            System.err.println("Error al cargar las propiedades: " + e.getMessage());
        }

        System.out.println("Introduzca el nombre de una propiedad.");
        String clave = new Scanner(System.in).nextLine();
        System.out.println("Introduzca el valor deseado");
        String valor = new Scanner(System.in).nextLine();
        properties.setProperty(clave,valor);

        try (FileOutputStream fileOut = new FileOutputStream("config.properties")) {
            properties.store(fileOut, "Configuración de la App");
            System.out.println("Propiedades guardadas en el archivo.");
        } catch (IOException e) {
            System.err.println("Error al guardar las propiedades: " + e.getMessage());
        }


    }
}
