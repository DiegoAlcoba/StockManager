package modelo.entidad;

import modelo.entidad.Producto;

import java.math.BigDecimal;

import modelo.entidad.Pedido;

/**
 *
 * @author diego
 */
public class DetallesPedido {
    private int nDetallePedido; //Autogenerado en la BD
    private int idPedido;
    private String nombreProducto;
    private int cantidad;
    private BigDecimal coste;
    
    
    public DetallesPedido() {
        //Vacío
    }

    /* Constructor para meter el producto en un pedido */
    public DetallesPedido(int pedidoId, String nombre, int cant, BigDecimal precio) {
        this.idPedido = pedidoId;
        this.nombreProducto = nombre;
        this.cantidad = cant;
        this.coste = precio;
    }

    /* Constructor para obtener los detalles de un producto de un pedido */
    public DetallesPedido(int id, int pedidoId, String nombre, int cant, BigDecimal precio) {
        this.nDetallePedido = id;
        this.idPedido = pedidoId;
        this.nombreProducto = nombre;
        this.cantidad = cant;
        this.coste = precio;
    }

    public int getDetalleId() {
        return this.nDetallePedido;
    }

    public int getIdPedido() {
        return this.idPedido;
    }

    public String getNombreProd() {
        return this.nombreProducto;
    }

    public int getCantidadProd() {
        return this.cantidad;
    }

    public BigDecimal getCosteUnitario() {
        return this.coste;
    }

    public void setnDetalle(int id) {
        this.nDetallePedido = id;
    }

    public void setIdPedido(int id) {
        this.idPedido = id;
    }

    public void setNombreProd(String nombre) {
        this.nombreProducto = nombre;
    }

    public void setCantidadProd (int cant) {
        this.cantidad = cant;
    }

    public void setCoste(BigDecimal precio) {
        this.coste = precio;
    }

}
