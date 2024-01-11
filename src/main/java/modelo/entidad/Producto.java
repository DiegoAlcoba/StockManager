package modelo.entidad;

import java.math.BigDecimal;

public class Producto {
    private String nombre;
    private String distribuidor;
    private String tipo;
    private BigDecimal coste;
    private int cantidad; //Se entiende que es la cantidad actual - la que hay almacenada en la base de datos
    
    public Producto() {
        //Sobrecarga de productores, necesario para cuando se desea crear un objeto sin valores iniciales, se asignan después con el constructor de debajo
    }
    
    public Producto(String name, String distribId, String tipo, BigDecimal precio, int cant){
        this.nombre = name;
        this.tipo = tipo;
        this.distribuidor = distribId;
        //TODO: añadir exceptions para precios y cantidades negativas.
        this.coste = precio;
        this.cantidad = cant;
    }

    //Setters
    public void setName(String nombre){
        this.nombre = nombre;
    }
    
    public void setDistribuidor(String distribuidor) {
        this.distribuidor = distribuidor;
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
    
    public String getDistrib() {
        return this.distribuidor;
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
