/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author diego
 */
public class Conexion {

    // Configuración de la conexión
    private static final String URL = "jdbc:postgresql://localhost:5432/StockManager_DB";
    private static final String USUARIO = "postgres";
    private static final String CONTRASENA = "1234";

    // Método para obtener la conexión a la base de datos
    public static Connection getConexion() {
        Connection connection = null;

        try {
            // Cargar el controlador JDBC de PostgreSQL
            Class.forName("org.postgresql.Driver");

            // Establecer la conexión
                connection = DriverManager.getConnection(URL, USUARIO, CONTRASENA);


                if (connection != null) {
                    System.out.println("¡Conexión exitosa!");
                } else {
                    System.out.println("No se pudo establecer la conexión.");
                }
            } catch (ClassNotFoundException e) {
                System.err.println("Error al cargar el controlador JDBC: " + e.getMessage());
            } catch (SQLException e) {
                System.err.println("Error en la conexión a la base de datos: " + e.getMessage());
        }
    
        return connection;
    }

    // Método para cerrar la conexión
    public static void closeConexion(Connection connection) {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Conexión cerrada.");
            }
        } catch (SQLException e) {
            System.err.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }
}
