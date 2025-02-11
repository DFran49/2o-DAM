/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ejerciciostema3.ejercicio11.modelo;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DFran49
 */
@Entity
@Table(name = "taxi")
@NamedQueries({
    @NamedQuery(name = "Taxi.TaxisDisponibles", query = "SELECT t FROM Taxi t WHERE t.matricula NOT IN"
                                + "(SELECT j.taxi.matricula FROM Jornada j WHERE j.fechaFin IS NULL)")
})
public class Taxi {
    @Id
    @Column(name = "matricula", length = 7)
    private String matricula;
    
    @Column(name = "marca", length = 20, nullable = false)
    private String marca;
    @Column(name = "modelo", length = 20, nullable = false)
    private String modelo;
    @Column(name = "precio", nullable = false, precision = 5, scale = 2)
    private BigDecimal precio;
    @Column(name = "plazas", nullable = false)
    private byte plazas;
    
    @OneToMany(mappedBy = "taxi", cascade = CascadeType.ALL)
    private List<Jornada> jornadas = new ArrayList<>();

    public Taxi() {
    }

    public Taxi(String matricula, String marca, String modelo, BigDecimal precio, int plazas) {
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.precio = precio;
        this.plazas = (byte)plazas;
    }
    
    public void agregarJornada(Jornada jornada) {
        jornadas.add(jornada);
        jornada.setTaxi(this);
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

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public byte getPlazas() {
        return plazas;
    }

    public void setPlazas(byte plazas) {
        this.plazas = plazas;
    }

    public List<Jornada> getJornadas() {
        return jornadas;
    }

    public void setJornadas(List<Jornada> jornadas) {
        this.jornadas = jornadas;
    }
    
    
}
