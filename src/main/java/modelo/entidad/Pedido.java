import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private List<Producto> productos;
    private List<Distribuidor> distribuidores;

    public Pedido(){
        this.productos = new ArrayList<>();
        this.distribuidores = new ArrayList<>();
    }

    public boolean addProducto(Producto producto){
        if(!inLista(producto)){
            return false;
        }else{
            this.productos.add(producto);
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
