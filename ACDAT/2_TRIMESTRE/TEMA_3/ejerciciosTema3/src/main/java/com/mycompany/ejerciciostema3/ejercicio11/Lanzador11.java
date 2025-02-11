/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ejerciciostema3.ejercicio11;

import com.mycompany.ejerciciostema3.ejercicio11.modelo.Jornada;
import com.mycompany.ejerciciostema3.ejercicio11.modelo.Taxi;
import com.mycompany.ejerciciostema3.ejercicio11.modelo.Taxista;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 *
 * @author DFran49
 */
public class Lanzador11 {
    public static void main(String[] args) {
        int opcion = 1;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("flota_persistence");
        EntityManager em = emf.createEntityManager();
        while (opcion >= 1 && opcion <= 7) {            
            System.out.println("""
                    -----------------------------------------------
                        1. Alta de nuevo taxista.
                        2. Alta de nuevo taxi.
                        3. Comienzo de la jornada taxista.
                        4. Fin de la jornada taxista.
                        5. Información de un taxista y su taxi.
                        6. Mostrar taxistas trabajando.
                        7. Mostrar taxistas fuera de servicio.
                        8. Salir.
                    -----------------------------------------------""");
            opcion = new Scanner(System.in).nextInt();
            
            switch (opcion) {
                case 1 -> {
                    System.out.println("Opción elegida: Dar de alta nuevo taxista.");
                    System.out.print("Introduce el DNI (Con letra): ");
                    String dni = new Scanner(System.in).nextLine();

                    System.out.print("Introduce el nombre (máx. 20 caracteres): ");
                    String nombre = new Scanner(System.in).nextLine();

                    System.out.print("Introduce la fecha de nacimiento (formato YYYY-MM-DD): ");
                    LocalDate fechaNacimiento = LocalDate.parse(new Scanner(System.in).nextLine(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    Taxista t = new Taxista(dni, nombre, fechaNacimiento);
                    
                    em.getTransaction().begin();
                    try {
                        em.persist(t);
                        em.getTransaction().commit();
                    } catch (Exception e) {
                        em.getTransaction().rollback();
                        System.out.println("Error al persistir el objeto: " + e.getMessage());
                    }
                }
                case 2 -> {
                    System.out.print("Introduce la matrícula (7 caracteres): ");
                    String matricula = new Scanner(System.in).nextLine();

                    System.out.print("Introduce la marca (máx. 20 caracteres): ");
                    String marca = new Scanner(System.in).nextLine();

                    System.out.print("Introduce el modelo (máx. 20 caracteres): ");
                    String modelo = new Scanner(System.in).nextLine();

                    System.out.print("Introduce el precio (hasta 5 dígitos con 2 decimales): ");
                    BigDecimal precio = new Scanner(System.in).nextBigDecimal();

                    System.out.print("Introduce el número de plazas: ");
                    byte plazas = new Scanner(System.in).nextByte();
                    
                    Taxi t = new Taxi(matricula, marca, modelo, precio, plazas);
                    
                    em.getTransaction().begin();
                    try {
                        em.persist(t);
                        em.getTransaction().commit();
                    } catch (Exception e) {
                        em.getTransaction().rollback();
                        System.out.println("Error al persistir el objeto: " + e.getMessage());
                    }
                }
                case 3 -> {
                    System.out.println("Introduce el DNI del taxista que va a iniciar la jornada");
                    String dni = new Scanner(System.in).nextLine();
                    em.getTransaction().begin();
                    Taxista taxista = em.find(Taxista.class, dni);
                    
                    Taxi taxi = null;
                    try {
                        taxi = em.createNamedQuery("Taxi.TaxisDisponibles", Taxi.class).getResultList().get(0);
                    } catch (Exception e) {
                        System.err.println("No hay taxis disponibles.");
                    }
                       
                    if (taxi != null) {
                        Jornada j = new Jornada(taxista,taxi);     
                        try {
                            em.persist(j);
                            em.persist(taxi);
                            em.persist(taxista);
                            em.getTransaction().commit();
                        } catch (Exception e) {
                            em.getTransaction().rollback();
                            System.out.println("Error al persistir el objeto: " + e.getMessage());
                        }
                    }
                }
                case 4 -> {
                    
                }
                case 5 -> {
                    
                }
                case 6 -> {
                    
                }
                case 7 -> {
                    
                }
                default -> {
                    System.out.println("Adios.");
                    em.close();
                    emf.close();
                }
            }
        }
        
        
        
        try {
            /*em.getTransaction().begin();
            Taxi t = new Taxi("1234ABC","Opel","Corsa",new BigDecimal(12.3),4);
            Taxista ts = new Taxista("12345678A","Roberto",LocalDate.of(1989, 7, 10));
            em.persist(t);
            em.persist(ts);
            
            Jornada j = new Jornada(ts,t);
            
            em.persist(j);
            em.persist(t);
            em.persist(ts);*/
            
            //em.getTransaction().commit();
            
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("Error al persistir el objeto: " + e.getMessage());
        } finally {
            em.close();
            emf.close();
        }
    }
}
