/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ejerciciostema3.ejercicio1;

import com.mycompany.ejerciciostema3.ejercicio1.modelo.Alumno;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 *
 * @author DFran49
 */
public class Lanzador1 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("alumnos_persistence");
        
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Alumno alumno = new Alumno("Fulgencio", "C/Falsa", LocalDate.now(), new BigDecimal(5.7));
            em.persist(alumno);
            em.getTransaction().commit();
            em.close();
            emf.close();
        } catch (Exception e) {
            System.out.println("Error al persistir el objeto: " + e.getMessage());
        }
    }
}
