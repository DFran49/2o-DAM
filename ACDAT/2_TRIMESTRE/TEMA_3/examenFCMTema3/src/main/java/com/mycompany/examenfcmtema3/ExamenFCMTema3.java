/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.examenfcmtema3;

import com.mycompany.examenfcmtema3.modelos.Equipo;
import com.mycompany.examenfcmtema3.modelos.Jugador;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Tuple;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Francisco
 */
public class ExamenFCMTema3 {
    private static JpaUtil jpa = new JpaUtil();
    private static EntityManager em = jpa.openConnection();

    public static void main(String[] args) {
        int opcion = 1;
        while (opcion >= 1 && opcion <= 6) {            
            System.out.println("""
                           ---------GESTIÓN DE EQUIPOS-----------|
                           |1. Insertar datos de prueba en la BD.|
                           |2. Insertar jugador.                 |
                           |3. Eliminar equipo.                  |
                           |4. Actualizar jugador.               |
                           |5. Mostrar jugadores por equipo.     |
                           |6. Mostrar estadísticas de jugadores |
                           |0. Salir                             |
                           |-------------------------------------|
                            """);
            System.out.println("Selecciona una opción");
            try {
               opcion = new Scanner(System.in).nextInt();
                
                switch (opcion) {
                    case 1 ->
                        insertarPrueba();
                    case 2 -> {
                        //Se reutiliza la función insertarJugador para insertar y actualizar
                        System.out.println("Insertando jugador. Introduzca los datos solicitados.");
                        insertarJugador(new Jugador());
                    }
                    case 3 ->
                        eliminarEquipo();
                    case 4 ->
                        actualizarJugador();
                    case 5 ->
                        mostrarJugadores();
                    case 6 ->
                        mostrarStats();
                    default ->
                        salir();
                } 
            } catch (Exception fallo) {
                System.err.println("Introduzca un valor numérico");
            }
            
        }
    }

    private static void insertarPrueba() {
        System.out.println("Introduciendo datos de prueba.");
        List<Jugador> j = em.createQuery("SELECT j FROM Jugador j", Jugador.class).getResultList();
        List<Equipo> e = em.createQuery("SELECT e FROM Equipo e", Equipo.class).getResultList();
        if (e.isEmpty() && j.isEmpty()) {
            try {
                em.getTransaction().begin();
                Equipo eRM = new Equipo("Madrid","Real Madrid", new BigDecimal(800000000.0));
                Equipo eFCB = new Equipo("Barcelona","FC Barcelona", new BigDecimal(700000000.0));
                Equipo eMC = new Equipo("Manchester","Manchester City", new BigDecimal(900000000.0));
                em.persist(eRM);
                em.persist(eFCB);
                em.persist(eMC);

                Jugador karim = new Jugador((byte)35,LocalDate.of(2025, Month.JANUARY, 01),"Karim Benzema","Delantero",new BigDecimal(50000000.0),eRM);
                Jugador vinicius = new Jugador((byte)23,LocalDate.of(2025, Month.FEBRUARY, 15),"Vinicius Jr","Delantero",new BigDecimal(120000000.0),eRM);
                Jugador pedri = new Jugador((byte)21,LocalDate.of(2025, Month.MARCH, 20),"Pedri","Centrocampista",new BigDecimal(100000000.0),eFCB);
                em.persist(karim);
                em.persist(vinicius);
                em.persist(pedri);
                em.getTransaction().commit();
                System.out.println("Datos de prueba introducidos.");
            } catch (Exception exc) {
                em.getTransaction().rollback();
                System.err.println(exc.getMessage());
            }
        } else {
            System.err.println("Ya hay datos en alguna de las tablas.");
        }
        
    }

