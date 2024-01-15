package modelo.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.spi.DirStateFactory.Result;

import org.postgresql.jdbc.ResourceLock;

import modelo.entidad.Distribuidor;

/**
 *
 * @author diego
 */
public class OperacionesBD_distribuidor {
    
    /* Inserta un nuevo distribuidor en la base de datos */
    public static boolean addDistrib_BD (Distribuidor distrib) {
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

            return true;
        
        } catch (SQLException e) {
            System.err.println("Error al añadir el distribuidor: " + e.getMessage());
        }

        return false;
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
    public static boolean delDistribuidor_BD (String name) {
        String query = "DELETE FROM distribuidor WHERE nombreDist = ?";
                
        try (Connection conn = Conexion.getConexion();
         PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            
            // Se establece el valor a cada uno de los parámetro de la sentencia
            preparedStatement.setString(1, name);
            
            // Ejecuta la consulta SQL
            preparedStatement.executeUpdate();
            System.out.println("Distribuidor eliminado correctamente.");

            return true;
        } catch (SQLException e) {
            System.err.println("Error al eliminar el distribuidor de la base de datos: " + e.getMessage());
        }
        
        return false;
    }

    /* Devuelve un vector con los distribuidores almacenados en la base de datos */
    public static Distribuidor[] getListaDistribuidores_BD() {
        int totalDists = nDistribuidores();

        Distribuidor[] distribuidores = new Distribuidor[totalDists];

        String query = "SELECT * FROM distribuidor";
                
        try (Connection conn = Conexion.getConexion();
         PreparedStatement preparedStatement = conn.prepareStatement(query);
         ResultSet resultSet = preparedStatement.executeQuery()) {
            
            int i = 0;

            while (resultSet.next()) {
                Distribuidor distrib = new Distribuidor();
                
                distrib.setId(resultSet.getString("distribId"));
                distrib.setNombre(resultSet.getString("nombreDist"));
                distrib.setMail(resultSet.getString("emailDist"));
                distrib.setTlfn(resultSet.getInt("distTelf"));

                distribuidores[i++] = distrib;
            }

            return distribuidores;
        
        } catch (SQLException e) {
            System.err.println("Error al obtener los distribuidores: " + e.getMessage());
        }

        return null;
    }

    //Método que devuelve el número de distribuidores totales en la BD
    private static int nDistribuidores() {
        int cantidad = 0;

        String query = "SELECT COUNT(*) AS cantidad FROM distribuidor";

        try (Connection conn = Conexion.getConexion();
         PreparedStatement preparedStatement = conn.prepareStatement(query);
         ResultSet countResultSet = preparedStatement.executeQuery()) {
            
            if (countResultSet.next()) {
                cantidad = countResultSet.getInt("cantidad");
            }
        
        } catch (SQLException e) {
            System.err.println("Error al obtener la cantidad de distribuidores: " + e.getMessage());
        }

        return cantidad;
    }



}
