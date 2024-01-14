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
    private Date fecha;
    private BigDecimal precioTotal;
    private List<Producto> productos;
    private Distribuidor distribuidor;

    /*
     * Recibe como parámetro un distribuidor? 
     * 
     * Al crearse un pedido recibe los parámetros de arriba,
     * del distribuidor solo tiene su ID igual que el ID del usuario
     */
    public Pedido(Distribuidor distribuidor){
        //TODO: generar una id de pedido
        this.fecha = new Date(System.currentTimeMillis());
        this.distribuidor = distribuidor;
        this.productos = new ArrayList<>();
        this.precioTotal = new BigDecimal(0);
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

    public Distribuidor getDistribuidor(){
        return this.distribuidor;
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
