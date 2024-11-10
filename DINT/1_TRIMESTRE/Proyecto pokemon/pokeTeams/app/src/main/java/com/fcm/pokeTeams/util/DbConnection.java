/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fcm.pokeTeams.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author DFran49
 */
public class DbConnection {
    private static final String URL = "jdbc:mariadb://localhost:3306/pokeTeams";
    private static final String USER = "root";
    private static final String PASSWORD = "root"; // Constructor privado para evitar instancias 
    private DbConnection() { }
    
    public static Connection getConnection() throws SQLException { 
    // Obtener conexión a la BD 
    return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    
    public static void closeConnection(Connection connection) throws SQLException {
// Cerrar conexión
    if (connection != null) connection.close();
    }
}
