package modelo.entidad;

import java.math.BigDecimal;

public class Producto {
    private String nombre;
    private int distribId;
    private String tipo;
    private BigDecimal coste;
    private int cantidad; //Se entiende que es la cantidad actual - la que hay almacenada en la base de datos
    
    public Producto() {
        //Sobrecarga de productores, necesario para cuando se desea crear un objeto sin valores iniciales, se asignan después con el constructor de debajo
    }
    
    public Producto(String name, int distribId, String tipo, BigDecimal precio, int cant){
        if(precio.compareTo(BigDecimal.ZERO) < 0 || cant < 0){
            throw new IllegalArgumentException("El precio y la cantidad no pueden ser negativos");
        }
        this.nombre = name;
        this.tipo = tipo;
        this.distribId = distribId;
        this.coste = precio;
        this.cantidad = cant;
    }

    //Setters
    public void setName(String nombre){
        this.nombre = nombre;
    }
    
    public void setDistribuidorId(int distribuidorID) {
        this.distribId = distribuidorID;
    }

    public void setTipo(String tipo){
        this.tipo = tipo;
    }

    public void setPrecio(BigDecimal precio){
        this.coste = precio;
    }

    public void setCantidad(int cant){
        this.cantidad = cant;
    }

    //Getters
    public String getName(){
        return this.nombre;
    }
    
    public int getDistribId() {
        return this.distribId;
    }

    public String getTipo(){
        return this.tipo;
    }

    public BigDecimal getPrecio(){
        return this.coste;
    }

    public int getCantidad(){
        return this.cantidad;
    }
    
    public boolean isEqual(Producto producto){
         // Comprobación de nulidad y comparación de cadenas
         return this.nombre != null && producto != null && this.nombre.equals(producto.getName()) && this.tipo != null && this.tipo.equals(producto.getTipo());
    }
}
