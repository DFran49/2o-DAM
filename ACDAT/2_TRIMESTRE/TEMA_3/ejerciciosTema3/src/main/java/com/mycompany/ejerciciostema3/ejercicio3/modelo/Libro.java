/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ejerciciostema3.ejercicio3.modelo;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author DFran49
 */
@Entity
@Table(name = "libro")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private short id;
    
    @Column(name = "titulo", length = 100, nullable = false)
    private String titulo;
    
    @OneToOne(mappedBy = "libro", fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_editorial")
    private Editorial editorial;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_autor")
    private Autor autor;
    
    @ManyToMany(mappedBy = "libros")
    private Set<Biblioteca> bibliotecas = new HashSet<>();

    public Libro() {
    }

    public Libro(String titulo, Autor autor) {
        this.titulo = titulo;
        this.autor = autor;
        autor.agregarLibro(this);
    }

    public short getId() {
        return id;
    }

    public void setId(short id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Editorial getEditorial() {
        return editorial;
    }

    public void setEditorial(Editorial editorial) {
        this.editorial = editorial;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Set<Biblioteca> getBibliotecas() {
        return bibliotecas;
    }

    public void setBibliotecas(Set<Biblioteca> bibliotecas) {
        this.bibliotecas = bibliotecas;
    }
    
}
