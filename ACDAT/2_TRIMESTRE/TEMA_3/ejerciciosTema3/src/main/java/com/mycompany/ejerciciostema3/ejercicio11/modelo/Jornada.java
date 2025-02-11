/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ejerciciostema3.ejercicio11.modelo;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * @author DFran49
 */
@Entity
@Table(name = "jornada")
public class Jornada {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "id_taxista", nullable = false)
    private Taxista taxista;
    
    @ManyToOne
    @JoinColumn(name = "id_taxi", nullable = false)
    private Taxi taxi;
    
    @Column(name = "fechaInicio", nullable = false)
    private LocalDateTime fechaInicio;
    @Column(name = "fechaFin", nullable = true)
    private LocalDateTime fechaFin;

    public Jornada() {
    }

    public Jornada(Taxista taxista, Taxi taxi) {
        this.taxista = taxista;
        this.taxi = taxi;
        taxista.agregarJornada(this);
        taxi.agregarJornada(this);
    }
    
    @PrePersist
    public void prePersist() {
        this.fechaInicio = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Taxista getTaxista() {
        return taxista;
    }

    public void setTaxista(Taxista taxista) {
        this.taxista = taxista;
    }

    public Taxi getTaxi() {
        return taxi;
    }

    public void setTaxi(Taxi taxi) {
        this.taxi = taxi;
    }

    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDateTime fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDateTime getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDateTime fechaFin) {
        this.fechaFin = fechaFin;
    }

}
