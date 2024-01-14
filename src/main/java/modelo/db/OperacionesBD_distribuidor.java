package modelo.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modelo.entidad.Distribuidor;

/**
 *
 * @author diego
 */
public class OperacionesBD_distribuidor {
    
    /* Inserta un nuevo distribuidor en la base de datos */
    public static void addDistrib_BD (Distribuidor distrib) {
        String query = "INSERT INTO distribuidor (distribId, nombreDist, emailDist, distTelf) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = Conexion.getConexion();
         PreparedStatement preparedStatement = conn.prepareStatement(query)) {

            // Se establece el valor a cada uno de los parámetro de la sentencia
            preparedStatement.setString(1, distrib.getId());
            preparedStatement.setString(2, distrib.getNombre());
            preparedStatement.setString(3, distrib.getMail());
            preparedStatement.setInt(4, distrib.getTlfn());

            // Ejecuta la consulta SQL
            preparedStatement.executeUpdate();
            System.out.println("Distribuidor añadido correctamente.");
        
        } catch (SQLException e) {
            System.err.println("Error al añadir el distribuidor: " + e.getMessage());
        }
    }

    /* Devuelve un distribuidor de la base de datos */
    public static Distribuidor getDistribuidor_BD (String name) {
        String query = "SELECT * FROM distribuidor WHERE nombreDist = ?";
                
        try (Connection conn = Conexion.getConexion();
         PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            
            // Se establece el valor a cada uno de los parámetro de la sentencia
            preparedStatement.setString(1, name);
            
            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                if (resultSet.next()) {
                    Distribuidor distrib = new Distribuidor();
                    distrib.setId(resultSet.getString("distribId"));
                    distrib.setNombre(resultSet.getString("nombreDist"));
                    distrib.setMail(resultSet.getString("emailDist"));
                    distrib.setTlfn(resultSet.getInt("distTelf"));
                    
                    return distrib;
                
                } else {
                    // Manejar el caso en el que no se encuentra el producto
                    return null;
                }
            }
        
        } catch (SQLException e) {
            System.err.println("Error al obtener el distribuidor: " + e.getMessage());

            return null;
        }
    }

    /* Elimina un distribuidor de la base de datos */
    public static void delDistribuidor_BD (String name) {
        String query = "DELETE FROM distribuidor WHERE nombreDist = ?";
                
        try (Connection conn = Conexion.getConexion();
         PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            
            // Se establece el valor a cada uno de los parámetro de la sentencia
            preparedStatement.setString(1, name);
            
            // Ejecuta la consulta SQL
            preparedStatement.executeUpdate();
            System.out.println("Distribuidor eliminado correctamente.");
        
        } catch (SQLException e) {
            System.err.println("Error al eliminar el distribuidor de la base de datos: " + e.getMessage());
        }
    }

}
