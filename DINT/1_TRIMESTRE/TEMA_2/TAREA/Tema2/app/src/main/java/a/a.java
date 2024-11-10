/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package a;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DFran49
 */
public class a {
    private static final String URL = "jdbc:mariadb://localhost:3306/pokeTeams";
    public static void main(String[] args) {
        b papa = new b();
        try {
            String query = "SELECT * FROM entrenador";
            
            Statement statement = b.getConexion().createStatement();
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {                
                System.out.println(result.getString("nombre"));
            }
            System.out.println("ola");
         // Obtener la conexión // Realizar operaciones con la conexión a la BD 
        } catch (SQLException e) {
            System.out.println("Error al conectar con la BD: " + e.getMessage());
        } finally {
            try {
                ConnectionManager.closeConnection(b.getConexion());
            } catch (SQLException e) {
                System.out.println("Error al desconectar de la BD: " + e.getMessage());
            }
        }

    }
}
