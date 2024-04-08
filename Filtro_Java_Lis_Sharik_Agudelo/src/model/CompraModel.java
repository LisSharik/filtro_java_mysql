package model;

import database.CRUD;
import database.Configdb;
import entity.Cliente;
import entity.Compra;
import entity.Producto;
import entity.Tienda;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CompraModel implements CRUD {
    @Override
    public Object insert(Object object) {
        Connection objConnection = Configdb.openConnection();
        Compra objCompra = (Compra) object;

        try {
            String sql = "INSERT INTO compra (cantidad, id_cliente, id_producto) VALUES (? , ?, ?);";
            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql,
                    PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setInt(1, objCompra.getCantidad());
            objPrepare.setInt(2,objCompra.getId_cliente());
            objPrepare.setInt(3, objCompra.getId_producto());

            objPrepare.execute();
            ResultSet objResult = objPrepare.getGeneratedKeys();

            while (objResult.next()){
                objCompra.setIdCompra(objResult.getInt(1));
            }

            JOptionPane.showMessageDialog(null,"Compra realizada exitosamente");

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error " + e.getMessage());
            System.out.println(e.getMessage());

        }
        Configdb.closeConnection();
        return objCompra;
    }

    @Override
    public boolean update(Object object) {
        Compra objCompra = (Compra) object;
        boolean isUpdated = false;
        Connection objConnection = Configdb.openConnection();
        try {
            String sql = "UPDATE compra SET cantidad = ?, id_cliente = ?, id_producto = ? WHERE id_compra = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setInt(1,objCompra.getCantidad());
            objPrepare.setInt(2,objCompra.getId_cliente());
            objPrepare.setInt(3,objCompra.getId_producto());


            objPrepare.setInt(4,objCompra.getIdCompra());

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
        Compra objCompra = (Compra) object;
        boolean isDeleted = false;
        Connection objConnection = Configdb.openConnection();

        try {
            String sql = "DELETE FROM compra WHERE compra.id_compra = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            objPrepare.setInt(1, objCompra.getIdCompra());

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
        List<Object> listCompras = new ArrayList<>();

        try {
            String sql = "SELECT * FROM compra\n" +
                    "INNER JOIN cliente on cliente.id_cliente = compra.id_cliente\n" +
                    "INNER JOIN producto on producto.id_producto = compra.id_producto\n" +
                    "INNER JOIN tienda on tienda.id_tienda = producto.id_tienda\n" +
                    "ORDER BY compra.id_compra ASC;";

            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql);
            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                Compra objCompra = new Compra();
                objCompra.setIdCompra(objResult.getInt("compra.id_compra"));
                objCompra.setFechaCompra(objResult.getString("compra.fecha_compra"));
                objCompra.setCantidad(objResult.getInt("compra.cantidad"));
                objCompra.setId_cliente(objResult.getInt("compra.id_cliente"));
                objCompra.setId_producto(objResult.getInt("compra.id_producto"));

                Cliente objCliente = new Cliente();
                objCliente.setIdCliente(objResult.getInt("cliente.id_cliente"));
                objCliente.setNombre(objResult.getString("cliente.nombre"));
                objCliente.setApellido(objResult.getString("cliente.apellido"));
                objCliente.setEmail(objResult.getString("cliente.email"));
                objCompra.setObjCliente(objCliente);

                Tienda objTienda = new Tienda();
                objTienda.setIdTienda(objResult.getInt("tienda.id_tienda"));
                objTienda.setNombre(objResult.getString("tienda.nombre"));
                objTienda.setUbucacion(objResult.getString("tienda.ubucacion"));

                Producto objProducto = new Producto();
                objProducto.setIdProducto(objResult.getInt("producto.id_producto"));
                objProducto.setNombre(objResult.getString("producto.nombre"));
                objProducto.setPrecio(objResult.getDouble("producto.precio"));
                objProducto.setId_tienda(objResult.getInt("producto.id_tienda"));
                objProducto.setStock(objResult.getInt("producto.stock"));
                objProducto.setObjTienda(objTienda);
                objCompra.setObjProducto(objProducto);

                listCompras.add(objCompra);


            }


        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error " + e.getMessage());
            System.out.println(e.getMessage());
        }

        Configdb.closeConnection();
        return listCompras;
    }

    public List<Object> findByProducto(String search) {
        Connection objConnection = Configdb.openConnection();
        List<Object> listCompras = new ArrayList<>();

        try {
            String sql = "SELECT * FROM compra\n" +
                    "INNER JOIN cliente on cliente.id_cliente = compra.id_cliente\n" +
                    "INNER JOIN producto on producto.id_producto = compra.id_producto\n" +
                    "INNER JOIN tienda on tienda.id_tienda = producto.id_tienda\n" +
                    "WHERE producto.nombre LIKE '%" + search +"%'\n" +
                    "ORDER BY compra.id_compra ASC;";

            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql);
            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                Compra objCompra = new Compra();
                objCompra.setIdCompra(objResult.getInt("compra.id_compra"));
                objCompra.setFechaCompra(objResult.getString("compra.fecha_compra"));
                objCompra.setCantidad(objResult.getInt("compra.cantidad"));
                objCompra.setId_cliente(objResult.getInt("compra.id_cliente"));
                objCompra.setId_producto(objResult.getInt("compra.id_producto"));

                Cliente objCliente = new Cliente();
                objCliente.setIdCliente(objResult.getInt("cliente.id_cliente"));
                objCliente.setNombre(objResult.getString("cliente.nombre"));
                objCliente.setApellido(objResult.getString("cliente.apellido"));
                objCliente.setEmail(objResult.getString("cliente.email"));
                objCompra.setObjCliente(objCliente);

                Tienda objTienda = new Tienda();
                objTienda.setIdTienda(objResult.getInt("tienda.id_tienda"));
                objTienda.setNombre(objResult.getString("tienda.nombre"));
                objTienda.setUbucacion(objResult.getString("tienda.ubucacion"));

                Producto objProducto = new Producto();
                objProducto.setIdProducto(objResult.getInt("producto.id_producto"));
                objProducto.setNombre(objResult.getString("producto.nombre"));
                objProducto.setPrecio(objResult.getDouble("producto.precio"));
                objProducto.setId_tienda(objResult.getInt("producto.id_tienda"));
                objProducto.setStock(objResult.getInt("producto.stock"));
                objProducto.setObjTienda(objTienda);
                objCompra.setObjProducto(objProducto);

                listCompras.add(objCompra);


            }


        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error " + e.getMessage());
            System.out.println(e.getMessage());
        }

        Configdb.closeConnection();
        return listCompras;
    }

    public boolean validateStock(Object object){
        boolean valid = true;
        Compra objCompra = (Compra) object;
        System.out.println(objCompra.getObjProducto().getStock());
        System.out.println(objCompra.getCantidad());

        if (objCompra.getCantidad() > objCompra.getObjProducto().getStock()){
            JOptionPane.showMessageDialog(null, "Cantidad no valida");
            valid = false;
        }



        return valid;
    }
}
