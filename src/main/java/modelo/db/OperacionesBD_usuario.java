package modelo.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modelo.entidad.Distribuidor;
import modelo.entidad.Usuario;

/**
 *
 * @author diego
 */
public class OperacionesBD_usuario {
    
    /*Inserta un nuevo usuario en la base de datos*/
    public static boolean addUsuario_BD (Usuario user) {
        String query = "INSERT INTO usuario (nombreUsuario, contrasena, privilegios, nombre, SSId, email, numTelefono) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = Conexion.getConexion();
         PreparedStatement preparedStatement = conn.prepareStatement(query)) {
    
        // Se establece el valor a cada uno de los parámetro de la sentencia
        preparedStatement.setString(1, user.getUsername());
        preparedStatement.setString(2, user.getPass());
        preparedStatement.setInt(3, getPrivsInt(user.getPrivileges())); 
        preparedStatement.setString(4, user.getNombre());
        preparedStatement.setInt(5, user.getSSId());
        preparedStatement.setString(6, user.getEmail());
        preparedStatement.setInt(7, user.getTlfn());
        
        // Ejecuta la consulta SQL
        preparedStatement.executeUpdate();
        System.out.println("Usuario añadido correctamente.");

        return true;
           
        } catch (SQLException e) {
            System.err.println("Error al añadir el usuario: " + e.getMessage());
        }

        return false;
    }
    
    /*Devuelve un usuario de la base de datos*/
    public static Usuario getUsuario_BD (String username) {
        String query = "SELECT * FROM usuario WHERE nombreUsuario = ?";

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
                    user.setPrivileges(getPrivsBool(resultSet.getInt("privilegios")));
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
    
    /* Elimina un usuario de la base de datos */
    public static boolean delUsuario_BD (String username) {
        String query = "DELETE FROM usuario WHERE nombreUsuario = ?";
        
        try (Connection conn = Conexion.getConexion();
         PreparedStatement preparedStatement = conn.prepareStatement(query)) {
    
            // Se establece el valor a cada uno de los parámetro de la sentencia
            preparedStatement.setString(1, username);
            
            // Ejecuta la consulta SQL
            preparedStatement.executeUpdate();
            System.out.println("Usuario eliminado correctamente.");

            return true;
            
        } catch (SQLException e) {
            System.err.println("Error al eliminar el usuario " + username + ": " + e.getMessage());
        }

        return false;
    }

    /* Cambia la contraseña del usuario recibido por parámetro */
    public static void changePassUser_BD(String username, String oldPass, String newPass) {
        String query = "UPDATE usuario SET contrasena = ? WHERE nombreUsuario = ? AND contrasena = ?";
        
        try (Connection conn = Conexion.getConexion();
         PreparedStatement preparedStatement = conn.prepareStatement(query)) {
    
            // Se establece el valor a cada uno de los parámetro de la sentencia
            preparedStatement.setString(1, newPass);
            preparedStatement.setString(2, username);
            preparedStatement.setString(3, oldPass);
            
            // Ejecuta la consulta SQL y almacena el nº de filas alteradas
            int rows = preparedStatement.executeUpdate();
            
            if (rows > 0) {
                System.out.println("Contraseña actualizada correctamente.");
            } else {
                System.out.println("No se pudo cambiar la contraseña. Verifique las credenciales introducidas.");
            }
           
        } catch (SQLException e) {
            System.err.println("Error al : " + e.getMessage());
        }
    }

    /* Cambia los privilegios del usuario recibido por parámetro */
    public static void changePrivsUser_BD(Usuario user) {
        String query = "UPDATE usuario SET privilegios = ? WHERE nombreUsuario = ?";
        
        try (Connection conn = Conexion.getConexion();
         PreparedStatement preparedStatement = conn.prepareStatement(query)) {
    
            // Se establece el valor a cada uno de los parámetro de la sentencia
            preparedStatement.setInt(1, getPrivsInt(!user.getPrivileges())); //Al negarlo pasa de false a true y viceversa
            preparedStatement.setString(2, user.getNombre());
            
            // Ejecuta la consulta SQL y almacena el nº de filas alteradas
            int rows = preparedStatement.executeUpdate();
            
            if (rows > 0) {
                System.out.println("Privilegios de usuario actualizados correctamente.");
            } else {
                System.out.println("No se pudieron cambiar los privilegios del usuario.");
            }
           
        } catch (SQLException e) {
            System.err.println("Error al : " + e.getMessage());
        }
    }

    /* Devuelve un vector con los usuarios almacenados en la base de datos */
    public static Usuario[] getListaUsuarios_BD() {
        int totalUsers = nUsuarios();

        Usuario[] usuarios = new Usuario[totalUsers];

        String query = "SELECT * FROM usuario";
                
        try (Connection conn = Conexion.getConexion();
         PreparedStatement preparedStatement = conn.prepareStatement(query);
         ResultSet resultSet = preparedStatement.executeQuery()) {
            
            int i = 0;

            while (resultSet.next()) {
                Usuario user = new Usuario();
                    
                user.setUserId(resultSet.getInt("userId"));
                user.setUsername(resultSet.getString("nombreUsuario"));
                user.setPass(resultSet.getString("contrasena"));
                user.setPrivileges(getPrivsBool(resultSet.getInt("privilegios")));
                user.setNombre(resultSet.getString("nombre"));
                user.setSSId(resultSet.getInt("SSId"));
                user.setEmail(resultSet.getString("email"));
                user.setTlfn(resultSet.getInt("numTelefono"));

                usuarios[i++] = user;
            }

            return usuarios;
        
        } catch (SQLException e) {
            System.err.println("Error al obtener los usuarios: " + e.getMessage());
        }

        return null;
    }

    //Método que devuelve el número de usuarios totales en la BD
    private static int nUsuarios() {
        int cantidad = 0;

        String query = "SELECT COUNT(*) AS cantidad FROM usuario";

        try (Connection conn = Conexion.getConexion();
         PreparedStatement preparedStatement = conn.prepareStatement(query);
         ResultSet countResultSet = preparedStatement.executeQuery()) {
            
            if (countResultSet.next()) {
                cantidad = countResultSet.getInt("cantidad");
            }
        
        } catch (SQLException e) {
            System.err.println("Error al obtener la cantidad de usuarios: " + e.getMessage());
        }

        return cantidad;
    }

    //Método para conversión de booleano a int para los privilegios del usuario
    private static int getPrivsInt(boolean privs) {
        if (privs) {
            return 1;
        }
        else {
            return 0;
        }
    }

    //Método para conversión de int a booleano para los privilegios del usuario
    private static boolean getPrivsBool(int privs) {
        if (privs == 1) {
            return true;
        }
        else {
            return false;
        }
    }

}
