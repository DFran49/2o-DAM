/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ejerciciostema3.ejercicio5;

import com.mycompany.ejerciciostema3.ejercicio2.modelo.Cliente;
import com.mycompany.ejerciciostema3.ejercicio2.modelo.Comercial;
import com.mycompany.ejerciciostema3.ejercicio2.modelo.Pedido;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 *
 * @author DFran49
 */
public class Lanzador5 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("empresa_persistence");
        
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            System.out.println("---------CLIENTE----------");
            System.out.print("Ingrese el nombre: ");
            String cNombre = new Scanner(System.in).nextLine();

            System.out.print("Ingrese el primer apellido: ");
            String cApellido1 = new Scanner(System.in).nextLine();

            System.out.print("Ingrese el segundo apellido (opcional, presione Enter para omitir): ");
            String cApellido2 = new Scanner(System.in).nextLine();
            if (cApellido2.isEmpty()) {
                cApellido2 = null;
            }

            System.out.print("Ingrese la ciudad (opcional, presione Enter para omitir): ");
            String cCiudad = new Scanner(System.in).nextLine();
            if (cCiudad.isEmpty()) {
                cCiudad = null;
            }

            System.out.print("Ingrese la categoría (opcional, presione Enter o -1 para omitir): ");
            String categoriaInput = new Scanner(System.in).nextLine();
            int cCategoria = categoriaInput.isEmpty() || categoriaInput.equals("-1") ? null : Integer.parseInt(categoriaInput);
            
            Cliente cli = new Cliente(cNombre,cApellido1,cApellido2,cCiudad,cCategoria);
            em.persist(cli);
            
            System.out.println("---------COMERCIAL----------");
            System.out.print("Ingrese el nombre: ");
            String comNombre = new Scanner(System.in).nextLine();

            System.out.print("Ingrese el primer apellido: ");
            String comApellido1 = new Scanner(System.in).nextLine();

            System.out.print("Ingrese el segundo apellido (opcional, presione Enter para omitir): ");
            String comApellido2 = new Scanner(System.in).nextLine();
            if (comApellido2.isEmpty()) {
                comApellido2 = null;
            }

            System.out.print("Ingrese la comisión (opcional, use punto decimal, presione Enter para omitir): ");
            String comisionInput = new Scanner(System.in).nextLine();
            BigDecimal comComision = comisionInput.isEmpty() ? null : new BigDecimal(comisionInput);
            
            Comercial com = new Comercial(comNombre,comApellido1,comApellido2,comComision);
            em.persist(com);
            
            System.out.println("---------PEDIDO----------");
            System.out.print("Ingrese el total (usar punto decimal): ");
            BigDecimal pTotal = new BigDecimal(new Scanner(System.in).nextLine());

            System.out.print("Ingrese la fecha (formato YYYY-MM-DD, presione Enter para omitir): ");
            String fechaInput = new Scanner(System.in).nextLine();
            LocalDate pFecha = null;
            if (!fechaInput.isEmpty()) {
                try {
                    pFecha = LocalDate.parse(fechaInput);
                } catch (DateTimeParseException e) {
                    System.out.println("Formato de fecha inválido. Debe ser YYYY-MM-DD.");
                    return;
                }
            }
            
            Pedido ped = new Pedido(pTotal,pFecha,cli,com);
            em.persist(ped);
            em.getTransaction().commit();
            em.close();
            emf.close();
        } catch (Exception e) {
            System.out.println("Error al persistir el objeto: " + e.getMessage());
        }
    }
}
