/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package a;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DFran49
 */
public  class b {
    static Connection conexion;

    public b() {
        try {
            this.conexion = ConnectionManager.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(b.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Connection getConexion() {
        return conexion;
    }
    
    
}
