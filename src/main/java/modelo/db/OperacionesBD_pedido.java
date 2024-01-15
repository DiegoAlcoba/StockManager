package modelo.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modelo.entidad.Pedido;

/**
 *
 * @author diego
 */
public class OperacionesBD_pedido {
    
    /* Añadir un pedido realizado a la base de datos */
    public static void addPedido_BD(Pedido pedido) {
        String query = "INSERT INTO pedido (userId, fecha, precioTotal, distribId) VALUES (?, ?, ?, ?)";
                
        try (Connection conn = Conexion.getConexion();
         PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            
            // Se establece el valor a cada uno de los parámetro de la sentencia
            preparedStatement.setInt(1, pedido.getUsuarioId()); // Falta implementación en constructor
            preparedStatement.setDate(2, pedido.getFecha());
            preparedStatement.setBigDecimal(3, pedido.getPrecioTotal());
            preparedStatement.setInt(4, pedido.getDistribuidor());
            
            // Ejecuta la consulta SQL
            preparedStatement.executeUpdate();
            System.out.println("Pedido añadido correctamente.");
        
        } catch (SQLException e) {
            System.err.println("Error al insertar el pedido en la base de datos: " + e.getMessage());
        }
    }

    /* Recuperar un pedido realizado de la base de datos */

}
