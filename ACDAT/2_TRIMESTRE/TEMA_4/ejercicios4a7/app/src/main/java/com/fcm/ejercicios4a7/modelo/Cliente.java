/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package com.fcm.ejercicios4a7.modelo;

/**
 *
 * @author allae
 */
import java.time.LocalDate;

public record Cliente(String id, String nombre, String email, String telefono, String fechaRegistro) {

    public Cliente(String id, String nombre, String email, String telefono) {
        this(id, nombre, email, telefono, LocalDate.now().toString());
    }
}
