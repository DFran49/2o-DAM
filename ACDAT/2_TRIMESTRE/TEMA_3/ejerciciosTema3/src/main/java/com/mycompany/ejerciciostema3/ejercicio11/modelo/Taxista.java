/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ejerciciostema3.ejercicio11.modelo;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DFran49
 */
@Entity
@Table(name = "taxista")
public class Taxista {
    @Id
    @Column(name = "dni", length = 9)
    private String dni;
    
    @Column(name = "nombre", length = 20, nullable = false)
    private String nombre;
    @Column(name = "fechaNacimiento", nullable = false)
    private LocalDate fechaNacimiento;
    
    @OneToMany(mappedBy = "taxista", cascade = CascadeType.ALL)
    private List<Jornada> jornadas = new ArrayList<>();

    public Taxista() {
    }

    public Taxista(String dni, String nombre, LocalDate fechaNacimiento) {
        this.dni = dni;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
    }
    
    public void agregarJornada(Jornada jornada) {
        jornadas.add(jornada);
        jornada.setTaxista(this);
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public List<Jornada> getJornadas() {
        return jornadas;
    }

    public void setJornadas(List<Jornada> jornadas) {
        this.jornadas = jornadas;
    }
    
    
}
