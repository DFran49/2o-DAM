/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ejerciciostema3.ejercicio2.modelo;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 *
 * @author DFran49
 */
@Entity
@Table(name = "pedido")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private short id;
    
    @Column(name = "total", nullable = false, precision = 7, scale = 2)
    private BigDecimal total;
    @Column(name = "fecha", nullable = true)
    private LocalDate fecha;
    
    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;
    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "id_comercial")
    private Comercial comercial;

    public Pedido() {
    }

    public Pedido(BigDecimal total, LocalDate fecha, Cliente cliente, Comercial comercial) {
        this.total = total;
        this.fecha = fecha;
        this.cliente = cliente;
        this.comercial = comercial;
    }

    public short getId() {
        return id;
    }

    public void setId(short id) {
        this.id = id;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Comercial getComercial() {
        return comercial;
    }

    public void setComercial(Comercial comercial) {
        this.comercial = comercial;
    }
    
    
}
