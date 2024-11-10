/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pasovariablesv3;

/**
 *
 * @author Molina
 */
public class Datos {
    String dato;
    Integer año;

    public Datos(String dato, Integer año) {
        this.dato = dato;
        this.año = año;
    }

    public String getDato() {
        return dato;
    }

    public void setDato(String dato) {
        this.dato = dato;
    }

    public Integer getAño() {
        return año;
    }

    public void setAño(Integer año) {
        this.año = año;
    }

    @Override
    public String toString() {
        return "Datos{" + "dato=" + dato + ", a\u00f1o=" + año + '}';
    }

   
    
}
