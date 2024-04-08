package entity;

public class Tienda {
    private int idTienda;
    private String nombre;
    private String ubucacion;

    public Tienda() {
    }

    public Tienda(String nombre, String ubucacion) {
        this.nombre = nombre;
        this.ubucacion = ubucacion;
    }

    public int getIdTienda() {
        return idTienda;
    }

    public void setIdTienda(int idTienda) {
        this.idTienda = idTienda;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUbucacion() {
        return ubucacion;
    }

    public void setUbucacion(String ubucacion) {
        this.ubucacion = ubucacion;
    }

    @Override
    public String toString() {
        return "ID: " + idTienda +  " - Nombre: " + nombre + " - Ubicación: " + ubucacion;
    }
}
