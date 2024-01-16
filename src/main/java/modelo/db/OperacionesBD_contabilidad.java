package modelo.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modelo.entidad.Contabilidad;
import modelo.entidad.Usuario;

/**
 *
 * @author diego
 */
public class OperacionesBD_contabilidad {
    
    /* Inserta un nuevo balance en la base de datos */
    public static boolean addBalance_BD (Contabilidad cont) {
        String query = "INSERT INTO contabilidad (userId, fechaInicio, fechaFin, ingresos, gastos, balance) VALUES (?, ?, ?, ?, ?, ?)";
                
        try (Connection conn = Conexion.getConexion();
         PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            
            // Se establece el valor a cada uno de los parámetro de la sentencia
            preparedStatement.setInt(1, cont.getIdUsuario());
            preparedStatement.setDate(2, cont.getInicio());
            preparedStatement.setDate(3, cont.getFin());
            preparedStatement.setInt(4, cont.getIngresos());
            preparedStatement.setInt(5, cont.getGastos());
            preparedStatement.setInt(6, cont.getBalance());
            
            // Ejecuta la consulta SQL
            preparedStatement.executeUpdate();
            System.out.println("Registro de balance añadido correctamente.");

            return true;
        
        } catch (SQLException e) {
            System.err.println("Error al añadir el registro en la base de datos: " + e.getMessage());
        }

        return false;
    }

    /* Devuelve el balance con el id pasado por parámetro
     *  -- Primero se recuperan todos los registros almacenados, y de ahí el que escojamos para mostrar los detalles podemos obtener su id para proporcionarlo
    */
    public static Contabilidad getBalance_BD(int id) {
        String query = "SELECT * FROM contabilidad WHERE operacionId = ?";

        try (Connection conn = Conexion.getConexion();
         PreparedStatement preparedStatement = conn.prepareStatement(query)) {
    
            // Se establece el valor a cada uno de los parámetro de la sentencia
            preparedStatement.setInt(1, id);
            
            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                if (resultSet.next()) {
                    Contabilidad cont = new Contabilidad();
                    
                    cont.setOperacionId(resultSet.getInt("operacionid"));
                    cont.setIdUsuario(resultSet.getInt("userId"));
                    cont.setInicio(resultSet.getDate("fechaInicio"));
                    cont.setFin(resultSet.getDate("fechaFin"));
                    cont.setIngresos(resultSet.getInt("ingresos"));
                    cont.setGastos(resultSet.getInt("gastos"));
                    cont.setBalance(resultSet.getInt("balance"));

                    return cont;
                
                } else {
                    // Manejar el caso en el que no se encuentra el producto
                    return null;
                }
            } 
        } catch (SQLException e) {
            System.err.println("Error al obtener los datos de la operación: " + e.getMessage());

            return null;
        }
    }

    /* Devuelve un vector con todas las operaciones de contabilidad realizadas */
    public static Contabilidad[] getListaBalances_BD() {
        int totalOps = nOps();

        Contabilidad[] ops = new Contabilidad[totalOps];

        String query = "SELECT * FROM contabilidad";
                
        try (Connection conn = Conexion.getConexion();
         PreparedStatement preparedStatement = conn.prepareStatement(query);
         ResultSet resultSet = preparedStatement.executeQuery()) {
            
            int i = 0;

            while (resultSet.next()) {
                Contabilidad cont = new Contabilidad();
                    
                cont.setOperacionId(resultSet.getInt("operacionid"));
                cont.setIdUsuario(resultSet.getInt("userId"));
                cont.setInicio(resultSet.getDate("fechaInicio"));
                cont.setFin(resultSet.getDate("fechaFin"));
                cont.setIngresos(resultSet.getInt("ingresos"));
                cont.setGastos(resultSet.getInt("gastos"));
                cont.setBalance(resultSet.getInt("balance"));

                ops[i++] = cont;
            }

            return ops;
        
        } catch (SQLException e) {
            System.err.println("Error al obtener las operaciones realizadas: " + e.getMessage());
        }

        return null;
    }

    //Método que devuelve el número total de operaciones almacenadas en la base de datos
    private static int nOps() {
        int cantidad = 0;

        String query = "SELECT COUNT(*) AS cantidad FROM contabilidad";

        try (Connection conn = Conexion.getConexion();
         PreparedStatement preparedStatement = conn.prepareStatement(query);
         ResultSet countResultSet = preparedStatement.executeQuery()) {
            
            if (countResultSet.next()) {
                cantidad = countResultSet.getInt("cantidad");
            }
        
        } catch (SQLException e) {
            System.err.println("Error al obtener la cantidad de operaciones de contabilidad: " + e.getMessage());
        }

        return cantidad;
    }
}
