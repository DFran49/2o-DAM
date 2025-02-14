/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ejerciciostema3.ejercicio6;

import com.mycompany.ejerciciostema3.ejercicio3.modelo.Autor;
import com.mycompany.ejerciciostema3.ejercicio3.modelo.Biblioteca;
import com.mycompany.ejerciciostema3.ejercicio3.modelo.Editorial;
import com.mycompany.ejerciciostema3.ejercicio3.modelo.Libro;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.Scanner;

/**
 *
 * @author Francisco
 */
public class Lanzador6 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestion_biblioteca_persistence");
        
        try (EntityManager em = emf.createEntityManager()) {
            int opcion = 1;
            
            while (opcion >= 1 && opcion <= 5) {                
                System.out.println("""
                                   SELECCIONE UNA OPCIÓN:
                                   1. Crear un autor.
                                   2. Crear un libro.
                                   3. Crear una editorial.
                                   4. Crear una biblioteca.
                                   5. Añadir un libro a una biblioteca.
                                   0. Salir
                                   """);
                String x = new Scanner(System.in).nextLine();
                if (x.matches("[0-5]")) {
                    opcion = Integer.parseInt(x);
                    switch (opcion) {
                        case 1 -> {
                            crearAutor(em);
                        }
                        case 2 -> {
                            crearLibro(em);
                        }
                        case 3 -> {
                            crearEditorial(em);
                        }
                        case 4 -> {
                            crearBiblioteca(em);
                        }
                        case 5 -> {
                            añadirLibro(em);
                        }
                        default -> {
                            System.out.println("Adiós.");
                            em.close();
                            emf.close();
                        }
                    }
                } else {
                    System.out.println("Esa opción no existe, intentelo de nuevo.");
                }
            }
        } catch (Exception e) {
            System.out.println("Error al persistir el objeto: " + e.getMessage());
        }
    }
    
    private static void crearAutor(EntityManager em) {
        em.getTransaction().begin();
        System.out.println("---------AUTOR----------");
        System.out.print("Ingrese el nombre: ");
        String aNombre = new Scanner(System.in).nextLine();

        Autor a = new Autor(aNombre);
        em.persist(a);
        em.getTransaction().commit();
        System.out.println("Autor - " + a.getNombre() + " - Insertado");
    }
    
    private static void crearLibro(EntityManager em) {
        em.getTransaction().begin();
        System.out.println("---------LIBRO----------");
        System.out.print("Ingrese el título: ");
        String lTitulo = new Scanner(System.in).nextLine();

        System.out.print("Ingrese el id del autor: ");
        int lAutor = new Scanner(System.in).nextInt();
        
        Autor a = em.find(Autor.class, lAutor);

        if (a == null) {
            System.err.println("No se ha encontrado ningun autor con el id: " + lAutor);
        } else {
            Libro l = new Libro(lTitulo,a);
            em.persist(a);
            System.out.println("Libro - " + l.getTitulo() + " - Insertado");
        }
        em.getTransaction().commit();
    }
    
    private static void crearEditorial(EntityManager em) {
        em.getTransaction().begin();
        System.out.println("---------EDITORIAL----------");
        System.out.print("Ingrese el nombre de la editorial: ");
        String eNombre = new Scanner(System.in).nextLine();

        System.out.print("Ingrese el id del libro: ");
        int eLibro = new Scanner(System.in).nextInt();
        Libro l = em.find(Libro.class, eLibro);
        
        if (l == null) {
            System.err.println("No se ha encontrado ningun libro con el id: " + eLibro);
        } else {
            Editorial e = new Editorial(eNombre,l);
            em.persist(e);
            System.out.println("Libro - " + e.getLibro() + " - Insertado en la editorial: " + e.getNombre());
        }
        em.getTransaction().commit();
    }
    
    private static void crearBiblioteca(EntityManager em) {
        em.getTransaction().begin();
        System.out.println("---------BIBLIOTECA----------");
        System.out.print("Ingrese el nombre de la biblioteca: ");
        String bNombre = new Scanner(System.in).nextLine();

        Biblioteca b = new Biblioteca(bNombre);
        em.persist(b);
        em.getTransaction().commit();
        System.out.println("Biblioteca - " + b.getNombre() + " - Insertada");
    }
    
    private static void añadirLibro(EntityManager em) {
        em.getTransaction().begin();
        System.out.println("---------AÑADIR LIBRO A BIBLIOTECA----------");
        System.out.print("Ingrese el id de la biblioteca: ");
        int bId = new Scanner(System.in).nextInt();
        Biblioteca b = em.find(Biblioteca.class, bId);

        System.out.print("Ingrese el id del libro a añadir: ");
        int bLibro = new Scanner(System.in).nextInt();
        
        Libro l = em.find(Libro.class, bLibro);
        if (b == null) {
            System.err.println("No se ha encontrado ninguna biblioteca con el id: " + bId);
        } else {
            if (l == null) {
                System.err.println("No se ha encontrado ningun libro con el id: " + bLibro);
            } else {
                b.getLibros().add(l);
                em.persist(b);
                System.out.println("Libro - " + l.getTitulo() + " - Insertado en la biblioteca: " + b.getNombre());
            }
        }
        em.getTransaction().commit();
    }
}
