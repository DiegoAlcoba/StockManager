package modelo.entidad;

public class Usuario {
    private int userID;
    private String username;
    private String password;
    private boolean isAdmin;
    private String nombre;
    private String dni;
    private String mail;
    private int tlfn;
    
    public Usuario(int userID, String username, String password, boolean isAdmin, String nombre, String dni, String mail, int tlfn) {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
        this.nombre = nombre;
        this.dni = dni;
        this.mail = mail;
        this.tlfn = tlfn;
    }

    public int getUserId() {
        return this.userID;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPass() {
        return this.password;
    }
    
    public String getPrivileges() {
        return this.isAdmin;
    }

    
    public int getSSId() {
        return this.dni;
    }

    public String getEmail() {
        return this.mail;
    }

    public int getTlfn() {
        return this.tlfn;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setUserId(int userID) {
        this.userID = userID;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPass(String password) {
        this.password = password;
    }

    public void setPrivileges(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public void setSSId(String dni) {
        this.dni = dni;
    }

    public void setEmail(String mail) {
        this.mail = mail;
    }

    public void setTlfn(int tlfn) {
        this.tlfn = tlfn;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String toString() {
        return "Usuario{" + "userID=" + userID + ", username=" + username + ", password=" + password + ", isAdmin=" + isAdmin + ", nombre=" + nombre + ", dni=" + dni + ", mail=" + mail + ", tlfn=" + tlfn + '}';
    }

}
