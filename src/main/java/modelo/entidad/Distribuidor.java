package modelo.entidad;

import java.util.ArrayList;
import java.util.List;
import modelo.entidad.Producto;

public class Distribuidor {
    private String distribId;
    private String nombre;
    private String mail;
    private int tlfn;
    private List<Producto> productos;

    public Distribuidor(String id, String name, String mail, int telef){
        this.distribId = id;
        this.nombre = name;
        this.mail = mail;
        this.tlfn = telef;
        this.productos = new ArrayList<>();
    }

    public Distribuidor() {
        //TODO Auto-generated constructor stub
    }

    //setter
    public void setId(String id) {
        this.distribId = id;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public void setMail(String mail){
        this.mail = mail;
    }

    public void setTlfn(int telef) {
        this.tlfn = telef;
    }

    public void setProductos(List<Producto> productos){
        this.productos = productos;
    }
    //Getters
    public String getId() {
        return this.distribId;
    }

    public String getNombre(){
        return this.nombre;
    }

    public String getMail(){
        return this.mail;    
    }

    public int getTlfn() {
        return this.tlfn;
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
