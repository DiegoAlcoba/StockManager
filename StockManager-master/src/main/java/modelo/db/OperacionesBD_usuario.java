package modelo.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modelo.entidad.Producto;
import modelo.entidad.Usuario;

/**
 *
 * @author diego
 */
public class OperacionesBD_usuario {
    
    //Insertar un nuevo usuario en la base de datos
    public static void addUser_BD (Usuario user) {
        String query = "INSERT INTO USUARIO (nombreUsuario, contrasena, privilegios, nombre, SSId, email, numTelefono) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = Conexion.getConexion();
         PreparedStatement preparedStatement = conn.prepareStatement(query)) {
    
        // Se establece el valor a cada uno de los parámetro de la sentencia
        preparedStatement.setString(1, user.getUsername());
        preparedStatement.setString(2, user.getPass());
        preparedStatement.setBoolean(3, user.getPrivileges());
        preparedStatement.setString(4, user.getNombre());
        preparedStatement.setInt(5, user.getSSId());
        preparedStatement.setString(6, user.getEmail());
        preparedStatement.setInt(7, user.getTlfn());
        
        // Ejecuta la consulta SQL
        preparedStatement.executeUpdate();
        System.out.println("Usuario añadido correctamente.");
           
        } catch (SQLException e) {
            System.err.println("Error al añadir el usuario: " + e.getMessage());
        }
    }
    
    //Recuperar un usuario de la base de datos
    public static Usuario getUsuario_BD (String username) {
        String query = "SELECT * FROM usuario WHERE username = ?";

        try (Connection conn = Conexion.getConexion();
         PreparedStatement preparedStatement = conn.prepareStatement(query)) {
    
        // Se establece el valor a cada uno de los parámetro de la sentencia
        preparedStatement.setString(1, username);
        
        try (ResultSet resultSet = preparedStatement.executeQuery()) {
                
                if (resultSet.next()) {
                    Usuario user = new Usuario();
                    user.setUserId(resultSet.getInt("userId"));
                    user.setUsername(resultSet.getString("nombreUsuario"));
                    user.setPass(resultSet.getString("contrasena"));
                    user.setPrivileges(resultSet.getBoolean("privilegios"));
                    user.setNombre(resultSet.getString("nombre"));
                    user.setSSId(resultSet.getInt("SSId"));
                    user.setEmail(resultSet.getString("email"));
                    user.setTlfn(resultSet.getInt("numTelefono"));
                    
                    return user;
                
                } else {
                    // Manejar el caso en el que no se encuentra el producto
                    return null;
                }
            }
           
        } catch (SQLException e) {
            System.err.println("Error al obtener el usuario: " + e.getMessage());

            return null;
        }
    }

    /*
     * ACCIONES QUE FALTAN: Eliminar usuario; cambiar contraseña, cambiar privilegios, etc 
     *                                        (O que actualice todo el perfil automáticamente? -> 
     *                                         en código solo se alteran los campos que se requieran, 
     *                                         se altera la tabla entera??)
     */


}
