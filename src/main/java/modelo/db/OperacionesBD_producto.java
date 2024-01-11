package modelo.db;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.entidad.Producto;

/**
 *
 * @author diego
 */
public class OperacionesBD_producto {

    // Método para insertar un nuevo producto en la BD
    public static void addProducto_BD (Producto prod) {
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
            preparedStatement.executeUpdate();  //Sentencia que modifica la base de datos

            System.out.println("Producto añadido correctamente.");
            
        //Si se ejecuta el try cierra la conexión automáticamente al finalizar la consulta
        } catch (SQLException e) {
            System.err.println("Error al insertar producto: " + e.getMessage());
        }
    }
    
    //Obtener el producto con el nombre especificado de la BD
    public static Producto getProducto_BD (String nombreProd) {
        String query = "SELECT * FROM PRODUCTO WHERE nombreProd = ?";

        try (Connection conn = Conexion.getConexion();
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {

            preparedStatement.setString(1, nombreProd);

            try (ResultSet resultSet = preparedStatement.executeQuery()) { //Sentencia que devuelve un conjunto de resultados
                
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
    
    //Actualizar cantidad de un producto de la BD
    public static void newCantidad_BD (String nombreProd, int nCant) {
        String query = "UPDATE PRODUCTO  SET cantidad = ? WHERE nombreProd = ?";
        
          try (Connection conn = Conexion.getConexion();
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {

              preparedStatement.setInt(1, nCant);
              preparedStatement.setString(2, nombreProd);

              preparedStatement.executeUpdate();

              System.out.println("Cantidad del producto actualizada correctamente");
              
        } catch (SQLException e) {
            System.err.println("Error al actualizar la cantidad: " + e.getMessage());
        }
    }
    
    //Eliminar producto de la BD
    public static void delProducto_BD (String nombreProd) {
        String query = "DELETE FROM PRODUCTO WHERE nombreProd = ?";
        
        try (Connection conn = Conexion.getConexion();
            PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            
            preparedStatement.setString(1, nombreProd);
            preparedStatement.executeUpdate();

            System.out.println("Producto eliminado correctamente");
              
        } catch (SQLException e) {
            System.err.println("Error al eliminar el producto: " + e.getMessage());
        }
        
    }




}