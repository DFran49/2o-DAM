/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fcm.ExamenTema2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Francisco
 */
public class Conexion {
    public static Connection conectarBaseDatos(String bd) {
        String url = "jdbc:mariadb://localhost/"+bd;
        String usuario = "root", clave = "root";
        try {
            return DriverManager.getConnection(url,usuario,clave);
        } catch (SQLException ex) {
            System.err.println("No se ha podido conectar a la base de datos");
        }
        return null;
    }
    
    public static void cerrarConexionBaseDatos(Connection con) {
        try {
            con.close();
        } catch (SQLException ex) {
            System.err.println("No se ha podido cerrar la conexión a la base de datos");
        }
    }
}
