package modelo.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modelo.entidad.DetallesPedido;
import modelo.entidad.Pedido;
import modelo.entidad.Producto;

/**
 *
 * @author diego
 */
public class OperacionesBD_detallePedido {

    /* El método recibe el pedido, el producto del pedido, y la cantidad que se va a pedir del producto */
    public static boolean addProdPedido_BD (Producto prod, Pedido pedido, int cantidad) {
        String query = "INSERT INT detallePedido (idPedido, nombreProducto, cantidad, costeUnitario) VALUES (?, ?, ?, ?)";
                
        try (Connection conn = Conexion.getConexion();
         PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            
            // Se establece el valor a cada uno de los parámetro de la sentencia
            preparedStatement.setInt(1, pedido.getPedidoId());
            preparedStatement.setString(2, prod.getName());
            preparedStatement.setInt(3, cantidad);
            preparedStatement.setBigDecimal(4, prod.getPrecio());
            
            // Ejecuta la consulta SQL
            preparedStatement.executeUpdate();
            System.out.println("Producto añadido al pedido correctamente.");

            return true;
        
        } catch (SQLException e) {
            System.err.println("Error al añadir el pedido al pedido: " + e.getMessage());
        }

        return false;
    }

    /* Método que devuelve todos los productos, y sus características, de un mismo pedido */
    /* Se le pasa como parámetro el id del pedido en cuestión */
    public static DetallesPedido[] getDetallesPedido_BD(int pedidoId) {
        int totalProds = nProdsPedido(pedidoId);

        DetallesPedido[] productos = new DetallesPedido[totalProds];

        String query = "SELECT * FROM detallePedido WHERE idPedido = ?";
                
        try (Connection conn = Conexion.getConexion();
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {
    
            preparedStatement.setInt(1, pedidoId);
    
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                int i = 0;

                while (resultSet.next()) {
                    DetallesPedido prod = new DetallesPedido();

                    prod.setnDetalle(resultSet.getInt("detalleId"));
                    prod.setIdPedido(resultSet.getInt("idPedido"));
                    prod.setNombreProd(resultSet.getString("nombreProducto"));
                    prod.setCantidadProd(resultSet.getInt("cantidad"));
                    prod.setCoste(resultSet.getBigDecimal("costeUnitario"));
                    
                    productos[i++] = prod;
                }
            }

            return productos;
    
        } catch (SQLException e) {
            System.err.println("Error al obtener los productos del pedio: " + e.getMessage());
        }
            
        return null;
    }

    //Método que devuelve el número de registros que tienen el pedidoId indicado
    private static int nProdsPedido(int id) {
        int cantidad = 0;
    
        String query = "SELECT COUNT(*) AS cantidad FROM detallePedido WHERE idPedido = ?";
    
        try (Connection conn = Conexion.getConexion();
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {
    
            preparedStatement.setInt(1, id);
    
            try (ResultSet countResultSet = preparedStatement.executeQuery()) {
                if (countResultSet.next()) {
                    cantidad = countResultSet.getInt("cantidad");
                }
            }
    
        } catch (SQLException e) {
            System.err.println("Error al obtener la cantidad de productos: " + e.getMessage());
        }
    
        return cantidad;
    }

}