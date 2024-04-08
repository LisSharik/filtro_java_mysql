package model;

import database.CRUD;
import database.Configdb;
import entity.Cliente;
import entity.Producto;
import entity.Tienda;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductoModel implements CRUD {
    @Override
    public Object insert(Object object) {
        Connection objConnection = Configdb.openConnection();
        Producto objProducto = (Producto) object;

        try {
            String sql = "INSERT INTO producto(nombre,precio,id_tienda,stock) VALUES (?, ?,  ?, ?);";
            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql,
                    PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setString(1, objProducto.getNombre());
            objPrepare.setDouble(2, objProducto.getPrecio());
            objPrepare.setInt(3, objProducto.getId_tienda());
            objPrepare.setInt(4, objProducto.getStock());

            objPrepare.execute();

            ResultSet objResult = objPrepare.getGeneratedKeys();

            while (objResult.next()){
                objProducto.setIdProducto(objResult.getInt(1));
            }

            JOptionPane.showMessageDialog(null,"Producto agregado exitosamente");


        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error " + e.getMessage());
            System.out.println(e.getMessage());

        }
        Configdb.closeConnection();
        return objProducto;
    }

    @Override
    public boolean update(Object object) {
        Producto objProducto = (Producto) object;
        boolean isUpdated = false;
        Connection objConnection = Configdb.openConnection();

        try {
            String sql = "UPDATE producto SET nombre = ?, precio = ?, id_tienda = ?,stock = ? WHERE id_producto = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setString(1, objProducto.getNombre());
            objPrepare.setDouble(2, objProducto.getPrecio());
            objPrepare.setInt(3,objProducto.getId_tienda());
            objPrepare.setInt(4, objProducto.getStock());

            objPrepare.setInt(5, objProducto.getIdProducto());


            if (objPrepare.executeUpdate() > 0 ){
                isUpdated = true;
                JOptionPane.showMessageDialog(null, "Se ha actualizado correctamente");
            }


        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error " + e.getMessage());
            System.out.println(e.getMessage());

        }
        Configdb.closeConnection();
        return isUpdated;

    }

    @Override
    public boolean delete(Object object) {
        Producto objProducto = (Producto) object;
        boolean isDeleted = false;
        Connection objConnection = Configdb.openConnection();

        try {
            String sql = "DELETE FROM producto WHERE producto.id_producto = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            objPrepare.setInt(1, objProducto.getIdProducto());

            if(objPrepare.executeUpdate() > 0){
                isDeleted = true;
                JOptionPane.showMessageDialog(null, "La eliminacion fue exitosa");
            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error " + e.getMessage());
            System.out.println(e.getMessage());

        }
        Configdb.closeConnection();
        return isDeleted;
    }

    @Override
    public List<Object> findAll() {
        Connection objConnection = Configdb.openConnection();
        List<Object> listProductos = new ArrayList<>();

        try {
            String sql = "SELECT * FROM producto\n" +
                    "INNER JOIN tienda on tienda.id_tienda = producto.id_tienda\n" +
                    "ORDER BY producto.id_producto ASC;";

            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql);
            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                Producto objProducto = new Producto();
                objProducto.setIdProducto(objResult.getInt("producto.id_producto"));
                objProducto.setNombre(objResult.getString("producto.nombre"));
                objProducto.setPrecio(objResult.getDouble("producto.precio"));
                objProducto.setId_tienda(objResult.getInt("producto.id_tienda"));
                objProducto.setStock(objResult.getInt("producto.stock"));

                Tienda objTienda = new Tienda();
                objTienda.setIdTienda(objResult.getInt("tienda.id_tienda"));
                objTienda.setNombre(objResult.getString("tienda.nombre"));
                objTienda.setUbucacion(objResult.getString("tienda.ubucacion"));
                objProducto.setObjTienda(objTienda);

                listProductos.add(objProducto);

            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error " + e.getMessage());
            System.out.println(e.getMessage());
        }

        Configdb.closeConnection();
        return listProductos;

    }

    public List<Object> findByTienda(String search){
        Connection objConnection = Configdb.openConnection();
        List<Object> listProductos = new ArrayList<>();

        try {
            String sql = "SELECT * FROM producto\n" +
                    "INNER JOIN tienda on tienda.id_tienda = producto.id_tienda\n" +
                    "WHERE tienda.nombre LIKE '%"+ search +"%' \n" +
                    "ORDER BY producto.id_producto ASC;";

            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql);
            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                Producto objProducto = new Producto();
                objProducto.setIdProducto(objResult.getInt("producto.id_producto"));
                objProducto.setNombre(objResult.getString("producto.nombre"));
                objProducto.setPrecio(objResult.getDouble("producto.precio"));
                objProducto.setId_tienda(objResult.getInt("producto.id_tienda"));
                objProducto.setStock(objResult.getInt("producto.stock"));

                Tienda objTienda = new Tienda();
                objTienda.setIdTienda(objResult.getInt("tienda.id_tienda"));
                objTienda.setNombre(objResult.getString("tienda.nombre"));
                objTienda.setUbucacion(objResult.getString("tienda.ubucacion"));
                objProducto.setObjTienda(objTienda);

                listProductos.add(objProducto);

            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error " + e.getMessage());
            System.out.println(e.getMessage());
        }

        Configdb.closeConnection();
        return listProductos;
    }
    public List<Object> findByNombre(String search){
        Connection objConnection = Configdb.openConnection();
        List<Object> listProductos = new ArrayList<>();

        try {
            String sql = "SELECT * FROM producto\n" +
                    "INNER JOIN tienda on tienda.id_tienda = producto.id_tienda\n" +
                    "WHERE producto.nombre LIKE '%"+ search +"%' \n" +
                    "ORDER BY producto.id_producto ASC;";

            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql);
            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                Producto objProducto = new Producto();
                objProducto.setIdProducto(objResult.getInt("producto.id_producto"));
                objProducto.setNombre(objResult.getString("producto.nombre"));
                objProducto.setPrecio(objResult.getDouble("producto.precio"));
                objProducto.setId_tienda(objResult.getInt("producto.id_tienda"));
                objProducto.setStock(objResult.getInt("producto.stock"));

                Tienda objTienda = new Tienda();
                objTienda.setIdTienda(objResult.getInt("tienda.id_tienda"));
                objTienda.setNombre(objResult.getString("tienda.nombre"));
                objTienda.setUbucacion(objResult.getString("tienda.ubucacion"));
                objProducto.setObjTienda(objTienda);

                listProductos.add(objProducto);

            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error " + e.getMessage());
            System.out.println(e.getMessage());
        }

        Configdb.closeConnection();
        return listProductos;
    }

}
