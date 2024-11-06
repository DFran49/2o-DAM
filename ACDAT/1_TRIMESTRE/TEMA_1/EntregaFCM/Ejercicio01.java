import java.io.*;
import java.util.Scanner;

public class Ejercicio01 {

    public static void main(String[] args) {
        System.out.println("Introduzca la ruta del primer fichero:");
        File archivo1 = new File(new Scanner(System.in).nextLine());
        System.out.println("Introduzca la ruta del segundo fichero:");
        File archivo2 = new File(new Scanner(System.in).nextLine());

        if (!archivo1.exists() || !archivo2.exists()) {
            System.out.println("Alguno de los ficheros pasados como parámetro no existe. Intentelo de nuevo");
        } else if (!archivo1.isFile() || !archivo2.isFile()) {
            System.out.println("Alguno de los dos ficheros pasados como parámetro no es un archivo. Intentelo de nuevo");
        } else {
            if (archivo1.length() != archivo2.length()) {
                System.out.println("Los ficheros no son iguales debido a tamaños distintos.");
            } else {
                try {
                    RandomAccessFile leer1 = new RandomAccessFile(archivo1,"r");
                    RandomAccessFile leer2 = new RandomAccessFile(archivo2,"r");
                    boolean iguales = true;

                    for (int i = 0; i < leer1.length() ; i++) {
                        byte byte1 = leer1.readByte();
                        byte byte2 = leer2.readByte();
                        if (byte1 != byte2) {
                            i = (int) leer1.length()+1;
                            iguales = false;
                        }
                    }

                    if (iguales) {
                        System.out.println("Los ficheros son iguales.");
                    } else {
                        System.out.println("Los ficheros son distintos");
                    }
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
        }
    }
}
