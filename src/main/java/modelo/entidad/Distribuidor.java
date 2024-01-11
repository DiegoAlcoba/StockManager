import java.util.ArrayList;
import java.util.List;

public class Distribuidor {
    private String nombre;
    private String mail;
    private List<Producto> productos;

    public Distribuidor(String name, String mail){
        this.nombre = name;
        this.mail = mail;
        this.productos = new ArrayList<>();
    }

    //setter
    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public void setMail(String mail){
        this.mail = mail;
    }

    public void setProductos(List<Producto> productos){
        this.productos = productos;
    }
    //Getters
    public String getNombre(){
        return this.nombre;
    }

    public String getMail(){
        return this.mail;    
    }

    public List<Producto> getProductos(){
        return this.productos;
    }
    /**
     * Precondicion: El producto a añadir no comparte nombre y tipo con ningun otro producto del catalogo
     * Funcion de adicion de un producto al catalogo de productos
     * @param producto
     * @return true en caso de haberse añadido
     *         false en caso existir algun producto igual
     */
    public boolean addProducto(Producto producto){
        for(int i = 0; i < this.productos.size(); i++){
            if(this.productos.get(i).isEqual(producto)){
                return false;
            }
        }
        this.productos.add(producto);
        return true;
    }
}   
