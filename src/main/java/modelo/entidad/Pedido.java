package modelo.entidad;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import modelo.entidad.Producto;

public class Pedido {
    private int pedidoId; //Autogenerado en la base de datos    
    private int usuarioId;
    private Date fecha;
    private BigDecimal precioTotal;
    private Producto productos;
    private String distribuidorId;

    /* Constructor vacío para crear un objeto Pedido sin valores */
    public Pedido() {
    
    }
    public Pedido (int usuarioId, Producto producto) {
        this.usuarioId = usuarioId;
        this.fecha = new Date(System.currentTimeMillis());
        this.precioTotal = new BigDecimal(0);
        this.productos = producto;
    }

    /* Constructor para crear un Pedido en código antes de guardarlo en la BD */
    public Pedido (int usuarioId, Date fecha, BigDecimal precioTotal, String distribuidorId) {
        this.usuarioId = usuarioId;
        this.fecha = fecha;
        this.precioTotal = precioTotal;
        this.distribuidorId = distribuidorId;
    }

    /* Constructor utilizado al recuperar un pedido de la BD */

    /*
     * Al recuperar no se recuperan los productos del pedido, solo los detalles del pedido
     * List<Producto> productos
     */
    public Pedido(int pedidoId, int usuarioId, Date fecha, BigDecimal precioTotal, String distribuidorId){
        this.pedidoId = pedidoId;
        this.usuarioId = usuarioId;
        this.fecha = fecha;
        this.precioTotal = precioTotal;
        this.distribuidorId = distribuidorId;
    }

    public Date getFecha(){
        return this.fecha;
    }

    public BigDecimal getPrecioTotal(){
        return this.precioTotal;
    }

    public Producto getProductos(){
        return this.productos;
    }

    public String getDistribuidor(){
        return this.distribuidorId;
    }
    
    public int getPedidoId(){
        return this.pedidoId;
    }

    public int getUserId(){
        return this.usuarioId;
    }

    public void setFecha(Date fecha){
        this.fecha = fecha;
    }

    public void setPrecioTotal(BigDecimal precioTotal){
        this.precioTotal = precioTotal;
    }

    public void setProductos(Producto productos){
        this.productos = productos;
    }

    public void setDistribuidor(String distribuidorId){
        this.distribuidorId = distribuidorId;
    }

    public void setPedidoId(int pedidoId){
        this.pedidoId = pedidoId;
    }

    public void setUserId(int usuarioId){
        this.usuarioId = usuarioId;
    }

}
