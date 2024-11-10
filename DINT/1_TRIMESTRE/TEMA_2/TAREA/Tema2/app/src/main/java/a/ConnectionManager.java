/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package a;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author DFran49
 */
public class ConnectionManager {
    private static final String URL = "jdbc:mariadb://localhost:3306/pokeTeams";
    private static final String USER = "root";
    private static final String PASSWORD = "root"; // Constructor privado para evitar instancias 
    private ConnectionManager() { }
    
    public static Connection getConnection() throws SQLException { 
    // Obtener conexión a la BD 
    return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    
    public static void closeConnection(Connection connection) throws SQLException {
// Cerrar conexión
    if (connection != null) connection.close();
    }
}
