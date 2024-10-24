/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.javafx.R2_Básicos;

import javafx.scene.image.Image;

/**
 *
 * @author Molina
 */
class CervezaImagen {

    private String nombre;
    private Image imagen;
    private String pais;
    private double graduacion;

    public CervezaImagen(String nombre, Image imagen, String pais, double graduacion) {
        this.nombre = nombre;
        this.imagen = imagen;
        this.pais = pais;
        this.graduacion = graduacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Image getImagen() {
        return imagen;
    }

    public void setImagen(Image imagen) {
        this.imagen = imagen;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public double getGraduacion() {
        return graduacion;
    }

    public void setGraduacion(double graduacion) {
        this.graduacion = graduacion;
    }

}
