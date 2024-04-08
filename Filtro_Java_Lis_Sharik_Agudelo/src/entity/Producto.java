package entity;

import java.text.NumberFormat;

public class Producto {
    private int idProducto;
    private String nombre;
    private double precio;
    private int stock;
    private int id_tienda;
    private Tienda objTienda;
    private NumberFormat formateador = NumberFormat.getCurrencyInstance();
    private String precioFormateado;
    public Producto() {
    }

    public Producto(String nombre, double precio, int stock, int id_tienda, Tienda objTienda) {
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.id_tienda = id_tienda;
        this.objTienda = objTienda;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precioFormateado = formateador.format(precio);
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getId_tienda() {
        return id_tienda;
    }

    public void setId_tienda(int id_tienda) {
        this.id_tienda = id_tienda;
    }

    public Tienda getObjTienda() {
        return objTienda;
    }

    public void setObjTienda(Tienda objTienda) {
        this.objTienda = objTienda;
    }

    public String getPrecioFormateado() {
        return precioFormateado;
    }


    @Override
    public String toString() {
        return "ID: " + idProducto + " - Nombre: " + nombre
                + " - Precio: " + precioFormateado + " - Cantidad: " + stock
                + " - Tienda: " + objTienda.getNombre();
    }
}
