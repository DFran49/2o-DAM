/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fcm.ejercicios4a7.ejercicio4;

import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import java.time.LocalDate;
import java.util.Scanner;

/**
 *
 * @author allae
 */
public class Ejercicio4 {
    private static final String URI = "mongodb://root:root@localhost:27017";
    private static final String DB_NAME = "gestionClientes";
    private static final String COLLECTION_NAME = "clientes";
    private static final MongoClient mongoClient = MongoClients.create(URI);
    private static final MongoDatabase database = mongoClient.getDatabase(DB_NAME);
    private static final MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        do {
            mostrarMenu();
            opcion = scanner.nextInt();
            scanner.nextLine();
            switch (opcion) {
                case 1 -> crearCliente(scanner);
                case 2 -> mostrarCliente(scanner);
                case 3 -> actualizarCliente(scanner);
                case 4 -> eliminarCliente(scanner);
                case 5 -> mostrarTodosLosClientes();
                case 0 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
        scanner.close();
        mongoClient.close();
    }

    private static void mostrarMenu() {
        System.out.println("|---- Menú CRUD de clientes -----|");
        System.out.println("| 1. Crear cliente.              |");
        System.out.println("| 2. Mostrar cliente.            |");
        System.out.println("| 3. Actualizar cliente.         |");
        System.out.println("| 4. Eliminar cliente.           |");
        System.out.println("| 5. Mostrar todos los clientes. |");
        System.out.println("| 0. Salir.                      |");
        System.out.println("|--------------------------------|");
        System.out.print("Seleccione una opción: ");
    }

    private static void crearCliente(Scanner scanner) {
        System.out.print("Ingrese ID: ");
        String id = scanner.nextLine();
        System.out.print("Ingrese nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese email: ");
        String email = scanner.nextLine();
        System.out.print("Ingrese teléfono: ");
        String telefono = scanner.nextLine();

        Cliente cliente = new Cliente(id, nombre, email, telefono);
        collection.insertOne(cliente.toDocument());
        System.out.println("Cliente creado exitosamente.");
    }

    private static void mostrarCliente(Scanner scanner) {
        System.out.print("Ingrese ID del cliente: ");
        String id = scanner.nextLine();
        Document doc = collection.find(Filters.eq("id", id)).first();
        if (doc != null) {
            Cliente cliente = Cliente.fromDocument(doc);
            System.out.println(cliente);
        } else {
            System.out.println("Cliente no encontrado.");
        }
    }

    private static void actualizarCliente(Scanner scanner) {
        System.out.print("Ingrese ID del cliente a actualizar: ");
        String id = scanner.nextLine();
        Document doc = collection.find(Filters.eq("id", id)).first();
        if (doc != null) {
            System.out.print("Ingrese nuevo nombre: ");
            String nombre = scanner.nextLine();
            System.out.print("Ingrese nuevo email: ");
            String email = scanner.nextLine();
            System.out.print("Ingrese nuevo teléfono: ");
            String telefono = scanner.nextLine();
            
            Cliente clienteActualizado = new Cliente(id, nombre, email, telefono);
            Document update = new Document("$set", clienteActualizado.toDocument());
            collection.updateOne(Filters.eq("id", id), update);
            System.out.println("Cliente actualizado.");
        } else {
            System.out.println("Cliente no encontrado.");
        }
    }

    private static void eliminarCliente(Scanner scanner) {
        System.out.print("Ingrese ID del cliente a eliminar: ");
        String id = scanner.nextLine();
        collection.deleteOne(Filters.eq("id", id));
        System.out.println("Cliente eliminado.");
    }

    private static void mostrarTodosLosClientes() {
        for (Document doc : collection.find()) {
            Cliente cliente = Cliente.fromDocument(doc);
            System.out.println(cliente);
        }
    }
}