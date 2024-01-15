/*
 * Cuerpo para las consultas sql en java
 */

        String query = "";
                
        try (Connection conn = Conexion.getConexion();
         PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            
            // Se establece el valor a cada uno de los parámetro de la sentencia
            preparedStatement.set();
            
            // Ejecuta la consulta SQL
            preparedStatement.executeUpdate();
            System.out.println(" correctamente.");
        
        } catch (SQLException e) {
            System.err.println("Error al : " + e.getMessage());
        }