/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ejerciciostema3.ejercicio3;

import com.mycompany.ejerciciostema3.ejercicio3.modelo.Autor;
import com.mycompany.ejerciciostema3.ejercicio3.modelo.Biblioteca;
import com.mycompany.ejerciciostema3.ejercicio3.modelo.Editorial;
import com.mycompany.ejerciciostema3.ejercicio3.modelo.Libro;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 *
 * @author DFran49
 */
public class Lanzador3 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestion_biblioteca_persistence");
        
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Autor autor = new Autor("Carlos");
            
            Libro libro1 = new Libro("La sombra del viento",autor);
            Libro libro2 = new Libro("Maravillosas aventuras de JPA",autor);
            
            Editorial editorial = new Editorial("Santillana",libro1);
            Editorial editorial2 = new Editorial("MultiLibros",libro2);
            
            Biblioteca biblioteca = new Biblioteca("Local de Granada");
            biblioteca.getLibros().add(libro1);
            biblioteca.getLibros().add(libro2);
            
            em.persist(autor);
            em.persist(libro1);
            em.persist(libro2);
            em.persist(editorial);
            em.persist(editorial2);
            em.persist(biblioteca);
            
            em.getTransaction().commit();
            em.close();
            emf.close();
        } catch (Exception e) {
            System.out.println("Error al persistir el objeto: " + e.getMessage());
        }
    }
}
