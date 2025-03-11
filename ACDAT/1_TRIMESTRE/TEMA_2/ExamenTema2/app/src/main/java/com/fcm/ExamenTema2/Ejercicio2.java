/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fcm.ExamenTema2;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Francisco
 */
public class Ejercicio2 {
    private static boolean ejecucion = true;
    
    public static void main(String[] args) {
        while (ejecucion) {            
            System.out.println("""
                           1. Mostrar clientes de la BD vieja.
                           2. Migrar clientes a la BD nueva.
                           3. Mostrar clientes de la BD nueva.
                           0. Salir
                           """);
            System.out.println("Elija una opción.");
            try {
                int opcion = new Scanner(System.in).nextInt();
                switch (opcion) {
                    case 1 -> leerClientesViejos();
                    case 2 -> migrar();
                    case 3 -> leerClientesNuevos();
                    default -> salir();
                }
            } catch (Exception e) {
                System.err.println("No ha introducido una opción válida, inténtelo de nuevo.");
            }
        }
    }
    
    private static void leerClientesViejos() {
        System.out.println("Mostrando los clientes viejos.");
        
        try (Connection con = Conexion.conectarBaseDatos("tienda_vieja")) {
            String sql = "SELECT * FROM clientes";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            System.out.println("CLIENTES:");
            String mostrar = "";
            while (rs.next()) {
                mostrar += rs.getInt("id");
                mostrar += " - ";
                mostrar += rs.getString("nombre");
                mostrar += " - ";
                mostrar += rs.getString("email");
                mostrar += " - ";
                mostrar += rs.getString("telefono");
                System.out.println(mostrar);
                mostrar = "";
            }
        } catch (SQLException ex) {
            System.err.println("No se han podido leer los datos de la BBDD.");
        }
    }
    
    private static void migrar() {
        System.out.println("Migrando clientes.");
        Connection conN = Conexion.conectarBaseDatos("tienda_nueva");
        Connection conV = Conexion.conectarBaseDatos("tienda_vieja");
        
        try {
            String sql = "SELECT * FROM clientes";
            PreparedStatement psV = conV.prepareStatement(sql);
            ResultSet rsV = psV.executeQuery();
            //Prepared Statement y ResutSet de tabla "nueva"
            PreparedStatement psN = conN.prepareStatement(sql);
            ResultSet rsN = psN.executeQuery();
            
            //Preparar INSERT
            String sqlIns = "INSERT INTO clientes (id_cliente, nombre_completo, correo, telefono, fecha_migracion) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement psIns;
            
            //TRANSACTION
            PreparedStatement psTrans = conN.prepareStatement("START TRANSACTION;");
            psTrans.executeQuery();
            boolean existe = false;
            while (rsV.next()) {
                while (rsN.next()) {
                    if (rsV.getInt("id") == rsN.getInt("id_cliente")) {
                        existe = true;
                    }
                }
                if (existe) {
                    System.err.println("Ese cliente ya existe.");
                } else {
                    psIns = conN.prepareStatement(sqlIns);
                    psIns.setInt(1, rsV.getInt("id"));
                    psIns.setString(2, rsV.getString("nombre"));
                    psIns.setString(3, rsV.getString("email"));
                    psIns.setString(4, rsV.getString("telefono"));
                    psIns.setDate(5, Date.valueOf(LocalDate.now()));
                    if (psIns.executeUpdate() > 0) {
                        System.out.println("Cliente migrado correctamente.");
                    } else {
                        System.out.println("No se pudo migrar el cliente.");
                    }
                }
            }
            PreparedStatement psCom = conN.prepareStatement("COMMIT;");
            psTrans.executeQuery();
        } catch (SQLException ex) {
            System.err.println("No se han podido leer los datos de la BBDD.");
            try {
                PreparedStatement psCom = conN.prepareStatement("ROLLBACK;");
                psCom.executeQuery();
            } catch (SQLException ex1) {
                Logger.getLogger(Ejercicio2.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }
    
    private static void leerClientesNuevos() {
        
        /*System.out.println("Mostrando los clientes nuevos.");
        
        try (Connection con = Conexion.conectarBaseDatos("tienda_nueva")) {
            String sql = "SELECT * FROM clientes";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            System.out.println("CLIENTES:");
            String mostrar = "";
            while (rs.next()) {
                mostrar += rs.getInt("id_cliente");
                mostrar += " - ";
                mostrar += rs.getString("nombre_completo");
                mostrar += " - ";
                mostrar += rs.getString("correo");
                mostrar += " - ";
                mostrar += rs.getString("telefono");
                mostrar += " - ";
                mostrar += rs.getDate("fecha_migracion");
                System.out.println(mostrar);
                mostrar = "";
            }
        } catch (SQLException ex) {
            System.err.println("No se han podido leer los datos de la BBDD.");
        }*/
    }
    
    private static void salir() {
        ejecucion = false;
        System.out.println("Adios.");
    }
}
