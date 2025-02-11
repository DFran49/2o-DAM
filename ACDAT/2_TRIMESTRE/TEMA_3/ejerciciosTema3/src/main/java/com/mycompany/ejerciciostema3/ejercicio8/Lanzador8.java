/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ejerciciostema3.ejercicio8;

import com.mycompany.ejerciciostema3.ejercicio2.modelo.Cliente;
import com.mycompany.ejerciciostema3.ejercicio2.modelo.Pedido;
import jakarta.persistence.*;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author DFran49
 */
public class Lanzador8 {
    public static void main(String[] args) {
        System.out.println("Introduzca lel id del cliente:");
        short id = new Scanner(System.in).nextShort();

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("empresa_persistence");
        EntityManager em = emf.createEntityManager();
        try {
            

            Cliente c = new Cliente();
            c.setId(id);
            List<Pedido> pedidos = em.createQuery("SELECT p FROM Pedido p WHERE p.cliente = :cliente",Pedido.class)
                    .setParameter("cliente", c).getResultList();

            System.out.println("--------------------------------------\n");
            for (Pedido p : pedidos) {
                System.out.println(p.getTotal() + " : " + p.getFecha());
            }
            System.out.println("\n--------------------------------------");
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println(e.getMessage());
        } finally {
            em.close();
            emf.close();
        }
    }
}
