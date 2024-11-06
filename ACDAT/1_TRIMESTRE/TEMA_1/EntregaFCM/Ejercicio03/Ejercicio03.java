package Ejercicio03;

import java.io.*;
import java.util.Properties;
import java.util.Scanner;

public class Ejercicio03 {
    public static void main(String[] args) {
        File pokedex = new File("");
        Properties propiedades = new Properties();
        try {
            propiedades.load(new FileReader("config.properties"));
            pokedex = new File(propiedades.getProperty("FicheroConfig"));
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        if (!pokedex.exists()) {
            try {
                pokedex.createNewFile();
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }

        System.out.println("Gestión de Pokémon\n" +
                "1.- Crear Pokémon\n" +
                "2.- Mostrar todos los Pokémon\n" +
                "3.- Buscar pokémon por ID\n" +
                "4.- Actualizar Pokémon\n" +
                "5.- Eliminar Pokémon\n" +
                "0.- Salir");
        int opcion = new Scanner(System.in).nextInt();

            switch (opcion) {
                case 1 -> {
                    System.out.println("Introduzca el id del pokemon");
                    int id = new Scanner(System.in).nextInt();
                    System.out.println("Introduzca el nombre del pokemon");
                    String nombre = new Scanner(System.in).nextLine();
                    System.out.println("Introduzca el tipo del pokemon");
                    String tipo = new Scanner(System.in).nextLine();
                    System.out.println("Introduzca el ataque del pokemon");
                    int atk = new Scanner(System.in).nextInt();
                    System.out.println("Introduzca la vida del pokemon");
                    int hp = new Scanner(System.in).nextInt();
                    try (FileOutputStream fos = new FileOutputStream(pokedex,true);
                         ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                        oos.writeObject(new Pokemon(id,nombre,tipo,atk,hp));
                        oos.flush();
                    } catch (IOException e) {
                        System.err.println(e.getMessage());
                    }
                }
                case 2 -> {
                    try (FileInputStream fis = new FileInputStream(pokedex);
                         ObjectInputStream ois = new ObjectInputStream(fis)) {
                        System.out.println(ois.readObject());
                        System.out.println(ois.readObject());
                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }
                }
                case 3 -> {}
                case 4 -> {}
                case 5 -> {}
                default -> System.out.println("Hasta la próxima.");
            }
    }
}
