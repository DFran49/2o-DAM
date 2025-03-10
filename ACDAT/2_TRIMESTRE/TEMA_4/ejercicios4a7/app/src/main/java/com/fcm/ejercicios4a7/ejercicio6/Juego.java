/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fcm.ejercicios4a7.ejercicio6;

/**
 *
 * @author allae
 */
public class Juego {
    private String titulo;
    private String genero;
    private double precio;
    private String fechaLanzamiento;

    // Constructor
    public Juego(String titulo, String genero, double precio, String fechaLanzamiento) {
        this.titulo = titulo;
        this.genero = genero;
        this.precio = precio;
        this.fechaLanzamiento = fechaLanzamiento;
    }

    // Getters y Setters
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    public void setFechaLanzamiento(String fechaLanzamiento) {
        this.fechaLanzamiento = fechaLanzamiento;
    }
}