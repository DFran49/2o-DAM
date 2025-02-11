/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ejerciciostema3.ejercicio1.modelo;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 *
 * @author DFran49
 */
@Entity
@Table(name = "alumno")
public class Alumno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "nombre", length = 50, nullable = false)
    private String nombre;
    @Column(name = "direccion", length = 50, nullable = true)
    private String direccion;
    @Column(name = "fechaNacimiento", nullable = false)
    private LocalDate fechaNacimiento;
    @Column(name = "notaMedia", nullable = false, precision = 3, scale = 1)
    private BigDecimal notaMedia;

    public Alumno() {  }

    public Alumno(String nombre, String direccion, LocalDate fechaNacimiento, BigDecimal notaMedia) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.fechaNacimiento = fechaNacimiento;
        this.notaMedia = notaMedia;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public BigDecimal getNotaMedia() {
        return notaMedia;
    }

    public void setNotaMedia(BigDecimal notaMedia) {
        this.notaMedia = notaMedia;
    }
    
    
}
