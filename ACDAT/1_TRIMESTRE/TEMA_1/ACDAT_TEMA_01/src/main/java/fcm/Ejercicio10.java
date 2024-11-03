package fcm;

import java.io.*;
import java.util.ArrayList;

public class Ejercicio10 {
    //10. Crear dos archivos binarios distintos, cada uno con diferentes tipos de datos escritos usando
    //FileOutputStream y/o DataOutputStream. Escribir un programa que lea ambos archivos y combine sus datos
    //en un tercer archivo binario. Nota: se valorará que el programa cree los archivos de entrada por sí mismo.

    private static File origen1 = new File("origen1.bin");
    private static File origen2 = new File("origen2.bin");
    private static File destino = new File("destino.bin");

    public static void main(String[] args) {
        origen1.delete();
        origen2.delete();
        destino.delete();
        try {
            origen1.createNewFile();
            origen2.createNewFile();
            destino.createNewFile();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        escribir1();
        escribir2();
        escribirDestino();
        leerDestino();
    }

    private static void escribir1() {
        try (FileOutputStream fo = new FileOutputStream(origen1)) {
            String texto = "Los Pokémon tienen habilidades únicas y movimientos especiales. Cada entrenador busca capturarlos y entrenarlos para ganar batallas y convertirse en campeón.";
            for (int i = 0; i < texto.length(); i++) {
                fo.write((byte) texto.charAt(i));
            }
            fo.flush();
            System.out.println("Datos escritos exitosamente en " + origen1.getName());
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }

    private static void escribir2() {
        ArrayList<String> oraciones = new ArrayList<>();
        oraciones.add("Link, el héroe elegido, debe explorar mazmorras, resolver acertijos y enfrentar enemigos para salvar Hyrule y rescatar a la princesa Zelda.");
        oraciones.add("En su aventura, Link reúne artefactos místicos, atraviesa tierras peligrosas y combate al malvado Ganon para restaurar la paz en Hyrule.");
        oraciones.add("Con valor y sabiduría, Link recorre vastos reinos, encuentra aliados y descubre secretos ancestrales para proteger el reino de Hyrule.");
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(origen2))) {
            for (String s : oraciones) {
                dos.writeUTF(s);
            }
            dos.flush();
            System.out.println("Datos escritos en el archivo correctamente.");
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }

    private static void escribirDestino() {
        try (BufferedInputStream bis1 = new BufferedInputStream(new FileInputStream(origen1));
             BufferedInputStream bis2 = new BufferedInputStream(new FileInputStream(origen2));
             BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(destino))) {
            byte[] imageBytes = bis1.readAllBytes();
            bos.write(imageBytes);
            System.out.println("La imagen ha sido copiada usando readAllBytes.");
            bis1.close();
            byte[] imageBytes2 = bis2.readAllBytes();
            bos.write(imageBytes2);
            bis2.close();
        } catch (IOException e) {
            System.err.println("Ocurrió un error durante la copia de los ficheros: " + e.getMessage());
        }
    }

    private static void leerDestino() {
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(destino))) {
            byte[] imageBytes = bis.readAllBytes();
            for (byte b : imageBytes) {
                System.out.print((char)b);
            }
        } catch (IOException e) {
            System.err.println("Ocurrió un error durante la lectura del archivo: " + e.getMessage());
        }
    }
}
