/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ejerciciostema3.ejercicio4;

import com.mycompany.ejerciciostema3.ejercicio4.modelo.Coche;
import jakarta.persistence.*;
import java.util.Scanner;

/**
 *
 * @author DFran49
 */
public class Lanzador4 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("coches_persistence");
        
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            
            System.out.println("Introduce la matrícula del coche:");
            String mat = new Scanner(System.in).nextLine();
            System.out.println("Introduce la marca del coche:");
            String mar = new Scanner(System.in).nextLine();
            System.out.println("Introduce el modelo del coche:");
            String mod = new Scanner(System.in).nextLine();
            System.out.println("Introduce las plazas del coche:");
            byte plazas = new Scanner(System.in).nextByte();
            
            Coche coche = new Coche(mat,mar,mod,plazas);
            em.persist(coche);
            
            em.getTransaction().commit();
            em.close();
            emf.close();
        } catch (Exception e) {
            System.out.println("Error al persistir el objeto: " + e.getMessage());
        }
    }
}
