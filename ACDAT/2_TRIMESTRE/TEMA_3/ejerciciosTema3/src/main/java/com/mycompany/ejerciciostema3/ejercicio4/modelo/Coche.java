/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ejerciciostema3.ejercicio4.modelo;

import jakarta.persistence.*;

/**
 *
 * @author DFran49
 */
@Entity
@Table(name = "coche")
public class Coche {
    @Id
    @Column(name = "matricula", length = 7)
    private String matricula;
    
    @Column(name = "marca", length = 20, nullable = false)
    private String marca;
    @Column(name = "modelo", length = 20, nullable = false)
    private String modelo;
    @Column(name = "plazas", nullable = false)
    private byte plazas;

    public Coche() {
    }

    public Coche(String matricula, String marca, String modelo, byte plazas) {
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.plazas = plazas;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public byte getPlazas() {
        return plazas;
    }

    public void setPlazas(byte plazas) {
        this.plazas = plazas;
    }
    
    
    
}
