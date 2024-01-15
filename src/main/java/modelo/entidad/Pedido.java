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
    private List<Producto> productos;
    private String distribuidorId;

    /* Constructor vacío para crear un objeto Pedido sin valores */
    public Pedido() {
    
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

    public List<Producto> getProductos(){
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

    public void setProductos(List<Producto> productos){
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

    public boolean addProducto(Producto producto){
        if(!inLista(producto)){
            return false;
        }else{
            this.productos.add(producto);
            this.precioTotal = this.precioTotal.add(producto.getPrecio());
            return true;
        }
    }

    public boolean removeProducto(Producto producto){
        if(!inLista(producto)){
            return false;
        }else{
            this.productos.remove(producto);
            this.precioTotal = this.precioTotal.subtract(producto.getPrecio());
            return true;
        }
    }

    private boolean inLista(Producto producto){
        for(int i = 0; i < productos.size(); i++){
            if(producto.isEqual(productos.get(i))){
                return true;
            }
        }
        return false;
    }

}
