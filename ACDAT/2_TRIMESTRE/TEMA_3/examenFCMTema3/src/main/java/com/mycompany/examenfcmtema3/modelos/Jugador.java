/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.examenfcmtema3.modelos;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 *
 * @author Francisco
 */
@Entity
@Table(name = "jugador")
@NamedQueries({
    @NamedQuery(name = "Jugador.Estadisticas", query = "SELECT COUNT(*), AVG(j.edad), MAX(j.valorMercado), MIN(j.valorMercado) FROM Jugador j")
})
public class Jugador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_jugador")
    private int id;
    
    @Column(name = "edad", nullable = false, columnDefinition = "TINYINT UNSIGNED")
    private byte edad;
    @Column(name = "fecha_contratacion", nullable = true)
    private LocalDate fechaContratacion;
    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;
    @Column(name = "posicion", nullable = false, length = 50)
    private String posicion;
    @Column(name = "valor_mercado", nullable = false, precision = 12, scale = 2)
    private BigDecimal valorMercado;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_equipo")
    private Equipo equipo;

    public Jugador() {
    }

    public Jugador(byte edad, LocalDate fechaContratacion, String nombre, String posicion, BigDecimal valorMercado, Equipo equipo) {
        this.edad = edad;
        this.fechaContratacion = fechaContratacion;
        this.nombre = nombre;
        this.posicion = posicion;
        this.valorMercado = valorMercado;
        equipo.agregarJugador(this);
        this.equipo = equipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte getEdad() {
        return edad;
    }

    public void setEdad(byte edad) {
        this.edad = edad;
    }

    public LocalDate getFechaContratacion() {
        return fechaContratacion;
    }

    public void setFechaContratacion(LocalDate fechaContratacion) {
        this.fechaContratacion = fechaContratacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public BigDecimal getValorMercado() {
        return valorMercado;
    }

    public void setValorMercado(BigDecimal valorMercado) {
        this.valorMercado = valorMercado;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }
    
    
}
