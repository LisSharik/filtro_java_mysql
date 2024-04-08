package entity;

import java.text.NumberFormat;
import java.time.LocalDate;

public class Compra {
    private int idCompra;
    private int cantidad;
    private String fechaCompra = LocalDate.now().toString();
    private int id_cliente;
    private Cliente objCliente;
    private  int id_producto;
    private Producto objProducto;

    private NumberFormat formatear = NumberFormat.getCurrencyInstance();
    private String precioIva;
    private double IVA = 19;


    public Compra() {
    }

    public Compra(int cantidad, int id_cliente, Cliente objCliente, int id_producto, Producto objProducto) {
        this.cantidad = cantidad;
        this.id_cliente = id_cliente;
        this.objCliente = objCliente;
        this.id_producto = id_producto;
        this.objProducto = objProducto;

    }

    public int getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(String fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public Cliente getObjCliente() {
        return objCliente;
    }

    public void setObjCliente(Cliente objCliente) {
        this.objCliente = objCliente;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public Producto getObjProducto() {
        return objProducto;
    }

    public void setObjProducto(Producto objProducto) {
        this.objProducto = objProducto;
    }



    public double getIVA() {
        return IVA / 100;
    }


    public void setIVA(double IVA) {
        this.IVA = IVA;
    }

    public String getPrecioIva() {
        double precio = this.objProducto.getPrecio();
        return formatear.format(precio += precio * this.getIVA());

    }


    @Override
    public String toString() {
        return "ID: " + idCompra  + " - Fecha: " + fechaCompra + " - Tienda: " + objProducto.getObjTienda().getNombre()
                + " - Ubicacion: " + objProducto.getObjTienda().getUbucacion()
                + "\n Cliente: " + objCliente.getNombre() + " " + objCliente.getApellido()
                + "\n Producto: " + objProducto.getNombre() + " - Cantidad: " + cantidad + " - Precio: " + objProducto.getPrecioFormateado()
                + "\n IVA: " + IVA + "%"
                + "\n Total: " + this.getPrecioIva();

    }
}
