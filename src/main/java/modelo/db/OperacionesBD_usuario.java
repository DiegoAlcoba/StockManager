package modelo.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import modelo.entidad.Usuario;

/**
 *
 * @author diego
 */
public class OperacionesBD_usuario {
    
    //Insertar un nuevo usuario en la base de datos
    public static void addUser_BD (Usuario user) {
        String query = "INSERT INTO USUARIO (userId, nombreUsuario, contrasena, privilegios, nombre, SSId, email, numTelefono) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = Conexion.getConexion();
         PreparedStatement preparedStatement = conn.prepareStatement(query)) {
    
        // Se establece el valor a cada uno de los parámetro de la sentencia
        preparedStatement.setInt(1, user.getUserId());
        preparedStatement.setString(2, user.getUsername());
        preparedStatement.setString(3, user.getPass());
        preparedStatement.setString(4, user.getPrivileges);
        preparedStatement.setString(5, user.getName());
        preparedStatement.setInt(6, user.getSSId);
        preparedStatement.setString(7, user.getEmail());
        preparedStatement.setInt(8, user.getTlfn);
        
        // Ejecuta la consulta SQL
        preparedStatement.executeUpdate();
        System.out.println("Usuario añadido correctamente.");
           
    } catch (SQLException e) {
        System.err.println("Error al añadir el usuario: " + e.getMessage());
    }
    }
    
}
