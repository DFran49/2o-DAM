package jl.conferenciasapp.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbConnection {
    private static final String URL = "jdbc:mariadb://%s:%d/%s";
    private static String usuario;
    private static String clave;
    private static Connection connection;

    private DbConnection() {
        // Constructor privado para evitar instanciación
    }

    public static void setCredentials(String usuario, String clave) {
        DbConnection.usuario = usuario;
        DbConnection.clave = clave;
    }

    // Método estático para obtener una conexión a la base de datos
    public static Connection getConnection() throws SQLException, IOException {
        if (connection == null || connection.isClosed()) {
            Properties propiedades = new Properties();
            try (InputStream fis = DbConnection.class.getClassLoader().getResourceAsStream("config.prop")) {
                propiedades.load(fis);
                String servidor = propiedades.getProperty("servidor");
                int puerto = Integer.parseInt(propiedades.getProperty("puerto"));
                String nombre_bd = propiedades.getProperty("nombre_bd");
                String url = String.format(URL, servidor, puerto, nombre_bd);

                connection = DriverManager.getConnection(url, usuario, clave);
            }
        }

        return connection;
    }

    // Método estático para cerrar la conexión a la base de datos
    public static void closeConnection() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }
}


