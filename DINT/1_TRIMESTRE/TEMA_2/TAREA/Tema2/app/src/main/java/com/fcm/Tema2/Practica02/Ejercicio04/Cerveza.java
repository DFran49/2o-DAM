/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fcm.Tema2.Practica02.Ejercicio04;

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
        private int indice = -1;
        
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

        public int getIndice() {
            return indice;
        }

        public void setIndice(int indice) {
            this.indice = indice;
        }
        
        public String mostrarInfo() {
            return "Imagen:" + this.imagen.getUrl() +
                    "\n Nombre: " + this.nombre +
                    "\n Nacionalidad: " + this.nacionalidad +
                    "\n Grados: " + this.grados;
        }
}
