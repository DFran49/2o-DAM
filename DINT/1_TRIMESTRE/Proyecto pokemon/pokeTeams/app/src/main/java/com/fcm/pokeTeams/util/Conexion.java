/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fcm.pokeTeams.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DFran49
 */
public class Conexion extends Object {
    static Connection conexion;

    public Conexion() {
        try {
            this.conexion = DbConnection.getConnection();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public static Connection getConexion() {
        return conexion;
    }
}
