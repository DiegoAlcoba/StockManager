package modelo.entidad;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import modelo.entidad.Producto;

public class Pedido {
    private int pedidoId; //Como en el usuario, el id se asigna automáticamente al añadirse a la base de datos
    /*
     * Falta el id del usuario que ha hecho el pedido
     */
    private int usuarioId;
    private Date fecha;
    private BigDecimal precioTotal;
    private List<Producto> productos;
    private int distribuidorId;

    public Pedido(int pedidoId, int usuarioId, Date fecha, BigDecimal precioTotal, List<Producto> productos, int distribuidorId){
        this.pedidoId = pedidoId;
        this.usuarioId = usuarioId;
        this.fecha = fecha;
        this.precioTotal = precioTotal;
        this.productos = productos;
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

    public int getDistribuidor(){
        return this.distribuidorId;
    }
    
    public int getPedidoId(){
        return this.pedidoId;
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
