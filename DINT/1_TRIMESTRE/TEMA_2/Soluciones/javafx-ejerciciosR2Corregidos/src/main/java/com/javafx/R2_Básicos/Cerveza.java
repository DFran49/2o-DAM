/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.javafx.R2_Básicos;

/**
 *
 * @author Molina
 */
class Cerveza {
    private String nombre;
    private String url;
    private String pais;
    private double graduacion;

    public Cerveza(String nombre, String url, String pais, double graduacion) {
        this.nombre = nombre;
        this.url = url;
        this.pais = pais;
        this.graduacion = graduacion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getUrl() {
        return url;
    }

    public String getPais() {
        return pais;
    }

    public double getGraduacion() {
        return graduacion;
    }
}