    private static void insertarJugador(Jugador jugador) {
        System.out.println("Introduzca el nombre (En actualizar se puede dejar en blanco):");
        String nombre = new Scanner(System.in).nextLine();
        System.out.println("Introduzca la posición (En actualizar se puede dejar en blanco):");
        String posicion = new Scanner(System.in).nextLine();
        System.out.println("Introduzca la edad (En actualizar se puede dejar en blanco):");
        String ed = new Scanner(System.in).nextLine();
        Byte edad = null;
        if (!ed.isEmpty()) edad = Byte.parseByte(ed);
        System.out.println("Introduzca el valor de mercado:");
        String val = new Scanner(System.in).nextLine();
        BigDecimal valor = null;
        if (!val.isEmpty()) valor = new BigDecimal(val);
        System.out.println("Introduzca el id del equipo:");
        int idEquipo = new Scanner(System.in).nextInt();
        
        Equipo e = em.find(Equipo.class, idEquipo);
        if (e != null) {
            try {
                em.getTransaction().begin();
                Jugador j = jugador;
                if (edad != null && edad >= 1 && edad <= 60) j.setEdad(edad);
                if (e != j.getEquipo()) j.setFechaContratacion(LocalDate.now());
                if (!nombre.isEmpty()) j.setNombre(nombre);
                if (!posicion.isEmpty()) j.setPosicion(posicion);
                if (valor != null) j.setValorMercado(valor);
                j.setEquipo(e);
                em.persist(j);
                em.getTransaction().commit();
                System.out.println("Jugador introducido o actualizado.");
            } catch (Exception ex) {
                em.getTransaction().rollback();
                System.err.println(ex.getMessage());
            }
        } else {
            System.err.println("El equipo introducido no existe, inténtelo de nuevo.");
        }
    }

    private static void eliminarEquipo() {
        System.out.println("Eliminando equipo. Introduzca los datos solicitados.");
        System.out.println("Introduzca el id del equipo a eliminar:");
        int idEquipo = new Scanner(System.in).nextInt();
        
        Equipo e = em.find(Equipo.class, idEquipo);
        if (e != null) {
            try {
                em.getTransaction().begin();
                em.remove(e);
                em.getTransaction().commit();
                System.out.println("Equipo eliminado.");
            } catch (Exception ex) {
                em.getTransaction().rollback();
                System.err.println(ex.getMessage());
            }
        } else {
            System.err.println("El equipo introducido no existe, inténtelo de nuevo.");
        }
    }

    private static void actualizarJugador() {
        System.out.println("Eliminando equipo. Introduzca los datos solicitados.");
        System.out.println("Introduzca el id del jugador a actualizar:");
        int id = new Scanner(System.in).nextInt();
        
        Jugador j = em.find(Jugador.class, id);
        if (j != null) {
            insertarJugador(j);
        } else {
            System.err.println("El jugador introducido no existe, inténtelo de nuevo.");
        }
    }

    private static void mostrarJugadores() {
        System.out.println("Jugadores por equipo:");
        List<Equipo> equipos = em.createNamedQuery("Equipo.Equipos",Equipo.class).getResultList();
        for (Equipo e : equipos) {
            System.out.println(e.getNombre());
            if (e.getJugadores().isEmpty()) {
                System.out.println("***Sin jugadores***");
            } else {
                for (Jugador j : e.getJugadores()) {
                    System.out.println(j.getId() + " : " + j.getNombre() + " : "  + j.getPosicion()+ " : " + j.getEdad()+ " : " + j.getValorMercado() 
                            + " : " + j.getFechaContratacion());
                }
            }
        }
    }

    private static void mostrarStats() {
        System.out.println("Información de los jugadores:");
        Tuple t = em.createNamedQuery("Jugador.Estadisticas",Tuple.class).getResultList().get(0);
        System.out.println("Total de jugadores: " + t.get(0));
        System.out.println("Edad promedio: " + t.get(1));
        System.out.println("Valor de mercado máximo: " + t.get(2));
        System.out.println("Valor de mercado mínimo: " + t.get(3));
    }

    private static void salir() {
        System.out.println("Adios.");
        jpa.closeConnection();
    }
}
