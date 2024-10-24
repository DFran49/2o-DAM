/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fcm.Tema2.Practica02.Ejercicio06;

import com.fcm.Tema2.Practica02.Ejercicio05.*;
import com.fcm.Tema2.Practica02.Ejercicio04.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author DFran49
 */
public class Cerveza {
    private Image imagen;
        private String nombre;
        private String nacionalidad;
        private int grados;
        
        public Cerveza(Image imagen, String nombre, String nacionalidad, int grados) {
            this.imagen = imagen;
            this.nombre = nombre;
            this.nacionalidad = nacionalidad;
            this.grados = grados;
        }

        public Image getImagen() {
            return imagen;
        }

        public String getNombre() {
            return nombre;
        }

        public String getNacionalidad() {
            return nacionalidad;
        }

        public int getGrados() {
            return grados;
        }
        
        public String mostrarInfo() {
            return "Imagen:" + this.imagen.getUrl() +
                    "\n Nombre: " + this.nombre +
                    "\n Nacionalidad: " + this.nacionalidad +
                    "\n Grados: " + this.grados;
        }
}
