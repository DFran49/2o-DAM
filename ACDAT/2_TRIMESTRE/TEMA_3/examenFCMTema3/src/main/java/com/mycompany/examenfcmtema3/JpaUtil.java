/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.examenfcmtema3;

import jakarta.persistence.*;

/**
 *
 * @author Francisco
 */
public class JpaUtil {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("futbol_persistence");
    private static EntityManager em;

    public JpaUtil() {
        em = emf.createEntityManager();
    }
    
    public EntityManager openConnection() {
        return em;
    }
    
    public void closeConnection() {
        emf.close();
        em.close();
    }
}
