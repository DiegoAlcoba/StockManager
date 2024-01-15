package modelo.db;

//import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modelo.entidad.Distribuidor;
import modelo.entidad.Producto;

/**
 *
 * @author diego
 */
public class OperacionesBD_producto {

    /* Inserta un nuevo producto en la base de datos */
    public static void addProducto_BD (Producto prod) {
        String query = "INSERT INTO producto (nombreProd, distribId, tipo, cantidad, costeUnitario) VALUES (?, ?, ?, ?, ?)";

        //Se realiza la conexión a la BD y se prepara la sentencia SQL para la consulta
        try (Connection conn = Conexion.getConexion();
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {

            // Se establece el valor a cada uno de los parámetro de la sentencia
            preparedStatement.setString(1, prod.getName());
            preparedStatement.setString(2, prod.getDistribId());
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
    
    /* Devuelve un producto de la base de datos */
    public static Producto getProducto_BD (String nombreProd) {
        String query = "SELECT * FROM producto WHERE nombreProd = ?";

        try (Connection conn = Conexion.getConexion();
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {

            preparedStatement.setString(1, nombreProd);

            try (ResultSet resultSet = preparedStatement.executeQuery()) { //Sentencia que devuelve un conjunto de resultados
                
                if (resultSet.next()) {
                    Producto prod = new Producto();
                    prod.setName(resultSet.getString("nombreProd"));
                    prod.setDistribuidorId(resultSet.getString("distribId"));
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
    
    /* Actualiza la cantidad de un producto de la base de datos */
    public static void newCantidad_BD (String nombreProd, int nCant) {
        String query = "UPDATE producto  SET cantidad = ? WHERE nombreProd = ?";
        
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
    
    /* Elimina un producto de la base de datos */
    public static void delProducto_BD (String nombreProd) {
        String query = "DELETE FROM producto WHERE nombreProd = ?";
        
        try (Connection conn = Conexion.getConexion();
            PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            
            preparedStatement.setString(1, nombreProd);
            preparedStatement.executeUpdate();

            System.out.println("Producto eliminado correctamente");
              
        } catch (SQLException e) {
            System.err.println("Error al eliminar el producto: " + e.getMessage());
        }
        
    }

/* Devuelve un vector con los productos almacenados en la base de datos */
    public static Producto[] getListaProductos_BD() {
        int totalProds = nProds();

        Producto[] productos = new Producto[totalProds];

        String query = "SELECT * FROM producto";
                
        try (Connection conn = Conexion.getConexion();
         PreparedStatement preparedStatement = conn.prepareStatement(query);
         ResultSet resultSet = preparedStatement.executeQuery()) {
            
            int i = 0;

            while (resultSet.next()) {
                Producto prod = new Producto();
                
                prod.setName(resultSet.getString("nombreProd"));
                prod.setDistribuidorId(resultSet.getString("distribId"));
                prod.setTipo(resultSet.getString("tipo"));
                prod.setPrecio(resultSet.getBigDecimal("costeUnitario"));
                prod.setCantidad(resultSet.getInt("cantidad"));

                productos[i++] = prod;
            }
        
        } catch (SQLException e) {
            System.err.println("Error al obtener los productos: " + e.getMessage());
        }

        return productos;
    }

    //Método que devuelve el número de productos totales en la BD
    private static int nProds() {
        int cantidad = 0;

        String query = "SELECT COUNT(*) AS cantidad FROM producto";

        try (Connection conn = Conexion.getConexion();
         PreparedStatement preparedStatement = conn.prepareStatement(query);
         ResultSet countResultSet = preparedStatement.executeQuery()) {
            
            if (countResultSet.next()) {
                cantidad = countResultSet.getInt("cantidad");
            }
        
        } catch (SQLException e) {
            System.err.println("Error al obtener la cantidad de productos: " + e.getMessage());
        }

        return cantidad;
    }


}