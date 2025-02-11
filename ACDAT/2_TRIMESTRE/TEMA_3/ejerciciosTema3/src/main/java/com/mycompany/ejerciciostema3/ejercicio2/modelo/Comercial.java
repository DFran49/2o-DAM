/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ejerciciostema3.ejercicio2.modelo;

import jakarta.persistence.*;
import java.math.BigDecimal;

/**
 *
 * @author DFran49
 */
@Entity
@Table(name = "comercial")
public class Comercial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private short id;
    
    @Column(name = "nombre", length = 20, nullable = false)
    private String nombre;
    @Column(name = "apellido1", length = 20, nullable = false)
    private String apellido1;
    @Column(name = "apellido2", length = 20, nullable = true)
    private String apellido2;
    @Column(name = "comision", nullable = true, precision = 3, scale = 2)
    private BigDecimal comision;

    public Comercial() {
    }

    public Comercial(String nombre, String apellido1, String apellido2, BigDecimal comision) {
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.comision = comision;
    }

    public short getId() {
        return id;
    }

    public void setId(short id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public BigDecimal getComision() {
        return comision;
    }

    public void setComision(BigDecimal comision) {
        this.comision = comision;
    }
    
    
}
