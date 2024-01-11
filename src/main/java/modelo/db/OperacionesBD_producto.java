package modelo.db;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import modelo.entidad.Producto;

public class OperacionesBD_producto {

    // Método para insertar un nuevo registro
    public static void addProducto(String nombre, String idDistribuidor, String tipo, int cantidad, BigDecimal precio) {
        String query = "INSERT INTO producto (nombreProd, distribId, tipo, cantidad, costeUnitario) VALUES (?, ?, ?, ?, ?)";

        //Se realiza la conexión a la BD y se prepara la sentencia SQL para la consulta
        try (Connection conn = Conexion.getConexion();
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {

            // Se establece el valor a cada uno de los parámetro de la sentencia
            preparedStatement.setString(1, nombre);
            preparedStatement.setString(2, idDistribuidor);
            preparedStatement.setString(3, tipo);
            preparedStatement.setInt(4, cantidad);
            preparedStatement.setBigDecimal(5, precio);

            // Ejecuta la consulta SQL
            preparedStatement.executeUpdate();

            System.out.println("Datos insertados correctamente.");
        } catch (SQLException e) {
            System.err.println("Error al insertar datos: " + e.getMessage());
        }
    }
    
    public static Producto getProducto(String nombreProd) {
        
        
        
    }
    
    
    
    
}
