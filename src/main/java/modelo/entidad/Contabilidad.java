package modelo.entidad;

import java.sql.Date;

/**
 *
 * @author diego
 */
public class Contabilidad {
    private int operacionId; //Autogenerado al almacenarse en la base de datos
    private int userId; //ID del usuario que realizó la contabilidad
    private Date fechaInicio;
    private Date fechaFin;
    private int ingresos;
    private int gastos;
    private int balance;

    //Constructores de la clase
    public Contabilidad() {

    }

    public Contabilidad (int idUsuario, Date fechaI, Date fechaF, int ganado, int pagado, int diferencia) {
        this.userId = idUsuario;
        this.fechaInicio = fechaI;
        this.fechaFin = fechaF;
        this.ingresos = ganado;
        this.gastos = pagado;
        this.balance = diferencia;
    }

    public Contabilidad (int idOpeacion, int idUsuario, Date fechaI, Date fechaF, int ganado, int pagado, int diferencia) {
        this.operacionId = idOpeacion;
        this.userId = idUsuario;
        this.fechaInicio = fechaI;
        this.fechaFin = fechaF;
        this.ingresos = ganado;
        this.gastos = pagado;
        this.balance = diferencia;
    }

    //Getters
    public int getOperacionId() {
        return this.operacionId;
    }

    public int getIdUsuario() {
        return this.userId;
    }

    public Date getInicio() {
        return this.fechaInicio;
    }

    public Date getFin() {
        return this.fechaFin;
    }

    public int getIngresos() {
        return this.ingresos;
    }

    public int getGastos() {
        return this.gastos;
    }

    public int getBalance() {
        return this.balance;
    }

    //Setters
    public void setOperacionId(int id) {
        this.operacionId = id;
    }

    public void setIdUsuario(int id) {
        this.userId = id;
    }

    public void setInicio(Date fecha) {
        this.fechaInicio = fecha;
    }

    public void setFin(Date fecha) {
        this.fechaFin = fecha;
    }

    public void setIngresos(int ganado) {
        this.ingresos = ganado;
    }

    public void setGastos(int gastado) {
        this.gastos = gastado;
    }

    public void setBalance(int dif) {
        this.balance = dif;
    }
}