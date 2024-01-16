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
    private static final String URL = "jdbc:postgresql://localhost:5432/StockManager_BD";
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

                    //Realiza la copia de seguridad cada vez que se establece una conexión
                    //backup();

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

    /* Método que realiza una copia de la base de datos en su estado actual - NO FUNCIONAL*/
    //private static void backup() {
    //    
    //    try {
    //        // Ruta donde se guardará la copia de seguridad
    //        String backupPath = "F:\\Mis documentos\\CLASE\\INSO I\\Proyecto\\StockManager";
//
    //        // Nombre del archivo de la copia de seguridad
    //        String backupFileName = "StockManager_BD_old.sql";
//
    //        // Comando para realizar la copia de seguridad con pg_dump
    //        String[] cmd = {
    //            "C:\\Program Files\\PostgreSQL\\16",
    //            "-h", "localhost",
    //            "-U", "postgres",
    //            "-d", "StockManager_BD",
    //            "-f", backupPath + backupFileName
    //        };
//
    //        // Ejecutar el comando
    //        ProcessBuilder processBuilder = new ProcessBuilder(cmd);
    //        Process process = processBuilder.start();
//
    //        // Esperar a que el proceso termine
    //        int exitCode = process.waitFor();
//
    //        if (exitCode == 0) {
    //            System.out.println("Copia de seguridad exitosa.");
    //        } else {
    //            System.err.println("Error al realizar la copia de seguridad.");
    //        }
    //    } catch (IOException | InterruptedException e) {
    //        e.printStackTrace();
    //    }
    //}

    // Método para cerrar la conexión
    //public static void closeConexion(Connection connection) {
    //    try {
    //        if (connection != null && !connection.isClosed()) {
    //            connection.close();
    //            System.out.println("Conexión cerrada.");
    //        }
    //    } catch (SQLException e) {
    //        System.err.println("Error al cerrar la conexión: " + e.getMessage());
    //    }
    //}
}
