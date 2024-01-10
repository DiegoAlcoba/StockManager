public class Producto {
    private String nombre;
    private String tipo;
    private int precioVenta;
    private int cantActual;
    private int cantMinima;
    private int cantMax;
    public Producto(String name, String tipo, int price, int actual, int min, int max){
        this.nombre = name;
        this.tipo = tipo;
        //TODO: añadir exceptions para precios y cantidades negativas.
        this.precioVenta = price;
        this.cantActual = actual;
        this.cantMinima = min;
        this.cantMax = max;
    }
    //Setters
    public void setName(String nombre){
        this.nombre = nombre;
    }

    public void setTipo(String tipo){
        this.tipo = tipo;
    }

    public void setPrecioVenta(int price){
        this.precioVenta = price;
    }

    public void setCantActual(int cant){
        this.cantActual = cant;
    }

    public void setCantMin(int cant){
        this.cantActual = cant;
    }

    public void setCantMax(int max){
        this.cantMax = max;
    }
    //Getters
    public String getName(){
        return this.nombre;
    }

    public String getTipo(){
        return this.tipo;
    }

    public int getPrecioVenta(){
        return this.precioVenta;
    }

    public int getCantActual(){
        return this.cantActual;
    }

    public int getCantMin(){
        return this.cantMinima;
    }

    public int getCantMax(){
        return this.cantMax;
    }
    
    public boolean isEqual(Producto producto){
        if(this.nombre == producto.getName() && this.tipo == producto.getTipo()){
            return true;
        }
        return false;
    }
}
