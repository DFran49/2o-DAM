/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package com.fcm.ejercicios4a7.ejercicio5;

/**
 *
 * @author allae
 */
import java.time.LocalDate;
import org.bson.Document;

record Cliente(String id, String nombre, String email, String telefono, LocalDate fechaRegistro) {
    public Cliente(String id, String nombre, String email, String telefono) {
        this(id, nombre, email, telefono, LocalDate.now());
    }

    public Document toDocument() {
        return new Document("id", id)
                .append("nombre", nombre)
                .append("email", email)
                .append("telefono", telefono)
                .append("fechaRegistro", fechaRegistro.toString());
    }

    public static Cliente fromDocument(Document doc) {
        return new Cliente(
                doc.getString("id"),
                doc.getString("nombre"),
                doc.getString("email"),
                doc.getString("telefono"),
                LocalDate.parse(doc.getString("fechaRegistro"))
        );
    }
}
