/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ejerciciostema3.ejercicio7;

import com.mycompany.ejerciciostema3.ejercicio4.modelo.Coche;
import jakarta.persistence.*;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author DFran49
 */
public class Lanzador7 {
    public static void main(String[] args) {
        System.out.println("Introduzca la matrícula del coche a buscar:");
        String matricula = new Scanner(System.in).nextLine();
        
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("coches_persistence");
            EntityManager em = emf.createEntityManager();
            
            Coche coche = em.createQuery("SELECT c FROM Coche c WHERE c.matricula = :matricula",Coche.class)
                    .setParameter("matricula", matricula).getSingleResult();
            
            System.out.println("--------------------------------------\n");
            System.out.println(coche.getMarca() + " : " + coche.getModelo() + " : " + coche.getPlazas());
            System.out.println("\n--------------------------------------");
            
            em.close();
            emf.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
