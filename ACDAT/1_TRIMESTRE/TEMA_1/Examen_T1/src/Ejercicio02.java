import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Ejercicio02 {
    public static void main(String[] args) {
        File calculosTrigonometricos = new File("trigonometrics.bin");
        System.out.println("Gestión de ángulos\n" +
                "1. - Crear fichero.\n" +
                "2.- Buscar ángulo.\n" +
                "0.- Salir");
        int opcion = new Scanner(System.in).nextInt();

        try (RandomAccessFile raTrigo = new RandomAccessFile(calculosTrigonometricos, "rw")) {
            switch (opcion) {
                case 1 -> {
                    if (!calculosTrigonometricos.exists()) {
                        try {
                            calculosTrigonometricos.createNewFile();
                        } catch (IOException e) {
                            System.err.println(e.getMessage());
                        }
                    }

                        raTrigo.setLength(0);
                        for (int i = 0; i <= 360; i++) {
                            double seno = Math.sin(Math.toRadians(i));
                            double coseno = Math.sin(Math.toRadians(i));
                            raTrigo.writeInt(i);
                            raTrigo.writeDouble(seno);
                            raTrigo.writeDouble(coseno);
                        }

                }
                case 2 -> {
                    try {
                        System.out.println("Introduzca un ángulo a buscar.");
                        int angulo = -1;
                        try {
                            angulo = new Scanner(System.in).nextInt();
                        } catch (Exception e) {
                            System.err.println("Se debe introducir un número entero.");
                        }
                        if (angulo < 0 || angulo > 360) {
                            System.err.println("El ángulo elegido debe estar entre 0 y 360");
                        } else {
                            //Se averigua la posición de ese ángulo multiplicandolo por la longitud de las combinaciones
                            //Cada combinación son 20 (4+8+8) bytes
                            raTrigo.seek(20*angulo);
                            System.out.println(raTrigo.readInt());
                            System.out.println(raTrigo.readDouble());
                            System.out.println(raTrigo.readDouble());
                        }
                    } catch (Exception e) {
                        System.err.println("ERROR! El fichero no existe, use la opción 1.");
                    }
                    if (!calculosTrigonometricos.exists()) {
                        System.err.println("ERROR! El fichero no existe, use la opción 1.");
                    }
                }
                default -> System.out.println("Hasta la próxima.");
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
