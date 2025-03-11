/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fcm.ejercicios4a7.ejercicio4;

import java.time.LocalDate;
import org.bson.Document;

/**
 *
 * @author Francisco
 */
class Cliente {
    private String id;
    private String nombre;
    private String email;
    private String telefono;
    private LocalDate fechaRegistro;

    public Cliente(String id, String nombre, String email, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.fechaRegistro = LocalDate.now();
    }

    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public String getEmail() { return email; }
    public String getTelefono() { return telefono; }
    public LocalDate getFechaRegistro() { return fechaRegistro; }

    public Document toDocument() {
        return new Document("id", id)
                .append("nombre", nombre)
                .append("email", email)
                .append("telefono", telefono)
                .append("fechaRegistro", fechaRegistro.toString());
    }

    public static Cliente fromDocument(Document doc) {
        return new Cliente(doc.getString("id"), doc.getString("nombre"), doc.getString("email"), doc.getString("telefono"));
    }

    @Override
    public String toString() {
        return "Cliente{id='" + id + "', nombre='" + nombre + "', email='" + email + "', telefono='" + telefono + "', fechaRegistro=" + fechaRegistro + "}";
    }
}
