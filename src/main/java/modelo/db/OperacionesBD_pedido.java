package modelo.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modelo.entidad.Distribuidor;
import modelo.entidad.Pedido;
import modelo.entidad.Producto;

/**
 *
 * @author diego
 */
public class OperacionesBD_pedido {
    
    /* Añadir un pedido realizado a la base de datos */
    public static boolean addPedido_BD(Pedido pedido) {
        String query = "INSERT INTO pedido (userId, fecha, precioTotal, distribId) VALUES (?, ?, ?, ?)";
                
        try (Connection conn = Conexion.getConexion();
         PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            
            // Se establece el valor a cada uno de los parámetro de la sentencia
            preparedStatement.setInt(1, pedido.getUserId()); // Falta implementación en constructor
            preparedStatement.setDate(2, pedido.getFecha());
            preparedStatement.setBigDecimal(3, pedido.getPrecioTotal());
            preparedStatement.setString(4, pedido.getDistribuidor());
            
            // Ejecuta la consulta SQL
            preparedStatement.executeUpdate();
            System.out.println("Pedido añadido correctamente.");

            return true;
        
        } catch (SQLException e) {
            System.err.println("Error al insertar el pedido en la base de datos: " + e.getMessage());
        }

        return false;
    }

    /* Recuperar un pedido realizado de la base de datos */
    public static Pedido getPedido_BD (int idPedido) {
        String query = "SELECT * FROM pedido WHERE idPedido = ?";
                
        try (Connection conn = Conexion.getConexion();
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {

            preparedStatement.setInt(1, idPedido);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                
                if (resultSet.next()) {
                    Pedido pedido = new Pedido();
                    pedido.setPedidoId(resultSet.getInt("pedidoId"));
                    pedido.setUserId(resultSet.getInt("userId"));
                    pedido.setFecha(resultSet.getDate("fecha"));
                    pedido.setPrecioTotal(resultSet.getBigDecimal("precioTotal"));
                    pedido.setDistribuidor(resultSet.getString("distribId"));
                    
                    return pedido;
                
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

    /* Devuelve un vector con los pedidos realizados */
    public static Pedido[] getListaPedidos_BD() {
        int totalPeds = nPedidos();

        Pedido[] pedidos = new Pedido[totalPeds];

        String query = "SELECT * FROM pedido";
                
        try (Connection conn = Conexion.getConexion();
         PreparedStatement preparedStatement = conn.prepareStatement(query);
         ResultSet resultSet = preparedStatement.executeQuery()) {
            
            int i = 0;

            while (resultSet.next()) {
                Pedido pedido = new Pedido();
                
                pedido.setPedidoId(resultSet.getInt("pedidoId"));
                pedido.setUserId(resultSet.getInt("userId"));
                pedido.setFecha(resultSet.getDate("fecha"));
                pedido.setPrecioTotal(resultSet.getBigDecimal("precioTotal"));
                pedido.setDistribuidor(resultSet.getString("distribId"));

                pedidos[i++] = pedido;
            }

            return pedidos;
        
        } catch (SQLException e) {
            System.err.println("Error al obtener los distribuidores: " + e.getMessage());
        }

        return null;
    }

    //Método que devuelve el número de pedidos totales en la BD
    private static int nPedidos() {
        int cantidad = 0;

        String query = "SELECT COUNT(*) AS cantidad FROM pedido";

        try (Connection conn = Conexion.getConexion();
         PreparedStatement preparedStatement = conn.prepareStatement(query);
         ResultSet countResultSet = preparedStatement.executeQuery()) {
            
            if (countResultSet.next()) {
                cantidad = countResultSet.getInt("cantidad");
            }
        
        } catch (SQLException e) {
            System.err.println("Error al obtener la cantidad de pedidos realizados: " + e.getMessage());
        }

        return cantidad;
    }
}
