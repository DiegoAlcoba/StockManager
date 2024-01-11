package modelo.entidad;

public class Usuario {
    private String nombre;
    private String dni;
    private boolean administrador;
    public String getPrivileges;
    public int getSSId;
    public int getTlfn;

    /*
    FIJATE EN LA CLASE "OperacionesBD_usuario" A LA HORA DE HACER EL CONSTRUCTOR
    Y LOS GETTERS Y LOS SETTERS, QUE SEAN IGUALES PARA QUE NO HAYA QUE CAMBIAR COSAS
    */
    
    public Usuario(/*Fijate en los datos de usuario en la base de datos a la hora de hacer el constructor*/){
        this.nombre = nombre;
        this.administrador = administrador;
        this.dni = dni;
    }

    /* --- COMPLETAR ---*/
    public int getUserId() {
        
    }

    public String getUsername() {
        
    }

    public String getPass() {
        
    }
    
    public String getPrivileges() {
        
    }

    public String getName() {
        
    }
    
    public int getSSId() {
        
    }

    public String getEmail() {
        
    }

    public int getTlfn() {
        
    }


    
}
