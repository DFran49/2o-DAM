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
    private static MongoClient clienteMongo = MongoClients.create("mongodb://root:root@localhost:27017");
    private static MongoDatabase baseDatos = clienteMongo.getDatabase("Ejercicio4");
    private static final MongoCollection<Document> coleccionClientes = baseDatos.getCollection("clientes");

    public static void main(String[] args) {
        int eleccion = 0;
        do {
            
            System.out.println("\n┌---- Menú CRUD de clientes -----┐");
            System.out.println("│ 1. Crear cliente.              │");
            System.out.println("│ 2. Mostrar cliente.            │");
            System.out.println("│ 3. Actualizar cliente.         │");
            System.out.println("│ 4. Eliminar cliente.           │");
            System.out.println("│ 5. Mostrar todos los clientes. │");
            System.out.println("│ 0. Salir.                      │");
            System.out.println("└--------------------------------┘");
            System.out.print("Seleccione una opción: ");
            
            try {
                eleccion = new Scanner(System.in).nextInt();

                switch (eleccion) {
                    case 1 -> agregarCliente();
                    case 2 -> buscarCliente();
                    case 3 -> modificarCliente();
                    case 4 -> borrarCliente();
                    case 5 -> listarClientes();
                    case 0 -> System.out.println("Saliendo...");
                    default -> System.out.println("Opción inválida.");
                }
            } catch (Exception e) {
                System.out.println("Error: Entrada inválida. Intente de nuevo.");
            }
        } while (eleccion != 0);

        clienteMongo.close();
    }


    private static void agregarCliente() {
        try {
            System.out.print("Ingrese ID: ");
            String idCliente = new Scanner(System.in).nextLine();
            System.out.print("Ingrese nombre: ");
            String nombreCliente = new Scanner(System.in).nextLine();
            System.out.print("Ingrese email: ");
            String correoCliente = new Scanner(System.in).nextLine();
            System.out.print("Ingrese teléfono: ");
            String telefonoCliente = new Scanner(System.in).nextLine();
            String fechaAlta = LocalDate.now().toString();

            Document nuevoCliente = new Document("_id", idCliente)
                    .append("nombre", nombreCliente)
                    .append("email", correoCliente)
                    .append("telefono", telefonoCliente)
                    .append("fechaRegistro", fechaAlta);

            coleccionClientes.insertOne(nuevoCliente);
            System.out.println("Cliente creado exitosamente.");
        } catch (Exception e) {
            System.out.println("Error al crear cliente: " + e.getMessage());
        }
    }

    private static void buscarCliente() {
        try {
            System.out.print("Ingrese ID del cliente: ");
            String idCliente = new Scanner(System.in).nextLine();
            Document cliente = coleccionClientes.find(Filters.eq("_id", idCliente)).first();

            if (cliente != null) {
                System.out.println(cliente.toJson());
            } else {
                System.out.println("Cliente no encontrado.");
            }
        } catch (Exception e) {
            System.out.println("Error al buscar cliente: " + e.getMessage());
        }
    }

    private static void modificarCliente() {
        try {
            System.out.print("Ingrese ID del cliente a actualizar: ");
            String idCliente = new Scanner(System.in).nextLine();
            Document cliente = coleccionClientes.find(Filters.eq("_id", idCliente)).first();

            if (cliente != null) {
                System.out.print("Nuevo nombre: ");
                String nombreCliente = new Scanner(System.in).nextLine();
                System.out.print("Nuevo email: ");
                String correoCliente = new Scanner(System.in).nextLine();
                System.out.print("Nuevo teléfono: ");
                String telefonoCliente = new Scanner(System.in).nextLine();

                Document actualizacion = new Document("$set", new Document("nombre", nombreCliente)
                        .append("email", correoCliente)
                        .append("telefono", telefonoCliente));

                coleccionClientes.updateOne(Filters.eq("_id", idCliente), actualizacion);
                System.out.println("Cliente actualizado.");
            } else {
                System.out.println("Cliente no encontrado.");
            }
        } catch (Exception e) {
            System.out.println("Error al actualizar cliente: " + e.getMessage());
        }
    }

    private static void borrarCliente() {
        try {
            System.out.print("Ingrese ID del cliente a eliminar: ");
            String idCliente = new Scanner(System.in).nextLine();
            coleccionClientes.deleteOne(Filters.eq("_id", idCliente));
            System.out.println("Cliente eliminado.");
        } catch (Exception e) {
            System.out.println("Error al eliminar cliente: " + e.getMessage());
        }
    }

    private static void listarClientes() {
        try {
            for (Document cliente : coleccionClientes.find()) {
                System.out.println(cliente.toJson());
            }
        } catch (Exception e) {
            System.out.println("Error al mostrar clientes: " + e.getMessage());
        }
    }
}