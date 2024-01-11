package modelo.db;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.entidad.Producto;

public class OperacionesBD_producto {

    // Método para insertar un nuevo registro
    public static void addProducto_BD(Producto prod) {
        String query = "INSERT INTO PRODUCTO (nombreProd, distribId, tipo, cantidad, costeUnitario) VALUES (?, ?, ?, ?, ?)";

        //Se realiza la conexión a la BD y se prepara la sentencia SQL para la consulta
        try (Connection conn = Conexion.getConexion();
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {

            // Se establece el valor a cada uno de los parámetro de la sentencia
            preparedStatement.setString(1, prod.getName());
            preparedStatement.setString(2, prod.getDistrib());
            preparedStatement.setString(3, prod.getTipo());
            preparedStatement.setInt(4, prod.getCantidad());
            preparedStatement.setBigDecimal(5, prod.getPrecio());

            // Ejecuta la consulta SQL
            preparedStatement.executeUpdate();

            System.out.println("Datos insertados correctamente.");
        } catch (SQLException e) {
            System.err.println("Error al insertar datos: " + e.getMessage());
        }
    }
    
    public static Producto getProducto_BD(String nombreProd) {
        String query = "SELECT * FROM PRODUCTO WHERE nombreProd = ?";

        try (Connection conn = Conexion.getConexion();
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {

            preparedStatement.setString(1, nombreProd);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                
                if (resultSet.next()) {
                    Producto prod = new Producto();
                    prod.setName(resultSet.getString("nombreProd"));
                    prod.setDistribuidor(resultSet.getString("distribId"));
                    prod.setTipo(resultSet.getString("tipo"));
                    prod.setPrecio(resultSet.getBigDecimal("costeUnitario"));
                    prod.setCantidad(resultSet.getInt("cantidad"));
                    
                    return prod;
                
                } else {
                    // Manejar el caso en el que no se encuentra el producto
                    return null;
                }
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener el producto: " + e.getMessage());
            
            return null;
        }
    }
    
    
    
    


















}